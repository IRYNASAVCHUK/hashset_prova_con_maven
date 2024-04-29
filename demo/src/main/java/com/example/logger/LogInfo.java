package com.example.logger;

import com.example.record.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.LogRecord;

public class LogInfo {
    private String event;
    private Object target;
    private Object[] args;
    private Object result;
    private String name;

    public String getEvent() {
        return event;
    }

    public Object getTarget() {
        return target;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public LogInfo(LogRecord record) {
        this.event = record.getMessage();
        this.name = record.getSourceClassName() + "." + record.getSourceMethodName();
        Object[] params = record.getParameters();
        Object targetsObject = null;
        if (params == null || params.length == 0)
            throw new IllegalArgumentException("No parameters found in the record.");
        MyRecord myRecord = (MyRecord) params[0];
        extracted(record, targetsObject, myRecord);
        getArgObjects(myRecord);
    }

    private void extracted(LogRecord record, Object targetsObject, MyRecord myRecord) {
        if (myRecord instanceof MyRecordExiting) {
            targetsObject = ((MyRecordExiting<?>) myRecord).thisObject();
            getResultValue(myRecord, (MyRecordExiting<?>) myRecord);
        } else if (myRecord instanceof MyRecordEntering) {
            targetsObject = ((MyRecordEntering) myRecord).thisObject();
        }
        this.target = (targetsObject == null) ? record.getSourceClassName() : getMap(targetsObject);
    }

    private void getResultValue(MyRecord myRecord, MyRecordExiting<?> exitingRecord) {
        Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
        System.out.println("returnType: \t" + returnType);
        if (!returnType.equals(void.class)) {
            Object returnValue = ((MyRecordExiting<?>) myRecord).result();
            if (returnValue != null)
                this.result = (returnType.isPrimitive()) ? primitiveType(returnType, returnValue) : getMap(returnValue);
            else
                this.result = new Object[0];
        }
    }

    private void getArgObjects(MyRecord myRecord) {
        Object[] argObjects = myRecord.params();
        Class<?>[] argsType = myRecord.paramsType();
        ArrayList<Object> argsList = new ArrayList<>();
        if (argObjects != null && argObjects.length > 0) {
            if (argObjects.length != argsType.length)
                throw new IllegalArgumentException("Mismatched array lengths.");
            for (int i = 0; i < argObjects.length; i++)
                if (argObjects[i] != null)
                    argsList.add((argsType[i].isPrimitive()) ? primitiveType(argsType[i], argObjects[i])
                            : getMap(argObjects[i]));
                else
                    argsList.add(new Object());
            this.args = argsList.toArray();
        } else {
            this.args = new Object[0];
        }
    }

    private Map<String, Object> getMap(Object val) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("@", System.identityHashCode(val));
        resultMap.put("class", val.getClass().getName());
        if (isWrapperOrString(val))
            resultMap.put("const", val);
        return resultMap;
    }

    private boolean isWrapperOrString(Object obj) {
        return obj instanceof Integer || obj instanceof Double || obj instanceof Float ||
                obj instanceof Character || obj instanceof Boolean || obj instanceof Short ||
                obj instanceof Byte || obj instanceof Long || obj instanceof String;
    }

    private Object primitiveType(Class<?> type, Object value) {
        switch (type.getTypeName()) {
            case "boolean":
                value = (boolean) value;
                break;
            case "byte":
                value = (byte) value;
                break;
            case "char":
                value = (char) value;
                break;
            case "short":
                value = (short) value;
                break;
            case "int":
                value = (int) value;
                break;
            case "long":
                value = (long) value;
                break;
            case "float":
                value = (float) value;
                break;
            case "double":
                value = (double) value;
                break;
        }
        return value;
    }
}