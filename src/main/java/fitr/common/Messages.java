package fitr.common;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_GREET = "Hello! Welcome to Fitr.";
    public static final String MESSAGE_SUGGEST_QUESTION = "What can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    public static final String FORMAT_FOOD = "food <Name of food> / <Number of Calories> <Quantity (Optional)>";
    public static final String FORMAT_EXERCISE = "exercise <Name of exercise> / "
            + "<Number of Calories> <Quantity (Optional)>";
    public static final String FORMAT_DELETE = "delete food <Index from Food List> "
            + "or delete exercise <Index from Exercise List>";

    //User class messages
    public static final String USER_SETUP_GREET = "Before we begin, let me get to know you :)";
    public static final String INPUT_NAME = "Please enter your name:";
    public static final String INPUT_AGE = "Please enter your age:";
    public static final String INPUT_HEIGHT = "Please enter your height (in m):";
    public static final String INPUT_WEIGHT = "Please enter your weight (in kg):";
    public static final String INPUT_GENDER = "Please enter your gender (Enter 'M' for Male or 'F' for Female):";
    public static final String SETUP_COMPLETE = "Setup complete!";
    public static final String ERROR_INVALID_AGE_INPUT = "Oops! That is an invalid age input.\n";
    public static final String ERROR_INVALID_GENDER_INPUT = "Oops! That is an invalid gender input.\n";
    public static final String ERROR_INVALID_HEIGHT_INPUT = "Oops! That is an invalid height input.\n";
    public static final String ERROR_INVALID_WEIGHT_INPUT = "Oops! That is an invalid weight input.\n";
    public static final String NAME_OUTPUT_HEADER = "Name: ";
    public static final String AGE_OUTPUT_HEADER = "Age: ";
    public static final String GENDER_OUTPUT_HEADER = "Gender: ";
    public static final String HEIGHT_OUTPUT_HEADER = "Height (in m): ";
    public static final String WEIGHT_OUTPUT_HEADER = "Weight (in kg): ";
    public static final String LINE_BREAK = "\n";
    public static final String MALE_SYMBOL = "M";
    public static final String FEMALE_SYMBOL = "F";
    public static final String MALE_STRING = "Male";
    public static final String FEMALE_STRING = "Female";


    public static final String ERROR_INVALID_COMMAND = "Sorry this is an invalid command!";
    public static final String ERROR_INVALID_INDEX = "Please input a valid index!";
    public static final String ERROR_INVALID_CALORIES = "Sorry! Calories should be a number.";

    //ViewCommand class messages
    public static final String ERROR_INVALID_VIEW_COMMAND = "Sorry this is an invalid view command!";
    public static final String EMPTY_FOOD_LIST = "The food list is empty...";
    public static final String EMPTY_EXERCISE_LIST = "The exercise list is empty...";
    public static final String FOOD_LIST_HEADER = "Here is the list of your food:";
    public static final String EXERCISE_LIST_HEADER = "Here is the list of your exercises:";
    public static final String CALORIE_CONSUMED_HEADER = "Total calorie consumed:";
    public static final String CALORIE_BURNT_HEADER = "Total calorie burnt:";
    public static final String NET_CALORIE_HEADER = "Net calorie:";
    public static final String BMI_HEADER = "Your BMI is: ";
    public static final String USER_PROFILE_HEADER = "User profile:";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "] ";
    public static final String FOOD_HEADER = "Food: ";
    public static final String EXERCISE_HEADER = "Exercise: ";
    public static final String SPACE_FORMATTING = "\n    ";
    public static final String CAL_HEADER = "Cal: ";
    public static final String BURNT_CAL_HEADER = "Burnt Cal: ";

    //EditProfileCommand class messages
    public static final String EDIT_NAME = "edit name";
    public static final String EDIT_HEIGHT = "edit height";
    public static final String EDIT_WEIGHT = "edit weight";
    public static final String EDIT_AGE = "edit age";
    public static final String EDIT_GENDER = "edit gender";
    public static final String MISSING_FILE = "Theres no file";
    public static final String EDIT_NAME_HEADER = "Change your name to: ";
    public static final String NAME_ECHO_HEADER = "Name changed to: ";
    public static final String EDIT_HEIGHT_HEADER = "Change your height (in m) to: ";
    public static final String HEIGHT_ECHO_HEADER = "Height (in m) changed to: ";
    public static final String EDIT_WEIGHT_HEADER = "Change your weight (in kg) to: ";
    public static final String WEIGHT_ECHO_HEADER = "Weight (in kg) changed to: ";
    public static final String EDIT_AGE_HEADER = "Change your age to: ";
    public static final String AGE_ECHO_HEADER = "Age changed to: ";
    public static final String EDIT_GENDER_HEADER = "Change your gender (M|F) to: ";
    public static final String GENDER_ECHO_HEADER = "Gender changed to: ";
}
