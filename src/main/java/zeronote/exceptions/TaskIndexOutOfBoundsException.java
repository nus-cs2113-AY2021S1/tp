package zeronote.exceptions;

//@@author neilbaner

public class TaskIndexOutOfBoundsException extends ZeroNoteException {
    public TaskIndexOutOfBoundsException(String enteredCommand) {
        super(enteredCommand);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("Please enter a valid index between 1 and " + super.problematicInput + ".");
    }
}
