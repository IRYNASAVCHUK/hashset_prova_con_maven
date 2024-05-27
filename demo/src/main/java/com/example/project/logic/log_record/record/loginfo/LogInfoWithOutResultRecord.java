package com.example.project.logic.log_record.record.loginfo;

import java.util.List;

public record LogInfoWithOutResultRecord(String event, Object target, List<Object[]> args, String name)
        implements LogInfoRecordInterface {
}
