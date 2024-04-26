package com.example.utils;

import com.example.logger.ConfigLoader;

public class Constants {
        public static final String ENTRY = (ConfigLoader.getConfigStringValue("eventEntry") != null)
                        ? ConfigLoader.getConfigStringValue("eventEntry")
                        : "eventEntry";
        public static final String RETURN = (ConfigLoader.getConfigStringValue("eventReturn") != null)
                        ? ConfigLoader.getConfigStringValue("eventReturn")
                        : "eventReturn";
        public static final String CONFIG_FILE = "config.json";
        public static final String LOG_FILE = (ConfigLoader.getConfigStringValue("logFile") != null)
                        ? ConfigLoader.getConfigStringValue("logFile")
                        : "logFile.json";
        public static final int LEVEL = (ConfigLoader.getConfigStringValue("level") == null
                        || ConfigLoader.getConfigIntValue("level") <= 0)
                                ? 0
                                : ConfigLoader.getConfigIntValue("level");
}
