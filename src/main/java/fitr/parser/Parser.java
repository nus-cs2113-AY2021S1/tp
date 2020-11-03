package fitr.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fitr.command.AddFoodCommand;
import fitr.command.ClearCommand;
import fitr.command.Command;
import fitr.command.CompleteGoalCommand;
import fitr.command.InvalidCommand;
import fitr.command.AddExerciseCommand;
import fitr.command.ViewCommand;
import fitr.command.RecommendCommand;
import fitr.command.HelpCommand;
import fitr.command.DeleteCommand;
import fitr.command.ExitCommand;
import fitr.command.AddGoalCommand;
import fitr.common.Commands;
import fitr.ui.Ui;

/**
 * Parses the user input.
 */
public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses the user input and return a corresponding command.
     *
     * @param userInput String of user input
     * @return a Command object
     */
    public static Command parse(String userInput) {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            return new InvalidCommand(userInput);
        }

        String userCommand = matcher.group("command").trim();
        String arguments = matcher.group("arguments").trim();

        switch (userCommand.toLowerCase()) {
        case Commands.COMMAND_FOOD:
            return new AddFoodCommand(arguments);
        case Commands.COMMAND_EXERCISE:
            return new AddExerciseCommand(arguments);
        case Commands.COMMAND_VIEW:
            return new ViewCommand(arguments);
        case Commands.COMMAND_RECOMMEND:
            if(arguments.length() != 0) {
                return new InvalidCommand("Extra parameters");
            }
            return new RecommendCommand();
        case Commands.COMMAND_EDIT:
            return new EditCommandParser(arguments).editCommand();
        case Commands.COMMAND_HELP:
            return new HelpCommand(arguments);
        case Commands.COMMAND_DELETE:
            return new DeleteCommand(arguments);
        case Commands.COMMAND_CLEAR:
            return new ClearCommand(arguments);
        case Commands.COMMAND_BYE:
            return new ExitCommand(arguments);
        case Commands.COMMAND_GOAL:
            return new AddGoalCommand(arguments);
        case Commands.COMMAND_COMPLETE:
            return new CompleteGoalCommand(arguments);
        default:
            return new InvalidCommand(userCommand);
        }
    }

}
