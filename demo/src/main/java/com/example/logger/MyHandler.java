package com.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyHandler extends FileHandler {
    public static final String DEFAULT_LOG_FILE = "logFile.json";
    private static final String LOG_FILE;

    public MyHandler() throws IOException, SecurityException {
        super();
    }
    /*
     * TODO: ho messo nel config.json campo "logFile"
     * Comento:
     * va bene come nome di default, ma dovrebbe essere possibile passare il nome da
     * linea di comando o definirlo nel file di configurazione
     */
    static {
        String logFileName = ConfigLoader.getConfigValue("logFile");
        LOG_FILE = logFileName != null ? logFileName : DEFAULT_LOG_FILE;
    }

    public static void configureHandler(Logger logger) {
        try {
            Handler fileHandler = new FileHandler(LOG_FILE, true);
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