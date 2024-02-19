package com.example.logger;

//public record MyRecord<T>(Class<?> returnType, T result, Object[] params){}
public record MyRecordExiting<T>(Class<T> returnType, T result, Object[] params, Object thisObject){}