package seedu.notus.command;

import seedu.notus.ui.AsciiArt;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.CommandMessage.EXIT_MESSAGE;


//@@author R-Ramana
/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    @Override
    public String execute() {
        return EXIT_MESSAGE + Formatter.LS
                + AsciiArt.getGoodbyeArt();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
