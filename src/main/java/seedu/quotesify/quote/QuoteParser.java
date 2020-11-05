package seedu.quotesify.quote;

import seedu.quotesify.author.Author;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;

import java.util.HashMap;

public class QuoteParser {
    public static final String FLAG_DELIMITER = "/";
    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String ERROR_MISSING_QUOTE = "I don't see the quote anywhere";
    public static final String ERROR_MISSING_AUTHOR = "Author name cannot be empty if \"/by\" flag is present";
    public static final String ERROR_MISSING_REFERENCE = "Reference field cannot be empty if \"/from\" flag is present";
    public static final String ERROR_MISSING_REFERENCE_OR_AUTHOR = "Author name and Reference cannot be empty if "
            + "\"/by\" flag and \"/from\" flag are present";
    public static final String ERROR_INVALID_QUOTE_NUM = "Quote number provided is invalid";
    public static final String ERROR_INVALID_PARAMETERS = "Invalid parameters!";
    public static final String ERROR_DUPLICATE_REFERENCE_FLAG = "Reference flag can only be used once!";
    public static final String ERROR_DUPLICATE_AUTHOR_FLAG = "Author flag can only be used once!";

    public static Quote parseAddParameters(String userInput) throws QuotesifyException {
        assert !userInput.isEmpty() : "field should not be empty";
        if (userInput.contains(FLAG_REFERENCE) && userInput.contains(FLAG_AUTHOR)) {
            return parseQuoteWithReferenceAndAuthorInput(userInput);
        } else if (userInput.contains(FLAG_REFERENCE)) {
            return parseQuoteWithReferenceInput(userInput);
        } else if (userInput.contains(FLAG_AUTHOR)) {
            return parseQuoteWithAuthorInput(userInput);
        } else {
            return parseQuoteWithoutFlags(userInput);
        }
    }

    public static Quote parseQuoteWithoutFlags(String userInput) throws QuotesifyException {
        // Throws exception if quote is empty
        String quote = trimAndCheckEmptyQuote(userInput);
        return new Quote(quote);
    }

    public static String trimAndCheckEmptyQuote(String quote) throws QuotesifyException {
        assert !quote.isEmpty() : "quote field should not be empty";
        quote = quote.trim();
        if (!quote.isEmpty()) {
            return quote;
        } else {
            throw new QuotesifyException(ERROR_MISSING_QUOTE);
        }
    }

