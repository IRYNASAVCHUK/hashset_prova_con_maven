package com.example.logger;

import com.example.record.enteringexiting.*;
import com.example.record.loginfo.*;

import java.util.*;
import java.util.logging.LogRecord;

public class LogInfo {
    public static LogInfoRecordInterface create(String event, Object target, List<Object[]> args, Object[] result,
            String name,
            Class<?> returnType, EnteringExitingRecordInterface myRecord) {
        return (myRecord instanceof ExitingRecord && !returnType.equals(void.class))
                ? new LogInfoWithResultRecord(event, target, args, result, name)
                : new LogInfoWithOutResultRecord(event, target, args, name);
    }

    public static LogInfoRecordInterface create(LogRecord record) {
        var params = record.getParameters();
        if (params == null || params.length == 0)
            throw new IllegalArgumentException("No parameters found in the record.");
        var event = record.getMessage();
        var className = record.getSourceClassName();
        var name = className + "." + record.getSourceMethodName();
        var myRecord = (EnteringExitingRecordInterface) params[0];
        var target = (getTargetObject(myRecord) == null) ? className : myRecord.thisObject();
        var args = createArgsPair(myRecord);
        Class<?> returnType = null;
        Object[] result = null;
        if (myRecord instanceof ExitingRecord){
            returnType = ((ExitingRecord<?>) myRecord).returnType();
            result = new Object[] { returnType,((ExitingRecord<?>) myRecord).result() };
        }
        
        return LogInfo.create(event, target, args, result, name, returnType, myRecord);
    }

    private static Object getTargetObject(EnteringExitingRecordInterface myRecord) {
        return (myRecord instanceof ExitingRecord) ? ((ExitingRecord<?>) myRecord).thisObject()
                : ((EnteringRecord) myRecord).thisObject();
    }

    private static List<Object[]> createArgsPair(EnteringExitingRecordInterface myRecord) {
        Object[] argObjects = myRecord.params();
        Class<?>[] argsType = myRecord.paramsType();
        List<Object[]> pair = new ArrayList<>();
        if (argObjects == null || argObjects.length == 0)
            return pair;
        if (argObjects.length != argsType.length)
            throw new IllegalArgumentException("Mismatched array lengths.");
        for (int i = 0; i < argObjects.length; i++)
            pair.add(new Object[] { argsType[i], argObjects[i] });
        return pair;
    }
}
