package com;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfExtractor {

    public static void main(String[] args) {
        // 指定PDF文件的路径
        String filePath = "./1.pdf";

        try (PDDocument document = PDDocument.load(new File(filePath))) {
            if (!document.isEncrypted()) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                String text = pdfStripper.getText(document);
                // 输出文本内容到控制台或进行其他处理
                System.out.println("Text from PDF: \n" + text);
            } else {
                System.err.println("Error: PDF is encrypted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}