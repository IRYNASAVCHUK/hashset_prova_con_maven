package com.example.logger;

import com.example.record.enteringexiting.*;
import com.example.record.loginfo.*;

import java.util.*;
import java.util.logging.LogRecord;

public class LogInfo {
    public static LogInfoRecordInterface create(String event, Object target, Object[] args, Object result, String name,
            Class<?> returnType, EnteringExitingRecordInterface myRecord) {
        return (myRecord instanceof ExitingRecord && !returnType.equals(void.class))
                ? new LogInfoWithResultRecord(event, target, args, result, name)
                : new LogInfoWithOutResultRecord(event, target, args, name);
    }

    public static LogInfoRecordInterface create(LogRecord record) {
        Object[] params = record.getParameters();
        if (params == null || params.length == 0)
            throw new IllegalArgumentException("No parameters found in the record.");
        String event = record.getMessage();
        String className = record.getSourceClassName();
        String name = className + "." + record.getSourceMethodName();
        EnteringExitingRecordInterface myRecord = (EnteringExitingRecordInterface) params[0];
        Object target = getTargetObject(className, myRecord);
        Object[] args = getArgObjects(myRecord);
        Object result = null;
        Class<?> returnType = null;
        if (myRecord instanceof ExitingRecord) {
            returnType = ((ExitingRecord<?>) myRecord).returnType();
            if (!returnType.equals(void.class))
                result = getResultValue(myRecord, (ExitingRecord<?>) myRecord, returnType);
        }
        return LogInfo.create(event, target, args, result, name, returnType, myRecord);
    }

    private static Object getTargetObject(String className, EnteringExitingRecordInterface myRecord) {
        Object targetsObject = (myRecord instanceof ExitingRecord) ? ((ExitingRecord<?>) myRecord).thisObject()
                : (myRecord instanceof EnteringRecord) ? ((EnteringRecord) myRecord).thisObject()
                        : null;
        return (targetsObject == null) ? className : getMap(targetsObject);
    }

    private static Object getResultValue(EnteringExitingRecordInterface myRecord, ExitingRecord<?> exitingRecord,
            Class<?> returnType) {
        Object returnValue = ((ExitingRecord<?>) myRecord).result();
        return (returnValue == null) ? new Object[0]
                : (returnType.isPrimitive()) ? primitiveType(returnType, returnValue)
                        : getMap(returnValue);
    }

    private static Object[] getArgObjects(EnteringExitingRecordInterface myRecord) {
        Object[] argObjects = myRecord.params();
        Class<?>[] argsType = myRecord.paramsType();
        ArrayList<Object> argsList = new ArrayList<>();
        if (argObjects == null || argObjects.length == 0)
            return new Object[0];
        if (argObjects.length != argsType.length)
            throw new IllegalArgumentException("Mismatched array lengths.");
        for (int i = 0; i < argObjects.length; i++)
            argsList.add((argObjects[i] == null) ? new Object()
                    : (argsType[i].isPrimitive()) ? primitiveType(argsType[i], argObjects[i])
                            : getMap(argObjects[i]));
        return argsList.toArray();
    }

    private static Map<String, Object> getMap(Object val) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("@", System.identityHashCode(val));
        resultMap.put("class", val.getClass().getName());
        if (isWrapperOrString(val))
            resultMap.put("const", val);
        return resultMap;
    }

    private static boolean isWrapperOrString(Object obj) {
        return obj instanceof Integer || obj instanceof Double || obj instanceof Float ||
                obj instanceof Character || obj instanceof Boolean || obj instanceof Short ||
                obj instanceof Byte || obj instanceof Long || obj instanceof String;
    }

    private static Object primitiveType(Class<?> type, Object value) {
        return value = switch (type.getTypeName()) {
            case "boolean"->(boolean) value;
            case "byte"->(byte) value;
            case "char"->(char) value;
            case "short"->(short) value;
            case "int"->(int) value;
            case "long"->(long) value;
            case "float"->(float) value;
            case "double"-> (double) value;
            default->value;
        };
    }
}
