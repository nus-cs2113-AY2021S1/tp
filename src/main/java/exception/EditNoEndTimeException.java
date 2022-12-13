package exception;

public class EditNoEndTimeException extends NuScheduleException {

    @Override
    public String getMessage() {
        return "This conversion requires a valid END time.";
    }
}
