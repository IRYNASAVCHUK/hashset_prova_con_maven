package com.example.project.logic.gson;

import com.example.project.logic.log_record.record.Levels;
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

    public static Levels parseJsonObjectLevels(JsonObject objectLevel) {
        int target = getIntFromJsonObject(objectLevel, "target", 0);
        int args = getIntFromJsonObject(objectLevel, "args", 0);
        int result = getIntFromJsonObject(objectLevel, "result", 0);
        return new Levels(target, args, result);
    }

    private static int getIntFromJsonObject(JsonObject object, String key, int defaultValue) {
        if (object.has(key)) {
            int value = object.get(key).getAsInt();
            return (value > 0) ? value : defaultValue;
        }
        return defaultValue;
    }
}
