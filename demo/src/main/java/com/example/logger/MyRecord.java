package com.example.logger;

//public record MyRecord<T>(Class<?> returnType, T result, Object[] params){}
public record MyRecord<T>(Class<T> returnType, T result, Object[] params){}