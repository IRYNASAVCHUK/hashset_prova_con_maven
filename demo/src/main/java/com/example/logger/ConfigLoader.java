package com.example.logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static JsonNode configNode;

    static {
        try {
            configNode = objectMapper.readTree(new File("demo/src/main/java/com/example/logger/config.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key) {
        return configNode.get(key).asText();
    }
}
