package parser;

import commands.Command;
import commands.ExcludeCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_MISSING_ARGS;

//@@author Darticune
public class ExcludeCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + ExcludeCommand.MESSAGE_USAGE);
        }
        return new ExcludeCommand(commandArgs);
    }

}
