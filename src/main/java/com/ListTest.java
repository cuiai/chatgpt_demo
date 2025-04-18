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
        List<Map<String,String>> list = new ArrayList<>();
        for (Map<String, String> map : list) {
            System.out.println(map);
        }
    }

}
