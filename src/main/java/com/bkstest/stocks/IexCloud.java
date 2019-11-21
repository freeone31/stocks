package com.bkstest.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IexCloud {
    private HttpRequest httpRequest = new HttpRequest();

    public Portfolio getPortfolio(Asset asset) throws Exception {
        try {
            Map<String, Double> sectorsMoney = new HashMap<>();

            double totalSum = 0;

            for (CompanyStocks companyStocks : asset.getStocks()) {
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, String> params = new HashMap<>();
                params.put("token", Config.instance.getToken());
                params.put("filter", "sector");

                JsonNode sectorJson = objectMapper.readTree(sendHttpRequestToIexCloud(companyStocks.getSymbol().toLowerCase() + "/company", params));

                String sector = sectorJson.get("sector").asText();

                params.put("filter", "latestPrice");

                JsonNode latestPriceJson = objectMapper.readTree(sendHttpRequestToIexCloud(companyStocks.getSymbol().toLowerCase() + "/quote", params));

                double latestPrice = latestPriceJson.get("latestPrice").asDouble();
                double currentSum = latestPrice * companyStocks.getVolume();

                totalSum += currentSum;

                if (sectorsMoney.containsKey(sector)) {
                    sectorsMoney.put(sector, sectorsMoney.get(sector) + currentSum);
                } else {
                    sectorsMoney.put(sector, currentSum);
                }
            }

            List<Allocation> allocations = new ArrayList<>();

            Portfolio portfolio = new Portfolio(totalSum, allocations);

            for (Map.Entry<String, Double> entry : sectorsMoney.entrySet()) {
                allocations.add(new Allocation(entry.getKey(), entry.getValue(), entry.getValue() / totalSum));
            }

            return portfolio;

        } catch (Exception e) {
            System.out.println("Ошибка при получении портфеля с IEX Cloud API");
            System.out.println(asset);
            e.printStackTrace();
            throw e;
        }
    }

    private String sendHttpRequestToIexCloud(String path, Map<String, String> paramsMap) throws Exception {
        return httpRequest.send("GET", "https://cloud.iexapis.com/stable/stock/", path, null, paramsMap);
    }
}
