package com.example.record.loginfo;

public record LogInfoWithResultRecord(String event, Object target, Object[] args, Object result, String name)
        implements LogInfoRecordInterface {

}
