package seedu.duke.constants;

public class FilterMessages {
    public static final String INVALID_FILTER_CODE = "invalid filter type in the code";
    public static final String NO_FILTER_RESULT = "Filter returns no result!!";
    public static final String FILTER_MESSAGE = "Printing all %d word(s) in your filter list:\n";
    public static final String FILTER_MESSAGE_LIMIT = "Printing the first %d out of %d word(s) "
            + "from your filter list:\n";
    public static final String PRINT_LIMIT_NOT_FOUND = "There are cases that\n"
            + "- You didn't specify the print limit\n" + "- Your limit is not an integer\n"
            + "The program will print out all the words in your filter list.\n";
    public static final String FILTER_UNKNOWN_COMMAND = "Filter command format is incorrect.";
    public static final String FILTER_UNKNOWN_TYPE = "Filter type was not recognized in the command.";
    public static final String LONG_FILTER_LIST_MESSAGE = "Your filter list has more than %d word(s)\n"
            + "Do you want to print all the words in the filter list? y/n\n";
    public static final String INVALID_LIST_FILTER_ANSWER = "Your answer is not y/n. "
            + "The filter list will not be printed."
            + "You can use \"list filter words\" command to print out the filter result.";
    public static final String INVALID_LIMIT_LIST_FILTER_WORDS =
            "Your print limit is not valid so no words will be printed out.\n"
            + "You can use \"list filter words\" command to print out the filter result.";
    public static final String INVALID_PRINT_LIMIT_MESSAGE = "Your limit is invalid.";

}
