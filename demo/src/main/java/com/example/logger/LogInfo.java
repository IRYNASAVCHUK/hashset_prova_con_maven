package com.example.logger;

import com.example.record.*;

import java.util.ArrayList;
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
        if (params[0] instanceof MyRecord) {
            MyRecord myRecord = (MyRecord) params[0];
            if (myRecord instanceof MyRecordExiting) {
                MyRecordExiting<?> exitingRecord = (MyRecordExiting<?>) myRecord;
                targetsObject = exitingRecord.thisObject();

                Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
                if (!returnType.equals(void.class)) {
                    Object returnValue = ((MyRecordExiting<?>) myRecord).result();
                    if (returnValue != null) {
                        if (isPrimitiveOrWrapper(returnValue)||returnValue instanceof String) {
                            this.result = exitingRecord.result();
                        } else {
                            this.result = System.identityHashCode(returnValue);
                        }
                    }
                }
                this.result = exitingRecord.result() == null ? null : exitingRecord.result();
            } else if (myRecord instanceof MyRecordEntering) {
                MyRecordEntering enteringRecord = (MyRecordEntering) myRecord;
                targetsObject = enteringRecord.thisObject();
            }
            this.target = (targetsObject == null) ? record.getSourceClassName()
                    : System.identityHashCode(targetsObject);

            Object[] argObjects = myRecord.params();
            if (argObjects != null && argObjects.length > 0) {
                ArrayList<Object> argsList = new ArrayList<>();
                for (Object arg : argObjects) {
                    if (isPrimitiveOrWrapper(arg)||arg instanceof String) {
                        argsList.add(arg);
                    } else {
                        argsList.add(System.identityHashCode(arg));
                    }
                }
                this.args = argsList.toArray();
            }
        }
    }

    private boolean isPrimitiveOrWrapper(Object obj) {
        return obj.getClass().isPrimitive() || isWrapper(obj);
    }

    private boolean isWrapper(Object obj) {
        return obj instanceof Integer ||
                obj instanceof Double ||
                obj instanceof Float ||
                obj instanceof Character ||
                obj instanceof Boolean ||
                obj instanceof Short ||
                obj instanceof Byte ||
                obj instanceof Long;
    }

}

/*
 * 
 *
 * 
 * if (myRecord instanceof MyRecordExiting<?>) {
 * Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
 * if (!returnType.equals(void.class)) {
 * resultString += "\n\t\t\"result\": [";
 * Object returnValue = ((MyRecordExiting<?>) myRecord).result();
 * if (returnValue != null) {
 * 
 * if (returnType.isPrimitive()) {
 * resultString += primitiveReturnValue(returnType, returnValue);
 * } else if (returnValue instanceof String) {
 * resultString += "\"" + returnValue + "\"";
 * } else if (isWrapper(returnValue)) {
 * resultString += returnValue;
 * } else {
 * resultString += "\"" + System.identityHashCode(returnValue) + "\"";
 * }
 * } else
 * resultString += "\"" + null + "\"";
 * resultString += "],";
 * }
 * }
 * }
 */