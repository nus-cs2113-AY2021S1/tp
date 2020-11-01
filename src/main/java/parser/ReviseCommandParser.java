package parser;

import commands.Command;
import commands.ReviseCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MODULE;

//@@author neojiaern
public class ReviseCommandParser {

    public Command parse(String commandArgs, String accessLevel)
            throws IncorrectAccessLevelException, InvalidInputException {
        if (!accessLevel.equals(MODULE)) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                   accessLevel, MODULE));
        }

        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, CHAPTER)
                    + ReviseCommand.MESSAGE_USAGE);
        }

        int chapterIndex;
        try {
            chapterIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, CHAPTER)
                    + ReviseCommand.MESSAGE_USAGE);
        }
        return new ReviseCommand(chapterIndex);
    }

}
