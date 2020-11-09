package parser;

import commands.Command;
import commands.GoChapterCommand;
import commands.GoCommand;
import commands.GoModuleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import static common.Messages.ADMIN;
import static common.Messages.CHAPTER;
import static common.Messages.MESSAGE_INCORRECT_ACCESS_AT_CHAPTER_LEVEL;
import static common.Messages.MESSAGE_MISSING_INDEX;
import static common.Messages.MESSAGE_NON_INTEGER;
import static common.Messages.MODULE;

//@@author gua-guargia
public class GoCommandParser {
    public Command parse(String commandArgs, String accessLevel)
            throws InvalidInputException, IncorrectAccessLevelException {
        int moduleIndex;
        String type = "";
        String messageUsage = "";
        switch (accessLevel) {
        case ADMIN:
            type = MODULE;
            messageUsage = GoModuleCommand.MESSAGE_USAGE;
            break;
        case MODULE:
            type = CHAPTER;
            messageUsage = GoChapterCommand.MESSAGE_USAGE;
            break;
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS_AT_CHAPTER_LEVEL,
                    GoCommand.COMMAND_WORD));
        }

        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(String.format(MESSAGE_MISSING_INDEX, type)
                    + messageUsage);
        }

        try {
            moduleIndex = Integer.parseInt(commandArgs) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(MESSAGE_NON_INTEGER, type)
                    + messageUsage);
        }

        switch (accessLevel) {
        case ADMIN:
            return new GoModuleCommand(moduleIndex);
        case MODULE:
            return new GoChapterCommand(moduleIndex);
        default:
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INCORRECT_ACCESS_AT_CHAPTER_LEVEL,
                    GoCommand.COMMAND_WORD));
        }
    }

}
