package zeronote.exceptions;

//@@author neilbaner

public abstract class ZeroNoteException extends Exception {
    public String problematicInput;

    public ZeroNoteException() {

    }

    public ZeroNoteException(String enteredCommand) {
        this.problematicInput = enteredCommand;
    }

    public abstract void printErrorMessage();
}
