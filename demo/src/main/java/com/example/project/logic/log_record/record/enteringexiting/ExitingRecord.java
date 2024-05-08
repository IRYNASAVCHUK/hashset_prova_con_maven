package com.example.project.logic.log_record.record.enteringexiting;

public record ExitingRecord<T>(Class<T> returnType, T result, Object[] params, Class<?>[] paramsType, Object thisObject)
        implements EnteringExitingRecordInterface {
}