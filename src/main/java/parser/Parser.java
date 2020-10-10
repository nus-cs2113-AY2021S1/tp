package parser;

import commands.Command;
import commands.ListCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.BackChapterCommand;
import commands.GoChapterCommand;
import commands.HelpCommand;
import commands.RemoveCommand;
import commands.ReviseCommand;
import commands.ExitCommand;
import commands.GoModuleCommand;
import commands.BackModuleCommand;
import commands.EditCommand;

import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;


public class Parser {
    public static final String QUESTION_ANSWER_PREFIX = " \\| ";
    public static final String QUESTION_PREFIX = "q:";
    public static final String ANSWER_PREFIX = "a:";

    public static Command parse(String fullCommand) throws InvalidInputException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(fullCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return prepareList(commandArgs);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(commandArgs);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemove(commandArgs);
        case ReviseCommand.COMMAND_WORD:
            return prepareRevise(commandArgs);
        case ExitCommand.COMMAND_WORD:
            return prepareExit(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(commandArgs);
        case AddModuleCommand.COMMAND_WORD:
            return prepareAddModule(commandArgs);
        case AddChapterCommand.COMMAND_WORD:
            return prepareAddChapter(commandArgs);
        case BackModuleCommand.COMMAND_WORD:
            return prepareBackModule(commandArgs);
        case BackChapterCommand.COMMAND_WORD:
            return prepareBackChapter(commandArgs);
        case GoModuleCommand.COMMAND_WORD:
            return prepareGoModule(commandArgs);
        case GoChapterCommand.COMMAND_WORD:
            return prepareGoChapter(commandArgs);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(commandArgs);
        default:
            throw new InvalidInputException();
        }
    }

    private static Command prepareGoChapter(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new GoChapterCommand(commandArgs);
    }

    private static Command prepareGoModule(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new GoModuleCommand(commandArgs);
    }

    private static Command prepareBackChapter(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new BackChapterCommand();
    }

    private static Command prepareBackModule(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new BackModuleCommand();
    }

    private static Command prepareAddChapter(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new AddChapterCommand(commandArgs);
    }

    private static Command prepareAddModule(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new AddModuleCommand(commandArgs);
    }

    private static String[] splitCommandTypeAndArgs(String userCommand) {
        String[] commandTypeAndParams = userCommand.trim().split(" ", 2);
        if (commandTypeAndParams.length != 2) {
            commandTypeAndParams = new String[]{commandTypeAndParams[0], ""};
        }
        return commandTypeAndParams;
    }

    private static Command prepareList(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new ListCommand();
    }

    private static Command prepareAdd(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
            if (question.isEmpty() || answer.isEmpty()) {
                throw new InvalidInputException();
            }
            return new AddCommand(question, answer);
        } catch (IndexOutOfBoundsException | InvalidInputException e) {
            throw new InvalidInputException();
        }
    }

    private static Command prepareRemove(String commandArgs) throws InvalidInputException {
        int removeIndex;
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        try {
            removeIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
        return new RemoveCommand(removeIndex);
    }

    private static Command prepareEdit(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException();
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(questionAndAnswer[0]);
            String answer = parseAnswer(questionAndAnswer[1]);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException();
            }

            return new EditCommand(editIndex, question, answer);
        } catch (NumberFormatException | InvalidInputException | IndexOutOfBoundsException e) {
            throw new InvalidInputException();
        }
    }

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException();
        }

        return arg.substring(2).trim();
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException();
        }

        return arg.substring(2).trim();
    }

    private static Command prepareRevise(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new ReviseCommand(commandArgs);
    }

    private static Command prepareExit(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new ExitCommand();
    }

    private static Command prepareHelp(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new HelpCommand();
    }

    public static String parseQuestioninFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.QUESTION_PREFIX))) {
            throw new InvalidFileFormatException();
        }

        String question = arg.substring(3).trim();
        if (question.isEmpty()) {
            throw new InvalidFileFormatException();
        }

        return question;
    }

    public static String parseAnswerinFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.ANSWER_PREFIX))) {
            throw new InvalidFileFormatException();
        }

        String answer = arg.substring(3).trim();
        if (answer.isEmpty()) {
            throw new InvalidFileFormatException();
        }

        return answer;
    }
}
