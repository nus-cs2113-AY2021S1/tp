package exception;

public class EditNoEndTimeException extends NuScheduleException {

    @Override
    public String getMessage() {
        return "Conversion of an assignment to personalEvent/class requires a valid END time.";
    }
}
