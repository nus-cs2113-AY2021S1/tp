package zeronote.exceptions;

//@@author Lusi711

/**
 * Signals that the user is in the wrong mode.
 */
public class IncorrectAppModeException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("Please check that you are in the correct mode.");
    }
}
