package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

/**
 * Adds a patient to the list of patients.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public String trimmedCommand;

    public AddCommand(String trimmedCommand) {
        this.trimmedCommand = trimmedCommand;
    }

    /**
     * Adds a new patient to the patient list using details inputted by the user.
     * @param patients List of patients.
     * @param ui       Ui object of the program.
     * @param storage  Storage object used for saving data to files.
     * @throws RexException If there is an error in the NRIC inputted by the user, the data fails
     *     to save successfully, or the NRIC already exists in the patient list.
     */
    @Override
    public void execute(PatientList patients, Ui ui, Storage storage) throws RexException {
        String nric = extractNric();
        if (isExistingPatient(patients, nric)) {
            throw new RexException("A patient with this NRIC is already registered!");
        }
        patients.addNewPatient(ui.getPatientName(), nric, ui.getPatientDateOfBirth());
        ui.showLine();
        ui.showPatientAdded(patients.getPatientUsingIndex(patients.getSize() - 1));
        storage.save(patients);
    }

    /**
     * Extracts the NRIC from the 'add' command inputted by the user.
     * @return Patient's NRIC as a String.
     * @throws RexException If the NRIC inputted by the user is invalid.
     */
    public String extractNric() throws RexException {
        String nric = trimmedCommand.replaceFirst("(?i)" + COMMAND_WORD, "").trim().toUpperCase();
        if (nric.isBlank()) {
            throw new RexException("Please enter a valid NRIC after 'add'.");
        } else if (nric.length() != LEGAL_NRIC_LENGTH) {
            throw new RexException("Invalid NRIC length!");
        } else if (!LEGAL_NRIC_STATUSES.contains(nric.charAt(0))) {
            throw new RexException("Beginning character of NRIC must be 'S', 'T', 'F', or 'G'!");
        } else if (!isNumeric(nric.substring(1, LEGAL_NRIC_LENGTH - 1))) {
            throw new RexException("Number sequence of NRIC is invalid!");
        } else if (!Character.isLetter(nric.charAt(nric.length() - 1))) {
            throw new RexException("Final character of NRIC must be an English alphabet!");
        } else {
            return nric;
        }
    }

    /**
     * Checks if the NRIC entered by the user already exists in the patient list.
     * @param patients The list of patients.
     * @param nric The NRIC entered by the user.
     * @return <code>true</code> if NRIC already exists; <code>false</code> otherwise.
     */
    public boolean isExistingPatient(PatientList patients, String nric) {
        for (int i = 0; i < patients.getSize(); i++) {
            if (patients.getPatientUsingIndex(i).getNric().equals(nric)) {
                return true;
            }
        }
        return false;
    }
}
