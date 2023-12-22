package com;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cuikai
 * @date 2023/9/26 08:43:04
 */
public class JsonTest {
    public static void main(String[] args) {
        java.util.Date day=new Date();

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(day));

//        通过Date类来获取当前时间，比较常用。需要使用Java.util.Date类，速度一般。

        SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf1.format(System.currentTimeMillis()));

//        通过System类中的currentTimeMillis方法来获取当前时间，无需导入类，速度最快。
//
//        3、通过Calendar类来获取当前时间

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);

        int date = calendar.get(Calendar.DATE);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        int minute = calendar.get(Calendar.MINUTE);

        int second = calendar.get(Calendar.SECOND);

        System.out.println(year + "年" + month + "月" + date + "日" + hour + "时" + minute + "分" + second + "秒");

//        可以对每个时间域单独修改 对时间进行加减操作等。
//
//        可灵活构建，显示格式，但速度较慢。
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Class.class.getResource("/").getPath());;


    }
}
