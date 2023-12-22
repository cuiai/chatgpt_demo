package com;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cuikai
 * @date 2023/11/17 10:01:02
 */
public class ListTest {
    public static void main(String[] args) {
        List<Map<String, String>> a = new ArrayList<>();
        HashMap<String, String> hashMap1 = new HashMap<>();
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap1.put("ID","1");
        hashMap2.put("ID","3");
        a.add(hashMap1);
        a.add(hashMap2);
        List<Map<String, String>> list1 = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("OFFCYCLEID","1");
        HashMap<String, String> hashMap11 = new HashMap<>();
        hashMap11.put("OFFCYCLEID","3");
        HashMap<String, String> hashMap111 = new HashMap<>();
        hashMap111.put("OFFCYCLEID","5");
        list1.add(hashMap);
        list1.add(hashMap11);
        list1.add(hashMap111);

        List<Map<String, String>> list_new = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        for (Map<String, String> map : a) {
            list.add(map.get("ID"));
        }
        for (Map<String, String> map : list1) {
            if (!list.contains(map.get("OFFCYCLEID"))){
                list_new.add(map);
            }
        }
        System.out.println(list_new);

    }

}
