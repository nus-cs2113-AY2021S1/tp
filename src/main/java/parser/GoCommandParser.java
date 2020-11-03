package parser;

import commands.Command;
import commands.GoChapterCommand;
import commands.GoCommand;
import commands.GoModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_SPECIAL_CHARACTER;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MODULE;

//@@author gua-guargia
public class GoCommandParser {
    private static final String MESSAGE_INCORRECT_ACCESS = "Go command can only be called at admin and module level.\n";

    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (!checkSpecialCharacter(commandArgs)) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_SPECIAL_CHARACTER, GoCommand.COMMAND_WORD));
        }
        switch (accessLevel) {
        case ADMIN:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + GoModuleCommand.MESSAGE_USAGE);
            }
            return new GoModuleCommand(commandArgs);
        case MODULE:
            if (commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + GoChapterCommand.MESSAGE_USAGE);
            }
            return new GoChapterCommand(commandArgs);
        default:
            throw new IncorrectAccessLevelException(MESSAGE_INCORRECT_ACCESS);
        }
    }

    private static boolean checkSpecialCharacter(String commandArgs) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(commandArgs);
        boolean includeSpecialCharacter = matcher.matches();
        return includeSpecialCharacter;
    }

}
