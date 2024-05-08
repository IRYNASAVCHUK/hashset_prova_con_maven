package com.example.project.logic.utils;

import com.example.project.logic.logger.ConfigLoader;
import com.google.gson.JsonElement;

public class Constants {
        public static final String ENTRY = (ConfigLoader.getConfigValue("eventEntry", JsonElement::getAsString) != null)
                        ? ConfigLoader.getConfigValue("eventEntry", JsonElement::getAsString)
                        : "eventEntry";
        public static final String RETURN = (ConfigLoader.getConfigValue("eventReturn",
                        JsonElement::getAsString) != null)
                        ? ConfigLoader.getConfigValue("eventReturn", JsonElement::getAsString)
                        : "eventReturn";
        public static final String CONFIG_FILE = "config.json";
        public static final String LOG_FILE = (ConfigLoader.getConfigValue("logFile", JsonElement::getAsString) != null)
                        ? ConfigLoader.getConfigValue("logFile", JsonElement::getAsString)
                        : "logFile.json";
        public static final int LEVEL = (ConfigLoader.getConfigValue("objectLevel", JsonElement::getAsInt) == null
                        || ConfigLoader.getConfigValue("objectLevel", JsonElement::getAsInt) <= 0)
                        ? 0
                        : ConfigLoader.getConfigValue("objectLevel", JsonElement::getAsInt);
        public static final boolean ALL_FIELDS = (ConfigLoader.getConfigValue("allFields",JsonElement::getAsBoolean) != null)
                        ? ConfigLoader.getConfigValue("allFields", JsonElement::getAsBoolean)
                        : false;
}
