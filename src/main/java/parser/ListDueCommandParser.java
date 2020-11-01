package parser;

import commands.Command;
import commands.ListDueCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

//@@author Darticune
public class ListDueCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, ListDueCommand.COMMAND_WORD));
        }
        return new ListDueCommand();
    }

}
