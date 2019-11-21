package com.bkstest.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IexCloud {
    private HttpRequest httpRequest = new HttpRequest();

    public Portfolio getPortfolio(Asset asset) throws Exception {
        try {
            Map<String, BigDecimal> sectorsMoney = new HashMap<>();

            BigDecimal totalSum = new BigDecimal(0);

            for (CompanyStocks companyStocks : asset.getStocks()) {
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, String> params = new HashMap<>();
                params.put("token", Config.instance.getToken());
                params.put("filter", "sector");

                JsonNode sectorJson = objectMapper.readTree(sendHttpRequestToIexCloud(companyStocks.getSymbol().toLowerCase() + "/company", params));

                String sector = sectorJson.get("sector").asText();

                params.put("filter", "latestPrice");

                JsonNode latestPriceJson = objectMapper.readTree(sendHttpRequestToIexCloud(companyStocks.getSymbol().toLowerCase() + "/quote", params));

                BigDecimal latestPrice = new BigDecimal(latestPriceJson.get("latestPrice").asDouble());
                BigDecimal currentSum = latestPrice.multiply(new BigDecimal(companyStocks.getVolume()));

                totalSum = totalSum.add(currentSum);

                if (sectorsMoney.containsKey(sector)) {
                    sectorsMoney.put(sector, sectorsMoney.get(sector).add(currentSum));
                } else {
                    sectorsMoney.put(sector, currentSum);
                }
            }

            List<Allocation> allocations = new ArrayList<>();

            Portfolio portfolio = new Portfolio(totalSum, allocations);

            for (Map.Entry<String, BigDecimal> entry : sectorsMoney.entrySet()) {
                allocations.add(new Allocation(entry.getKey(), entry.getValue().setScale(2, BigDecimal.ROUND_DOWN), entry.getValue().divide(totalSum, BigDecimal.ROUND_DOWN).setScale(3, BigDecimal.ROUND_HALF_UP)));
            }

            portfolio.setValue(totalSum.setScale(2, BigDecimal.ROUND_DOWN));

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
