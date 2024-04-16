package com.example.logger;

import com.example.utils.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static JsonNode configNode;

    static {
        try {
            configNode = objectMapper.readTree(new File(Constants.CONFIG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key) {
        return configNode.get(key).asText();
    }
}
