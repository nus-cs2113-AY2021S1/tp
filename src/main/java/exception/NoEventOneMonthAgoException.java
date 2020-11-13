package exception;

/*
* Represents the case when there is no event happened one month ago but the user typed in autoclear
* */
public class NoEventOneMonthAgoException extends NuScheduleException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "OOPS!! There is no events happened one month ago for you to auto clear :(";
    }
}
