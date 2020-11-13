package exception;

/**
 * Represent the user want to input a class without give it an ending time.
 */
public class NoEndTimeClassException extends ClassException {
    /**
     * The message to be printed.
     * @return the message.
     */
    @Override
    public String getMessage() {
        return "Every class should end at some time. Give it an ending, please. Mark it by \"/e\".";
    }
}
