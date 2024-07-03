package logic.logger;

import java.util.logging.*;
import com.google.gson.*;

import logic.LogInfo;
import logic.ObjectAdapter;
import logic.utils.Constants;
import logic.utils.Records;

public class MyFormatter extends Formatter {
    private static final ObjectAdapter adapter = new ObjectAdapter(Constants.LEVELS, Constants.ALL_FIELDS);
    private static final Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Records.LogInfoWithOutResult.class, adapter);
        builder.registerTypeAdapter(Records.LogInfoWithResult.class, adapter);
        gson = builder.setPrettyPrinting().serializeNulls().create();//.setPrettyPrinting()
    }

    @Override
    public String format(LogRecord record) {
        Records.LogInfoInterface logInfo = LogInfo.create(record);
        return gson.toJson(logInfo);
    }
}