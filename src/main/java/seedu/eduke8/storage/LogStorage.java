package seedu.eduke8.storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;

public class LogStorage extends LocalStorage {
    public LogStorage(String filePath) {
        super(filePath);
    }

    /**
     * Sets up saving logs to file instead of default print to console.
     * Returns file where the logs are saved.
     *
     * @return Log file.
     * @throws IOException  If the file is not found or cannot be written.
     */
    @Override
    public File save() throws IOException {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        // Only log severe to console
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        // Log all to the file
        file = super.save();

        FileHandler fh = new FileHandler(file.getAbsolutePath());
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        LOGGER.addHandler(fh);

        LOGGER.log(Level.INFO, "Logging to file started");

        return file;
    }
}
