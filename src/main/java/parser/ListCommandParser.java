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

/**
 * Parses input arguments and creates a new ListModulesCommand, ListChaptersCommand or ListCardsCommand object.
 */
public class ListCommandParser {

    /**
     * Parses the given arguments in the context of the ListCommand.
     *
     * @param commandArgs input arguments of the command
     * @param accessLevel access level of the user
     * @return a ListModulesCommand, ListChaptersCommand or ListCardsCommand object based on the access level
     * @throws InvalidInputException if the user input is not of the expected format
     * @throws IncorrectAccessLevelException if the command cannot be executed at the access level
     */
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
