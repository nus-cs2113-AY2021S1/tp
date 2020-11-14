package seedu.quotesify.category;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.ui.UiMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Represents a Category Parser.
 */
public class CategoryParser {
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";
    private static final String TO_FLAG = " /to ";
    private static final String SPACE = " ";
    private static final String ERROR_INVALID_PARAMS = "Invalid parameters!";
    private static final String ERROR_TO_FLAG_DETECTED = "The flag \"/to\" is not allowed as a category name!";
    private static final String ERROR_TAG_BOOK_DETECTED = "The tag \"-b\" is not allowed as a category name!";
    private static final String ERROR_TAG_QUOTE_DETECTED = "The tag \"-q\" is not allowed as a category name!";
    private static final String ERROR_NO_SPACE_ALLOWED = "No spaces are allowed in categories, use a - instead!";

    /**
     * Converts a string array to a stack.
     *
     * @param tokens Array of string arguments.
     * @return A stack of string arguments.
     */
    private static Stack<String> convertStringArrayToStack(String[] tokens) {
        Stack<String> parameters = new Stack<>();
        for (String token : tokens) {
            parameters.push(token);
        }
        return parameters;
    }

    /**
     * Fetches the essential parameters from a string array.
     *
     * @param information User input arguments.
     * @return Parameters in {categories, book-number, quote-number, book-tag-count, quote-tag-count} format.
     * @throws QuotesifyException If invalid parameters are specified.
     */
    public static String[] getRequiredParameters(String information) throws QuotesifyException {
        String categories;
        String bookTitle = "";
        String quoteNum = "";
        int bookTagCount = 0;
        int quoteTagCount = 0;

        Stack<String> parameters = convertStringArrayToStack(information.split(" "));
        String line = "";

        while (!parameters.empty()) {
            String item = parameters.pop();
            if (item.equals(TAG_BOOK)) {
                bookTagCount++;
                bookTitle = line.trim();
                line = "";
                continue;
            } else if (item.equals(TAG_QUOTE)) {
                quoteTagCount++;
                quoteNum = line.trim();
                line = "";
                continue;
            }
            line = item + " " + line;
        }
        categories = line.trim();

        if (bookTagCount > 1 || quoteTagCount > 1) {
            throw new QuotesifyException(ERROR_INVALID_PARAMS);
        }

        return new String[]{categories, bookTitle, quoteNum, bookTagCount + "", quoteTagCount + ""};
    }

    /**
     * Returns an integer result after validation of parameters.
     *
     * @param parameters Parameters from getRequiredParameters().
     * @return -1 If categories is empty, 0 if books and quotes are not specified, 1 if both are specified.
     */
    public static int validateParametersResult(String[] parameters) {
        String categoryName = parameters[0];
        int bookTagCount = Integer.parseInt(parameters[3]);
        int quoteTagCount = Integer.parseInt(parameters[4]);

        if (categoryName.isEmpty()) {
            return -1;
        }

        if (bookTagCount == 0 && quoteTagCount == 0) {
            return 0;
        }

        return 1;
    }

    /**
     * Parses the user input for edit category command into a string array of parameters.
     *
     * @param information User specified input.
     * @return An array of parameters.
     * @throws QuotesifyException If invalid parameters are specified.
     */
    public static String[] getEditParameters(String information) throws QuotesifyException {
        try {
            String[] oldAndNewCategory = information.split(TO_FLAG, 2);
            validateCategoryName(oldAndNewCategory[1]);
            return new String[]{oldAndNewCategory[0].trim(), oldAndNewCategory[1].trim()};
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_INVALID_PARAMS
                    + System.lineSeparator() + UiMessage.EDIT_CATEGORY_COMMAND);
        }
    }

    /**
     * Checks category names for invalid input.
     *
     * @param name Category name.
     * @throws QuotesifyException If invalid inputs are detected.
     */
    public static void validateCategoryName(String name) throws QuotesifyException {
        if (name.equals(TO_FLAG.trim())) {
            throw new QuotesifyException(ERROR_TO_FLAG_DETECTED);
        } else if (name.contains(SPACE)) {
            throw new QuotesifyException(ERROR_NO_SPACE_ALLOWED);
        } else if (name.equals(TAG_BOOK)) {
            throw new QuotesifyException(ERROR_TAG_BOOK_DETECTED);
        } else if (name.equals(TAG_QUOTE)) {
            throw new QuotesifyException(ERROR_TAG_QUOTE_DETECTED);
        }
    }

    /**
     * Parses a string of space delimited category names into a list.
     *
     * @param categories User specified categories.
     * @return A list of category names.
     */
    public static List<String> parseCategoriesToList(String categories) {
        return Arrays.asList(categories.split(SPACE));
    }
}
