package parser;

import commands.Command;
import commands.RescheduleCommand;
import exception.IncorrectAccessLevelException;
import exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static common.Messages.MESSAGE_DATE_FORMAT;
import static common.Messages.MESSAGE_INVALID_ACCESS;
import static common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static common.Messages.MESSAGE_MISSING_ARGS;
import static common.Messages.MODULE;

//@@author Jane-Ng
public class RescheduleCommandParser {
    private static final String MESSAGE_NO_DUE_DATE = "The due date is missing.\n";
    private static final String MESSAGE_DUE_DATE_BEFORE_TODAY = "You cannot enter a due date that is before today.\n";

    public Command parse(String commandArgs, String accessLevel)
            throws IncorrectAccessLevelException, InvalidInputException {
        if (!accessLevel.equals(MODULE)) {
            throw new IncorrectAccessLevelException(String.format(MESSAGE_INVALID_ACCESS,
                    accessLevel, MODULE));
        }

        if (commandArgs.isEmpty()) {
            throw new InvalidInputException(MESSAGE_MISSING_ARGS + RescheduleCommand.MESSAGE_USAGE);
        }

        try {
            String[] args = commandArgs.split(" ", 2);
            int index = Integer.parseInt(args[0].trim()) - 1;
            LocalDate dueDate = LocalDate.parse(args[1].trim());
            if (dueDate.isBefore(LocalDate.now())) {
                throw new InvalidInputException(MESSAGE_DUE_DATE_BEFORE_TODAY);
            }
            return new RescheduleCommand(index, dueDate);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RescheduleCommand.COMMAND_WORD) + RescheduleCommand.MESSAGE_USAGE);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(MESSAGE_DATE_FORMAT + RescheduleCommand.MESSAGE_USAGE);
        }
    }

}
