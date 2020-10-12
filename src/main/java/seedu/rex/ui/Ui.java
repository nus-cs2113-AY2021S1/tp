package seedu.rex.ui;

import seedu.rex.commands.Command;
import seedu.rex.commands.ExitCommand;
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
     * Reads command from user.
     *
     * @return String command from user.
     */
    public String readCommand() {
        System.out.println();
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
}
