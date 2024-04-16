package com.example.logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.*;

import com.example.utils.Constants;

public class MyHandler extends FileHandler {
    public MyHandler() throws IOException, SecurityException {
        super(Constants.LOG_FILE, false); // false - sovrascrive il file, ogni volta che lanciamo main
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
            RandomAccessFile file = new RandomAccessFile(Constants.LOG_FILE, "rw");
            if (file.length() == 0) {
                file.writeBytes("[\n\t");
            } else {
                file.seek(file.length()-2);
                file.writeBytes(",\n\t");
            }
            file.writeBytes(new MyFormatter().format(record));
            file.writeBytes("\n]");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
