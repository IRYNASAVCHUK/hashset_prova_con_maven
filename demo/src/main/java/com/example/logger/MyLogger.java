package com.example.logger;

import java.util.logging.*;

public class MyLogger extends Logger {
    
    protected MyLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }

    private static final Logger logger = Logger.getLogger("");
//    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

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
}
