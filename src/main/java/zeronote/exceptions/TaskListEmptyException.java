package zeronote.exceptions;

//@@author neilbaner

public class TaskListEmptyException extends ZeroNoteException {
    @Override
    public void printErrorMessage() {
        System.out.println("The Task List is empty!");
    }
}
