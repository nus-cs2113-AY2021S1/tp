package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user's input is missing a title.
 */
public class TaskTitleException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("Your task is missing a title!");
        System.out.println("Please type in the format: add /tTITLE /byDEADLINE");
    }
}
