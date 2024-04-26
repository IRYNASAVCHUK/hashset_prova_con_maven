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
        return configObject.get(key).getAsString();
    }
    public static int getConfigIntValue(String key) {
        return configObject.get(key).getAsInt();
    }
}
