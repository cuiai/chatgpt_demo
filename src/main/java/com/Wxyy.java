package com;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author cuikai
 * @date 2023/9/25 18:14:57
 */
public class Wxyy {
    private static  String CHAT_URL = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant";

    static String inp = "{\"messages\": [\n" +
            "            {\n" +
            "                \"role\": \"%s\",\n" +
            "                \"content\": \"%s\"\n" +
            "            }\n" +
            "        ]}";

    public static String getAnswerBaiDu(String text) throws IOException {
        CHAT_URL = CHAT_URL+"?access_token=xxxx";
        URL u=new URL(CHAT_URL);
        HttpURLConnection conn=(HttpURLConnection) u.openConnection();
        conn.setConnectTimeout(10*1000);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", "application/json");
        OutputStream out= conn.getOutputStream();
        try{
            String json=String.format(inp,"user",text);
            System.err.println(json);
            out.write(json.getBytes("UTF-8"));
            out.close();
            out=null;
            InputStream in = conn.getInputStream();
            if(conn.getResponseCode()==200){
                //流转换为二进制数组，read()是转换方法
                byte[] data = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((len = in.read(data)) != -1){
                    bos.write(data,0,len);
                }
                bos.close();
                in.close();
                conn.disconnect();
                return new String(bos.toByteArray(), "UTF-8");
            }
            else in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(out!=null){
                out.close();
            }
            conn.disconnect();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        System.out.print("input>>");
        String answer = getAnswerBaiDu("头疼怎么办");
        System.err.println(answer);
        //使用ObjectMapper直接将String串转成对像
        ObjectMapper objectMapper = new ObjectMapper();
//        BaiduChatModel chatModel = objectMapper.readValue(answer, BaiduChatModel.class);
//        System.err.println(chatModel.toString());
//        System.err.println(chatModel.getResult());

    }

}
