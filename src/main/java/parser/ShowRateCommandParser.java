package parser;

import commands.Command;
import commands.ShowRateCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_EXTRA_ARGS;
import static common.Messages.MESSAGE_INVALID_ACCESS;

//@@author gua-guargia
public class ShowRateCommandParser {

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
