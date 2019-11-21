package com.bkstest.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioCalculatorFacade {

    public Portfolio calculate(Asset asset) {
        try {

            String token = "pk_3ec338f68d904a849a37feccb7b224f4";

//            Map<String, String> sectors = new HashMap<>();
//            Map<String, Double> assetValues = new HashMap<>();

            Map<String, Double> sectorsMoney = new HashMap<>();

            double sum = 0;

            for (Stock stock : asset.getStocks()) {

                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                params.put("filter", "sector");

                String sector = request(stock.getSymbol().toLowerCase() + "/company", params);
                System.out.println(sector);

                ObjectMapper m = new ObjectMapper();
                JsonNode s = m.readTree(sector);
                System.out.println(s);

                String news = s.get("sector").asText();
                System.out.println(news);

//                sectors.put(stock.getSymbol(), sector);

                params.put("filter", "latestPrice");

                String latestPrice = request(stock.getSymbol().toLowerCase() + "/quote", params);
                System.out.println(latestPrice);

                JsonNode p = m.readTree(latestPrice);
                System.out.println(p);

                double newp = p.get("latestPrice").asDouble();
                System.out.println(newp);

//                assetValues.put(stock.getSymbol(), Double.valueOf(latestPrice) * stock.getVolume());

                double cursum = newp * stock.getVolume();

                sum += cursum;

                if (sectorsMoney.containsKey(sector)) {
                    sectorsMoney.put(sector, sectorsMoney.get(sector) + cursum);
                } else {
                    sectorsMoney.put(sector, cursum);
                }
            }

            List<Allocation> allocations = new ArrayList<>();
            Portfolio portfolio = new Portfolio(sum);
            portfolio.setAllocations(allocations);

            for (Map.Entry<String, Double> entry : sectorsMoney.entrySet()) {
                allocations.add(new Allocation(entry.getKey(), entry.getValue(), entry.getValue() / sum));
            }
            System.out.println(portfolio);
            return portfolio;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Portfolio();
    }

//    private String request(String method, String path/*, Map<String, String> headers*/) throws Exception {
//        return request(method, path, /*headers,*/ null);
//    }

    private synchronized String request(/*String method,*/ String path, /*Map<String, String> headers,*/ Map<String, String> paramsMap) throws Exception {
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

            String url = "https://cloud.iexapis.com/stable/stock/";
            String method = "GET";

            if (method.equals("GET") && !params.isEmpty()) {
                con = (HttpsURLConnection) new URL(url + path + "?" + params).openConnection();
            } else {
                con = (HttpsURLConnection) new URL(url + path).openConnection();
            }


            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod(method);
            con.setDoInput(true);

//            for (Map.Entry<String, String> header : headers.entrySet()) {
//                con.setRequestProperty(header.getKey(), header.getValue());
//            }

//            if (method.equals("POST") && !params.isEmpty()) {
//                con.setDoOutput(true);
//
//                try (OutputStream os = con.getOutputStream(); OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8); BufferedWriter writer = new BufferedWriter(osw)) {
//                    writer.write(params);
//                    writer.flush();
//                }
//            } else {
                con.setDoOutput(false);
//            }

            int responseCode = con.getResponseCode();

            switch (responseCode) {
                case HttpsURLConnection.HTTP_OK:
                    try (InputStream is = con.getInputStream(); InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(isr)) {
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }

//                        JsonObject result = new JsonParser().parse(sb.toString()).getAsJsonObject();
                        String result = sb.toString();
                        if (result != null /*&& !result.isJsonNull()*/) {
                            return result;
                        } else {
                            throw new Exception("Ошибка при обращении к сервису ВТБ: неопределенный ответ сервиса");
                        }
                    }
                default:
                    throw new Exception("Ошибка при обращении к сервису ВТБ: код " + responseCode);
            }
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
