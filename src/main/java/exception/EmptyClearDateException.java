package exception;

/*
* Represents the case when the user input clear before but no date is given
* */
public class EmptyClearDateException extends DeleteException {
    /**
     * Prepare the message to be printed when this exception is experienced.
     *
     * @return the message
     */
    public String getMessage() {
        return "OOPS!!! You should input the date before which you want to delete all events :)";
    }
}
