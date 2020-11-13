package seedu.duke.loggings;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.storage.writing.WritingsLoader;

public class Logging {
    private static final Logger logWritingLoader = Logger.getLogger(WritingsLoader.class.getName());

    public static void test() {
        logWritingLoader.log(Level.INFO, "Your database has been up to date!");
    }
}
