package com.example.record;

public record MyRecordEntering(Object[] params, Object thisObject) implements MyRecord {
    @Override
    public Object[] params() {
        return params;
    }
}
