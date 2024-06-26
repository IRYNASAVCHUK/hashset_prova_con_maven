package logic.logger;

import java.util.logging.*;
import com.google.gson.*;

import logic.gson.ObjectAdapter;
import logic.log_record.LogInfo;
import logic.log_record.record.loginfo.*;
import logic.utils.Constants;

public class MyFormatter extends Formatter {
    private static final ObjectAdapter adapter = new ObjectAdapter(Constants.LEVELS, Constants.ALL_FIELDS);
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LogInfoWithOutResultRecord.class, adapter);
        builder.registerTypeAdapter(LogInfoWithResultRecord.class, adapter);
        gson = builder.serializeNulls().create();//.setPrettyPrinting()
    }

    @Override
    public String format(LogRecord record) {
        LogInfoRecordInterface logInfo = LogInfo.create(record);
        return gson.toJson(logInfo);
    }
}