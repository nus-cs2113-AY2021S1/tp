package seedu.duke.command.repeatExceptions;

public class MissingDeadlineRepeatException extends Exception{

    private String message;

    public MissingDeadlineRepeatException() {
        message = "Error! You cannot repeat an event that has no deadline!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
