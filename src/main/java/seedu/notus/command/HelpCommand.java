package seedu.notus.command;

import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.notus.storage.StorageManager.LOGS_DIR;
import static seedu.notus.util.CommandMessage.HELP_COMMAND_USAGE;

//@@author R-Ramana
/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("HelpCommand");

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute() {
        setupLogger();
        LOGGER.log(Level.INFO, "Logger Setup, will return HELP_STRING.");

        return Formatter.formatString(HELP_COMMAND_USAGE, true, false);
    }

    /**
     * Sets up Logger files and gets ready to log messages to the file.
     */
    public void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler(LOGS_DIR + "HelpCommand.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (IOException error) {
            LOGGER.log(Level.SEVERE, "File logger not working.", error);
        }
    }
}
