package parser;

import access.Access;

import commands.AddCommand;
import commands.BackCommand;
import commands.Command;
import commands.EditCommand;
import commands.ExitCommand;
import commands.GoCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.ListDueCommand;
import commands.RemoveCommand;
import commands.ReviseCommand;

import exception.IncorrectAccessLevelException;
import exception.InvalidFileFormatException;
import exception.InvalidInputException;
import storage.Storage;

public class Parser {
    private static final String QUESTION_ANSWER_PREFIX = " \\| ";
    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";

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
            return prepareAdd(commandArgs, access);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemove(commandArgs);
        case ReviseCommand.COMMAND_WORD:
            return prepareRevise(commandArgs, access);
        case ExitCommand.COMMAND_WORD:
            return prepareExit(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(commandArgs);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(commandArgs, access);
        case BackCommand.COMMAND_WORD:
            return prepareBack(commandArgs);
        case GoCommand.COMMAND_WORD:
            return prepareGo(commandArgs);
        case ListDueCommand.COMMAND_WORD:
            return prepareListDue(commandArgs);
        default:
            throw new InvalidInputException("There is no such command type.\n");
        }
    }

    private static Command prepareGo(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new GoCommand(commandArgs);
    }

    private static Command prepareBack(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new BackCommand();
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

    private static Command prepareAdd(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isChapterLevel()) {
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException("The arguments are missing.\n"
                        + AddCommand.CARD_MESSAGE_USAGE);
            }
            return prepareAddCard(commandArgs);
        } else if (access.isModuleLevel()) {
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException("The arguments are missing.\n"
                        + AddCommand.CHAPTER_MESSAGE_USAGE);
            }
            return prepareAddChapter(commandArgs);
        } else if (access.isAdminLevel()) {
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException("The arguments are missing.\n"
                        + AddCommand.MODULE_MESSAGE_USAGE);
            }
            return prepareAddModule(commandArgs);
        } else {
            assert !access.isChapterLevel() && !access.isAdminLevel() && !access.isModuleLevel() : access.getLevel();
            throw new IncorrectAccessLevelException("Add command can only be called at admin, "
                    + "module and chapter level.");
        }
    }

    private static Command prepareAddCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
            if (question.isEmpty() || answer.isEmpty()) {
                throw new InvalidInputException("The content for question / answer is empty.\n"
                        + AddCommand.CARD_MESSAGE_USAGE);
            }
            return new AddCommand(question, answer, CHAPTER_LEVEL);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The format for the add command is incorrect.\n"
                    + AddCommand.CARD_MESSAGE_USAGE);
        }
    }

    private static Command prepareAddChapter(String commandArgs) {
        return new AddCommand(commandArgs);
    }

    private static Command prepareAddModule(String commandArgs) {
        return new AddCommand(commandArgs);
    }

    private static Command prepareRemove(String commandArgs) throws InvalidInputException {
        int removeIndex;
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException("The index for module / chapter / flashcard is missing.\n"
                    + RemoveCommand.MESSAGE_USAGE);
        }
        try {
            removeIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The index for module / chapter / flashcard should be an integer.\n"
                    + RemoveCommand.MESSAGE_USAGE);
        }
        return new RemoveCommand(removeIndex);
    }

    private static Command prepareEdit(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isChapterLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException("The arguments are missing.\n"
                    + EditCommand.CARD_MESSAGE_USAGE);
        }
        if (access.isAdminLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException("The arguments are missing.\n"
                    + EditCommand.MODULE_MESSAGE_USAGE);
        }
        if (access.isModuleLevel() && commandArgs.isEmpty()) {
            throw new InvalidInputException("The arguments are missing.\n"
                    + EditCommand.CHAPTER_MESSAGE_USAGE);
        }


        if (access.isChapterLevel()) {
            return prepareEditCard(commandArgs);
        } else if (access.isAdminLevel()) {
            return prepareEditModule(commandArgs);
        } else if (access.isModuleLevel()) {
            return prepareEditChapter(commandArgs);
        } else {
            assert !access.isChapterLevel() && !access.isAdminLevel() && !access.isModuleLevel() : access.getLevel();
            throw new IncorrectAccessLevelException("Edit command can only be called at admin, "
                    + "module and chapter level.");
        }
    }

    private static Command prepareEditCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException("The flashcard number is missing.\n"
                        + EditCommand.CARD_MESSAGE_USAGE);
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_PREFIX, 2);
            String question = parseQuestion(questionAndAnswer[0]);
            String answer = parseAnswer(questionAndAnswer[1]);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException("The content for question and answer are both empty.\n"
                        + EditCommand.CARD_MESSAGE_USAGE);
            }

            return new EditCommand(editIndex, question, answer, CHAPTER_LEVEL);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The flashcard number needs to be an integer.\n"
                    + EditCommand.CARD_MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The format for the edit command is incorrect.\n"
                    + EditCommand.CARD_MESSAGE_USAGE);
        }
    }

    private static Command prepareEditModule(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException("The module number is missing.\n"
                        + EditCommand.MODULE_MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException("The module name is missing.\n"
                        + EditCommand.MODULE_MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException("This command should be called at chapter level only.\n");
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditCommand(editIndex, args[1].trim().toLowerCase(), ADMIN_LEVEL);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The module number needs to be an integer.\n"
                    + EditCommand.MODULE_MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The format for the edit command is incorrect.\n"
                    + EditCommand.MODULE_MESSAGE_USAGE);
        }
    }

    private static Command prepareEditChapter(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException("The chapter number is missing.\n"
                        + EditCommand.CHAPTER_MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException("The chapter name is missing.\n"
                        + EditCommand.CHAPTER_MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException("This command should be called at chapter level only.\n");
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditCommand(editIndex, args[1].trim().toLowerCase(), MODULE_LEVEL);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("The chapter number needs to be an integer.\n"
                    + EditCommand.CHAPTER_MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The format for the edit command is incorrect.\n"
                    + EditCommand.CHAPTER_MESSAGE_USAGE);
        }
    }

    private static boolean containsCardPrefix(String arg) {
        return arg.contains("q:") || arg.contains("a:");
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

    private static Command prepareRevise(String commandArgs, Access access)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (access.isAdminLevel() || access.isChapterLevel()) {
            throw new IncorrectAccessLevelException("Revise command can only be called at module level.\n");
        } else if (commandArgs.isEmpty()) {
            throw new InvalidInputException("The index for chapter to revise is missing.\n"
                    + ReviseCommand.MESSAGE_USAGE);
        }
        int chapterIndex;
        try {
            chapterIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectAccessLevelException("The index for chapter should be an integer.\n"
                    + ReviseCommand.MESSAGE_USAGE);
        }
        return new ReviseCommand(chapterIndex);
    }

    private static Command prepareExit(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException("There should not be any arguments for exit.");
        }
        return new ExitCommand();
    }

    private static Command prepareHelp(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException();
        }
        return new HelpCommand();
    }

    public static String parseQuestionInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.QUESTION_PREFIX))) {
            throw new InvalidFileFormatException();
        }

        String question = arg.substring(3).trim();
        if (question.isEmpty()) {
            throw new InvalidFileFormatException();
        }

        return question;
    }

    public static String parseAnswerInFile(String arg) throws InvalidFileFormatException {
        if (!(arg.trim().startsWith(Storage.ANSWER_PREFIX))) {
            throw new InvalidFileFormatException();
        }

        String answer = arg.substring(3).trim();
        if (answer.isEmpty()) {
            throw new InvalidFileFormatException();
        }

        return answer;
    }

    private static Command prepareListDue(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException("There should not be any arguments for list.");
        }
        return new ListDueCommand();
    }

}


