package common;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class KajiLog {
    private static final String LOG_FILE = "data/kaji.log";
    private static FileHandler fh;

    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        Arrays.stream(logger.getHandlers()).forEach(logger::removeHandler);

        try {
            if (fh == null) {
                fh = new FileHandler(LOG_FILE);
                fh.setFormatter(new SimpleFormatter());
                fh.setLevel(Level.INFO);
            }
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger is not working.");
        }

        return Logger.getLogger(name);
    }

}
