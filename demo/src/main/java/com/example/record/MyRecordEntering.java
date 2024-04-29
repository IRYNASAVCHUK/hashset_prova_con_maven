package com.example.record;

public record MyRecordEntering(Object[] params, Class<?>[] paramsType, Object thisObject)
        implements MyRecord {
}
