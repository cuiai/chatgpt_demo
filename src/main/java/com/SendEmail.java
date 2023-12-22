package com;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author cuikai
 * @date 2023/10/28 14:02:35
 */
public class SendEmail {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("123","1232");
        System.out.println(hashMap.get("123"));
        System.out.println(hashMap.get("456"));
    }
}
