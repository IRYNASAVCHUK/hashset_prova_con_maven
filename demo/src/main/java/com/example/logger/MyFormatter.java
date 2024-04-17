package com.example.logger;

import com.google.gson.Gson;
// import com.google.gson.GsonBuilder; prettyString

import java.util.logging.*;
// provare Gson

public class MyFormatter extends Formatter {
    private Gson gson = new Gson();//new GsonBuilder().setPrettyPrinting().create(); // <--semplice


    @Override
    public String format(LogRecord record) {
        LogInfo logInfo = new LogInfo(record);
        return gson.toJson(logInfo);
    }

   
}
