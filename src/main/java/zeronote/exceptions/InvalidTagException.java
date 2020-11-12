package zeronote.exceptions;

//@@author Lusi711

/**
 * Signals that the user entered an empty tag.
 */
public class InvalidTagException extends ZeroNoteException {
    public InvalidTagException(String problematicInput) {
        super(problematicInput);
    }

    public void setProblematicInput(String problematicInput) {
        this.problematicInput = problematicInput;
    }

    public void printErrorMessage() {
        System.out.println("Missing tag for command: " + problematicInput);
        System.out.println("Format to tag a task: tag [index] /t[tag]");
        System.out.println("Format to a selected notebook/section/page: tag /t[tag]");
    }
}
