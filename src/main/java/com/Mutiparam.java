package com;

/**
 * @author cuikai
 * @date 2023/11/7 11:28:44
 */
public class Mutiparam {
    public void test1(){
        
    }

    //    检测是否为数字
    public static double toNumeric(String str) {
        if ("".equals(str) || str == null)
            return 0;
        double d;
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return 0;
        }
        return d;
    }
}

