package com.example.record;

public record MyRecordExiting<T>(Class<T> returnType, T result, Object[] params, Class<?>[] paramsType, Object thisObject)
        implements MyRecord {
}