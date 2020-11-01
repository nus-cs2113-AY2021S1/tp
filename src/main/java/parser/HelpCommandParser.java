package parser;

import commands.Command;
import commands.HelpCommand;
import exception.InvalidInputException;

import static common.Messages.MESSAGE_EXTRA_ARGS;

//@@author Zhu-Ze-Yu
public class HelpCommandParser {

    public Command parse(String commandArgs) throws InvalidInputException {
        if (!commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_EXTRA_ARGS, HelpCommand.COMMAND_WORD));
        }
        return new HelpCommand();
    }
}
