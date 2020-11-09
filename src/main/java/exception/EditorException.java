package exception;

public class EditorException extends Exception {

    @Override
    public String getMessage() {
        return "The text editor is blank please add a valid cheatsheet description";
    }
}
