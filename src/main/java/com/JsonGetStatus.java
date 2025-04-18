package com;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.descriptor.InputSourceUtil;

import java.util.HashMap;

/**
 * @author: cuikai
 * @date: 2025/4/18 10:28:15
 */
public class JsonGetStatus {
    public static void main(String[] args) {
        JSONArray objects = new JSONArray();
        JSONObject obj = new JSONObject();
        HashMap<String, String> params = new HashMap<>();
        params.put("ITEMCODE","XYJAGZSGS230800018");
        obj.put("contractCode", "");
        obj.put("contractStartCode", "");
        obj.put("ictItemCode", params.get("ITEMCODE"));

        // 创建 statusList 数组
        JSONArray statusList = new JSONArray();
        statusList.add("20");
        statusList.add("50");

        // 将数组添加到 JSONObject
        obj.put("statusList", statusList);
        objects.add(obj);

        String json = objects.toString();
        System.out.println(json);
//        StringBuffer sb = new StringBuffer();
//        String result = "{\n" +
//                "    \"resultObject\": {\n" +
//                "        \"list\": [\n" +
//                "            {\n" +
//                "                \"contractCode\": \"XYJAGZSGS230800018\",\n" +
//                "                \"ictItemCode\": \"XYJAGZSGS230800018\",\n" +
//                "                \"ictItemName\": \"全省中小微ICT项目\",\n" +
//                "                \"contractStartCode\": \"XYJAGZSGS230800018\",\n" +
//                "                \"createType\": \"4\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"resultCode\": \"0\",\n" +
//                "    \"resultType\": \"0\"\n" +
//                "}";
//        sb.append(result);
//        JSONObject statusReturn = JSONObject.parseObject(sb.toString());
//        JSONObject resultObject = statusReturn.getJSONObject("resultObject");
//        JSONArray list = resultObject.getJSONArray("list");
//        JSONObject firstItem = list.getJSONObject(0); // 获取 list 的第一个元素
//        String createType = firstItem.getString("createType");
//        System.out.println(createType);

    }
}
