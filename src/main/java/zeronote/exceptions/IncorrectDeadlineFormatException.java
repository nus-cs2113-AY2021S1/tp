package zeronote.exceptions;

//@@author chuckiex3

/**
 * Signals that the user has input the date/time wrongly.
 */
public class IncorrectDeadlineFormatException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("You have entered the date and time wrongly. ");
        System.out.println("Oops! Your deadline should be in this format");
        System.out.println("dd-MM-yyyy HHmm where time is in 24h");
    }
}
