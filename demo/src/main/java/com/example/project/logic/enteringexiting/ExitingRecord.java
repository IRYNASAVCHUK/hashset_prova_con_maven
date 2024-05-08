package com.example.project.logic.enteringexiting;

public record ExitingRecord<T>(Class<T> returnType, T result, Object[] params, Class<?>[] paramsType, Object thisObject)
        implements EnteringExitingRecordInterface {
}