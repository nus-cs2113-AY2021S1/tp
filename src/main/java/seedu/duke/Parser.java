package seedu.duke;

import seedu.duke.command.Command;

/**
 * This class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Creates and returns the command based on user input.
     *
     * @param input  full user input.
     * @return command.
     * @throws DukeException If input command is unknown.
     */
    public static Command parse(String input) {
        Command command = createCommand(input);
        return command;
    }

    private static Command createCommand(String input) {
        Command command;

        if (input.compareToIgnoreCase(ExitCommand.BYE_KW) == 0) {
            command = new ExitCommand();
        } else if (input.compareToIgnoreCase(ListCommand.LIST_KW) == 0) {
            command = new ListCommand();
        } else if (input.startsWith(DoneCommand.DONE_KW)) {
            command = new DoneCommand(input);
        } else if (input.startsWith(DeleteCommand.DELETE_KW)) {
            command = new DeleteCommand(input);
        } else if (input.startsWith(FindCommand.FIND_KW)) {
            command = new FindCommand(input);
        } else if (input.startsWith(DateCommand.DATE_KW)) {
            command = new DateCommand(input);
        } else if (input.startsWith(Todo.TODO_KW)) {
            command = new AddTodoCommand(input);
        } else if (input.startsWith(Deadline.DEADLINE_KW)) {
            command = new AddDeadlineCommand(input);
        } else if (input.startsWith(Event.EVENT_KW)) {
            command = new AddEventCommand(input);
        } else {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }

        return command;
    }
}
