package com.example.logger;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LogInfoAdapter extends TypeAdapter<LogInfo> {

    @Override
    public void write(JsonWriter out, LogInfo value) throws IOException {
        
    }

    @Override
    public LogInfo read(JsonReader in) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

}
