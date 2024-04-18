package com.example.logger;

import java.util.logging.LogRecord;
import com.example.record.*;

public class LogInfo {
    private String event;
    private String target;
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
                this.result = exitingRecord.result() == null ? null : exitingRecord.result();
            } else if (myRecord instanceof MyRecordEntering) {
                MyRecordEntering enteringRecord = (MyRecordEntering) myRecord;
                targetsObject = enteringRecord.thisObject();
            }
            this.target = (targetsObject == null) ? record.getSourceClassName()
                    : Integer.toString(System.identityHashCode(targetsObject));
            this.args = myRecord.params();
        }
    }
}
