package parser;

import commands.Command;
import commands.HelpCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

/**
 * Parses input arguments and creates a new HelpCommand object.
 */
public class HelpCommandParser {

    /**
     * Parses the given arguments in the context of the HelpCommand.
     *
     * @param commandArgs input arguments of the command
     * @return a HelpCommand object
     * @throws InvalidInputException if the user input is not of the expected format
     */
    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, HelpCommand.COMMAND_WORD));
        }
        return new HelpCommand();
    }
}
