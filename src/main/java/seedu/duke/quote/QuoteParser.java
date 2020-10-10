package seedu.duke.quote;

import seedu.duke.author.Author;
import seedu.duke.commands.Command;
import seedu.duke.exception.QuotesifyException;

import java.util.HashMap;

public class QuoteParser {
    public static final String ERROR_MISSING_QUOTE = "I don't see the quote anywhere";
    public static final String ERROR_MISSING_AUTHOR = "Author name cannot be empty if \"/by\" flag is present";
    public static final String ERROR_MISSING_REFERENCE = "Reference field cannot be empty if \"/from\" flag is present";
    public static final String ERROR_MISSING_REFERENCE_OR_AUTHOR = "Author name and Reference cannot be empty if " +
            "\"/by\" flag and \"/from\" flag are present";
    public static Quote parseAddParameters(String userInput) throws QuotesifyException {
        if (userInput.contains(Command.FLAG_REFERENCE) && userInput.contains(Command.FLAG_AUTHOR)) {
            return parseQuoteWithReferenceAndAuthor(userInput);
        } else if (userInput.contains(Command.FLAG_REFERENCE)) {
            return parseQuoteWithReference(userInput);
        } else if (userInput.contains(Command.FLAG_AUTHOR)) {
            return parseQuoteWithAuthor(userInput);
        } else {
            return parseQuoteWithoutAdditionalInfo(userInput);
        }
    }

    public static Quote parseQuoteWithoutAdditionalInfo(String userInput) throws QuotesifyException {
        // Throws exception if quote is empty
        String quote = trimAndCheckEmptyQuote(userInput);
        return new Quote(quote);
    }

    public static Quote parseQuoteWithReferenceAndAuthor(String userInput) throws QuotesifyException {
        // Throws exception if any fields are empty
        String[]  quoteAndInformation = userInput.split(Command.FLAG_DELIMITER, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndInformation[0]);

        HashMap<String, String> referenceAndAuthorName = parseReferenceAndAuthor(quoteAndInformation[1]);
        String reference = referenceAndAuthorName.get(Command.REFERENCE_KEYWORD);
        String authorName = referenceAndAuthorName.get(Command.AUTHORNAME_KEYWORD);

        if (!reference.isEmpty() && !authorName.isEmpty()) {
            Author author = new Author(authorName);
            return new Quote(quote, reference, author);
        } else if (!reference.isEmpty()) {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        } else {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static HashMap parseReferenceAndAuthor(String userInput) throws QuotesifyException {
        System.out.println(userInput);
        HashMap<String, String> referenceAndAuthorName = new HashMap<String, String>();
        String[] referenceAndAuthor = userInput.split(Command.FLAG_DELIMITER,2);

        String reference = "";
        String authorName = "";
        try {
            if (referenceAndAuthor[0].startsWith("from")) {
                reference = referenceAndAuthor[0].substring(5).trim();
                authorName = referenceAndAuthor[1].substring(3).trim();
            } else {
                reference = referenceAndAuthor[1].substring(5).trim();
                authorName = referenceAndAuthor[0].substring(3).trim();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE_OR_AUTHOR);
        }

        referenceAndAuthorName.put("reference", reference);
        referenceAndAuthorName.put("authorName", authorName);
        return referenceAndAuthorName;
    }

    public static Quote parseQuoteWithReference(String userInput) throws QuotesifyException {
        // Throws exception if quote of reference is empty
        String[] quoteAndReference = userInput.split(Command.FLAG_REFERENCE, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndReference[0]);
        String reference = trimAndCheckEmptyReference(quoteAndReference[1]);
        return new Quote(quote, reference);
    }

    public static Quote parseQuoteWithAuthor(String userInput) throws QuotesifyException {
        // Throws exception if quote of author name is empty
        String[] quoteAndAuthor = userInput.split(Command.FLAG_AUTHOR, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndAuthor[0]);
        Author author = trimAndCheckEmptyAuthor(quoteAndAuthor[1]);
        return new Quote(quote, author);
    }

    public static String trimAndCheckEmptyQuote(String quote) throws QuotesifyException {
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

    public static String parseListWithAuthor(String userInput) throws QuotesifyException {
        System.out.println(userInput);
        String[] authorFlagAndName = userInput.split(Command.FLAG_AUTHOR);
        try{
            String authorName = authorFlagAndName[1].trim();
            return authorName;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static String parseListWithReference(String userInput) throws QuotesifyException {
        String[] referenceFlagAndName = userInput.split(Command.FLAG_REFERENCE);
        try {
            String reference = referenceFlagAndName[1].trim();
            return reference;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }
}
