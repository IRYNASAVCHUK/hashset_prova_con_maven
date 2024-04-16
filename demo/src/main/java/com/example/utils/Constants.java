package com.example.utils;

import com.example.logger.ConfigLoader;

public class Constants {
    public static final String ENTRY = ConfigLoader.getConfigValue("eventEntry");
    public static final String RETURN = ConfigLoader.getConfigValue("eventReturn");
    public static final String CONFIG_FILE = "config.json";
    public static final String LOG_FILE = (ConfigLoader.getConfigValue("logFile")!=null)? ConfigLoader.getConfigValue("logFile"):"logFile.json";

}