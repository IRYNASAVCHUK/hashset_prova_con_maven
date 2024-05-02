package com.example.record.loginfo;

public record LogInfoWithOutResultRecord(String event, Object target, Object[] args, String name)
        implements LogInfoRecordInterface {
}
