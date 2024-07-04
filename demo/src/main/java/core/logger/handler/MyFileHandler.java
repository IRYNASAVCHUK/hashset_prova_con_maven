package core.logger.handler;

import java.io.*;
import java.util.logging.*;

import core.logger.formatter.MyFormatter;
import core.utils.Constants;

public class MyFileHandler extends FileHandler {
    public MyFileHandler() throws IOException, SecurityException {
        super(Constants.LOG_FILE, false);
    }

    public static void configureHandler(Logger logger) {
        try {
            var fileHandler = new MyFileHandler();
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new MyFormatter());
            for (Handler existingHandler : logger.getHandlers())
                logger.removeHandler(existingHandler);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publish(LogRecord record) {
        try {
            if (Constants.FORMAT_JSON) {
                RandomAccessFile file = new RandomAccessFile(Constants.LOG_FILE, "rw");
                if (file.length() == 0)
                    file.writeBytes("[\n\t");
                else {
                    file.seek(file.length() - 2);
                    file.writeBytes(",\n\t");
                }
                file.writeBytes(new MyFormatter().format(record));
                file.writeBytes("\n]");
                file.close();
            } else {
                super.publish(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