    public static Quote parseQuoteWithReferenceAndAuthorInput(String userInput) throws QuotesifyException {
        // Throws exception if any fields are empty
        String[] quoteAndInformation = userInput.split(FLAG_DELIMITER, 2);
        String quote = trimAndCheckEmptyQuote(quoteAndInformation[0]);

        HashMap<String, String> referenceAndAuthorName = getReferenceAndAuthor(quoteAndInformation[1]);
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

    public static HashMap getReferenceAndAuthor(String userInput) throws QuotesifyException {
        String[] referenceAndAuthor = userInput.split(FLAG_DELIMITER,2);
        String reference = "";
        String authorName = "";
        try {
            if (referenceAndAuthor[0].startsWith("from")) {
                reference = referenceAndAuthor[0].substring(5).trim();
                authorName = referenceAndAuthor[1].substring(3).trim();
                assert !reference.isEmpty() : "reference field should not be empty";
                assert !authorName.isEmpty() : "author field should not be empty";
            } else {
                reference = referenceAndAuthor[1].substring(5).trim();
                authorName = referenceAndAuthor[0].substring(3).trim();
                assert !reference.isEmpty() : "reference field should not be empty";
                assert !authorName.isEmpty() : "author field should not be empty";
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE_OR_AUTHOR);
        }
        // throws exception is duplicate flags found
        checkExtraAuthorReferenceFlags(authorName, reference); // check this need catch or not
        HashMap<String, String> referenceAndAuthorName = new HashMap<String, String>();
        referenceAndAuthorName.put("reference", reference);
        referenceAndAuthorName.put("authorName", authorName);
        return referenceAndAuthorName;
    }

    public static void checkExtraAuthorReferenceFlags(String authorName, String reference) throws QuotesifyException {
        if (authorName.contains(FLAG_AUTHOR) || authorName.contains(FLAG_REFERENCE)) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        } else if (reference.contains(FLAG_AUTHOR) || reference.contains(FLAG_REFERENCE)) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        }
    }

    public static Quote parseQuoteWithReferenceInput(String userInput) throws QuotesifyException {
        // Throws exception if quote of reference is empty
        String[] quoteAndReference = trimAndCheckReferenceFlag(userInput);
        String quote = trimAndCheckEmptyQuote(quoteAndReference[0]);
        String reference = trimAndCheckEmptyReference(quoteAndReference[1]);
        return new Quote(quote, reference);
    }

    public static String[] trimAndCheckReferenceFlag(String userInput) throws QuotesifyException {
        String[] quoteAndReference = userInput.split(FLAG_REFERENCE, 2);
        if (hasExtraReferenceFlag(quoteAndReference[1])) {
            throw new QuotesifyException(ERROR_DUPLICATE_REFERENCE_FLAG);
        }
        return quoteAndReference;
    }

    public static boolean hasExtraReferenceFlag(String reference) {
        if (!reference.contains(FLAG_REFERENCE)) {
            return false;
        } else {
            return true;
        }
    }

    public static String trimAndCheckEmptyReference(String reference) throws QuotesifyException {
        assert !reference.isEmpty() : "reference field should not be empty";
        reference = reference.trim();
        if (!reference.isEmpty()) {
            return reference;
        } else {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        }
    }

    public static Quote parseQuoteWithAuthorInput(String userInput) throws QuotesifyException {
        // Throws exception if quote of author name is empty
        String[] quoteAndAuthor = trimAndCheckAuthorFlags(userInput);
        String quote = trimAndCheckEmptyQuote(quoteAndAuthor[0]);
        Author author = trimAndCheckEmptyAuthor(quoteAndAuthor[1]);
        return new Quote(quote, author);
    }

    public static String[] trimAndCheckAuthorFlags(String userInput) throws QuotesifyException {
        String[] quoteAndAuthor = userInput.split(FLAG_AUTHOR, 2);
        if (hasExtraAuthorFlag(quoteAndAuthor[1])) {
            throw new QuotesifyException(ERROR_DUPLICATE_AUTHOR_FLAG);
        }
        return quoteAndAuthor;
    }

    public static boolean hasExtraAuthorFlag(String authorName) {
        if (!authorName.contains(FLAG_AUTHOR)) {
            return false;
        } else {
            return true;
        }
    }

    public static Author trimAndCheckEmptyAuthor(String authorName) throws QuotesifyException {
        assert !authorName.isEmpty() : "author field should not be empty";
        authorName = authorName.trim();
        if (!authorName.isEmpty()) {
            return new Author(authorName);
        } else {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static String parseListWithAuthor(String userInput) throws QuotesifyException {
        String[] authorFlagAndName = userInput.split(FLAG_AUTHOR, 2);
        try {
            String authorName = authorFlagAndName[1].trim();
            if (hasExtraAuthorFlag(authorName)) {
                throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
            }
            return authorName;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    public static String parseListWithReference(String userInput) throws QuotesifyException {
        String[] referenceFlagAndName = userInput.split(FLAG_REFERENCE, 2);
        try {
            String reference = referenceFlagAndName[1].trim();
            if (hasExtraReferenceFlag(reference)) {
                throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
            }
            return reference;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        }
    }

    public static int getQuoteNumber(String userInput, QuoteList quotes, String command) throws QuotesifyException {
        try {
            if (!userInput.contains(command))  {
                throw new QuotesifyException("The \"" + command + "\" flag is missing!");
            }
            int quoteNumberToEdit = Integer.parseInt(userInput.split(command, 2)[0].trim());
            if (quoteNumberToEdit <= 0 || quoteNumberToEdit > quotes.getSize()) {
                throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
            } else {
                return quoteNumberToEdit - 1;
            }
        } catch (NumberFormatException e) {
            throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
        }
    }

    public static Quote getEditedQuote(String userInput) throws QuotesifyException {
        String quoteAndInformation = userInput.split(Command.FLAG_EDIT, 2)[1];
        return parseAddParameters(quoteAndInformation);
    }

    public static String getReflectionToAdd(String userInput) throws QuotesifyException {
        try {
            String reflection = userInput.split(Command.FLAG_REFLECT, 2)[1].trim();
            return reflection;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        }
    }

    public static String getEditedReflection(String userInput) {
        String updatedReflection = userInput.split(Command.FLAG_EDIT, 2)[1].trim();
        return updatedReflection;
    }
}
