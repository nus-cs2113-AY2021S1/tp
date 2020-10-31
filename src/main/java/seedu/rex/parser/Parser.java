package seedu.rex.parser;

import seedu.rex.Rex;
import seedu.rex.commands.AddDoctorCommand;
import seedu.rex.commands.AddPatientCommand;
import seedu.rex.commands.BookApptCommand;
import seedu.rex.commands.Command;
import seedu.rex.commands.CreateApptCommand;
import seedu.rex.commands.DeleteApptCommand;
import seedu.rex.commands.DeleteDoctorCommand;
import seedu.rex.commands.DeletePatientCommand;
import seedu.rex.commands.EditApptCommand;
import seedu.rex.commands.EditPatientCommand;
import seedu.rex.commands.ExitCommand;
import seedu.rex.commands.ListApptCommand;
import seedu.rex.commands.ListPatientCommand;
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
     * Reads inputted patient details and returns the patient.
     *
     * @param line Input line to parse to patient details.
     * @return Patient object with patient details.
     */
    public static Patient readPatient(String line) {
        assert line != null && !line.equals("") : "No patients to read!";

        String[] patientDetails = line.split(" \\| ");
        String name = patientDetails[0];
        String nric = patientDetails[1];
        LocalDate dateOfBirth = LocalDate.parse(patientDetails[2]);
        return new Patient(name, nric, dateOfBirth);
    }

    /**
     * Reads in appointment and returns Appointment object.
     *
     * @param line Line to parse.
     * @return Appointment object.
     */
    public static Appointment readAppointment(String line) {
        String[] appointmentComponents = line.split(" \\| ");
        LocalDate date = LocalDate.parse(appointmentComponents[0]);
        String bookedStatus = appointmentComponents[1];
        String nric;
        String doctorName;
        if (bookedStatus.contentEquals("booked")) {
            nric = appointmentComponents[2];
            doctorName = appointmentComponents[3];
        } else {
            nric = null;
            doctorName = null;
        }

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
        int length = 2;
        switch (words[0]) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand();
            length = 1;
            break;
        case AddPatientCommand.COMMAND_WORD:
            command = new AddPatientCommand(trimmedCommand);
            break;
        case BookApptCommand.COMMAND_WORD:
            command = new BookApptCommand(trimmedCommand);
            break;
        case CreateApptCommand.COMMAND_WORD:
            command = new CreateApptCommand();
            length = 1;
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
            length = 1;
            break;
        case RetrievePatientCommand.COMMAND_WORD:
            command = new RetrievePatientCommand(trimmedCommand);
            break;
        case AddDoctorCommand.COMMAND_WORD:
            command = new AddDoctorCommand(trimmedCommand);
            break;
        case DeleteDoctorCommand.COMMAND_WORD:
            command = new DeleteDoctorCommand(trimmedCommand);
            break;
        case EditApptCommand.COMMAND_WORD:
            command = new EditApptCommand(trimmedCommand);
            break;
        case DeleteApptCommand.COMMAND_WORD:
            command = new DeleteApptCommand(trimmedCommand);
            break;
        default:
            throw new RexException(Command.COMMAND_ERROR);
        }
        if (!(command instanceof ExitCommand) && words.length < length) {
            throw new RexException("Invalid input!");
        }
        return command;
    }


    /**
     * Parses and reads doctor.
     *
     * @param line Line to parse.
     * @return Doctor object.
     */
    public static Doctor readDoctor(String line) {
        assert line != null && !line.equals("") : "No doctors to read!";

        String name = line.trim();
        return new Doctor(name);
    }
}
