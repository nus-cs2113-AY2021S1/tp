package parser;

import commands.Command;
import commands.ListCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.BackChapterCommand;
import commands.GoChapterCommand;
import commands.HelpCommand;
import commands.ReviseCommand;
import commands.ExitCommand;
import commands.GoModuleCommand;
import commands.BackModuleCommand;

import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;


public class Parser {
    public static Command parse(String fullCommand) throws InvalidInputException {
        String[] commandTypeAndArgs = splitCommandTypeAndArgs(fullCommand);
        String commandType = commandTypeAndArgs[0].trim().toLowerCase();
        String commandArgs = commandTypeAndArgs[1].trim();

        switch (commandType) {
        case ListCommand.COMMAND_WORD:
            return prepareList(commandArgs);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(commandArgs);
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
            String[] args = commandArgs.split(AddCommand.QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
            return new AddCommand(question, answer);
        } catch (IndexOutOfBoundsException | InvalidInputException e) {
            throw new InvalidInputException();
        }
    }

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(AddCommand.QUESTION_PREFIX))) {
            throw new InvalidInputException();
        }

        String question = arg.substring(2).trim();
        if (question.isEmpty()) {
            throw new InvalidInputException();
        }

        return question;
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(AddCommand.ANSWER_PREFIX))) {
            throw new InvalidInputException();
        }

        String answer = arg.substring(2).trim();
        if (answer.isEmpty()) {
            throw new InvalidInputException();
        }

        return answer;
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
