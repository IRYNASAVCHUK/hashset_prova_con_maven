package com.example.logger;

import com.example.utils.Constants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;

public class ConfigLoader {
    private static JsonObject configObject;

    static {
        try (FileReader reader = new FileReader(Constants.CONFIG_FILE)) {
            configObject = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key) {
        return configObject.get(key).getAsString();
    }
}
