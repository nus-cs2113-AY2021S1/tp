package seedu.notus.command;

import seedu.notus.ui.AsciiArt;
import seedu.notus.ui.Formatter;

//@@author R-Ramana
/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Exits the program.";

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Bye!";

    @Override
    public String execute() {
        return COMMAND_SUCCESSFUL_MESSAGE + Formatter.LS
                + AsciiArt.getGoodbyeArt();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
