package zeronote.exceptions;

// @@author neilbaner

public class IndexIncorrectFormatException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("I'm sorry, please enter a valid number!");
    }
}
