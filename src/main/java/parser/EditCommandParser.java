package parser;

import commands.Command;
import commands.EditCardCommand;
import commands.EditChapterCommand;
import commands.EditCommand;
import commands.EditModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.ADMIN;
import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MESSAGE_NO_NAME;
import static common.Messages.MESSAGE_NO_QUESTION_AND_ANSWER;
import static common.Messages.MODULE;

//@@author Jane-Ng
public class EditCommandParser {

    private static final String QUESTION_ANSWER_DIVIDER = " \\| ";
    private static final String QUESTION_PREFIX = "q:";
    private static final String ANSWER_PREFIX = "a:";

    private static final String MESSAGE_QUESTION_PREFIX = "There needs to be a \"q:\" prefix before the question.\n";
    private static final String MESSAGE_ANSWER_PREFIX = "There needs to be a \"a:\" prefix before the answer.\n";

    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        switch (accessLevel) {
        case ADMIN:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditModuleCommand.MESSAGE_USAGE);
            }
            return prepareEditModule(commandArgs);
        case MODULE:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditChapterCommand.MESSAGE_USAGE);
            }
            return prepareEditChapter(commandArgs);
        case CHAPTER:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + EditCardCommand.MESSAGE_USAGE);
            }
            return prepareEditCard(commandArgs);
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    EditCommand.COMMAND_WORD));
        }
    }

    private static Command prepareEditModule(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, MODULE)
                        + EditModuleCommand.MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_NO_NAME, MODULE)
                        + EditModuleCommand.MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                        ADMIN, CHAPTER));
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditModuleCommand(editIndex, args[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, MODULE)
                    + EditModuleCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditModuleCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditChapter(String commandArgs)
            throws InvalidInputException, IncorrectAccessLevelException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CHAPTER)
                        + EditChapterCommand.MESSAGE_USAGE);
            }

            if (args[1].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_NO_NAME, CHAPTER)
                        + EditChapterCommand.MESSAGE_USAGE);
            }

            if (containsCardPrefix(args[1].trim().toLowerCase())) {
                throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                        MODULE, CHAPTER));
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;
            return new EditChapterCommand(editIndex, args[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + EditChapterCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditChapterCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            if (args[0].trim().isEmpty()) {
                throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CARD)
                        + EditCardCommand.MESSAGE_USAGE);
            }

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_DIVIDER, 2);
            String question = parseQuestion(questionAndAnswer[0]);
            String answer = parseAnswer(questionAndAnswer[1]);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException(MESSAGE_NO_QUESTION_AND_ANSWER + EditCardCommand.MESSAGE_USAGE);
            }

            return new EditCardCommand(editIndex, question, answer);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CARD)
                    + EditCardCommand.MESSAGE_USAGE);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditCardCommand.MESSAGE_USAGE);
        }
    }

    private static boolean containsCardPrefix(String arg) {
        return arg.contains(QUESTION_PREFIX) || arg.contains(ANSWER_PREFIX);
    }

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException(MESSAGE_QUESTION_PREFIX + EditCardCommand.MESSAGE_USAGE);
        }

        return arg.substring(2).trim();
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException(MESSAGE_ANSWER_PREFIX + EditCardCommand.MESSAGE_USAGE);
        }

        return arg.substring(2).trim();
    }

}
