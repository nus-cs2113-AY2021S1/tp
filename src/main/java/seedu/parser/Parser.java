package seedu.parser;

import seedu.command.AddCommand;
import seedu.command.ByeCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.CompareCommand;
import seedu.command.DeleteCommand;
import seedu.command.EditCommand;
import seedu.command.FindCommand;
import seedu.command.HelpCommand;
import seedu.command.ListCommand;
import seedu.command.LogInCommand;
import seedu.command.RemoveUserCommand;
import seedu.exception.WhereGotTimeException;

/**
 * Parses the user's input.
 */
public class Parser {
    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_REMOVE = "remove";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_ADD = "add";
    private static final String COMMAND_EDIT = "edit";
    private static final String COMMAND_COMPARE = "compare";
    public static final int MAX_PASSWORD_LENGTH = 6;
    
    /**
     * Returns a Command object based on user's string input.
     *
     * @param input user's string input.
     * @return a new Command object based on user's input.
     * @throws WhereGotTimeException if user's input does not match any of the command.
     */
    public static Command parse(String input) throws WhereGotTimeException {
        String[] parsedInputs = input.split(" ", 2);
        switch (parsedInputs[0].toLowerCase()) {
        case COMMAND_CLEAR:
            checkClearValidity(parsedInputs);
            return new ClearCommand(parsedInputs[1]);
        case COMMAND_LIST:
            checkListValidity(parsedInputs);
            return new ListCommand(parsedInputs[1]);
        case COMMAND_DELETE:
            checkDeleteValidity(parsedInputs);
            return new DeleteCommand(parsedInputs[1]);
        case COMMAND_REMOVE:
            checkRemoveValidity(parsedInputs);
            return new RemoveUserCommand(parsedInputs[1]);
        case COMMAND_FIND:
            checkFindValidity(parsedInputs);
            return new FindCommand(parsedInputs[1]);
        case COMMAND_LOGIN:
            checkLogInValidity(parsedInputs);
            return new LogInCommand(parsedInputs[1]);
        case COMMAND_ADD:
            checkAddValidity(parsedInputs);
            return new AddCommand(parsedInputs[1]);
        case COMMAND_EDIT:
            checkEditValidity(parsedInputs);
            return new EditCommand(parsedInputs[1]);
        case COMMAND_HELP:
            return new HelpCommand();
        case COMMAND_BYE:
            return new ByeCommand();
        case COMMAND_COMPARE:
            return new CompareCommand(parsedInputs[0]);
        default:
            throw new WhereGotTimeException("Sorry! I don't know what that means. Enter 'help' for a "
                    + "list of valid commands!");
        }
    }
    
