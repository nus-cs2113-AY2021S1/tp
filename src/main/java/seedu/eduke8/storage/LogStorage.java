package seedu.eduke8.storage;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LogStorage extends LocalStorage {
    public LogStorage(String filePath) throws IOException {
        super(filePath);
    }

    // Set up save to file inside of print to console
    @Override
    public File save() throws IOException {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        // Only log severe to console
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        // Log all to the file
        FileHandler fh = new FileHandler(file.getAbsolutePath());
        fh.setFormatter(new SimpleFormatter());
        fh.setLevel(Level.ALL);
        LOGGER.addHandler(fh);

        LOGGER.log(Level.INFO, "Logging to file started");

        return file;
    }
}
