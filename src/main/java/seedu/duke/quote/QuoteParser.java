package seedu.duke.quote;

import seedu.duke.author.Author;
import seedu.duke.exception.QuotesifyException;

public class QuoteParser {
    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_DELIMETER = "/";
    public static final String ERROR_MISSING_QUOTE = "I don't see the quote anywhere";
    public static final String ERROR_MISSING_AUTHOR = "Author name cannot be empty if \"/by\" flag is present";
    public static final String ERROR_MISSING_REFERENCE = "Reference field cannot be empty if \"/from\" flag is present";

    public static Quote parseParameters(String userInput) throws QuotesifyException {
        if (userInput.contains(FLAG_REFERENCE) && userInput.contains(FLAG_AUTHOR)) {
            return parseQuoteWithReferenceAndAuthor(userInput);
        } else if (userInput.contains(FLAG_REFERENCE)) {
            return parseQuoteWithReference(userInput);
        } else if (userInput.contains(FLAG_AUTHOR)) {
            return parseQuoteWithAuthor(userInput);
        } else {
            return parseQuoteWithoutAdditionalInfo(userInput);
        }
    }

    public static Quote parseQuoteWithoutAdditionalInfo(String userInput) throws QuotesifyException {
        String quote = trimAndCheckEmptyQuote(userInput);
        return new Quote(quote);
    }

    public static Quote parseQuoteWithReferenceAndAuthor(String userInput) throws QuotesifyException{
        String[]  quoteAndInformation = userInput.split(FLAG_DELIMETER, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndInformation[0]);
        String[] referenceAndAuthor = quoteAndInformation[1].split(FLAG_DELIMETER,2);

        String reference = "";
        String authorName = "";
        if (referenceAndAuthor[0].startsWith("from")) {
            reference = referenceAndAuthor[0].substring(5);
            authorName = referenceAndAuthor[1].substring(3);
        } else {
            reference = referenceAndAuthor[1].substring(5);
            authorName = referenceAndAuthor[0].substring(3);
        }

        if (!reference.isEmpty() && !authorName.isEmpty()) {
            Author author = new Author(authorName);
            return new Quote(quote, reference, author);
        } else if (!reference.isEmpty()) {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        } else {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static Quote parseQuoteWithReference(String userInput) throws QuotesifyException {
        String[] quoteAndReference = userInput.split(FLAG_REFERENCE, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndReference[0]);
        String reference = trimAndCheckEmptyReference(quoteAndReference[1]);
        return new Quote(quote, reference);
    }

    public static Quote parseQuoteWithAuthor(String userInput) throws QuotesifyException{
        String[] quoteAndAuthor = userInput.split(FLAG_AUTHOR, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndAuthor[0]);
        Author author = trimAndCheckEmptyAuthor(quoteAndAuthor[1]);
        return new Quote(quote, author);
    }

    public static String trimAndCheckEmptyQuote(String quote) throws QuotesifyException{
        quote = quote.trim();
        if (!quote.isEmpty()) {
            return quote;
        } else {
            throw new QuotesifyException(ERROR_MISSING_QUOTE);
        }
    }

    public static Author trimAndCheckEmptyAuthor(String authorName) throws QuotesifyException {
        authorName = authorName.trim();
        if (!authorName.isEmpty()) {
            return new Author(authorName);
        } else {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static String trimAndCheckEmptyReference(String reference) throws QuotesifyException {
        reference = reference.trim();
        if (!reference.isEmpty()) {
            return reference;
        } else {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        }
    }


}
