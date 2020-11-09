package parser;

import commands.Command;
import commands.RemoveCardCommand;
import commands.RemoveChapterCommand;
import commands.RemoveCommand;
import commands.RemoveModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.ADMIN;
import static common.Messages.CARD;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INCORRECT_ACCESS;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MODULE;

//@@author neojiaern
/**
 * Parses input arguments and creates a new RemoveModuleCommand, RemoveChapterCommand or RemoveCardCommand object.
 */
public class RemoveCommandParser {

    /**
     * Parses the given arguments in the context of the RemoveCommand.
     *
     * @param commandArgs input arguments of the command
     * @param accessLevel access level of the user
     * @return a RemoveModuleCommand, RemoveChapterCommand or RemoveCardCommand object based on the access level
     * @throws InvalidInputException if the user input is not of the expected format
     * @throws IncorrectAccessLevelException if the command cannot be executed at the access level
     */
    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        int removeIndex;
        String messageUsage = "";
        String type = "";
        if (accessLevel.equals(ADMIN)) {
            type = MODULE;
            messageUsage = RemoveModuleCommand.MESSAGE_USAGE;
        } else if (accessLevel.equals(MODULE)) {
            type = CHAPTER;
            messageUsage = RemoveChapterCommand.MESSAGE_USAGE;
        } else if (accessLevel.equals(CHAPTER)) {
            type = CARD;
            messageUsage = RemoveCardCommand.MESSAGE_USAGE;
        } else {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    RemoveCommand.COMMAND_WORD));
        }

        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, type)
                    + messageUsage);
        }

        try {
            removeIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, type)
                    + messageUsage);
        }

        switch (accessLevel) {
        case ADMIN:
            return new RemoveModuleCommand(removeIndex);
        case MODULE:
            return new RemoveChapterCommand(removeIndex);
        case CHAPTER:
            return new RemoveCardCommand(removeIndex);
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS,
                    RemoveCommand.COMMAND_WORD));
        }
    }

}
