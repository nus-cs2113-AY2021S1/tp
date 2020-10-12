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

    public static final String INPUT_NAME = "Please enter your name:\n";
    public static final String INPUT_AGE = "Please enter your age:\n";
    public static final String INPUT_HEIGHT = "Please enter your height (in m):\n";
    public static final String INPUT_WEIGHT = "Please enter your weight (in kg):\n";
    public static final String INPUT_GENDER = "Please enter your gender (Enter 'M' for Male or 'F' for Female):\n";

    public static final String ERROR_INVALID_COMMAND = "Sorry this is an invalid command!";
    public static final String ERROR_INVALID_INDEX = "Please input a valid index!";
    public static final String ERROR_INVALID_CALORIES = "Sorry! Calories should be a number.";
    public static final String ERROR_INVALID_INPUT = "Oops! That is an invalid input.";
}
