package parser;

import commands.Command;
import commands.ExitCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

//@@author Jane-Ng
/**
 * Parses input arguments and creates a new ExitCommand object.
 */
public class ExitCommandParser {

    /**
     * Parses the given arguments in the context of the ExitCommand.
     *
     * @param commandArgs input arguments of the command
     * @return an ExitCommand object
     * @throws InvalidInputException if the user input is not of the expected format
     */
    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ExitCommand.COMMAND_WORD));
        }
        return new ExitCommand();
    }

}
