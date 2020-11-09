package seedu.rex.data.exception;

/**
 * Throws exceptions relating to Rex.
 */
public class RexException extends Exception {

    private static final String OOPS = "☹ OOPS!!! ";

    /**
     * Initializes message to throw.
     *
     * @param message Exception message to show.
     */
    public RexException(String message) {
        super(OOPS + message);
    }
}
