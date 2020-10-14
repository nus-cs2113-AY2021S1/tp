package seedu.duke.exception;

public class WrongNumberOfArgumentsException extends DukeException {

    private String message;

    public WrongNumberOfArgumentsException(String errorMessage) {

        super(errorMessage);
    }


}
