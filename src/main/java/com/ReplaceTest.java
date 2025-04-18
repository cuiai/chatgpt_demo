package com;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author: cuikai
 * @date: 2024/11/27 18:03:51
 */
public class ReplaceTest {
    public static void main(String[] args) {
//        String oldString = "12<ID>3456</ID>";
//        int idStart = oldString.indexOf("<ID>");
//        int idEnd = oldString.indexOf("</ID>");
//        String substring = oldString.substring(idStart+4, idEnd);
//        System.out.println(substring);
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("IS_GL","");
//        if (!"是".equals(hashMap.get("IS_GL"))){
//            double is_gl = Double.parseDouble(hashMap.get("IS_GL"));
//            System.out.println(is_gl);
//
//        }
//        HashSet<String> custSet = new HashSet<String>();
//        custSet.add("123");
//        custSet.add("128");
//        custSet.add("12");
//        for (String s : custSet) {
//           if (s.contains("125")){
//               System.out.println(s);
//           }
//        }
//        HashMap<String, String> hashMap = new HashMap<>();
//        String put = hashMap.put("123", "44565");
//        String orDefault = hashMap.get("41");
//        System.out.println(orDefault);


//        DecimalFormat decimalFormat = new DecimalFormat("#.00");
//        double format = Double.parseDouble(decimalFormat.format(12122.155515));
//        System.out.println(format);


//        double a = 111.22;
//        double b = 24545.44;
//
//        double fpze = 555.15;
//
//
//        double c = fpze*(a/(a+b));
//        double d = fpze*(b/(a+b));
//
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(c+d);


//        System.out.println("台江县智慧城市智能交通（红绿灯）建设1月合作分成".substring(0,18));string
//          String a = "123";
//          String[] split = a.split("2");
//          System.out.println(split[5]);
//        HashMap<String, String> hashMap = new HashMap<>();
//        HashMap<String, String> hashMapnew = null;
//        System.out.println(StringUtils.isEmpty(hashMap.get("123")));
//        StringUtils.isEmpty(hashMapnew.get("123"));
//        HashMap<String, String> hashMap = new HashMap<>();
//        System.out.println(hashMap.isEmpty());
//        double a = 3.15;
//        double b = 3.14;
//        BigDecimal bigDecimal = new BigDecimal(String.valueOf(a));
//        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(b));
//        if (bigDecimal.subtract(bigDecimal1).doubleValue()<1){
//            System.out.println(bigDecimal.subtract(bigDecimal1).doubleValue());
//        }
//        String srjh_mny ="";
//        srjh_mny = "19,000.1254";
//        srjh_mny = srjh_mny.replace(",", "");//去掉千分位符号
//        double srjh_mny_double = Double.parseDouble(srjh_mny);
//        double srjh_mny_double_f = srjh_mny_double*100;//转为以分为单位
//        srjh_mny = String.format("%.0f",srjh_mny_double_f);
//        System.out.println(srjh_mny);
//
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("1","");
//        hashMap.put("2","2");
//        HashMap<String, String> hashMap1 = new HashMap<>();
//        hashMap1.put("1","1");
//        hashMap1.put("2","2");
//        List<HashMap<String, String>> hashMaps = new ArrayList<>();
//        hashMaps.add(hashMap);
//        hashMaps.add(hashMap1);
//        StringBuffer str = new StringBuffer();
//        int i =0;
//        for (HashMap<String, String> map : hashMaps) {
//            i++;
//            if (!StringUtils.isEmpty(map.get("1"))){
//                if (i==1){
//                    str.append(map.get("1"));
//                }
//                else {
//                    if (str.length()==0){
//                        str.append(map.get("1"));
//                    }else {
//                        str.append(","+map.get("1"));
//                    }
//
//                }
//            }
//        }
//        System.out.println(str);
//
//        String date = "2024-01-02 00:00";
//        System.out.println(date.substring(0,10));
        HashMap<String, String> hashMap = new HashMap<>();
        HashMap<String, String> hashMap1 = null;
        System.out.println(hashMap.size());
        System.out.println(hashMap1.size());
    }
}
