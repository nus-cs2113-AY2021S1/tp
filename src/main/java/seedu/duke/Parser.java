package seedu.duke;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.CreateCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.InvalidCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.OffCommand;
import seedu.duke.commands.OnCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.commands.UsageCommand;

public class Parser {

    public static Command parseCommand(String userInput) {
        String[] words = userInput.trim().split(" ", 2);
        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case CreateCommand.COMMAND_WORD:
            return prepareCreateCommand(arguments);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemoveCommand(arguments);
        case AddCommand.COMMAND_WORD:
            return prepareAddCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);
        case OnCommand.COMMAND_WORD:
            return prepareOnCommand(arguments);
        case OffCommand.COMMAND_WORD:
            return prepareOffCommand(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case UsageCommand.COMMAND_WORD:
            return new UsageCommand();
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
        String power = arguments.substring(arguments.indexOf("w/") + 2, arguments.indexOf("t/")).trim();
        String type = arguments.substring(arguments.indexOf("t/") + 2).trim();
        if (powerIsNumeric(power)) {
            return new AddCommand(name, location, power, type);
        } else {
            return new InvalidCommand("Power is not in number");
        }
    }

    private static boolean powerIsNumeric(String power) {
        try {
            Double.parseDouble(power);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numerical value for power.");
            return false;
        }
    }

    private static Command prepareDeleteCommand(String arguments) {
        String name;
        if (arguments.contains("n/")) {
            name = arguments.replace("n/", "");
            return new DeleteCommand(name.trim());
        }
        return new InvalidCommand("Missing n/");
    }

    private static Command prepareCreateCommand(String arguments) {
        String location;
        if (arguments.contains("l/")) {
            location = arguments.replace("l/", "");
            return new CreateCommand(location);
        }
        return new InvalidCommand("Missing l/");
    }

    private static Command prepareRemoveCommand(String arguments) {
        String location;
        if (arguments.contains("l/")) {
            location = arguments.replace("l/", "");
            return new RemoveCommand(location);
        }
        return new InvalidCommand("Missing l/");
    }

    private static Command prepareOnCommand(String arguments) {
        String name;
        if (arguments.contains("n/")) {
            name = arguments.replace("n/", "");
            return new OnCommand(name);
        }
        return new InvalidCommand("Missing n/");
    }

    private static Command prepareOffCommand(String arguments) {
        String name;
        if (arguments.contains("n/")) {
            name = arguments.replace("n/", "");
            return new OffCommand(name);
        }
        return new InvalidCommand("Missing n/");
    }

}
