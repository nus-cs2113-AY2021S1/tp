package seedu.duke;

import seedu.duke.commands.*;

public class Parser {

    public static Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case CreateCommand.COMMAND_WORD:
            return new CreateCommand(arguments);
        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommand(arguments);
        case AddCommand.COMMAND_WORD:
            return prepareAddCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand("Invalid Command Format");
        }
    }

    private static Command prepareAddCommand(String arguments) {
        String location;
        if (arguments.contains("l/")) {
            location = arguments.substring(arguments.indexOf("l/") + 2, arguments.indexOf("n/")).trim();
        } else {
            return new InvalidCommand("Follow Add Command Format");
        }
        String name = arguments.substring(arguments.indexOf("n/") + 2, arguments.indexOf("w/")).trim();
        String type = arguments.substring(arguments.indexOf("t/") + 2).trim();
        return new AddCommand(name, location, type);
    }

    private static Command prepareDeleteCommand(String arguments) {
        String name;
        if (arguments.contains("n/")) {
            name = arguments.replace("n/", "");
            return new DeleteCommand(name.trim());
        }
        return new InvalidCommand("Missing n/");
    }

}
