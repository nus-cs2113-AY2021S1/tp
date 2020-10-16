package exception;

public class EmptyEventIndex extends NuScheduleException{
    public String getMessage() {
        return "Please provide a valid number.";
    }
}
