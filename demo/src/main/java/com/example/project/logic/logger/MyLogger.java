package com.example.project.logic.logger;

import com.example.project.logic.log_record.record.enteringexiting.EnteringRecord;
import com.example.project.logic.log_record.record.enteringexiting.ExitingRecord;
import com.example.project.logic.utils.Constants;

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
// INFO -> ALL
    public static void logMethodEntry(String methodName, Object thisObject, Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.ALL, thisObject.getClass().getName(), methodName, Constants.ENTRY,
                new EnteringRecord(params,paramsType, thisObject));
    }

    public static <T> void logMethodExit(String methodName,Object thisObject, Class<T> returnType, T result, Object[] params,  Class<?>[] paramsType) {
        logger.logp(Level.ALL, thisObject.getClass().getName(), methodName, Constants.RETURN,
                new ExitingRecord<>(returnType, result, params, paramsType, thisObject));
    }

    public static void logStaticMethodEntry(String className,String methodName, Object[] params, Class<?>[] paramsType) {
        logger.logp(Level.ALL,className , methodName, Constants.ENTRY,
                new EnteringRecord(params, paramsType, null));
    }

    public static <T> void logStaticMethodExit(String className, String methodName, Class<T> returnType, T result, Object[] params,  Class<?>[] paramsType) {
        logger.logp(Level.ALL, className, methodName, Constants.RETURN,
                new ExitingRecord<>(returnType, result, params, paramsType, null));
    }

}
