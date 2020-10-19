package seedu.duke.command;

import seedu.duke.ui.Formatter;

import com.diogonunes.jcolor.Attribute;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("HelpCommand");

    public static final String COMMAND_WORD = "help";

    public static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {
        HELP_STRING,
        colorize(AddNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(AddEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(CreateTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(DeleteNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(DeleteEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(DeleteTagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(EditNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(EditEventCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ExitCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(FindCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ListEventCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(ListNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ListTagCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(PinCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(RemindCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT()),
        colorize(TagCommand.COMMAND_USAGE, Attribute.BRIGHT_CYAN_TEXT()),
        colorize(ViewNoteCommand.COMMAND_USAGE, Attribute.BRIGHT_WHITE_TEXT())
    };

    @Override
    public String execute() {
        setupLogger();
        LOGGER.log(Level.INFO, "Logger Setup, will return HELP_STRING.");

        return Formatter.formatString(COMMANDS_USAGE, true);
    }

    public void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);

        try {
            FileHandler fileHandler = new FileHandler("HelpCommand.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (IOException error) {
            LOGGER.log(Level.SEVERE, "File logger not working.", error);
        }
    }
}
