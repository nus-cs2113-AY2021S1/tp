package parser;

import commands.Command;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_EXTRA_ARGS;
import static common.Messages.MESSAGE_INVALID_ACCESS;

//@@author gua-guargia

/**
 * Parses input arguments and creates a new ShowRateCommand object.
 */
public class ShowRateCommandParser {

    /**
     * Parses the given arguments in the context of the ShowRateCommand.
     *
     * @param commandArgs input arguments of the command
     * @param accessLevel access level of the user
     * @return an ShowRateCommand object
     * @throws InvalidInputException if the user input is not of the expected format
     * @throws IncorrectAccessLevelException if the access level is not at chapter level
     */
    public Command parse(String commandArgs, String accessLevel)
            throws IncorrectAccessLevelException, InvalidInputException {
        if (!accessLevel.equals(CHAPTER)) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    accessLevel, CHAPTER));
        }

        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ShowRateCommand.COMMAND_WORD));
        }

        return new ShowRateCommand();
    }

}
