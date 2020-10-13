package seedu.duke.common;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_GREET = "Hello! Welcome to Fitr. "
            + System.lineSeparator() + "What can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    public static final String FORMAT_FOOD = "food <Name of food> / <Number of Calories> <Quantity (Optional)>";
    public static final String FORMAT_EXERCISE = "exercise <Name of exercise> / "
            + "<Number of Calories> <Quantity (Optional)>";
    public static final String FORMAT_DELETE = "delete food <Index from Food List> "
            + "or delete exercise <Index from Exercise List>";

    //User class messages
    public static final String USER_SETUP_GREET = "Hi there, before we begin, let me get to know you :)";
    public static final String INPUT_NAME = "\nPlease enter your name:";
    public static final String INPUT_AGE = "\nPlease enter your age:";
    public static final String INPUT_HEIGHT = "\nPlease enter your height (in m):";
    public static final String INPUT_WEIGHT = "\nPlease enter your weight (in kg):";
    public static final String INPUT_GENDER = "\nPlease enter your gender (Enter 'M' for Male or 'F' for Female):";
    public static final String SETUP_COMPLETE = "\nSetup complete!\n";
    public static final String ERROR_INVALID_AGE_INPUT = "Oops! That is an invalid age input.\n";
    public static final String ERROR_INVALID_GENDER_INPUT = "Oops! That is an invalid gender input.\n";
    public static final String ERROR_INVALID_HEIGHT_INPUT = "Oops! That is an invalid height input.\n";
    public static final String ERROR_INVALID_WEIGHT_INPUT = "Oops! That is an invalid weight input.\n";

    public static final String ERROR_INVALID_COMMAND = "Sorry this is an invalid command!\n";
    public static final String ERROR_INVALID_INDEX = "Please input a valid index!\n";
    public static final String ERROR_INVALID_CALORIES = "Sorry! Calories should be a number.\n";

    //ViewCommand class messages
    public static final String ERROR_INVALID_VIEW_COMMAND = "Sorry this is an invalid view command!\n";
    public static final String EMPTY_FOOD_LIST = "The food list is empty...\n";
    public static final String EMPTY_EXERCISE_LIST = "The exercise list is empty...\n";
    public static final String FOOD_LIST_HEADER = "Here is the list of your food:\n";
    public static final String EXERCISE_LIST_HEADER = "Here is the list of your exercises:\n";
    public static final String CALORIE_CONSUMED_HEADER = "Total calorie consumed:\n";
    public static final String CALORIE_BURNT_HEADER = "Total calorie burnt:\n";
    public static final String NET_CALORIE_HEADER = "Net calorie:\n";
    public static final String BMI_HEADER = "Your BMI is:\n";
    public static final String USER_PROFILE_HEADER = "User profile:\n";

}
