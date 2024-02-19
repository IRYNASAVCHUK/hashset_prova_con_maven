package com.example.logger;

public record MyRecordExiting<T>(Class<T> returnType, T result, Object[] params, Object thisObject){}