package com.example.logger;

import com.example.record.loginfo.LogInfoRecordInterface;

import java.util.logging.*;
import com.google.gson.*;

public class MyFormatter extends Formatter {
    private final Gson gson;

    public MyFormatter() {
        gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String format(LogRecord record) {
        LogInfoRecordInterface logInfo = LogInfo.create(record);
        return gson.toJson(logInfo);
    }

}