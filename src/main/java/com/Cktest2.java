package com;

import java.nio.charset.Charset;

/**
 * @author: cuikai
 * @date: 2024/9/2 14:56:24
 */
public class Cktest2 {

        public static boolean isGarbled(String str, Charset charset) {
            return !new String(str.getBytes(charset), charset).equals(str);
        }

        public static void main(String[] args) {
            String str = "10pdf";
            Charset charset = Charset.forName("GBK");

            if(isGarbled(str, charset)) {
                System.out.println("字符串为乱码");
            } else {
                System.out.println("字符串不是乱码");
            }
        }

}
