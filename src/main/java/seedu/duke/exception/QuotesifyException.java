package seedu.duke.exception;

import seedu.duke.quote.Quote;

/**
 * Quotesify's personalised exception
 */
public class QuotesifyException extends Exception {
    /**
     * Quotesify constructor
     * @param errorMessage Quotesify exception message
     */
    public QuotesifyException(String errorMessage) {
        super(errorMessage);
    }
}
