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
/**
 * Parses input arguments and creates a new ReviseCommand.
 */
public class ReviseCommandParser {

    /**
     * Parses the given arguments in the context of the ReviseCommand.
     *
     * @param commandArgs input arguments of the command
     * @param accessLevel access level of the user
     * @return a ReviseCommand
     * @throws IncorrectAccessLevelException if the user input is not of the expected format
     * @throws InvalidInputException if the command cannot be executed at the access levels
     */
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
