package com.example.logger;

import java.io.IOException;
import java.io.RandomAccessFile;
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

    @Override
    public void publish(LogRecord record) {
        try {
            RandomAccessFile file = new RandomAccessFile(LOG_FILE, "rw");
            if (file.length() == 0) {
                file.writeBytes("[\n\t");
            } else {
                file.seek(file.length() - 2);
                file.writeBytes(",\n\t");
            }
            file.writeBytes(new MyFormatter().format(record));
            file.writeBytes("]");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
