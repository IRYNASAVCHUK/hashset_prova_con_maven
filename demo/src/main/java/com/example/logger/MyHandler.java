package com.example.logger;

import java.io.IOException;
import java.util.logging.*;

public class MyHandler extends FileHandler {
    public MyHandler() throws IOException, SecurityException {
        super();
    }

    private static final String LOG_FILE = "logFile.json"; /* va bene come nome di default, ma dovrebbe essere possibile passare il nome da
                                                              linea di comando o definirlo nel file di configurazione */

    public static void configureHandler(Logger logger) {
        try {
            Handler fileHandler = new FileHandler(LOG_FILE,false);
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