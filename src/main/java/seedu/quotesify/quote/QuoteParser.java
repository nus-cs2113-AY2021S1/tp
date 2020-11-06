package seedu.quotesify.quote;

import seedu.quotesify.author.Author;
import seedu.quotesify.commands.Command;
import seedu.quotesify.exception.QuotesifyException;

import java.util.HashMap;

/**
 * Represents a Quote Parser.
 */
public class QuoteParser {
    /** Flags. */
    public static final String FLAG_DELIMITER = "/";
    public static final String FLAG_AUTHOR = "/by";
    public static final String FLAG_REFERENCE = "/from";
    public static final String FLAG_REFLECT = "/reflect";
    public static final String FLAG_EDIT = "/to";

    /** Error messages for quote parser to return. */
    public static final String ERROR_MISSING_QUOTE = "I don't see the quote anywhere";
    public static final String ERROR_MISSING_AUTHOR = "Author name cannot be empty if \"/by\" flag is present";
    public static final String ERROR_MISSING_REFERENCE = "Reference field cannot be empty if \"/from\" flag is present";
    public static final String ERROR_MISSING_REFERENCE_OR_AUTHOR = "Author name and Reference cannot be empty if "
            + "\"/by\" flag and \"/from\" flag are present";
    public static final String ERROR_INVALID_QUOTE_NUM = "Quote number provided is invalid";
    public static final String ERROR_MISSING_QUOTE_NUM = "Please provide a quote number!";
    public static final String ERROR_INVALID_PARAMETERS = "Invalid parameters!";
    public static final String ERROR_DUPLICATE_REFERENCE_FLAG = "Reference flag can only be used once!";
    public static final String ERROR_DUPLICATE_AUTHOR_FLAG = "Author flag can only be used once!";
    public static final String ERROR_DUPLICATE_REFLECT_FLAG = "Reflect flag can only be used once!";
    public static final String ERROR_DUPLICATE_EDIT_FLAG = "Edit flag can only be used once!";
    public static final String ERROR_MISSING_REFLECTION_FIELD = "Please specify your reflection!";

    /**
     * Checks user input to determine which parser method to call to create the appropriate Quote object.
     *
     * @param userInput User specified input.
     * @return Quote object from the result of the subsequent parser method being called.
     * @throws QuotesifyException If invalid parameters are provided by user.
     */
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

    /**
     * Parses user input into a quote object with no author and reference information.
     *
     * @param userInput User specified input.
     * @return Quote object.
     * @throws QuotesifyException If an exception is thrown from trimAndCheckEmptyQuote method.
     */
    public static Quote parseQuoteWithoutFlags(String userInput) throws QuotesifyException {
        // Throws exception if quote is empty
        String quote = trimAndCheckEmptyQuote(userInput);
        assert !quote.isEmpty() : "quote should not be empty";
        return new Quote(quote);
    }

    /**
     * Checks if quote String is empty.
     *
     * @param quote User specified quote String.
     * @return Trimmed quote after being checked.
     * @throws QuotesifyException If quote field is empty.
     */
    public static String trimAndCheckEmptyQuote(String quote) throws QuotesifyException {
        assert !quote.isEmpty() : "quote field should not be empty";
        quote = quote.trim();
        if (!quote.isEmpty()) {
            return quote;
        } else {
            throw new QuotesifyException(ERROR_MISSING_QUOTE);
        }
    }

    /**
     * Parses user input into a Quote object with reference and author information.
     *
     * @param userInput User specified input.
     * @return Quote object.
     * @throws QuotesifyException If invalid parameters are provided by user.
     */
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

    /**
     * Gets reference title and author name.
     *
     * @param userInput User specified input.
     * @return Hashmap containing  reference title and author name.
     * @throws QuotesifyException If reference title or author name is empty.
     */
    public static HashMap getReferenceAndAuthor(String userInput) throws QuotesifyException {
        String[] referenceAndAuthor = userInput.split(FLAG_DELIMITER,2);
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
        // throws exception is duplicate flags found
        checkExtraAuthorReferenceFlags(authorName, reference);
        assert !reference.isEmpty() : "reference field should not be empty";
        assert !authorName.isEmpty() : "author field should not be empty";

        HashMap<String, String> referenceAndAuthorName = new HashMap<String, String>();
        referenceAndAuthorName.put("reference", reference);
        referenceAndAuthorName.put("authorName", authorName);

        return referenceAndAuthorName;
    }

