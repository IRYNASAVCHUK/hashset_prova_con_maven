package com.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyHandler extends FileHandler {
    public static final String DEFAULT_LOG_FILE = "logFile.json";
    private static final String LOG_FILE;

    public MyHandler() throws IOException, SecurityException {
        super(LOG_FILE, false); // false - sovrascrive il file, ogni volta che lanciamo main
    }

    static {
        String logFileName = ConfigLoader.getConfigValue("logFile");
        LOG_FILE = logFileName != null ? logFileName : DEFAULT_LOG_FILE;
    }

    public static void configureHandler(Logger logger) {
        try {
            MyHandler fileHandler = new MyHandler();
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new MyFormatter());
            for (Handler existingHandler : logger.getHandlers()) {
                logger.removeHandler(existingHandler);
            }
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
