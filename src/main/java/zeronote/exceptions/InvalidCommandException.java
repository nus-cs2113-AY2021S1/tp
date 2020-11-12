package zeronote.exceptions;

//@@author Lusi711

/**
 * Signals that the user has input an invalid command.
 */
public class InvalidCommandException extends ZeroNoteException {
    public InvalidCommandException(String problematicInput) {
        super(problematicInput);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("You have entered an invalid command: " + problematicInput);
    }
}
