package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user has input an invalid page name.
 */
public class InvalidPageException extends ZeroNoteException {
    public InvalidPageException(String problematicInput) {
        super(problematicInput);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Entered invalid page: " + problematicInput);
        System.out.println("Format for adding page: add /pPAGE; CONTENT");
        System.out.println("Format for selecting page: select /pPAGE");
        System.out.println("Format for deleting page: delete /nNOTEBOOK /sSECTION /pPAGE");
    }
}
