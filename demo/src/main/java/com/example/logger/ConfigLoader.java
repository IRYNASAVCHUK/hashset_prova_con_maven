package com.example.logger;

import com.example.utils.Constants;

import com.google.gson.*;
import java.io.*;


public class ConfigLoader {
    private static JsonObject configObject;

    static {
        try (FileReader reader = new FileReader(Constants.CONFIG_FILE)) {
            configObject = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigStringValue(String key) {
        JsonElement element = configObject.get(key);
        if (element != null && !element.isJsonNull()) {
            return element.getAsString();
        }
        return null;
    }
    
    public static Integer getConfigIntValue(String key) {
        JsonElement element = configObject.get(key);
        if (element != null && !element.isJsonNull()) {
            return element.getAsInt();
        }
        return null;
    }
}
