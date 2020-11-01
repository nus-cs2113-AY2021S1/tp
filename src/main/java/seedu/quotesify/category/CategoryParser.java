package seedu.quotesify.category;

import seedu.quotesify.exception.QuotesifyException;
import seedu.quotesify.ui.UiMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CategoryParser {
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";
    private static final String ERROR_INVALID_PARAMS = "Invalid parameters!";

    private static Stack<String> convertStringArrayToStack(String[] tokens) {
        Stack<String> parameters = new Stack<>();
        for (String token : tokens) {
            parameters.push(token);
        }
        return parameters;
    }

    public static String[] getRequiredParameters(String[] tokens) throws QuotesifyException {
        String categories;
        String bookTitle = "";
        String quoteNum = "";
        int bookTagCount = 0;
        int quoteTagCount = 0;

        Stack<String> parameters = convertStringArrayToStack(tokens);
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

        return new String[]{categories, bookTitle, quoteNum};
    }

    public static int validateParametersResult(String[] parameters) {
        String categoryName = parameters[0];
        String bookNum = parameters[1];
        String quoteNum = parameters[2];

        if (categoryName.isEmpty()) {
            return -1;
        }

        if (quoteNum.isEmpty() && bookNum.isEmpty()) {
            return 0;
        }
        return 1;
    }

    public static String[] getEditParameters(String information) throws QuotesifyException {
        try {
            String[] oldAndNewCategory = information.split(" /to ", 2);
            return new String[]{oldAndNewCategory[0].trim(), oldAndNewCategory[1].trim()};
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new QuotesifyException(ERROR_INVALID_PARAMS
                    + System.lineSeparator() + UiMessage.EDIT_CATEGORY_COMMAND);
        }
    }

    public static List<String> parseCategoriesToList(String categories) {
        return Arrays.asList(categories.split(" "));
    }
}
