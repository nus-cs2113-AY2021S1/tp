package exception;

public class NoSortCriteriaException extends NuScheduleException {
    @Override
    public String getMessage() {
        return "No sorting criteria given. Possible sorting criteria includes"
                + " description and time.";
    }
}
