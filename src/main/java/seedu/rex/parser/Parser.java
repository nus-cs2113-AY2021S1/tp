package seedu.rex.parser;

import seedu.rex.Rex;
import seedu.rex.commands.AddCommand;
import seedu.rex.commands.BookCommand;
import seedu.rex.commands.Command;
import seedu.rex.commands.CreateAppointmentCommand;
import seedu.rex.commands.DeleteCommand;
import seedu.rex.commands.EditCommand;
import seedu.rex.commands.ExitCommand;
import seedu.rex.commands.ListAppointmentsCommand;
import seedu.rex.commands.ListPatientsCommand;
import seedu.rex.commands.RetrieveCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
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

    public static Appointment readAppointment(String line) {
        String[] appointmentComponents = line.split(", ");
        LocalDate date = LocalDate.parse(appointmentComponents[0]);
        String bookedStatus = appointmentComponents[1];
        String nric = appointmentComponents[2];
        Appointment appointment = new Appointment(date);
        if (bookedStatus.equals("booked")) {
            appointment.book(Rex.getPatients().getPatientUsingIndex(Rex.getPatients().getExistingPatient(nric)));
        }
        return appointment;
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
        String[] words = trimmedCommand.split(" ");
        switch (words[0]) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            break;
        case AddCommand.COMMAND_WORD:
            command = new AddCommand(trimmedCommand);
            break;
        case BookCommand.COMMAND_WORD:
            command = new BookCommand(trimmedCommand);
            break;
        case CreateAppointmentCommand.COMMAND_WORD:
            command = new CreateAppointmentCommand();
            break;
        case EditCommand.COMMAND_WORD:
            command = new EditCommand(trimmedCommand);
            break;
        case DeleteCommand.COMMAND_WORD:
            command = new DeleteCommand(trimmedCommand);
            break;
        case ListAppointmentsCommand.COMMAND_WORD:
            command = new ListAppointmentsCommand(trimmedCommand);
            break;
        case ListPatientsCommand.COMMAND_WORD:
            command = new ListPatientsCommand();
            break;
        case RetrieveCommand.COMMAND_WORD:
            command = new RetrieveCommand(trimmedCommand);
            break;
        default:
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }


}
