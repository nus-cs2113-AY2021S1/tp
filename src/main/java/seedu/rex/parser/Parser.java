package seedu.rex.parser;

import seedu.rex.commands.AddCommand;
import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.commands.RetrieveCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Patient;

import java.time.LocalDate;

/**
 * Makes sense of user command.
 */
public class Parser {

    /**
     * Reads inputted patient details and return the patient.
     *
     * @param line Input line to parse to patient details.
     * @return Patient object with patient details.
     */
    public static Patient readPatient(String line) {
        assert line != null && !line.equals("") : "No patients to read!";

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

    /**
     * Reads and parse command.
     *
     * @param fullCommand Input string.
     * @return Command to be ran.
     * @throws RexException if command does not exist.
     */
    public static Command parse(String fullCommand) throws RexException {
        assert fullCommand != null && !fullCommand.equals("") : "No command to parse!";

        String trimmedCommand = fullCommand.trim().toLowerCase();
        Command command;
        if (trimmedCommand.equals(ExitCommand.COMMAND_WORD)) {
            command = new ExitCommand();
        } else if (trimmedCommand.contains(AddCommand.COMMAND_WORD)) {
            command = new AddCommand(trimmedCommand);
        } else if (trimmedCommand.contains(RetrieveCommand.COMMAND_WORD)) {
            command = new RetrieveCommand(trimmedCommand);
        } else {
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }


}
