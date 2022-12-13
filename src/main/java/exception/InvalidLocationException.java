package exception;

public class InvalidLocationException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "Please input a valid location or event number.";
    }
}
