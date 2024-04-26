package com.example.logger;

import com.example.record.*;
import com.google.gson.JsonNull;

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
        System.out.println("returnType: \t"+returnType);
        if (!returnType.equals(void.class)) {
            Object returnValue = ((MyRecordExiting<?>) myRecord).result();
            if (returnValue != null) {
                this.result = (returnType.getClass().isPrimitive()) ? returnValue : getMap(returnValue);
            }else{
                //this.result = JsonNull.INSTANCE; 

                this.result=new Object(); // se null  "result": {}
                System.out.println("CCCCCCCCcccccccccccccccccccccccccccccccccccccccccccc");
            }
        }
    }

    private void getArgObjects(MyRecord myRecord) {
        Object[] argObjects = myRecord.params();
        if (argObjects != null && argObjects.length > 0) {
            ArrayList<Object> argsList = new ArrayList<>();
            for (Object arg : argObjects){
                System.out.println("arg: "+arg.getClass().getSimpleName());
                argsList.add((arg.getClass().isPrimitive()) ? arg : getMap(arg));
            }
               
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
}