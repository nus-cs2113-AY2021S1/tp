package parser;

import commands.Command;
import commands.ListCardsCommand;
import commands.ListChaptersCommand;
import commands.ListCommand;
import commands.ListModulesCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_EXTRA_ARGS;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MODULE;

public class ListCommandParser {

    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ListCommand.COMMAND_WORD));
        }

        switch (accessLevel) {
        case ADMIN:
            return new ListModulesCommand();
        case MODULE:
            return new ListChaptersCommand();
        case CHAPTER:
            return new ListCardsCommand();
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    ListCommand.COMMAND_WORD));
        }
    }

}
