package com.example.logger;

import com.example.record.enteringexiting.EnteringRecord;
import com.example.record.enteringexiting.ExitingRecord;
import com.example.utils.Constants;

import java.util.logging.*;

public class MyLogger extends Logger {

    protected MyLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }

    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    static {
        configureLogger();
    }

    private static void configureLogger() {
        logger.setLevel(Level.ALL);
        MyHandler.configureHandler(logger);
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void logMethodEntry(String methodName, Object thisObject, Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.INFO, thisObject.getClass().getName(), methodName, Constants.ENTRY,
                new EnteringRecord(params,paramsType, thisObject));
    }

    public static <T> void logMethodExit(String methodName,Object thisObject, Class<T> returnType, T result, Object[] params,  Class<?>[] paramsType) {
        logger.logp(Level.INFO, thisObject.getClass().getName(), methodName, Constants.RETURN,
                new ExitingRecord<>(returnType, result, params, paramsType, thisObject));
    }

    public static void logStaticMethodEntry(String className,String methodName, Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.INFO,className , methodName, Constants.ENTRY,
                new EnteringRecord(params, paramsType, null));
    }

    public static <T> void logStaticMethodExit(String className, String methodName, Class<T> returnType, T result, Object[] params,  Class<?>[] paramsType) {
        logger.logp(Level.INFO, className, methodName, Constants.RETURN,
                new ExitingRecord<>(returnType, result, params, paramsType, null));
    }

}
