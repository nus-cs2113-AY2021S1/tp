package seedu.duke.command;

import seedu.duke.data.notebook.Tag.TagColor;
import seedu.duke.ui.InterfaceManager;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Lists all the commands and usage.
 */
public class HelpCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("HelpCommand");

    private static final String COLOR_CYAN_STRING = TagColor.COLOR_CYAN.getColor();
    private static final String COLOR_WHITE_STRING = TagColor.COLOR_WHITE.getColor();
    private static final String COLOR_RESET_STRING = TagColor.COLOR_RESET.getColor();

    public static final String COMMAND_WORD = "help";

    public static final String HELP_STRING = InterfaceManager.LS
            + "The recognized commands and their usages are listed below. "
            + "Parameters listed in brackets, [ ] represent optional inputs." + InterfaceManager.LS + COLOR_WHITE_STRING
            + AddNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + AddEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + CreateTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + DeleteNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + DeleteEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + DeleteTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + EditNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + EditEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + ExitCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + FindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + ListEventCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + ListNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + ListTagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + PinCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + RemindCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_CYAN_STRING
            + TagCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_WHITE_STRING
            + ViewNoteCommand.COMMAND_USAGE + InterfaceManager.LS + COLOR_RESET_STRING;

    @Override
    public String execute() {
        setupLogger();
        LOGGER.log(Level.INFO, "Logger Setup, will return HELP_STRING.");

        return HELP_STRING;
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
