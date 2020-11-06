package seedu.quotesify.exception;

/**
 * Represents the default exception class for Quotesify.
 */
public class QuotesifyException extends Exception {
    /**
     * Constructor for quotesify exception with an exception message.
     *
     * @param errorMessage Exception message.
     */
    public QuotesifyException(String errorMessage) {
        super(errorMessage);
    }
}
