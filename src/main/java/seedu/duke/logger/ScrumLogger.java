package seedu.duke.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;

public class ScrumLogger {
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static FileHandler fileHandler;
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setup() throws IOException {
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);

        String currentPath = System.getProperty("user.dir");
        int index = currentPath.indexOf("tp");
        String pathToTP = currentPath.substring(0, index + 2);
        fileHandler = new FileHandler(pathToTP + File.separator + "logs" + File.separator + "scrum.log");

        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        logger.addHandler(fileHandler);

    }

    public static void destroy() {
        if (!fileHandler.equals(null)) {
            logger.removeHandler(fileHandler);
            fileHandler.close();
        }
    }
}
