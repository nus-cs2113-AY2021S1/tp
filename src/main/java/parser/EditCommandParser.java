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
import static common.Messages.MESSAGE_ALPHANUMERIC_CHARACTERS;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MESSAGE_NO_QUESTION_AND_ANSWER;
import static common.Messages.MODULE;

//@@author Jane-Ng
public class EditCommandParser {

    private static final String QUESTION_ANSWER_DIVIDER = " \\| ";

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

    private static Command prepareEditModule(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);
            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            if (!ParserUtil.checkAlphanumericOnly(args[1].trim().toLowerCase())) {
                throw new InvalidInputException(String.format(MESSAGE_ALPHANUMERIC_CHARACTERS, MODULE));
            }

            return new EditModuleCommand(editIndex, args[1].trim());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditModuleCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditChapter(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            if (!ParserUtil.checkAlphanumericOnly(args[1].trim().toLowerCase())) {
                throw new InvalidInputException(String.format(MESSAGE_ALPHANUMERIC_CHARACTERS, CHAPTER));
            }

            return new EditChapterCommand(editIndex, args[1].trim());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditChapterCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareEditCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(" ", 2);

            int editIndex = Integer.parseInt(args[0].trim()) - 1;

            String[] questionAndAnswer = args[1].trim().split(QUESTION_ANSWER_DIVIDER, 2);
            String question = ParserUtil.parseQuestion(questionAndAnswer[0], EditCardCommand.MESSAGE_USAGE);
            String answer = ParserUtil.parseAnswer(questionAndAnswer[1], EditCardCommand.MESSAGE_USAGE);

            if (question.isEmpty() && answer.isEmpty()) {
                throw new InvalidInputException(MESSAGE_NO_QUESTION_AND_ANSWER + EditCardCommand.MESSAGE_USAGE);
            }

            return new EditCardCommand(editIndex, question, answer);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.COMMAND_WORD)
                    + EditCardCommand.MESSAGE_USAGE);
        }
    }

}
