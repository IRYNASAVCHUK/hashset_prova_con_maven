package com.example.logger;

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
/*
 * TODO:
 *      log(LogRecord record)
 * 
 *      setParameters(Object[] parameters)
 *      setSourceClassName(String sourceClassName)
 *      setSourceMethodName(String sourceMethodName)
 *      setMessage(String message): "ENTRY", "RETURN"
 *      setLevel(Level level): log level FINER
 * ***********************************
 * public void entering(String sourceClass,String sourceMethod, Object[] params)
 * 
 */
    public static LogRecord logEntering(){
        return null;
    }

    public static LogRecord logExiting(){
        return null;
    }
}
