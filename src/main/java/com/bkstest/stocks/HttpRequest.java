package com.bkstest.stocks;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequest {
    public String send(String method, String url, String path, Map<String, String> headers, Map<String, String> paramsMap) throws Exception {
        HttpsURLConnection con = null;

        try {
            String params = "";

            if (paramsMap != null && paramsMap.size() > 0) {
                StringBuilder sb = new StringBuilder();

                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    if (sb.toString().isEmpty()) {
                        sb.append(URLEncoder.encode(param.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(param.getValue(), "UTF-8"));
                    } else {
                        sb.append("&").append(URLEncoder.encode(param.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(param.getValue(), "UTF-8"));
                    }
                }

                params = sb.toString();
            }

            if (method.equals("GET") && !params.isEmpty()) {
                con = (HttpsURLConnection) new URL(url + path + "?" + params).openConnection();
            } else {
                con = (HttpsURLConnection) new URL(url + path).openConnection();
            }

            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod(method);
            con.setDoInput(true);

            if (headers != null) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }
            }

            if (method.equals("POST") && !params.isEmpty()) {
                con.setDoOutput(true);

                try (OutputStream os = con.getOutputStream(); OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8); BufferedWriter writer = new BufferedWriter(osw)) {
                    writer.write(params);
                    writer.flush();
                }
            } else {
                con.setDoOutput(false);
            }

            int responseCode = con.getResponseCode();

            switch (responseCode) {
                case HttpsURLConnection.HTTP_OK:
                    try (InputStream is = con.getInputStream(); InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(isr)) {
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }

                        return sb.toString();
                    }
                default:
                    throw new Exception("Ошибка при выполнении HTTP запроса: код " + responseCode);
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
