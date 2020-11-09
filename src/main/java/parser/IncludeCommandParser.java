package parser;

import commands.Command;
import commands.IncludeCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_MISSING_ARGS;

//@@author Darticune
public class IncludeCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + IncludeCommand.MESSAGE_USAGE);
        }
        return new IncludeCommand(commandArgs);
    }

}
