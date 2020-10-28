package seedu.notus.command;

import seedu.notus.ui.Formatter;

import com.diogonunes.jcolor.Attribute;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static com.diogonunes.jcolor.Ansi.colorize;

//@@author R-Ramana
/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("HelpCommand");

    public static final String COMMAND_WORD = "help";

    public static final String HELP_STRING = "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ], represent optional inputs.";

    public static final String[] COMMANDS_USAGE = {
        HELP_STRING,
        colorize(AddNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(AddEventCommand.COMMAND_USAGE, getColor(true)),
        colorize(ArchiveNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(CreateTagCommand.COMMAND_USAGE, getColor(true)),
        colorize(DeleteNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(DeleteEventCommand.COMMAND_USAGE, getColor(true)),
        colorize(DeleteTagCommand.COMMAND_USAGE, getColor(false)),
        colorize(EditNoteCommand.COMMAND_USAGE, getColor(true)),
        colorize(EditEventCommand.COMMAND_USAGE, getColor(false)),
        colorize(ExitCommand.COMMAND_USAGE, getColor(true)),
        colorize(FindCommand.COMMAND_USAGE, getColor(false)),
        colorize(ListEventCommand.COMMAND_USAGE, getColor(true)),
        colorize(ListNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(ListTagCommand.COMMAND_USAGE, getColor(true)),
        colorize(PinCommand.COMMAND_USAGE, getColor(false)),
        colorize(RemindCommand.COMMAND_USAGE, getColor(true)),
        colorize(TagNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(TagEventCommand.COMMAND_USAGE, getColor(true)),
        colorize(UnarchiveNoteCommand.COMMAND_USAGE, getColor(false)),
        colorize(ViewNoteCommand.COMMAND_USAGE, getColor(true))
    };

    @Override
    public String execute() {
        setupLogger();
        LOGGER.log(Level.INFO, "Logger Setup, will return HELP_STRING.");

        return Formatter.formatString(COMMANDS_USAGE, true);
    }

    public static Attribute getColor(boolean toggleColor) {
        int colorGold = 94;
        if (toggleColor) {
            //return Attribute.TEXT_COLOR(colorGold);
            return Attribute.BRIGHT_CYAN_TEXT();
        }
        return Attribute.BRIGHT_WHITE_TEXT();
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
