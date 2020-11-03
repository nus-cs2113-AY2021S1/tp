package parser;

import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.Command;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_ALPHANUMERIC_CHARACTERS;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_NO_QUESTION_OR_ANSWER;
import static common.Messages.MODULE;

public class AddCommandParser {

    private static final String QUESTION_ANSWER_DIVIDER = " \\| ";

    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        switch (accessLevel) {
        case ADMIN:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddModuleCommand.MESSAGE_USAGE);
            }
            return prepareAddModule(commandArgs);
        case MODULE:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddChapterCommand.MESSAGE_USAGE);
            }
            return prepareAddChapter(commandArgs);
        case CHAPTER:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + AddCardCommand.MESSAGE_USAGE);
            }
            return prepareAddCard(commandArgs);
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    AddCommand.COMMAND_WORD));
        }
    }

    //@@author gua-guargia
    private static Command prepareAddModule(String commandArgs) throws InvalidInputException {
        if (!ParserUtil.checkAlphanumericOnly(commandArgs)) {
            throw new InvalidInputException(String.format(MESSAGE_ALPHANUMERIC_CHARACTERS, MODULE));
        }
        return new AddModuleCommand(commandArgs);
    }

    private static Command prepareAddChapter(String commandArgs) throws InvalidInputException {
        if (!ParserUtil.checkAlphanumericOnly(commandArgs)) {
            throw new InvalidInputException(String.format(MESSAGE_ALPHANUMERIC_CHARACTERS, CHAPTER));
        }
        return new AddChapterCommand(commandArgs);
    }

    //@@author Jane-Ng
    private static Command prepareAddCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_DIVIDER, 2);
            String question = ParserUtil.parseQuestion(args[0], AddCardCommand.MESSAGE_USAGE);
            String answer = ParserUtil.parseAnswer(args[1], AddCardCommand.MESSAGE_USAGE);
            if (question.isEmpty() || answer.isEmpty()) {
                throw new InvalidInputException(MESSAGE_NO_QUESTION_OR_ANSWER
                        + AddCardCommand.MESSAGE_USAGE);
            }
            return new AddCardCommand(question, answer);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.COMMAND_WORD)
                    + AddCardCommand.MESSAGE_USAGE));
        }
    }

}
