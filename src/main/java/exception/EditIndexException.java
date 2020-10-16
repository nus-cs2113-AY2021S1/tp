package exception;

public class EditIndexException extends NuScheduleException{
    public String getMessage() {
        return "Please provide a valid number.";
    }
}
