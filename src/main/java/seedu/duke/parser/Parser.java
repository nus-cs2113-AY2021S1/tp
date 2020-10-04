package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.AddSubjectCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.SorryCommand;

/**
 * Allows the parsing of inputs provided by the user.
 */
public class Parser {

    /**
     * Parses the inputs provided by the user.
     *
     * @param fullCommand input by the user
     * @return returns a command instance to execute a command
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("done ")) {
            return new DoneCommand(fullCommand);
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("add")) {
            return new AddSubjectCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteCommand(fullCommand);
        } else {
            return new SorryCommand();
        }
    }
}