package exception;

public class InvalidSortCriteriaException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "Invalid sorting criteria given. Possible sorting criteria includes"
                + " description , location and time.";
    }
}
