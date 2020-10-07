package seedu.duke.category;

import java.util.Stack;

public class CategoryParser {
    private static final String TAG_BOOK = "-b";
    private static final String TAG_QUOTE = "-q";

    private static Stack<String> convertStringArrayToStack(String[] tokens) {
        Stack<String> parameters = new Stack<>();
        for (String token : tokens) {
            parameters.push(token);
        }
        return parameters;
    }

    public static String[] getRequiredParameters(String[] tokens) {
        String categoryName;
        String bookTitle = null;
        String quoteNum = "-1";

        Stack<String> parameters = convertStringArrayToStack(tokens);
        String line = "";

        while (!parameters.empty()) {
            String item = parameters.pop();
            if (item.equals(TAG_BOOK)) {
                bookTitle = line.trim();
                line = "";
                continue;
            } else if (item.equals(TAG_QUOTE)) {
                quoteNum = line.trim();
                line = "";
                continue;
            }
            line = item + " " + line;
        }
        categoryName = line.trim();
        return new String[]{categoryName, bookTitle, quoteNum};
    }
}
