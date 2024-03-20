package com.example.record;

public record MyRecordExiting<T>(Class<T> returnType, T result, Object[] params, Object thisObject)
        implements MyRecord {
}