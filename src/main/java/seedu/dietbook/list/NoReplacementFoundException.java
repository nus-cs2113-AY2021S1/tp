package seedu.dietbook.list;

public class NoReplacementFoundException extends Exception {
    
    /**
     * Constructor for exception: To be thrown when a mapping cannot be established in StringFormatter.
     * @param msg error message
     */
    public NoReplacementFoundException(String msg) {
        super(msg);
    }
}
