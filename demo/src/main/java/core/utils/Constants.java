package core.utils;

import java.util.function.Function;
import com.google.gson.JsonElement;



public class Constants {
        public static final String ENTRY = ConfigLoader.getConfigConstants("eventEntry", "enter",
                        JsonElement::getAsString);
        public static final String RETURN = ConfigLoader.getConfigConstants("eventReturn", "return",
                        JsonElement::getAsString);
        public static final String CONFIG_FILE = "config.json";
        public static final String LOG_FILE = ConfigLoader.getConfigConstants("logFileName", "logFile",
                        JsonElement::getAsString);
        public static final boolean ALL_FIELDS = ConfigLoader.getConfigConstants("allFields", false,
                        JsonElement::getAsBoolean);
        public static final boolean FORMAT_JSON = ConfigLoader.getConfigConstants("formatJSON", false,
                        JsonElement::getAsBoolean);
        public static final boolean ONLINE = ConfigLoader.getConfigConstants("onlineMode", false,
                        JsonElement::getAsBoolean);
        public static final String URL = ConfigLoader.getConfigConstants("url", "localhost",
                        JsonElement::getAsString);
        public static final int PORT = ConfigLoader.getConfigConstants("port", 8080,
                        JsonElement::getAsInt);
        public static final Records.Levels LEVELS;

        static {
                JsonElement objectLevelElement = ConfigLoader.getConfigValue("objectLevel", Function.identity());
                if (objectLevelElement != null && objectLevelElement.isJsonObject())
                        LEVELS = ConfigLoader.parseJsonObjectLevel(objectLevelElement.getAsJsonObject());
                else if (objectLevelElement != null && objectLevelElement.isJsonPrimitive()) {
                        var value = (objectLevelElement.getAsInt()) > 0 ? objectLevelElement.getAsInt() : 0;
                        LEVELS = new Records.Levels(value, value, value);
                } else
                        LEVELS = new Records.Levels(0, 0, 0);
        }

}
