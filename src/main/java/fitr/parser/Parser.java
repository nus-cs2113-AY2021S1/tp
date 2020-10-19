package fitr.parser;

import fitr.command.AddExerciseCommand;
import fitr.command.AddFoodCommand;
import fitr.command.ClearCommand;
import fitr.command.Command;
import fitr.command.DeleteCommand;
import fitr.command.ExitCommand;
import fitr.command.HelpCommand;
import fitr.command.InvalidCommand;
import fitr.command.ViewCommand;
import fitr.command.EditProfileCommand;
import fitr.common.Commands;

public class Parser {
    public static Command parse(String userInput) {
        String[] fullCommand = userInput.split("\\s+");
        switch (fullCommand[0].toLowerCase()) {
        case "food":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new AddFoodCommand(userInput);
        case "exercise":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new AddExerciseCommand(userInput);
        case "view":
            return new ViewCommand(userInput);
        case "edit":
            return new EditProfileCommand(userInput);
        case "help":
            return new HelpCommand(userInput);
        case "delete":
            if (fullCommand.length == 1) {
                return new InvalidCommand(userInput);
            }
            return new DeleteCommand(userInput);
        case Commands.COMMAND_CLEAR:
            return new ClearCommand(userInput);
        case "bye":
            return new ExitCommand(userInput);
        default:
            return new InvalidCommand(userInput);
        }
    }
}
