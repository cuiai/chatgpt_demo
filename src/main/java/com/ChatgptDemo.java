package com;
//import com.openai.api.*;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author cuikai
 * @date 2023/9/12 17:00:43
 */
public class ChatgptDemo {
        private static final String API_KEY = "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"; // 请替换为你的 API 密钥

        public static void main(String[] args) {
            try {
                URL url = new URL("https://api.openai.com/v1/engines/davinci/completions");
//                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)); // 替换 'your_proxy_host' 和 'your_proxy_port'
                // 使用代理打开连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 设置请求方式为 POST
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // 构建 JSON 输入数据
                String inputPayload = "{"
                        + "\"prompt\":\"Translate the following English text to French: 'Hello, how are you?'\","
                        + "\"max_tokens\":150"
                        + "}";

                // 发送请求
                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = inputPayload.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                // 获取响应
                int responseCode = conn.getResponseCode();
                System.out.println("Response Code: " + responseCode);

                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
                System.out.println("123");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



}
