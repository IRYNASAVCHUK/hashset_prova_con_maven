package com.example.logger;

public record MyResult<T>(Class<T> returnType, T result, Object[] params){}