package com.example.project.logic.log_record.record.enteringexiting;

public record EnteringRecord(Object[] params, Class<?>[] paramsType, Object thisObject)
                implements EnteringExitingRecordInterface {
}
