package parser;

import commands.Command;
import commands.ExitCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

//@@author Jane-Ng
public class ExitCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ExitCommand.COMMAND_WORD));
        }
        return new ExitCommand();
    }

}
