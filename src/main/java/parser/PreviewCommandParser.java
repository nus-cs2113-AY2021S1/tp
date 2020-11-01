package parser;

import commands.Command;
import commands.PreviewCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

//@@author Darticune
public class PreviewCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, PreviewCommand.MESSAGE_USAGE));
        }
        return new PreviewCommand();
    }

}
