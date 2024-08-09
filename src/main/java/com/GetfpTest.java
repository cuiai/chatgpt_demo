package com;

import java.util.List;

/**
 * @author: cuikai
 * @date: 2024/6/17 09:49:30
 */
public class GetfpTest {
    public static void main(String[] args) {
        PDFTextExtractor pdfTextExtractor = new PDFTextExtractor();
        List<String> getinfo = pdfTextExtractor.getinfo("./3.pdf");
        System.out.println(getinfo);
    }
}
