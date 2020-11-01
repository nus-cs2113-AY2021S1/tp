package parser;

import access.Access;

import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.BackAdminCommand;
import commands.BackCommand;
import commands.BackModuleCommand;
import commands.Command;
import commands.EditCardCommand;
import commands.EditChapterCommand;
import commands.EditCommand;
import commands.EditModuleCommand;
import commands.ExcludeCommand;
import commands.ExitCommand;
import commands.GoChapterCommand;
import commands.GoCommand;
import commands.GoModuleCommand;
import commands.HelpCommand;
import commands.HistoryCommand;
import commands.ListCommand;
import commands.ListModulesCommand;
import commands.ListChaptersCommand;
import commands.ListCardsCommand;
import commands.ListDueCommand;
import commands.PreviewCommand;
import commands.RemoveCardCommand;
import commands.RemoveChapterCommand;
import commands.RemoveCommand;
import commands.RemoveModuleCommand;
import commands.RescheduleCommand;
import commands.ReviseCommand;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static common.Messages.ADMIN;
import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_DATE_FORMAT;
import static common.Messages.MESSAGE_EXTRA_ARGS;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_INVALID_COMMAND_TYPE;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MESSAGE_NO_NAME;
import static common.Messages.MESSAGE_NO_QUESTION_AND_ANSWER;
import static common.Messages.MESSAGE_NO_QUESTION_OR_ANSWER;
import static common.Messages.MODULE;

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

    public static String parseQuestionInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.QUESTION_PREFIX))) {
            throw new InvalidFileFormatException("Questions in the file should begin with [Q].");
        }

        String question = arg.substring(3).trim();
        if (question.isEmpty()) {
            throw new InvalidFileFormatException("There should be a question after [Q] in the file.");
        }

        return question;
    }

    public static String parseAnswerInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.ANSWER_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [A].");
        }

        String answer = arg.substring(3).trim();
        if (answer.isEmpty()) {
            throw new InvalidFileFormatException("There should be a answer after [A] in the file.");
        }

        return answer;
    }

    public static String parsePreIntervalInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.PREVIOUS_INTERVAL_PREFIX))) {
            throw new InvalidFileFormatException("Previous intervals in the file should begin with [P].");
        }

        String preInterval = arg.substring(3).trim();
        if (preInterval.isEmpty()) {
            throw new InvalidFileFormatException("There should be a interval after [P] in the file.");
        }

        return preInterval;
    }

    public static String parseTaskNameInFile(String arg) throws InvalidFileFormatException {
        String name = arg.trim();
        if (name.isEmpty()) {
            throw new InvalidFileFormatException("There should be a name of the completed task.");
        }

        return name;
    }

    public static String parsePercentInFile(String arg) throws InvalidFileFormatException {
        String percent = arg.trim().substring(0,3);
        if (percent.isEmpty()) {
            throw new InvalidFileFormatException(
                    "There should be a number to indicate how many tasks have completed.");
        }
        return percent;
    }

    public static String parseRatingInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.RATING_PREFIX))) {
            throw new InvalidFileFormatException("Answers in the file should begin with [R].");
        }

        String rating = arg.substring(3).trim();
        if (rating.isEmpty()) {
            throw new InvalidFileFormatException("There should be a rating after [R] in the file.");
        }

        return rating;
    }

}
