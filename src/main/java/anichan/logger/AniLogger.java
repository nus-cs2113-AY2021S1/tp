package anichan.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents the Logger used in this project.
 */
public class AniLogger {
    // Settings used by AniLogger
    private static final int MAX_LOG_COUNT = 1;
    private static final int MAX_LOG_SIZE = (int) (Math.pow(1024, 3) * 5); // 5 Megabytes
    private static final String LOG_FILE = "data/AniChan.log";

    private static final Level LOG_FILE_LEVEL = Level.INFO;
    private static final Level CONSOLE_LEVEL = Level.SEVERE;

    private static FileHandler fileHandler;
    private static ConsoleHandler consoleHandler;

    /**
     * Returns the logger when provided a class name.
     *
     * @param className name of class requesting for logger
     * @return logger object
     */
    public static Logger getAniLogger(String className) {
        Logger logger = Logger.getLogger(className);
        logger.setUseParentHandlers(false);

        clearHandlers(logger);
        addConsoleHandler(logger);
        addFileHandler(logger);

        return Logger.getLogger(className);
    }

    /**
     * Adds Handler for console logging.
     *
     * @param logger logger object with console handler
     */
    private static void addConsoleHandler(Logger logger) {
        assert logger != null : "Logger is null!";

        if (consoleHandler == null) {
            consoleHandler = createConsoleHandler();
        }

        logger.addHandler(consoleHandler);
    }

    /**
     * Returns a console handler for logger.
     *
     * @return console handler
     */
    private static ConsoleHandler createConsoleHandler() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(CONSOLE_LEVEL);
        return consoleHandler;
    }

    /**
     * Adds Handler for file logging.
     *
     * @param logger logger object with file handler
     */
    private static void addFileHandler(Logger logger) {
        assert logger != null : "Logger is null!";

        try {
            if (fileHandler == null) {
                fileHandler = createFileHandler();
            }

            logger.addHandler(fileHandler);
        } catch (IOException exceptionMessage) {
            logger.log(Level.WARNING, "Failed to add file handler for logger.");
        }
    }

    /**
     * Returns a file handler for logger.
     *
     * @return file handler
     * @throws IOException when unable to initialise file handler
     */
    private static FileHandler createFileHandler() throws IOException {
        File file = new File(LOG_FILE);
        file.getParentFile().mkdirs();
        file.createNewFile();

        SimpleFormatter formatter = new SimpleFormatter();

        FileHandler fileHandler = new FileHandler(LOG_FILE, MAX_LOG_SIZE,
                MAX_LOG_COUNT, true);
        fileHandler.setFormatter(formatter);
        fileHandler.setLevel(LOG_FILE_LEVEL);

        return fileHandler;
    }

    /**
     * Clears the existing handlers from logger.
     *
     * @param logger logger object
     */
    private static void clearHandlers(Logger logger) {
        assert logger != null : "Logger is null!";

        Handler[] handlers = logger.getHandlers();

        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }
    }

}
