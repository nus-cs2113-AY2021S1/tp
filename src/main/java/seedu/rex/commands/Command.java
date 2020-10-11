package seedu.rex.commands;

import seedu.rex.data.PatientList;
import seedu.rex.data.exception.RexException;
import seedu.rex.storage.Storage;
import seedu.rex.ui.Ui;

import java.util.Set;

/**
 * Parent Command class.
 */
public abstract class Command {

    public static final String MESSAGE = Ui.LOGO + "\n How may i assist you?"
            .replace("\n", System.lineSeparator() + "\t");
    public static final String COMMAND_ERROR = "I'm sorry, but I don't know what that means :-(";
    public static final int LEGAL_NRIC_LENGTH = 9;
    public static final Set<Character> LEGAL_NRIC_STATUSES = Set.of('S', 'T', 'F', 'G');

    boolean isExit;

    /**
     * Initializes command to execute and set exit status to false.
     */
    public Command() {
        isExit = false;
    }

    /**
     * Executes command.
     *
     * @param patients PatientList object.
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @throws RexException If there is issue executing command.
     */
    public abstract void execute(PatientList patients, Ui ui, Storage storage) throws RexException;

    /**
     * Returns Rex's exit status.
     *
     * @return Exit status.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Checks if a given <code>String</code> consists of only integer characters.
     *
     * @param string The <code>String</code> to be checked.
     * @return <code>true</code> if the string contains only integer characters; <code>false</code> otherwise.
     */
    public boolean isInteger(String string) {
        if (string == null) {
            return false;
        }
        try {
            int stringAsInteger = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Extracts the NRIC from command inputted by the user.
     *
     * @param trimmedCommand Command that was trimmed.
     * @param commandWord    Command that was called.
     * @return Patient's NRIC as a String.
     * @throws RexException If the NRIC inputted by the user is invalid.
     */
    public String extractNric(String trimmedCommand, String commandWord) throws RexException {
        String nric = trimmedCommand.replaceFirst("(?i)" + commandWord, "").trim().toUpperCase();
        if (nric.isBlank()) {
            throw new RexException("Please enter a valid NRIC after '" + commandWord + "'.");
        } else if (nric.length() != LEGAL_NRIC_LENGTH) {
            throw new RexException("Invalid NRIC length!");
        } else if (!LEGAL_NRIC_STATUSES.contains(nric.charAt(0))) {
            throw new RexException("Beginning character of NRIC must be 'S', 'T', 'F', or 'G'!");
        } else if (!isInteger(nric.substring(1, LEGAL_NRIC_LENGTH - 1))) {
            throw new RexException("Number sequence of NRIC is invalid!");
        } else if (!Character.isLetter(nric.charAt(nric.length() - 1))) {
            throw new RexException("Final character of NRIC must be an English alphabet!");
        } else {
            return nric;
        }
    }
}
