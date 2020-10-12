package parser;

import access.Access;
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

import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;

import java.time.LocalDate;


public class Parser {
    private static final String QUESTION_ANSWER_DUE_DATE_PREFIX = " \\| ";
    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";
    private static final String DUE_DATE_PREFIX= "d:";

    private static final String ADMIN_LEVEL = "admin";
    private static final String MODULE_LEVEL = "module";
    private static final String CHAPTER_LEVEL = "chapter";

    public static Command parse(String fullCommand, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
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
            return prepareEdit(commandArgs, access);
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
            throw new InvalidInputException("There should not be any arguments for list.");
        }
        return new ListCommand();
    }

    private static Command prepareAdd(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_DUE_DATE_PREFIX, 3);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
            String dueDate = parseDueDate(args[2]);
            if (question.isEmpty() || answer.isEmpty() || dueDate.isEmpty()) {
                throw new InvalidInputException();
            }
            LocalDate dueBy = LocalDate.parse(dueDate);
            return new AddCommand(question, answer, dueBy);
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

    private static Command prepareEdit(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            return prepareEditCard(commandArgs);
        } else {
            throw new IncorrectAccessLevelException("Edit command can only be called at admin, "
                    + "module and chapter level.\n");
        }
    }

    private static Command prepareEditCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException("The flashcard number is missing.\n"
                        + EditCommand.MESSAGE_USAGE);
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_DUE_DATE_PREFIX, 2);
            String question = parseQuestion(questionAndAnswer[0]);
            String answer = parseAnswer(questionAndAnswer[1]);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException("The content for question and answer are both empty.\n"
                        + EditCommand.MESSAGE_USAGE);
            }

            return new EditCommand(editIndex, question, answer, CHAPTER_LEVEL);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The flashcard number needs to be an integer.\n"
                    + EditCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The format for the edit command is incorrect.\n"
                    + EditCommand.MESSAGE_USAGE);
        }
    }

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException("There needs to be a \"q:\" prefix before the question.\n"
                    + "Example: " + AddCommand.COMMAND_WORD + AddCommand.CARD_PARAMETERS + "\n"
                    + "         " + EditCommand.COMMAND_WORD + EditCommand.CARD_PARAMETERS);
        }

        return arg.substring(2).trim();
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException("There needs to be a \"a:\" prefix before the answer.\n"
                    + "Example: " + AddCommand.COMMAND_WORD + AddCommand.CARD_PARAMETERS + "\n"
                    + "         " + EditCommand.COMMAND_WORD + EditCommand.CARD_PARAMETERS);
        }

        return arg.substring(2).trim();
    }

    private static String parseDueDate(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(DUE_DATE_PREFIX))) {
            throw new InvalidInputException("There needs to be a \"d:\" prefix before the due date.\n"
                    + "Example: " + AddCommand.COMMAND_WORD + AddCommand.CARD_PARAMETERS + "\n"
                    + "         " + EditCommand.COMMAND_WORD + EditCommand.CARD_PARAMETERS);
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

    public static String parseDueDateinFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.DUE_DATE_PREFIX))) {
            throw new InvalidFileFormatException();
        }

        String dueDate = arg.substring(3).trim();
        if (dueDate.isEmpty()) {
            throw new InvalidFileFormatException();
        }

        return dueDate;
    }
}
