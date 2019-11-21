package com.bkstest.stocks;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;

public enum Config {
    instance;

    private String token;

    Config() {
        try (FileReader fileReader = new FileReader("../config.json")) {
            JsonNode config = new ObjectMapper().readTree(fileReader);

            if (!config.has("iextoken") || config.get("iextoken").isNull() || config.get("iextoken").asText() == null || config.get("iextoken").asText().isEmpty()) {
                throw new Exception("В файле конфигурации не указан токен для отправки запросов на сервис IEX Cloud.");
            }

            token = config.get("iextoken").asText();

        } catch (Exception e) {
            System.out.println("Ошибка при инициализации конфига приложения");
            e.printStackTrace();
            System.out.println("Выход из приложения...");
            System.exit(1);
        }
    }

    public String getToken() {
        return token;
    }
}
