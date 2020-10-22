package exception;

public class EditIndexOutOfBoundsException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "Index provided is out of bound. Please provide a valid integer.";
    }
}
