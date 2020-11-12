package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user has incorrectly typed the select command.
 */
public class InvalidSelectCommandException extends ZeroNoteException {
    public InvalidSelectCommandException(String problematicInput) {
        super(problematicInput);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Invalid select command: " + problematicInput);
        System.out.println("Format for selecting: select /nNOTEBOOK /sSECTION /pPAGE");
        System.out.println("You must select a notebook before selecting a section.");
        System.out.println("You must select a section before selecting a page.");
    }
}