    private static void checkEditValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your edit command!");
        }
    }

    private static void checkAddValidity(String[] input) throws WhereGotTimeException {
        try {
            if (input.length < 2) {
                throw new WhereGotTimeException("There is no description in your add command!");
            } else if (!input[1].contains("/")) {
                throw new WhereGotTimeException("An add command needs to be in a"
                        + " 'add /name /day /time /location' format!");
            }

            String[] position = input[1].split(" /", 4);
            if (position[0].isEmpty()) {
                throw new WhereGotTimeException("There is no name in your add command!");
            } else if (position[1].isEmpty()) {
                throw new WhereGotTimeException("There is no day in your add command!");
            } else if (position[2].isEmpty()) {
                throw new WhereGotTimeException("There is no time in your add command!");
            } else if (position[3].isEmpty()) {
                throw new WhereGotTimeException("There is no location in your add command!");
            }

            if (position[1].length() != 3) {
                throw new WhereGotTimeException("\nInvalid day Format \n" 
                        + "Correct format is add /name /day /timeStart-timeEnd /location\n" 
                        + "E.g: add /CS2113 Lec /mon /2000-2100 /LT1");
            }

            if (!position[0].contains("/")) {
                throw new WhereGotTimeException("An add command needs to be in "
                        + "a 'add /name /day /time /location' format!");
            }
        } catch (IndexOutOfBoundsException e) {

            throw new WhereGotTimeException("An add command needs to be in a 'add /name /day /time /location' format!");
        }
    }

    private static void checkListValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your list command!");
        } else if (!input[1].contains("/")) {
            throw new WhereGotTimeException("A list command needs to be in a 'list /day' format!");
        }

        String[] position = input[1].split("/",2);
        if (!position[0].isEmpty()) {
            throw new WhereGotTimeException("Unexpected input found! A list command needs to be in "
                    + "a 'list /day' format.");
        } else if (position[1].isEmpty()) {
            throw new WhereGotTimeException("There is no day in your list command!");
        }
    }

    private static void checkClearValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your clear command!");
        } else if (!input[1].contains("/")) {
            throw new WhereGotTimeException("An clear command needs to be in a 'clear /day' format!");
        }

        String[] position = input[1].split("/",3);
        if (!position[0].isEmpty()) {
            throw new WhereGotTimeException("Unexpected input found! A clear command needs to be in "
                    + "a 'clear /day' format.");
        } else if (position[1].isEmpty()) {
            throw new WhereGotTimeException("There is no day in your clear command!");
        }
    }

    private static void checkDeleteValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your delete command!");
        } else if (!input[1].contains("/")) {
            throw new WhereGotTimeException("A delete command needs to be in a 'delete /day /index' format!");
        }

        try {
            String[] position = input[1].split("/",3);
            if (!position[0].isEmpty()) {
                throw new WhereGotTimeException("Unexpected input found! A delete command needs to be in a"
                        + "'delete /day /index format.");
            } else if (position[1].isEmpty() || position[1].equals(" ")) {
                throw new WhereGotTimeException("There is no day in your delete command!");
            } else if (position[2].isEmpty()) {
                throw new WhereGotTimeException("There is no index in your delete command!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhereGotTimeException("A delete command needs to be in a '/day /index' format!");
        }
    }

    private static void checkRemoveValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your remove command!");
        }
        
        try {
            String[] position = input[1].split("/", 3);
            position[1] = position[1].trim();
    
            if (position[1].isEmpty()) {
                throw new WhereGotTimeException("There is no username in your remove command!");
            } else if (position[2].isEmpty()) {
                throw new WhereGotTimeException("A remove requires a password!");
            } else if (position[2].length() != MAX_PASSWORD_LENGTH) {
                throw new WhereGotTimeException("Password needs to be 6-digits long!");
            } else if (!position[2].matches("[0-9]+")) {
                throw new WhereGotTimeException("Password needs to be a 6-digits number!");
            } else if (!position[1].matches("^[a-zA-Z]*$")) {
                throw new WhereGotTimeException("Username needs to contain one word with only Alphabets!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhereGotTimeException("Wrong format! Please follow the format login /username /password");
        }
    }

    private static void checkLogInValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your login command!");
        }
        
        try {
            String[] position = input[1].split("/", 3);
            position[1] = position[1].trim();
                    
            if (position[1].isEmpty()) {
                throw new WhereGotTimeException("There is no username in your login command!");
            } else if (position[2].isEmpty()) {
                throw new WhereGotTimeException("A login requires a password!");
            } else if (position[2].length() != MAX_PASSWORD_LENGTH) {
                throw new WhereGotTimeException("Password needs to be 6-digits long!");
            } else if (!position[2].matches("[0-9]+")) {
                throw new WhereGotTimeException("Password needs to be a 6-digits number!");
            } else if (!position[1].matches("^[a-zA-Z]*$")) {
                throw new WhereGotTimeException("Username needs to contain one word with only Alphabets!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WhereGotTimeException("Wrong format! Please follow the format login /username /password");
        }
    }

    /**
     * Checks the validity of the KEYWORD for find.
     *
     * @param input user's string input.
     * @throws WhereGotTimeException if the KEYWORD is an empty field.
     */
    private static void checkFindValidity(String[] input) throws WhereGotTimeException {
        if (input.length < 2) {
            throw new WhereGotTimeException("There is no description in your find command!");
        } else if (!input[1].contains("/")) {
            throw new WhereGotTimeException("A find command needs to be in a 'find /keyword' format!");
        }

        String[] position = input[1].split("/",2);
        if (!position[0].isEmpty()) {
            throw new WhereGotTimeException("Unexpected input found! A find command needs to be in "
                    + "a 'find /keyword' format.");
        } else if (position[1].isEmpty()) {
            throw new WhereGotTimeException("There is no keyword in your find command!");
        }
    }
}
