package com.example.record.loginfo;

import java.util.List;

public record LogInfoWithResultRecord(String event, Object target, List<Object[]>  args, Object[] result, String name)
                implements LogInfoRecordInterface {

}
