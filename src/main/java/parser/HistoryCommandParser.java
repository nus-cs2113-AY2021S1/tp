package parser;

import commands.Command;
import commands.HistoryCommand;
import exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static common.Messages.MESSAGE_DATE_FORMAT;

//@@author Zhu-Ze-Yu
public class HistoryCommandParser {

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
