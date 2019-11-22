package com.bkstest.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;

public enum Config {
    instance;

    private String token;

    Config() {
        try {
            try (FileReader fileReader = new FileReader("../config.json")) {
                JsonNode config = new ObjectMapper().readTree(fileReader);

                if (!config.has("iextoken") || config.get("iextoken").isNull() || config.get("iextoken").asText() == null || config.get("iextoken").asText().isEmpty()) {
                    throw new Exception("В конфиге не указан токен для отправки запросов на сервис IEX Cloud.");
                }

                token = config.get("iextoken").asText();
                System.out.println("Токен получен из конфига.");

            } catch (Exception e) {
                System.out.println("Не удалось получить токен из конфига. " + e.toString());
            }

            if (token == null || token.isEmpty()) {
                if (System.getProperty("iextoken") == null || System.getProperty("iextoken").isEmpty()) {
                    throw new Exception("Не удалось получить токен для отправки запросов на сервис IEX Cloud.");
                } else {
                    token = System.getProperty("iextoken");
                    System.out.println("Токен получен из переданных свойств.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при инициализации приложения");
            e.printStackTrace();
            System.out.println("Выход из приложения...");
            System.exit(1);
        }
    }

    public String getToken() {
        return token;
    }
}
