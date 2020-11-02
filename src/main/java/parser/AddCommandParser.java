package parser;

import commands.AddCardCommand;
import commands.AddChapterCommand;
import commands.AddCommand;
import commands.AddModuleCommand;
import commands.Command;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MESSAGE_NO_QUESTION_OR_ANSWER;
import static common.Messages.MODULE;
import static common.Messages.MESSAGE_INVALID_SPECIAL_CHARACTER;

public class AddCommandParser {

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
        try {
            if (!checkSpecialCharacter(commandArgs)) {
                throw new InvalidInputException();
            }
            return new AddModuleCommand(commandArgs);
        } catch (InvalidInputException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_SPECIAL_CHARACTER, MODULE));
        }
    }

    private static Command prepareAddChapter(String commandArgs) throws InvalidInputException {
        try {
            if (!checkSpecialCharacter(commandArgs)) {
                throw new InvalidInputException();
            }
            return new AddChapterCommand(commandArgs);
        } catch (InvalidInputException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_SPECIAL_CHARACTER, CHAPTER));
        }
    }

    private static boolean checkSpecialCharacter(String commandArgs) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(commandArgs);
        boolean includeSpecialCharacter = matcher.matches();
        return includeSpecialCharacter;
    }

    //@@author Jane-Ng
    private static Command prepareAddCard(String commandArgs) throws InvalidInputException {
        try {
            String[] args = commandArgs.split(QUESTION_ANSWER_DIVIDER, 2);
            String question = parseQuestion(args[0]);
            String answer = parseAnswer(args[1]);
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

    private static String parseQuestion(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(QUESTION_PREFIX))) {
            throw new InvalidInputException(MESSAGE_QUESTION_PREFIX + AddCardCommand.MESSAGE_USAGE);
        }

        return arg.substring(2).trim();
    }

    private static String parseAnswer(String arg) throws InvalidInputException {
        if (!(arg.trim().toLowerCase().startsWith(ANSWER_PREFIX))) {
            throw new InvalidInputException(MESSAGE_ANSWER_PREFIX + AddCardCommand.MESSAGE_USAGE);
        }

        return arg.substring(2).trim();
    }

}
