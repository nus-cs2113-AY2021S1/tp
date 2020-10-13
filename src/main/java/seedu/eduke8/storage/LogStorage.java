package seedu.eduke8.storage;

import org.json.simple.parser.ParseException;
import seedu.eduke8.common.Displayable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogStorage extends LocalStorage {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public LogStorage(String filePath) {
        super(filePath);
    }

    // Set up save to file inside of print to console
    @Override
    public void save() throws IOException {
        createFileIfNotExists();

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        // Only log severe to console
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        // Log all to the file
        FileHandler fh = new FileHandler(filePath);
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        LOGGER.addHandler(fh);
    }

    @Override
    public ArrayList<Displayable> load() throws IOException, ParseException {
        return null;
    }
}
