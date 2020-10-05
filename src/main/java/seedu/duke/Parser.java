package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.PrintEventsCommand;
import seedu.duke.command.PrintListCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.AddCommand;

/**
 * Determines the type of command input by the user and calls for the respective command function.
 */
public class Parser {

    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_PRINT_LIST = "print list";
    public static final String COMMAND_PRINT_EVENTS = "print events";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    public static Command handleUserInput(String userInput) {

        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand(userInput);
        } else if (userInput.equals(COMMAND_PRINT_LIST)) {
            return new PrintListCommand(userInput);
        } else if (userInput.equals(COMMAND_PRINT_EVENTS)) {
          return new PrintEventsCommand(userInput);
        } else if (userInput.startsWith(COMMAND_DONE)) {
            return new DoneCommand(userInput);
        } else if (userInput.startsWith(COMMAND_DELETE)) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith(COMMAND_FIND)) {
            return new FindCommand(userInput);
        } else {
            /** An invalid command is catered for in AddCommand */
            return new AddCommand(userInput);
        }
    }
}
