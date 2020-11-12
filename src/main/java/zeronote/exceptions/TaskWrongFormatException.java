package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user's input does not follow the correct format expected.
 */
public class TaskWrongFormatException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("The input \"" + problematicInput + "\" doesn't follow the format expected. ");
    }
}
