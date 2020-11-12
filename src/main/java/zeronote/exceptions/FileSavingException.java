package zeronote.exceptions;

// @@author neilbaner

public class FileSavingException extends ZeroNoteException {
    public FileSavingException(String enteredCommand) {
        super(enteredCommand);
    }

    @Override
    public void printErrorMessage() {
        System.out.println("There was some error saving the file.");
        System.out.println("Here's the output from Java in case it helps debug the issue: ");
        System.out.println(problematicInput);
    }
}
