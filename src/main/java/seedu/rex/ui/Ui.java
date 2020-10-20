package seedu.rex.ui;

import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Interacts with user.
 */
public class Ui {

    public static final String LOGO =
            " _    _                 _ _        _                                  _____     __   __\n"
                    + "\t | |  | |               (_| |      | |                                |  __ \\    \\ \\ / /\n"
                    + "\t | |__| | ___  ___ _ __  _| |_ __ _| |___  __ _ _   _ _ __ _   _ ___  | |__) |___ \\ V / \n"
                    + "\t |  __  |/ _ \\/ __| '_ \\| | __/ _` | / __|/ _` | | | | '__| | | / __| |  _  // _ \\ > <  \n"
                    + "\t | |  | | (_) \\__ | |_) | | || (_| | \\__ | (_| | |_| | |  | |_| \\__ \\ | | \\ |  __// . \\"
                    + " \n"
                    + "\t |_|  |_|\\___/|___| .__/|_|\\__\\__,_|_|___/\\__,_|\\__,_|_|   \\__,_|___/ |_|  \\_\\___/_/ "
                    + "\\_\\\n"
                    + "\t                  | |                                                                   \n"
                    + "\t                  |_|                                                                   "
                    .replaceAll("\n", System.lineSeparator());
    private static final String DOTTED_LINE = "____________________________________________________________";
    private static final String DATE_ERROR = "Error in date format.";
    private final Scanner in = new Scanner(System.in);

    /**
     * Prints string with indent.
     *
     * @param string String to print.
     */
    private void printWithIndent(String string) {
        System.out.println("\t " + string);
    }

    /**
     * Prints dotted line.
     */
    public void showLine() {
        System.out.println("\t" + DOTTED_LINE);
    }

    /**
     * Prints error message.
     *
     * @param message Error message to print.
     */
    public void showError(String message) {
        printWithIndent(message);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        printWithIndent(Command.MESSAGE);
        showLine();
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        showError(Storage.LOAD_ERROR);
    }

    /**
     * Prints exit message.
     */
    public void showExit() {
        printWithIndent(ExitCommand.MESSAGE);
    }

    /**
     * Prints date error message.
     */
    public void showDateInputError() {
        showError(DATE_ERROR);
    }

    /**
     * Prints a message to indicate successful addition of a patient.
     *
     * @param patient The newly added <code>Patient</code>.
     */
    public void showPatientAdded(Patient patient) {
        printWithIndent("Patient successfully added: ");
        printWithIndent(patient.toString());
    }

    /**
     * Prints a message to indicate successful editting of a patient.
     *
     * @param patient The newly editted <code>Patient</code>.
     */
    public void showPatientEditted(Patient patient) {
        printWithIndent("Patient successfully editted: ");
        printWithIndent(patient.toString());
    }

    /**
     * Prints a message to indicate successful deletion of a patient.
     *
     * @param patient The deleted <code>Patient</code>.
     */
    public void showPatientDeleted(Patient patient) {
        printWithIndent("Patient successfully deleted: ");
        printWithIndent(patient.toString());
    }


    /**
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        System.out.println("Enter command: ");
        return in.nextLine();
    }

    /**
     * Reads the name of a new patient from the user.
     *
     * @return The name of the patient to be added.
     */
    public String getPatientName() {
        printWithIndent("Enter patient name: ");
        return in.nextLine().trim();
    }

    /**
     * Reads the date of birth of a new patient from the user.
     *
     * @return The date of birth of the patient.
     */
    public LocalDate getPatientDateOfBirth() {
        while (true) {
            try {
                printWithIndent("Enter patient date of birth (YYYY-MM-DD) including the dashes: ");
                return LocalDate.parse(in.nextLine().trim());
            } catch (DateTimeParseException e) {
                showLine();
                showDateInputError();
                showLine();
            }
        }
    }

    /**
     * Shows patient details.
     *
     * @param patient Patient to show.
     */
    public void showPatient(Patient patient) {
        assert patient != null : "Cannot show null patient!";

        printWithIndent("Patient Details: ");
        printWithIndent(patient.toString());
    }

    /**
     * Prints patient not found message.
     *
     * @param nric NRIC inputted.
     */
    public void printPatientNotFound(String nric) {
        printWithIndent("Patient " + nric + " not found in database!");
    }

    /**
     * Gets appointment date from user.
     *
     * @return User input string.
     */
    public String getNewAppointmentDate() {
        printWithIndent("Please enter the date of appointment in YYYY-MM-DD.");
        showLine();
        return in.nextLine();
    }

    /**
     * Prints appointment creation message.
     */
    public void showAppointmentCreatedMessage() {
        showLine();
        printWithIndent("New appointment created!");
    }

    /**
     * Gets appointment to be booked.
     *
     * @param appointments Arraylist of appointments.
     * @return User input.
     * @throws RexException If no appointments are available.
     */
    public String getAppointmentToBook(AppointmentList appointments) throws RexException {
        showLine();
        printWithIndent("Here are the list of available appointments.");
        int counter = 0;
        for (Appointment appointment : appointments.getAppointments()) {
            if (!appointment.isBooked()) {
                counter++;
                printWithIndent(counter + ". " + appointment.getDate().toString());
            }
        }
        if (counter == 0) {
            throw new RexException("No appointments available!");
        }
        printWithIndent("Please enter the index of appointment to book");
        showLine();
        return in.nextLine();
    }

    /**
     * Shows appointment booking message.
     *
     * @param appointment appointment that was booked.
     */
    public void showAppointmentBookedMessage(Appointment appointment) {
        showLine();
        printWithIndent("Appointment on " + appointment.getDate() + " booked!");

    }

    /**
     * Prints patient creation message.
     *
     * @param nric Patient's NRIC.
     */
    public void showCreatePatientMessage(String nric) {
        printWithIndent("Creating patient " + nric);
    }

    /**
     * Prints patient's appointment list header.
     *
     * @param nric Patient's NRIC
     */
    public void showAppointmentsListHeader(String nric) {
        printWithIndent("Listing appointments for patient " + nric + ": ");
    }

    /**
     * Prints details of patient's appointment.
     *
     * @param appointment appointment to be printed
     * @param counter     appointment counter under patient
     */
    public void showAppointmentLine(Appointment appointment, int counter) {
        printWithIndent(counter + ". " + appointment.getDate());
    }

    /**
     * Prints message if patient has no booked appointments.
     */
    public void showNoBookedAppointmentsMessage() {
        printWithIndent("No booked appointments found!");
    }

    /**
     * Prints all patients.
     *
     * @param patients Patients to print.
     */
    public void listPatients(PatientList patients) {
        printWithIndent("List of patients (" + patients.getPatients().size() + " in total):");
        for (Patient patient : patients.getPatients()) {
            printWithIndent("");
            printWithIndent("Name: " + patient.getName());
            printWithIndent("NRIC: " + patient.getNric());
            printWithIndent("DOB: " + patient.getDateOfBirth());
        }
    }

    public String getDoctorName() {
        printWithIndent("Enter doctor name: ");
        return in.nextLine().trim();
    }
}
