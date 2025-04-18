package com;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/**
 * @author: cuikai
 * @date: 2025/4/11 15:46:15
 */
public class HttpPostExample {


    public static void getLLM(HttpURLConnection connection,String prompt) throws IOException {
        String requestBody = "{" +
                "\"inputs\": {}," +
                "\"query\": \""+prompt+"\"," +
                "\"response_mode\": \"blocking\"," +
                "\"conversation_id\": \"\"," +
                "\"user\": \"abc-123\"," +
                "\"files\": [{" +
                "\"type\": \"image\"," +
                "\"transfer_method\": \"remote_url\"," +
                "\"url\": \"https://cloud.dify.ai/logo/logo-site.png\"" +
                "}]" +
                "}";
        // 发送请求体
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 获取响应码
//        int responseCode = connection.getResponseCode();
//        System.out.println("Response Code: " + responseCode);

        // 读取响应数据
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            ObjectMapper mapper = new ObjectMapper();
            String decodedText = mapper.readTree(response.toString()).get("answer").asText();
            System.out.println("Response: ");
            System.out.println("开始思考："+decodedText.replace("</think>","思考结束"));

        }
        connection.disconnect();

    }

    public static void main(String[] args) throws IOException {
        String apiUrl = "http://172.16.128.21:8888/v1/chat-messages";
        String apiKey = "app-5i6d8JsGB557I2KaNRvIyRV4"; // 替换为你的 API Key
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        for (int i = 0; i < count; i++) {
            System.out.println("**************");
            System.out.println("剩余提问次数："+(count-i));
            System.out.println("**************");
            // 创建 HTTP 连接
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Accept", "application/json");

            // 允许输出数据
            connection.setDoOutput(true);
            System.out.print("请输入内容: ");
            String input = scanner.nextLine(); // 读取一行输入
            getLLM(connection,input);
        }
        // 关闭连接
        System.out.println("**************");
        System.out.println("次数已用完！！！！");
        System.out.println("**************");
        scanner.close();
    }

}
