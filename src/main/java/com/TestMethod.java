package com;

import org.springframework.util.StringUtils;
import java.util.regex.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestMethod {
    public void test2(Map param){
        param.put("123","456");
        param.put("345","567");
    }
    /**
     - @date: 2023/12/13 8:42
     - @description: 主函数
     * @param args
    */
    public static void main(String[] args) {
//        Map<String,String> param= new HashMap<>();
//        new TestMethod().test2(param);
//        System.out.println(param);
//        String a  =  "  ";
//        boolean empty = StringUtils.isEmpty(a);
//        System.out.println(empty);
//        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
//        HashMap<String, String> hashMap = new HashMap<>();
//        String put = hashMap.put("123", "456");
//        System.out.println(put);
//        boolean add = hashMaps.add(hashMap);
//        String put1 = hashMaps.get(0).put("123", "789");
//        System.out.println(put1);
//        LocalDate currentDate = LocalDate.now();
//        int year = currentDate.getYear();
//        int month = currentDate.getMonthValue();
//        int day = currentDate.getDayOfMonth();
//
//        LocalDate previousMonth = currentDate.minusMonths(1);
//        int prevYear = previousMonth.getYear();
//        int prevMonth = previousMonth.getMonthValue();
//        int prevDay = previousMonth.getDayOfMonth();
//
//        String daymonth = String.format("%d%02d", prevYear, prevMonth);
//        System.out.println("Previous month format: " + daymonth);

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS a");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
//        String formattedDate = dateFormat.format(new Date());
//        System.out.println(formattedDate);
        String resx = "[\\u4e00-\\u9fa5]+";
        Pattern compile = Pattern.compile(resx);
        String test = "你好123";
        Matcher matcher = compile.matcher(test);
        boolean matches = matcher.matches();
        System.out.println(matches);


    }
}
