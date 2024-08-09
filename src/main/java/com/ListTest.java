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
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1","1");
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("2","2");
        ArrayList<Map<String, String>> maps = new ArrayList<>();
        maps.add(hashMap);
        maps.add(hashMap1);
        for (Map<String, String> map : maps) {
            map.put("3","3");
        }
        System.out.println(maps);

    }

}