    /**
     * Checks author name and reference title for extra flags.
     *
     * @param authorName Author name String.
     * @param reference Reference title String.
     * @throws QuotesifyException If any of the extra flags are found.
     */
    public static void checkExtraAuthorReferenceFlags(String authorName, String reference) throws QuotesifyException {
        if (authorName.contains(FLAG_AUTHOR) || authorName.contains(FLAG_REFERENCE)) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        } else if (reference.contains(FLAG_AUTHOR) || reference.contains(FLAG_REFERENCE)) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        }
    }

    /**
     * Parses user input into a Quote object with reference title.
     *
     * @param userInput User specified input.
     * @return Quote object.
     * @throws QuotesifyException If invalid parameters are provided by user.
     */
    public static Quote parseQuoteWithReferenceInput(String userInput) throws QuotesifyException {
        // Throws exception if quote of reference is empty
        String[] quoteAndReference = splitAndCheckReferenceFlag(userInput);
        String quote = trimAndCheckEmptyQuote(quoteAndReference[0]);
        String reference = trimAndCheckEmptyReference(quoteAndReference[1]);
        assert !quote.isEmpty() && !reference.isEmpty() : "quote and reference should not be empty";
        return new Quote(quote, reference);
    }

    /**
     * Splits user input to obtain quote and reference title.
     *
     * @param userInput User specified input.
     * @return String array containing quote and reference title.
     * @throws QuotesifyException If extra reference flag is found.
     */
    public static String[] splitAndCheckReferenceFlag(String userInput) throws QuotesifyException {
        String[] quoteAndReference = userInput.split(FLAG_REFERENCE, 2);
        if (hasExtraFlag(quoteAndReference[1], FLAG_REFERENCE)) {
            throw new QuotesifyException(ERROR_DUPLICATE_REFERENCE_FLAG);
        }
        return quoteAndReference;
    }

    /**
     * General method that checks user input for a specified extra flag.
     *
     * @param userInput User specified input.
     * @param flag Developer specified flag type to check.
     * @return False if extra flag is not found, true otherwise.
     */
    public static boolean hasExtraFlag(String userInput, String flag) {
        if (!userInput.contains(flag)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Trim reference title and checks if empty.
     *
     * @param reference Reference title.
     * @return Trimmed reference title.
     * @throws QuotesifyException If reference title is empty.
     */
    public static String trimAndCheckEmptyReference(String reference) throws QuotesifyException {
        assert !reference.isEmpty() : "reference field should not be empty";
        reference = reference.trim();
        if (!reference.isEmpty()) {
            return reference;
        } else {
            throw new QuotesifyException(ERROR_MISSING_REFERENCE);
        }
    }

    /**
     * Parses user input into a Quote object with author name.
     *
     * @param userInput User specified input.
     * @return Quote object.
     * @throws QuotesifyException If invalid parameters are provided by user.
     */
    public static Quote parseQuoteWithAuthorInput(String userInput) throws QuotesifyException {
        // Throws exception if quote of author name is empty
        String[] quoteAndAuthor = splitAndCheckAuthorFlags(userInput);
        String quote = trimAndCheckEmptyQuote(quoteAndAuthor[0]);
        Author author = trimAndCheckEmptyAuthor(quoteAndAuthor[1]);
        assert !quote.isEmpty() && !author.getName().isEmpty() : "quote and author name should not be empty";
        return new Quote(quote, author);
    }

    /**
     * Splits user input to obtain quote and author name.
     *
     * @param userInput User specified input.
     * @return String array containing quote and author name.
     * @throws QuotesifyException If extra author flag is found.
     */
    public static String[] splitAndCheckAuthorFlags(String userInput) throws QuotesifyException {
        String[] quoteAndAuthor = userInput.split(FLAG_AUTHOR, 2);
        if (hasExtraFlag(quoteAndAuthor[1], FLAG_AUTHOR)) {
            throw new QuotesifyException(ERROR_DUPLICATE_AUTHOR_FLAG);
        }
        return quoteAndAuthor;
    }

    /**
     * Trim author name and check if empty.
     *
     * @param authorName Author name.
     * @return Trimmed author name.
     * @throws QuotesifyException If author name is empty.
     */
    public static Author trimAndCheckEmptyAuthor(String authorName) throws QuotesifyException {
        assert !authorName.isEmpty() : "author field should not be empty";
        authorName = authorName.trim();
        if (!authorName.isEmpty()) {
            return new Author(authorName);
        } else {
            throw new QuotesifyException(ERROR_MISSING_AUTHOR);
        }
    }

    /**
     * Parses the user list command to identify author name or reference title depending on flag supplied.
     *
     * @param userInput User specified input.
     * @param flag Developer supplied author flag or reference flag.
     * @return String containing author name or reference title depending on the flag supplied.
     * @throws QuotesifyException If extra flag is found or information field is empty.
     */
    public static String parseListCommand(String userInput, String flag) throws QuotesifyException {
        try {
            String information = userInput.split(flag, 2)[1].trim();
            if (information.isEmpty() && flag.equals(FLAG_AUTHOR)) {
                throw new QuotesifyException(ERROR_MISSING_AUTHOR);
            } else if (information.isEmpty() && flag.equals(FLAG_REFERENCE)) {
                throw new QuotesifyException(ERROR_MISSING_AUTHOR);
            } else if (hasExtraFlag(information, flag)) {
                throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
            }
            assert !information.isEmpty();
            return information;
        } catch (ArrayIndexOutOfBoundsException e) {
            if (flag.equals(FLAG_AUTHOR)) {
                throw new QuotesifyException(ERROR_MISSING_AUTHOR);
            } else {
                throw new QuotesifyException(ERROR_MISSING_REFERENCE);
            }
        }
    }

    /**
     * Gets quote number from user input.
     *
     * @param userInput User specified input.
     * @param quoteListSize Size of quote list.
     * @param flag Developer specified flag to check.
     * @return Quote number
     * @throws QuotesifyException If required flag is not found, quote number is invalid or invalid use of extra flags.
     */
    public static int getQuoteNumber(String userInput, int quoteListSize, String flag) throws QuotesifyException {
        try {
            if (!userInput.contains(flag)) {
                throw new QuotesifyException("Invalid parameters, the \"" + flag + "\" flag is missing!");
            }
            String[] quoteNumberAndInformation = userInput.split(flag, 2);
            if (quoteNumberAndInformation[0].isEmpty()) {
                throw new QuotesifyException(ERROR_MISSING_QUOTE_NUM);
            } else if (quoteNumberAndInformation[0].contains(FLAG_DELIMITER)) {
                throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
            }
            int quoteNumberToEdit = Integer.parseInt(quoteNumberAndInformation[0].trim());
            if (quoteNumberToEdit <= 0 || quoteNumberToEdit > quoteListSize) {
                throw new QuotesifyException(ERROR_INVALID_QUOTE_NUM);
            }
            return quoteNumberToEdit - 1;
        } catch (NumberFormatException e) {
            if (!userInput.contains(flag)) {
                throw new QuotesifyException("Invalid parameters, the \"" + flag + "\" flag is missing!");
            }
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        }
    }

    /**
     * Gets edited quote from user input.
     *
     * @param userInput User specified input.
     * @return Edited Quote object.
     * @throws QuotesifyException If invalid parameters are provided by user.
     */
    public static Quote getEditedQuote(String userInput) throws QuotesifyException {
        String quoteAndInformation = userInput.split(Command.FLAG_EDIT, 2)[1];
        return parseAddParameters(quoteAndInformation);
    }

    /**
     * Gets reflection from user input.
     *
     * @param userInput User specified input.
     * @return String containing reflection.
     * @throws QuotesifyException If reflection is empty or extra reflect flag is found.
     */
    public static String getReflectionToAdd(String userInput) throws QuotesifyException {
        try {
            String reflection = userInput.split(Command.FLAG_REFLECT, 2)[1].trim();
            if (reflection.isEmpty()) {
                throw new QuotesifyException(ERROR_MISSING_REFLECTION_FIELD);
            }
            if (hasExtraFlag(reflection, FLAG_REFLECT)) {
                throw new QuotesifyException(ERROR_DUPLICATE_REFLECT_FLAG);
            }
            assert !reflection.isEmpty();
            return reflection;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_INVALID_PARAMETERS);
        }
    }

    /**
     * Gets edited reflection from user input.
     *
     * @param userInput User specifed input.
     * @return String containing edited reflection.
     * @throws QuotesifyException If extra edit flag is found or reflection is empty.
     */
    public static String getEditedReflection(String userInput) throws QuotesifyException {
        String updatedReflection = userInput.split(FLAG_EDIT, 2)[1].trim();
        if (hasExtraFlag(updatedReflection, FLAG_EDIT)) {
            throw new QuotesifyException(ERROR_DUPLICATE_EDIT_FLAG);
        }
        if (updatedReflection.isEmpty()) {
            throw new QuotesifyException(ERROR_MISSING_REFLECTION_FIELD);
        }
        assert !updatedReflection.isEmpty();
        return updatedReflection;
    }
}
