package com.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyHandler extends FileHandler {
    public MyHandler() throws IOException, SecurityException {
        super();
    }

    private static final String LOG_FILE = "logFile.txt";

    public static void configureHandler(Logger logger) {
        try {
            Handler fileHandler = new FileHandler(LOG_FILE);
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