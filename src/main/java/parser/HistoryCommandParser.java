package parser;

import commands.Command;
import commands.HistoryCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static common.Messages.MESSAGE_DATE_FORMAT;

/**
 * Parses input arguments and creates a new HistoryCommand object.
 */
public class HistoryCommandParser {

    /**
     * Parses the given arguments in the context of the HistoryCommand.
     *
     * @param commandArgs input arguments of the command
     * @return a HistoryCommand object
     * @throws InvalidInputException if the user input is not of the expected date format
     */
    public Command parse(String commandArgs) throws InvalidInputException {
        if (commandArgs.isEmpty()) {
            LocalDate date = java.time.LocalDate.now();
            commandArgs = date.toString();
        }

        try {
            LocalDate date = LocalDate.parse(commandArgs);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_DATE_FORMAT + HistoryCommand.MESSAGE_USAGE);
        }

        return new HistoryCommand(commandArgs);
    }

}
