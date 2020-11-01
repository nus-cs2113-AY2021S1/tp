package parser;

import access.Access;
import commands.AddCommand;
import commands.BackCommand;
import commands.Command;
import commands.EditCommand;
import commands.ExcludeCommand;
import commands.ExitCommand;
import commands.GoCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.ListCommand;
import commands.ListDueCommand;
import commands.PreviewCommand;
import commands.RemoveCommand;
import commands.RescheduleCommand;
import commands.ReviseCommand;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_INVALID_COMMAND_TYPE;

public class Parser {

    public static Command parse(String fullCommand, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(fullCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();

        String accessLevel = access.getAccessLevel();

        System.out.println("Command Type: " + commandType);

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(commandArgs, accessLevel);

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(commandArgs, accessLevel);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(commandArgs, accessLevel);

        case ReviseCommand.COMMAND_WORD:
            return new ReviseCommandParser().parse(commandArgs, accessLevel);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(commandArgs);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(commandArgs);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(commandArgs, accessLevel);

        case BackCommand.COMMAND_WORD:
            return new BackCommandParser().parse(commandArgs, accessLevel);

        case GoCommand.COMMAND_WORD:
            return new GoCommandParser().parse(commandArgs, accessLevel);

        case ListDueCommand.COMMAND_WORD:
            return new ListDueCommandParser().parse(commandArgs);

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommandParser().parse(commandArgs);

        case ShowRateCommand.COMMAND_WORD:
            return new ShowRateCommandParser().parse(commandArgs, accessLevel);

        case PreviewCommand.COMMAND_WORD:
            return new PreviewCommandParser().parse(commandArgs);

        case ExcludeCommand.COMMAND_WORD:
            return new ExcludeCommandParser().parse(commandArgs);

        case RescheduleCommand.COMMAND_WORD:
            return new RescheduleCommandParser().parse(commandArgs, accessLevel);

        default:
            throw new InvalidInputException(MESSAGE_INVALID_COMMAND_TYPE);
        }
    }

    private static String[] splitCommandTypeAndArgs(String userCommand) {
        String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
        if (commandTypeAndParams.length != 2) {
            commandTypeAndParams = new String[]{commandTypeAndParams[0], ""};
        }
        return commandTypeAndParams;
    }

}
