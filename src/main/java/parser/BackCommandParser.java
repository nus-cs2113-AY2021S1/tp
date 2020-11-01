package parser;

import commands.BackAdminCommand;
import commands.BackModuleCommand;
import commands.Command;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MODULE;

//@@author gua-guargia
public class BackCommandParser {
    private static final String MESSAGE_INCORRECT_ACCESS = "Back command can only be called "
            + "at module and chapter level.\n";

    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        switch (accessLevel) {
        case MODULE:
            if (!commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + BackAdminCommand.MESSAGE_USAGE);
            }
            return new BackAdminCommand();
        case CHAPTER:
            if (!commandArgs.isEmpty()) {
                throw new InvalidInputException(MESSAGE_MISSING_ARGS + BackModuleCommand.MESSAGE_USAGE);
            }
            return new BackModuleCommand();
        default:
            throw new IncorrectAccessLevelException(MESSAGE_INCORRECT_ACCESS);
        }
    }

}
