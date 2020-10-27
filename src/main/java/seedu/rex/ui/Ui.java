package seedu.rex.ui;

import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
import seedu.rex.data.AppointmentList;
import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.data.hospital.Appointment;
import seedu.rex.data.hospital.Doctor;
import seedu.rex.data.hospital.Patient;
import seedu.rex.storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
    private static final String BLANK_INPUT_ERROR = "Input cannot be blank!";
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
     * @param patient The newly edited <code>Patient</code>.
     */
    public void showPatientEdited(Patient patient) {
        printWithIndent("Patient successfully edited: ");
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
     * @throws RexException If user input is blank.
     */
    public String readCommand() throws RexException {
        String userInput;
        printWithIndent("Enter command: ");
        userInput = in.nextLine();
        checkInputNotBlank(userInput);
        return userInput;
    }

    /**
     * Reads the name of a new patient from the user.
     *
     * @return The name of the patient to be added.
     */
    public String getPatientName() {
        String patientName = "";
        while (true) {
            try {
                printWithIndent("Enter patient name: ");
                patientName = in.nextLine().trim();
                checkInputNotBlank(patientName);
                break;
            } catch (RexException e) {
                showLine();
                showError(e.getMessage());
                showLine();
            }
        }
        return patientName;
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
     * Gets appointment to be edited.
     *
     * @param appointments Arraylist of appointments.
     * @return User input.
     * @throws RexException If no appointments are available.
     */
    public String getAppointmentToEdit(AppointmentList appointments) throws RexException {
        showLine();
        printWithIndent("Here are the list of available appointments.");
        int counter = 0;
        for (Appointment appointment : appointments.getAvailableAppointments()) {
            counter++;
            printWithIndent(counter + ". " + appointment.getDate().toString());

        }
        if (counter == 0) {
            throw new RexException("No appointments available!");
        }
        printWithIndent("Please enter the index of appointment to change to");
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

    /**
     * Gets doctor name from user.
     *
     * @return Doctor's name.
     */
    public String getDoctorName() {
        printWithIndent("Enter doctor name: ");
        return in.nextLine().toUpperCase().trim();
    }

    /**
     * Shows the doctor added.
     *
     * @param newDoctor Doctor added.
     */
    public void showDoctorAdded(Doctor newDoctor) {
        printWithIndent("Doctor added: " + newDoctor.getName());
    }

    /**
     * Shows deleted doctor.
     *
     * @param deletedDoctor Deleted doctor.
     */
    public void showDoctorDeleted(Doctor deletedDoctor) {
        printWithIndent("Doctor successfully deleted: ");
        printWithIndent(deletedDoctor.toString());
    }

    /**
     * Shows doctor not found message.
     *
     * @param doctorName Name of doctor.
     */
    public void printDoctorNotFound(String doctorName) {
        printWithIndent("Patient " + doctorName + " not found in database!");
    }

    /**
     * Displays only the Objects in ArrayList that is required to be shown.
     *
     * @param list Generic ArrayList
     * @param <T>  Generic class
     * @return Object in ArrayList
     */
    public <T> int displayArrayList(ArrayList<T> list) {
        int i;
        for (i = 0; i < list.size(); i++) {
            printWithIndent((i + 1) + ". " + list.get(i));
        }
        return list.size();
    }

    /**
     * Alerts user that there is a maximum allowable input.
     *
     * @param maxAllowableInput maximum allowable input
     */
    public void indexOutOfBoundsMessage(int maxAllowableInput) throws RexException {
        throw new RexException("Index out of bounds! Input should be between 1 to " + maxAllowableInput + ".");
    }

    /**
     * Gets object of Arraylist corresponding to user selection.
     *
     * @param list Generic ArrayList
     * @param <T>  Generic class
     * @return Object in ArrayList
     */
    public <T> T getItemOfArrayList(ArrayList<T> list) throws RexException {
        int maxIndex = displayArrayList(list);
        if (maxIndex < 1) {
            throw new RexException("There are no available appointments to choose.");
        }
        printWithIndent("Enter the index: ");
        String inputString = in.nextLine();
        int input = Integer.parseInt(inputString);
        try {
            return list.get(input - 1);
        } catch (IndexOutOfBoundsException e) {
            indexOutOfBoundsMessage(maxIndex);
            return null;
        }
    }

    /**
     * Shows available appointments message.
     */
    public void showAvailableAppointmentsMessage() {
        printWithIndent("Here are the list of available appointments: ");
    }

    /**
     * Shows edit appointment message.
     */
    public void showEditAppointmentMessage() {
        printWithIndent("Here are the appointments. Choose one to edit");
    }

    /**
     * Shows deleted appointment.
     *
     * @param appointment Deleted appointment.
     */
    public void showDeleteAppointmentMessage(Appointment appointment) {
        printWithIndent("Deleted appointment: " + appointment);
    }

    /**
     * Shows delete appointment message.
     */
    public void showDeleteAppointmentMessage() {
        printWithIndent("Choose appointment to delete: ");
    }

    /**
     * Checks if a user's input is blank.
     * @param userInput The user's input.
     * @throws RexException If a user's input is blank.
     */
    public void checkInputNotBlank(String userInput) throws RexException {
        if (userInput.isBlank()) {
            throw new RexException(BLANK_INPUT_ERROR);
        }
    }
}
