package seedu.rex.parser;

import seedu.rex.commands.AddCommand;
import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Patient;

import java.time.LocalDate;

public class Parser {
    public static Patient readPatient(String line) {
        StringBuilder record = new StringBuilder(line);
        String name = record.substring(0, record.indexOf(", "));
        // Deletes first comma separator.
        record.delete(0, record.indexOf(", ") + 2);
        String nric = record.substring(0, record.indexOf(", "));
        // Deletes second comma separator.
        record.delete(0, record.indexOf(", ") + 2);
        LocalDate dateOfBirth = LocalDate.parse(record.toString());
        return new Patient(name, nric, dateOfBirth);
    }

    public static Command parse(String fullCommand) throws RexException {
        String trimmedCommand = fullCommand.trim().toLowerCase();
        Command command;
        if (trimmedCommand.equals(ExitCommand.COMMAND_WORD)) {
            command = new ExitCommand();
        } else if (trimmedCommand.contains(AddCommand.COMMAND_WORD)) {
            command = new AddCommand(trimmedCommand);
        } else {
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }


}
