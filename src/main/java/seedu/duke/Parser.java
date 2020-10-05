package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Creates and returns the command based on user input.
     *
     * @param input full user input.
     * @return command.
     * @throws DukeException If input command is unknown.
     */
    public static Command parse(String input) throws DukeException {
        Command command = createCommand(input);
        return command;
    }

    private static Command createCommand(String input) throws DukeException {
        Command command;

        if (input.compareToIgnoreCase(ExitCommand.BYE_KW) == 0) {
            command = new ExitCommand();
        } else {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }

        return command;
    }
}
