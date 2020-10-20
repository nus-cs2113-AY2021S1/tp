package seedu.rex.parser;

import seedu.rex.Rex;
import seedu.rex.commands.AddPatientCommand;
import seedu.rex.commands.AddDoctorCommand;
import seedu.rex.commands.BookApptCommand;
import seedu.rex.commands.Command;
import seedu.rex.commands.CreateApptCommand;
import seedu.rex.commands.DeletePatientCommand;
import seedu.rex.commands.EditApptCommand;
import seedu.rex.commands.EditPatientCommand;
import seedu.rex.commands.ExitCommand;
import seedu.rex.commands.ListApptCommand;
import seedu.rex.commands.ListPatientCommand;
import seedu.rex.commands.RemoveDoctorCommand;
import seedu.rex.commands.RetrievePatientCommand;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Doctor;
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
        String doctorName = appointmentComponents[3];
        Appointment appointment = new Appointment(date);
        if (bookedStatus.equals("booked")) {
            appointment.bookPatient(Rex.getPatients().getPatientFromNric(nric));
        }
        if (doctorName != null && !doctorName.equals("null")) {
            appointment.setDoctor(Rex.getDoctors().getDoctorFromName(doctorName));
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
        case AddPatientCommand.COMMAND_WORD:
            command = new AddPatientCommand(trimmedCommand);
            break;
        case BookApptCommand.COMMAND_WORD:
            command = new BookApptCommand(trimmedCommand);
            break;
        case CreateApptCommand.COMMAND_WORD:
            command = new CreateApptCommand();
            break;
        case EditPatientCommand.COMMAND_WORD:
            command = new EditPatientCommand(trimmedCommand);
            break;
        case DeletePatientCommand.COMMAND_WORD:
            command = new DeletePatientCommand(trimmedCommand);
            break;
        case ListApptCommand.COMMAND_WORD:
            command = new ListApptCommand(trimmedCommand);
            break;
        case ListPatientCommand.COMMAND_WORD:
            command = new ListPatientCommand();
            break;
        case RetrievePatientCommand.COMMAND_WORD:
            command = new RetrievePatientCommand(trimmedCommand);
            break;
        case AddDoctorCommand.COMMAND_WORD:
            command = new AddDoctorCommand(trimmedCommand);
            break;
        case RemoveDoctorCommand.COMMAND_WORD:
            command = new RemoveDoctorCommand(trimmedCommand);
            break;

        case EditApptCommand.COMMAND_WORD:
            command = new EditApptCommand(trimmedCommand);
            break;

        default:
            throw new RexException(Command.COMMAND_ERROR);
        }
        return command;
    }


    public static Doctor readDoctor(String line) {
        assert line != null && !line.equals("") : "No doctors to read!";

        String name = line.trim();
        return new Doctor(name);
    }
}
