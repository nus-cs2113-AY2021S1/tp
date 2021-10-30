package studyit;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class StudyItLog {
    public static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logger.addHandler(ch);



        try {
            String dirPath = "logs";
            File fileDir = new File(dirPath);

            if (!fileDir.exists()) {
                fileDir.mkdir();
            }

            FileHandler fh = new FileHandler("logs/StudyIt_Logger.log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.FINE);
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
