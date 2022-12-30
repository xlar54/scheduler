package com.scheduler.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {

    private static FileLogger single_instance = null;
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public static FileLogger getInstance()
    {
        if (single_instance == null)
            single_instance = new FileLogger();

        single_instance.initialize();

        return single_instance;
    }

    private void initialize() {

        try {

            // This block configure the logger with handler and formatter
            try {
                fh = new FileHandler("scheduler.log");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        logger.info(message);

        for(Handler h:logger.getHandlers())
            h.close();
    }

    public void warning(String message) {
        logger.warning(message);

        for(Handler h:logger.getHandlers())
            h.close();
    }

    public void severe(String message) {
        logger.severe(message);

        for(Handler h:logger.getHandlers())
            h.close();
    }
}
