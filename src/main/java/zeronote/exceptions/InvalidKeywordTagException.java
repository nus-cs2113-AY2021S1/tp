package zeronote.exceptions;

//@@author Lusi711

/**
 * Signals that the user has entered an invalid keyword or tag.
 */
public class InvalidKeywordTagException extends ZeroNoteException {
    public InvalidKeywordTagException(String problematicInput) {
        super(problematicInput);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Invalid/Missing keyword or tag: " + problematicInput);
        System.out.println("Format for finding by keyword: find KEYWORD");
        System.out.println("Format for finding by tag: find /tTAG");
    }
}
