package com.example.logger;

import java.util.logging.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyFormatter extends Formatter {
    private Gson gson = new GsonBuilder()
    //.serializeNulls()
    .setPrettyPrinting().create(); // new Gson();

    @Override
    public String format(LogRecord record) {
        LogInfo logInfo = new LogInfo(record);
        return gson.toJson(logInfo);
    }
}
