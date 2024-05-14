package com.example.project.logic.gson;

import com.example.project.logic.utils.Constants;
import com.google.gson.*;
import java.io.*;
import java.util.function.Function;

public class ConfigLoader {
    private static JsonObject configObject;

    static {
        try (FileReader reader = new FileReader(Constants.CONFIG_FILE)) {
            configObject = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getConfigValue(String key, Function<JsonElement, T> mapper) {
        var element = configObject.get(key);
        if (element != null && !element.isJsonNull()) {
            return mapper.apply(element);
        }
        return null;
    }

    public static <T> T getConfigConstants(String key, T defaultValue, Function<JsonElement, T> mapper) {
        T value = getConfigValue(key, mapper);
        return (value != null) ? value : defaultValue;
    }
}
