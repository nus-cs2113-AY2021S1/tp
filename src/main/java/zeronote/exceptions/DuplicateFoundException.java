package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user input a notebook/section/page title that has already been used.
 */
public class DuplicateFoundException extends ZeroNoteException {
    String duplicate;

    public DuplicateFoundException(String argument) {
        this.duplicate = argument;
    }

    @Override
    public void printErrorMessage() {
        System.out.println("A duplicate has been found for: " + duplicate);
        System.out.println("Unable to create " + duplicate + ", sorry.");
    }
}
