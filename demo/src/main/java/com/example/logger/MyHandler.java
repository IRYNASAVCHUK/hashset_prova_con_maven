package com.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyHandler extends FileHandler {
    public static final String DEFAULT_LOG_FILE = "logFile.json";
    private static final String LOG_FILE;

    public MyHandler() throws IOException, SecurityException {
        super();
    }

    static {
        String logFileName = ConfigLoader.getConfigValue("logFile");
        LOG_FILE = logFileName != null ? logFileName : DEFAULT_LOG_FILE;
    }

    public static void configureHandler(Logger logger) {
        try {
            Handler fileHandler = new FileHandler(LOG_FILE, false);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new MyFormatter());

            // Rimuovi gli handler esistenti per evitare duplicati
            for (Handler existingHandler : logger.getHandlers()) {
                logger.removeHandler(existingHandler);
            }

            // Aggiungi il nuovo handler
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}