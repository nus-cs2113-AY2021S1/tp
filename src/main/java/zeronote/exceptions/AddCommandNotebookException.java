package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user is unable to add a notebook, section or page.
 */
public class AddCommandNotebookException extends ZeroNoteException {
    public AddCommandNotebookException(String problematicInput) {
        super(problematicInput);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Unable to add notebook/section/page" + problematicInput);
    }
}
