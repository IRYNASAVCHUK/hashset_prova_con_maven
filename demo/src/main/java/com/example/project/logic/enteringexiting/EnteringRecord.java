package com.example.project.logic.enteringexiting;

public record EnteringRecord(Object[] params, Class<?>[] paramsType, Object thisObject)
        implements EnteringExitingRecordInterface {
}
