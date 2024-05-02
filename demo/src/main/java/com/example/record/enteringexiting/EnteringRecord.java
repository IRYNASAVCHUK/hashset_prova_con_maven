package com.example.record.enteringexiting;

public record EnteringRecord(Object[] params, Class<?>[] paramsType, Object thisObject)
        implements EnteringExitingRecordInterface {
}
