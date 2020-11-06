package seedu.quotesify.parser;

import seedu.quotesify.commands.BookmarkCommand;
import seedu.quotesify.commands.add.AddCommand;
import seedu.quotesify.commands.Command;
import seedu.quotesify.commands.delete.DeleteCommand;
import seedu.quotesify.commands.done.DoneCommand;
import seedu.quotesify.commands.edit.EditCommand;
import seedu.quotesify.commands.ExitCommand;
import seedu.quotesify.commands.find.FindCommand;
import seedu.quotesify.commands.HelpCommand;
import seedu.quotesify.commands.list.ListCommand;

/**
 * Represents a command parser.
 */
public class Parser {
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BOOKMARK = "bookmark";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_HELP = "help";

    /**
     * Returns a command type based on user input.
     *
     * @param input User input command.
     * @return Command object.
     */
    public Command parseUserCommand(String input) {
        String[] tokens = input.split(" ", 2);

        // if user did not provide arguments, let tokens[1] be empty string
        if (tokens.length == 1) {
            tokens = new String[]{tokens[0], ""};
        }

        String command = tokens[0].toLowerCase();
        String arguments = tokens[1];

        switch (command) {
        case COMMAND_ADD:
            return new AddCommand(arguments);
        case COMMAND_BOOKMARK:
            return new BookmarkCommand(arguments);
        case COMMAND_DELETE:
            return new DeleteCommand(arguments);
        case COMMAND_EDIT:
            return new EditCommand(arguments);
        case COMMAND_LIST:
            return new ListCommand(arguments);
        case COMMAND_DONE:
            return new DoneCommand(arguments);
        case COMMAND_FIND:
            return new FindCommand(arguments);
        case COMMAND_BYE:
            return new ExitCommand();
        case COMMAND_HELP:
            return new HelpCommand();
        default:
            // can consider throwing an exception here for invalid command
        }
        return null;
    }
}
