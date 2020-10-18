package seedu.duke;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AniLogger {
    private final Logger logger;
    public static final String LOG_PATH = "log/AniChan.log";

    public AniLogger(String className) {
        logger = Logger.getLogger(className);

        try {
            // Create file & folder
            File file = new File(LOG_PATH);
            file.getParentFile().mkdirs();
            file.createNewFile();

            // Add handler to logger
            FileHandler fileHandler = new FileHandler(LOG_PATH, 8096, 1, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.addHandler(fileHandler);
            logger.info("sss");
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
