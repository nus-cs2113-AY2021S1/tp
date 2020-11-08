package exception;

/*
* Represents the case when the user input a date in clear before command but there is no event before the given date
* */
public class NoDateBeforeException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "There is no events existing before the date given by you :(.";
    }
}
