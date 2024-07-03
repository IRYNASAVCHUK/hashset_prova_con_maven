package core.utils;

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
        return (element != null && !element.isJsonNull()) ? mapper.apply(element) : null;
    }

    public static <T> T getConfigConstants(String key, T defaultValue, Function<JsonElement, T> mapper) {
        T value = getConfigValue(key, mapper);
        return (value != null) ? value : defaultValue;
    }

    public static Records.Levels parseJsonObjectLevel (JsonObject objectLevel) {
        var target = getIntFromJsonObject(objectLevel, "target", 0);
        var args = getIntFromJsonObject(objectLevel, "args", 0);
        var result = getIntFromJsonObject(objectLevel, "result", 0);
        return new Records.Levels(target, args, result);
    }

    private static int getIntFromJsonObject(JsonObject object, String key, int defaultValue) {
        if (object.has(key)) {
            var value = object.get(key).getAsInt();
            return (value > 0) ? value : defaultValue;
        }
        return defaultValue;
    }
}
