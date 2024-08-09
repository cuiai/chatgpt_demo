package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @author: cuikai
 * @date: 2023/12/26 14:33:50
 */
public class ZifuchuanTest {
    private String getFileName(String name){
        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fileName = "index_"+name+"_"+sdf.format(tomorrow)+"_1.txt";
        return fileName;
    }
    public static void main(String[] args) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("1","aaab");
//        String[] a = {"a","aaa","bbb"};
//        boolean aa = Arrays.asList(a).contains(hashMap.get("1"));
//        System.out.println(aa);
//        String inputDate = "Tue Dec 31 00:00:00 CST 2024";
//        String inputPattern = "EEE MMM dd HH:mm:ss zzz yyyy"; // 输入日期时间的模式
//        String outputPattern = "yyyy-MM-dd"; // 输出日期时间的模式
//
//        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
//        inputFormat.setTimeZone(TimeZone.getTimeZone("CST")); // 设置时区，如果需要的话
//
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
//
//        Date date = null;
//        try {
//            date = inputFormat.parse(inputDate); // 解析日期时间字符串
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if (date != null) {
//            String formattedDate = outputFormat.format(date); // 格式化日期为yyyy-MM-dd格式
//            System.out.println(formattedDate); // 输出格式化后的日期
//        }
//        String abc = "";
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("a","123");
//        abc = hashMap.get("b");
//        System.out.println(abc);
//        Date longDate = new Date();
////将长日期转换为短日期
//        LocalDate shortDate = longDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//// 瀚出短日期
//        System.out.println(shortDate);
        ZifuchuanTest zifuchuanTest = new ZifuchuanTest();
        String fileName = zifuchuanTest.getFileName("456");
        System.out.println(fileName);

    }
}
