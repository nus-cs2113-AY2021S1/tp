package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user has input an invalid notebook name.
 */
public class InvalidNotebookException extends ZeroNoteException {
    public InvalidNotebookException(String problematicInput) {
        this.problematicInput = problematicInput;
    }

    @Override
    public void printErrorMessage() {
        System.out.println("You have entered an invalid notebook name: " + problematicInput);
        System.out.println("Format for adding notebook: add /nNOTEBOOK TITLE");
        System.out.println("Format for selecting notebook: select /nNOTEBOOK TITLE");
        System.out.println("Format for deleting notebook: delete /nNOTEBOOK TITLE");
    }
}
