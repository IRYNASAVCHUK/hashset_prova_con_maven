package com.example.project.logic.utils;

import com.example.project.logic.gson.ConfigLoader;
import com.google.gson.JsonElement;

public class Constants {
        public static final String ENTRY = ConfigLoader.getConfigConstants("eventEntry", "eventEntry",
                        JsonElement::getAsString);
        public static final String RETURN = ConfigLoader.getConfigConstants("eventReturn", "eventReturn",
                        JsonElement::getAsString);
        public static final String CONFIG_FILE = "config.json";
        public static final String LOG_FILE = ConfigLoader.getConfigConstants("logFile", "logFile",
                        JsonElement::getAsString);
        public static final boolean ALL_FIELDS = ConfigLoader.getConfigConstants("allFields", false,
                        JsonElement::getAsBoolean);
        public static final boolean FORMAT_JSON = ConfigLoader.getConfigConstants("formatJSON", false,
                        JsonElement::getAsBoolean);
        public static final int LEVEL = 2;
        // public static final int LEVEL = (ConfigLoader.getConfigValue("objectLevel",
        // JsonElement::getAsInt) == null
        // || ConfigLoader.getConfigValue("objectLevel", JsonElement::getAsInt) <= 0)
        // ? 0
        // : ConfigLoader.getConfigValue("objectLevel", JsonElement::getAsInt);

        public record Level(int target, int args, int result) {
        }

}
