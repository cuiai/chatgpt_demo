package com;

import com.sh.ideal.utils.GMUtil;

import javax.sound.sampled.Line;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @author: cuikai
 * @date: 2024/8/30 08:47:23
 */
public class GmTest {
    String[] stringArray = {
            "客户档案","拜访记录","商机信息","项目团队","解决方案",
            "方案审核","专题研讨","中台把关","投标记录","中标通知",
            "谈判记录","标前决策","合作对象","业务解构","采购需求",
            "标后决策","实施方案","变更说明","上家开工","上家过程",
            "上家验收","投资立项","运维记录","上家合同","下家合同",
            "下家开工","下家过程","下家验收", "收入确认","支出确认"};
    int[] intArray = {1,3,5,10,12,15,16,17,21,26,28,23,24,25,29,30};

    public StringBuffer getinfo(ArrayList<String> filenamelist) {
        StringBuffer info = new StringBuffer();
        ArrayList<Integer> index = new ArrayList<Integer>();
        Collections.sort(filenamelist);
        for (String s : filenamelist) {
            if (!s.contains("-")){
                info.append(s+" 命名错误;\n");
            }else {
                String[] split = s.split("-");
                if (split.length==0){
                    info.append(s+" 命名错误;\n");
                }
                else {
                    boolean integer = isInteger(split[0]);
                    if (integer){
                        int i = Integer.parseInt(split[0]);
                        if (i<1||i>30){
                            info.append(s+" 命名错误;\n");
                        }else{
                            index.add(i);
                            if (split.length<2){
                                info.append(s+" 命名错误;\n");
                            }
                            else {
                                if (split[1].length()<4){
                                    info.append(s+" 命名错误;\n");
                                }
                                else {
                                    String substring = split[1].substring(0, 4);
                                    if (!substring.equals(stringArray[i-1])){
                                        info.append(s+" 命名错误;\n");
                                    }
                                    if (contains(intArray,i)){
                                        if ("txt".equals(getFileExtension(s))){
                                            info.append(s+" 不能为txt文件;\n");
                                        }
                                    }
                                }

                            }
                        }
                    }else{
                        info.append(s+" 命名错误;\n");
                    }
                }
            }
        }
        for (int i = 1; i <= 30; i++) {
            if (!index.contains(i)){
                info.append(i+"-"+stringArray[i-1]+"缺失；\n");
            }
        }
        return info;
    }


    public  boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    // 尝试解码 rawEntryName 的多种可能编码
    public  boolean contains(int[] array, int target) {
        // 遍历数组
        for (int i = 0; i < array.length; i++) {
            // 如果找到目标元素，则返回true
            if (array[i] == target) {
                return true;
            }
        }
        // 遍历完数组后，如果没有找到目标元素，则返回false
        return false;
    }
    public String getFileExtension(String fileName) {
        // 检查文件名是否为空或null
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        // 找到最后一个'.'的位置
        int lastIndexOfDot = fileName.lastIndexOf('.');

        // 如果'.'不存在于文件名中，则返回空字符串
        if (lastIndexOfDot == -1) {
            return "";
        }

        // 返回'.'之后的所有字符，即扩展名
        return fileName.substring(lastIndexOfDot + 1);
    }

    public static void main(String[] args) {
        GmTest getfpTest = new GmTest();
        ArrayList<String> strings = new ArrayList<>();
        strings.add(".jpg");
        strings.add("1.jpg");
        strings.add("2---拜访记录.jpg");
        StringBuffer getinfo = getfpTest.getinfo(strings);
        System.out.println(getinfo.toString());
    }
}
