package com;

import java.util.*;

/**
 * @author cuikai
 * @date 2023/9/22 11:00:37
 */
public class HashTest {
    public static void main(String[] args) {
        // 创建两个 HashMap
//        Map<String, Integer> map1 = new HashMap<>();
//        map1.put("A", 1);
//        map1.put("B", 2);
//
//        Map<String, Integer> map2 = new HashMap<>();
//        map2.put("C", 3);
//        map2.put("D", 4);
//
//        // 组合两个 HashMap
//        Map<String, Integer> combinedMap = new HashMap<>();
//        combinedMap.putAll(map1);
//        combinedMap.putAll(map2);
//
//        // 打印组合后的 HashMap
//        System.out.println(combinedMap);
//        System.out.println(combinedMap.get("A"));
//
//        HashMap<String, String> stringStringHashMap = new HashMap<>();
//        stringStringHashMap.put("1","123");
//        stringStringHashMap.put("2","234");
//        ArrayList<HashMap<String, String>> hashMaps = new ArrayList<>();
//        hashMaps.add(stringStringHashMap);
//        System.out.println(hashMaps.get(0).get("1"));
//        String a = "A521000";
//        String substring = a.substring(0,5);
//        System.out.println(substring);

//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("number1");
//        strings.add("number2");
//        String reportArray[] = {"string1","string2","string3"};
//        System.out.println(reportArray[0]);
//        int length = reportArray.length;
//        int index = Arrays.binarySearch(reportArray, "string4");
//        System.out.println(index);
//        if (index<=length-1){
//            System.out.println("在里面");
//        }
//        else {
//            System.out.println("不在里面");
//        }


//
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("field1","value1");
//        hashMap.put("field2","value2");
//        if (hashMap.size()>0){
//            StringBuilder sql = new StringBuilder("INSERT INTO tablename (");
//
//// Add the fields
//            for(String key : hashMap.keySet()) {
//                sql.append(key).append(", ");
//            }
//
//// Remove the trailing comma and space
//            if(sql.length() > 0) {
//                sql.setLength(sql.length() - 2);
//            }
//
//            sql.append(") VALUES (");
//
//// Add the values
//            for(String value : hashMap.values()) {
//                sql.append("'").append(value).append("', ");
//            }
//
//// Remove the trailing comma and space
//            if(sql.length() > 0) {
//                sql.setLength(sql.length() - 2);
//            }
//
//            sql.append(");");
//
//            System.out.println(sql.toString());
//        }
//
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("LRZX_CODE","A123,A456");
//        String lrzx_code = ((String) hashMap.get("LRZX_CODE"));
//        lrzx_code = "'" + lrzx_code + "'";
//        lrzx_code = lrzx_code.replaceAll(",", "','");
//        System.out.println(lrzx_code);
//        九九乘法表
//        for (int i = 1; i < 10; i++) {
//            for (int j = 1; j <=i; j++) {
//                System.out.print(j+"*"+i+"="+(i*j)+"   ");
//            }
//            System.out.println("");
//
//        }
        System.out.println("请输入一个0-100内的数字");
        Random random = new Random();
        int i1 = random.nextInt(100);
        System.out.println(i1);
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        for (int j = 0; j < 10; j++) {
            int i = scanner.nextInt();
            if (i<i1){
                System.out.println("太小了");
            }
            else if (i>i1){
                System.out.println("太大了");
            }
            else
            {
                System.out.println("猜对了");
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.println("恭喜你最后答对了，答案是"+i1);
        }else
            System.out.println("很遗憾十次机会没有答对，答案是"+i1);
    }

}


