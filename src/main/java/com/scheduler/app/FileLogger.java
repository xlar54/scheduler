package com.scheduler.app;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
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

            // the following statement is used to log any messages
            logger.info( "File logging started.");

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warning(String message) {
        logger.warning(message);
    }

    public void severe(String message) {
        logger.severe(message);
    }
}
