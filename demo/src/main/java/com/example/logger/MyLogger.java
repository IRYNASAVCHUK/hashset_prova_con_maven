package com.example.logger;

import java.util.logging.*;

import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;

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

    public static LogRecord logEntering(MyRecordEntering enter) {
        LogRecord log = new LogRecord(Level.FINER, GLOBAL_LOGGER_NAME);
        log.setParameters(new Object[] { enter });
        log.setMessage("ENTRY");
        return log;
    }

    public static <T> LogRecord logExiting(MyRecordExiting<T> result) {
        LogRecord log = new LogRecord(Level.FINER, GLOBAL_LOGGER_NAME);
        log.setParameters(new Object[] { result });
        log.setMessage("RETURN");
        return log;
    }
}
