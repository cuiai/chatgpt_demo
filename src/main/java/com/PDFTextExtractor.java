package com;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PDFTextExtractor {
    public List<String> getinfo(String filepath) {
        List<String> a = new ArrayList();
        try {
            String fpnumber;
            double notax = 0.0;
            double tax = 0.0;
            double trasum;
            int count = 0;
            PDDocument document = PDDocument.load(new File(filepath));
            CustomPDFTextStripper stripper = new CustomPDFTextStripper();
            stripper.setSortByPosition(true); // 按位置排序
            stripper.setStartPage(0);
            stripper.setEndPage(document.getNumberOfPages());
            List<String> extractedText = stripper.getTextFromPDF(document);
            // 打印提取的文本
            for (int i = 0; i < extractedText.size(); i++) {
                if (extractedText.get(i).contains("发票号码")) {
                    if (extractedText.get(i).length()>10){
                        String[] result = extractedText.get(i).split("：");
                        fpnumber = result[1];
                    }
                    else {
                        fpnumber = extractedText.get(i+1);
                    }
                    if (count == 0){
                        System.out.println("发票号码:" + fpnumber);
                        a.add(fpnumber);
                        count++;
                    }
                }
                if (extractedText.get(i).equals("价税合计（大写）")) {
                    System.out.println("不含税:" + extractedText.get(i - 2).substring(1));
                    notax = Double.parseDouble(extractedText.get(i - 2).substring(1));
                    a.add(Double.toString(notax));
                }
                if (extractedText.get(i).equals("价税合计（大写）")) {
                    System.out.println("税额:" + extractedText.get(i - 1).substring(1));
                    tax = Double.parseDouble(extractedText.get(i - 1).substring(1));
                    a.add(Double.toString(tax));
                }
            }
            trasum = notax + tax;
            System.out.println("价税合计:" + trasum);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }
}

class CustomPDFTextStripper extends PDFTextStripper {
    private List<String> textList;

    public CustomPDFTextStripper() throws IOException {
        super();
        textList = new ArrayList<>();
    }

    public List<String> getTextFromPDF(PDDocument document) throws IOException {
        textList.clear(); // 清空之前的内容
        writeText(document, new OutputStreamWriter(System.out)); // 这里的OutputStreamWriter只是占位符，不会实际输出到控制台
        return textList;
    }

    @Override
    protected void writeString(String string, List<TextPosition> textPositions) {
        StringBuilder sb = new StringBuilder();
        float lastX = -1;
        float lastY = -1;

        for (TextPosition text : textPositions) {
            float x = text.getXDirAdj();
            float y = text.getYDirAdj();

            // 检查是否是新的一行
            if (lastX != -1 && (Math.abs(y - lastY) > 5)) {
                textList.add(sb.toString());
                sb.setLength(0); // 清空StringBuilder
            }

            sb.append(text.getUnicode());
            lastX = x;
            lastY = y;
        }

        // 添加最后一行
        if (sb.length() > 0) {
            textList.add(sb.toString());
        }
    }
}
