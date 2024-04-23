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
        MyRecord myRecord = (MyRecord) params[0];
        extracted(record, targetsObject, myRecord);
        getArgObjects(myRecord);
    }

    private void extracted(LogRecord record, Object targetsObject, MyRecord myRecord) {
        if (myRecord instanceof MyRecordExiting) {
            targetsObject = ((MyRecordExiting<?>) myRecord).thisObject();
            getReturnType(myRecord, (MyRecordExiting<?>) myRecord);
        } else if (myRecord instanceof MyRecordEntering) {
            targetsObject = ((MyRecordEntering) myRecord).thisObject();
        }
        this.target = (targetsObject == null) ? record.getSourceClassName()
                : System.identityHashCode(targetsObject);
    }

    private void getReturnType(MyRecord myRecord, MyRecordExiting<?> exitingRecord) {
        Class<?> returnType = ((MyRecordExiting<?>) myRecord).returnType();
        if (!returnType.equals(void.class)) {
            Object returnValue = ((MyRecordExiting<?>) myRecord).result();
            if (returnValue != null)
                this.result = (isPrimitiveOrWrapperOrString(returnValue)) ? exitingRecord.result()
                        : System.identityHashCode(returnValue);
        }
    }

    private void getArgObjects(MyRecord myRecord) {
        Object[] argObjects = myRecord.params();
        if (argObjects != null && argObjects.length > 0) {
            ArrayList<Object> argsList = new ArrayList<>();
            for (Object arg : argObjects)
                argsList.add((isPrimitiveOrWrapperOrString(arg)) ? arg
                        : System.identityHashCode(arg));
            this.args = argsList.toArray();
        } else {
            this.args = new Object[0];
        }
    }

    private boolean isPrimitiveOrWrapperOrString(Object obj) {
        return obj.getClass().isPrimitive() ||
                obj instanceof Integer ||
                obj instanceof Double ||
                obj instanceof Float ||
                obj instanceof Character ||
                obj instanceof Boolean ||
                obj instanceof Short ||
                obj instanceof Byte ||
                obj instanceof Long ||
                obj instanceof String;
    }
}