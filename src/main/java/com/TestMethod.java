package com;

import com.sh.ideal.utils.GMUtil;
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

        String s = GMUtil.SM2Encrypt("1234567890,hello", "04d0df09b9bddc8ecd8052dc033397b2b363af32e41076afe9d09d16da1e9a3f6e3e7fe6febcd6e4ab1c8586c831789270f8a4dea7a56f4d73de0a438e4de6991f");
        System.out.println(s);
        String s1 = GMUtil.SM2Decrypt("045AB6E32EF73085FA7A52934B0F94B9B4261867FA31599569B1D2F1534E4A192DDA8D0981D68DDC8B19FFE6F9205721F0FCB37EAF3F97F9A2AA6C3FB92FF260D0DC9336C700C3D6106150628FFCB34A9E94AE165856AE6D767851F7D565D711ACF8216784BE3A48D286CB126210E08DE6", "04d0df09b9bddc8ecd8052dc033397b2b363af32e41076afe9d09d16da1e9a3f6e3e7fe6febcd6e4ab1c8586c831789270f8a4dea7a56f4d73de0a438e4de6991f");
        System.out.println(s1);
    }
}
