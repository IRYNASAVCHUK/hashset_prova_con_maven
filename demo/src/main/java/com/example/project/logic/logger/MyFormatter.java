package com.example.project.logic.logger;

import com.example.project.logic.gson.ObjectAdapter;
import com.example.project.logic.log_record.LogInfo;
import com.example.project.logic.log_record.record.loginfo.*;
import com.example.project.logic.utils.Constants;

import java.util.logging.*;
import com.google.gson.*;

public class MyFormatter extends Formatter {
    private static final ObjectAdapter adapter = new ObjectAdapter(Constants.LEVEL, Constants.ALL_FIELDS);
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LogInfoWithOutResultRecord.class, adapter);
        builder.registerTypeAdapter(LogInfoWithResultRecord.class, adapter);
        gson = builder.setPrettyPrinting().serializeNulls().create();
    }

    @Override
    public String format(LogRecord record) {
        LogInfoRecordInterface logInfo = LogInfo.create(record);
        return gson.toJson(logInfo);
    }
}