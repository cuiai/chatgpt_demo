package com;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FpOCR {

    //这块是为了避免log4j打印一些没必要的信息
    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.INFO);
        });
    }


    public static void main(String[] args) {
        FpOCR fapiao1 = new FpOCR();
        //填写你自己的路径即可
        fapiao1.getInvoiceInfo("F:\\chatgpt_demo\\2.pdf");
    }

    /**
     * 获取电子发票pdf文件中的发票信息
     *
     * @param filePath 电子发票路径
     * @return 发票信息
     */
    public Object getInvoiceInfo(String filePath) {
        try {
            List<BufferedImage> imageList = extractImage(new File(filePath));
            if (imageList.isEmpty()) {
                System.out.println("pdf中未解析出图片，返回空");
                return null;
            }

            MultiFormatReader formatReader = new MultiFormatReader();
            //正常解析出来有3张图片，第一张是二维码，其他两张图片是发票上盖的章
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(imageList.get(0))));
            Map hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            Result result = formatReader.decode(binaryBitmap);
            if (result == null || StringUtils.isEmpty(result.getText())) {
                System.out.println("pdf中的第一张图片没有解析了字符串信息，直接返回空");
                return null;
            }

            System.out.println(String.format("从电子发票中识别出的信息为：%s", result.getText()));
            // 读取到的信息为 ： 01，发票类型，发票代码，发票号码，发票金额，开票日期，校验码，随机产生的摘要信息
            String[] infos = result.getText().split(",");
            if (infos.length != 8) {
                System.out.println("pdf中的第一张图片解析出的字符串数组长度不为8，返回空。");
                return null;
            }

            //这里自己去定义对象，属性自己看着定义，仅供参考
            /*Invoice invoice = new Invoice();
            invoice.setInvoiceType(infos[1]); //发票类型
            invoice.setInvoiceCode(infos[2]); //发票代码
            invoice.setInvoiceNo(infos[3]); // 发票号码
            invoice.setAmount(new BigDecimal(infos[4])); // 发票金额
            invoice.setInvoiceDate(DateUtils.parseDate(infos[5], "yyyyMMdd")); //开票日期
            invoice.setCheckCode(infos[6]); // 校验码*/

            //return invoice;
            return null;
        } catch (Exception e) {
            System.out.println("解析pdf中的二维码出现异常");
            return null;
        }
    }


    /**
     * 提取电子发票里面的图片
     *
     * @param pdfFile 电子发票文件对象
     * @return pdf中解析出的图片列表
     * @throws Exception
     */
    private List<BufferedImage> extractImage(File pdfFile) throws Exception {
        List<BufferedImage> imageList = new ArrayList<BufferedImage>();

        PDDocument document = PDDocument.load(pdfFile);
        PDPage page = document.getPage(1); //电子发票只有一页
        PDResources resources = page.getResources();

        for (COSName name : resources.getXObjectNames()) {
            if (resources.isImageXObject(name)) {
                PDImageXObject obj = (PDImageXObject) resources.getXObject(name);
                imageList.add(obj.getImage());
            }
        }
        document.close();
        return imageList;
    }
}