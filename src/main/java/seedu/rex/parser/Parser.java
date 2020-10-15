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
        if (trimmedCommand.equals(ExitCommand.COMMAND_WORD)) {
            command = new ExitCommand();
        } else if (trimmedCommand.contains(AddCommand.COMMAND_WORD)) {
            command = new AddCommand(trimmedCommand);
        } else if (trimmedCommand.contains(RetrieveCommand.COMMAND_WORD)) {
            command = new RetrieveCommand(trimmedCommand);
        } else if (trimmedCommand.contains(BookCommand.COMMAND_WORD)) {
            command = new BookCommand(trimmedCommand);
        } else if (trimmedCommand.contains(CreateAppointmentCommand.COMMAND_WORD)) {
            command = new CreateAppointmentCommand();
        } else if (trimmedCommand.contains(EditCommand.COMMAND_WORD)) {
            command = new EditCommand(trimmedCommand);
        } else if (trimmedCommand.contains(DeleteCommand.COMMAND_WORD)) {
            command = new DeleteCommand(trimmedCommand);
        } else if (trimmedCommand.contains(ListAppointmentsCommand.COMMAND_WORD)) {
            command = new ListAppointmentsCommand(trimmedCommand);
        } else {
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }


}
