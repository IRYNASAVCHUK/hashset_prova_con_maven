package com.example.logger;

import com.example.record.loginfo.LogInfoWithOutResultRecord;
import com.example.record.loginfo.LogInfoWithResultRecord;
import com.example.utils.Constants;

import java.util.logging.*;
import com.google.gson.*;

public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        final int level = Constants.LEVEL;
        var builder = new GsonBuilder();
        var adapter = new ObjectAdapter(level, false);

        
        var logInfo = LogInfo.create(record);
        var type = (logInfo instanceof LogInfoWithOutResultRecord)? LogInfoWithOutResultRecord.class:LogInfoWithResultRecord.class;
        builder.registerTypeAdapter(type, adapter);
        var gson = builder.setPrettyPrinting().serializeNulls().create();
        return gson.toJson(logInfo);
    }
}