package fitr.common;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String FITR_WORD = " _______   __   __\n"
            + "|    ___| |__| |  |_  .----.\n"
            + "|    ___| |  | |   _| |   _|\n"
            + "|___|     |__| |____| |__|\n";
    public static final String MESSAGE_GREET = FITR_WORD + "Hello! Welcome to Fitr.";
    public static final String MESSAGE_SUGGEST_QUESTION = "What can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String PHRASE_EXTRA_PARAMETERS = "Extra parameters";
    //Format messages
    public static final String FORMAT_FOOD = "food NAME_OF_FOOD /NUMBER_OF_CALORIES [QUANTITY]";
    public static final String FORMAT_EXERCISE = "exercise NAME_OF_EXERCISE /CALORIES_BURNT";
    public static final String FORMAT_FOOD_GOAL = "goal food GOAL_DESCRIPTION";
    public static final String FORMAT_SMART_FOOD_GOAL = "goal food < NUMBER_OF_CALORIES or goal food > "
            + "NUMBER_OF_CALORIES";
    public static final String FORMAT_EXERCISE_GOAL = "goal exercise GOAL_DESCRIPTION";
    public static final String FORMAT_SMART_EXERCISE_GOAL = "goal exercise < CALORIES_BURNT or goal exercise "
            + "> CALORIES_BURNT";
    public static final String FORMAT_VIEW_PROFILE = "view profile";
    public static final String FORMAT_VIEW_BMI = "view bmi";
    public static final String FORMAT_VIEW_FOOD = "view food";
    public static final String FORMAT_VIEW_FOOD_ON_SPECIFIED_DATE = "view food DATE/MONTH/YEAR (dd/MM/yyyy)";
    public static final String FORMAT_VIEW_EXERCISE = "view exercise";
    public static final String FORMAT_VIEW_EXERCISE_ON_SPECIFIED_DATE = "view exercise DATE/MONTH/YEAR (dd/MM/yyyy)";
    public static final String FORMAT_VIEW_GOAL = "view goal";
    public static final String FORMAT_VIEW_SUMMARY = "view summary";
    public static final String FORMAT_VIEW_SUMMARY_ON_SPECIFIED_DATE = "view summary DATE/MONTH/YEAR (dd/MM/yyyy)";
    public static final String FORMAT_EDIT_NAME = "edit name NEW_NAME";
    public static final String FORMAT_EDIT_AGE = "edit age NEW_AGE";
    public static final String RANGE_EDIT_AGE = "Valid range for age: 1 to 130";
    public static final String FORMAT_EDIT_GENDER = "edit gender NEW_GENDER ('M' for Male or 'F' for Female)";
    public static final String FORMAT_EDIT_HEIGHT = "edit height NEW_HEIGHT (in m)";
    public static final String RANGE_EDIT_HEIGHT = "Valid range for height (in m): 0.50 to 4.00";
    public static final String FORMAT_EDIT_WEIGHT = "edit weight NEW_WEIGHT (in kg)";
    public static final String RANGE_EDIT_WEIGHT = "Valid range for weight (in kg): 2.00 to 1000.00";
    public static final String FORMAT_EDIT_FITNESS = "edit fitness NEW_FITNESS_LEVEL (0 for Unfit; 1 for Normal; 2 for"
            + " Fit)";
    public static final String FORMAT_EDIT_FOOD =
            "edit food dd/MM/yyyy INDEX NAME_OF_FOOD /NUMBER_OF_CALORIES QUANTITY";
    public static final String FORMAT_EDIT_EXERCISE =
            "edit exercise dd/MM/yyyy INDEX NAME_OF_EXERCISE /CALORIES_BURNT";
    public static final String FORMAT_EDIT_GOAL = "edit goal INDEX TYPE_OF_GOAL GOAL_DESCRIPTION";
    public static final String FORMAT_DELETE_FOOD = "delete food DATE/MONTH/YEAR (dd/MM/yyyy) INDEX";
    public static final String FORMAT_DELETE_EXERCISE = "delete exercise DATE/MONTH/YEAR (dd/MM/yyyy) INDEX";
    public static final String FORMAT_DELETE_GOAL = "delete goal INDEX";
    public static final String FORMAT_CLEAR_FOOD = "clear food";
    public static final String FORMAT_CLEAR_EXERCISE = "clear exercise";
    public static final String FORMAT_CLEAR_GOAL = "clear goal";
    public static final String FORMAT_MARK_GOAL_AS_COMPLETE = "complete goal INDEX";

    //User class messages
    public static final String USER_SETUP_GREET = "Before we begin, let me get to know you :)";
    public static final String INPUT_NAME = "Please enter your name:";
    public static final String INPUT_AGE = "Please enter your age:";
    public static final String INPUT_HEIGHT = "Please enter your height (in m):";
    public static final String INPUT_WEIGHT = "Please enter your weight (in kg):";
    public static final String INPUT_GENDER = "Please enter your gender (Enter 'M' for Male or 'F' for Female):";
    public static final String SETUP_COMPLETE = "Setup complete!";
    public static final String ERROR_INVALID_AGE_INPUT = "Oops! That is an invalid age input.";
    public static final String ERROR_INVALID_GENDER_INPUT = "Oops! That is an invalid gender input.";
    public static final String ERROR_INVALID_HEIGHT_INPUT = "Oops! That is an invalid height input.";
    public static final String ERROR_INVALID_WEIGHT_INPUT = "Oops! That is an invalid weight input.";
    public static final String ERROR_INVALID_FITNESS_INPUT = "Oops! That is an invalid fitness level input.";
    public static final String NAME_OUTPUT_HEADER = "Name: ";
    public static final String AGE_OUTPUT_HEADER = "Age: ";
    public static final String GENDER_OUTPUT_HEADER = "Gender: ";
    public static final String HEIGHT_OUTPUT_HEADER = "Height (in m): ";
    public static final String WEIGHT_OUTPUT_HEADER = "Weight (in kg): ";
    public static final String FITNESS_OUTPUT_HEADER = "Your fitness level: ";
    public static final String LINE_BREAK = "\n";
    public static final String MALE_SYMBOL = "M";
    public static final String FEMALE_SYMBOL = "F";
    public static final String MALE_STRING = "Male";
    public static final String FEMALE_STRING = "Female";
    public static final String FIT_STRING = "Fit";
    public static final String UNFIT_STRING = "Unfit";
    public static final String NORMAL_STRING = "Normal";
    public static final String NULL_STRING = "NULL";

    public static final String ERROR_INVALID_FORMAT = "Sorry, you have keyed in an invalid format!";
    public static final String ERROR_INVALID_COMMAND = "Sorry, this is an invalid command!";
    public static final String ERROR_IN_FILE = "Sorry, there is an error in the file.";
    public static final String ERROR_INVALID_CALORIE = "Sorry, invalid calorie amount entered.";
    public static final String ERROR_FORMAT_MESSAGE = "Please input in the correct format!";
    public static final String ERROR_INVALID_INDEX = "Sorry, that index does not exist in the list";

    //ViewCommand class messages
    public static final String EMPTY_FOOD_LIST = "The food list is empty...";
    public static final String EMPTY_EXERCISE_LIST = "The exercise list is empty...";
    public static final String EMPTY_EXERCISE_LIST_DATE = "The exercise list on that date is empty...";
    public static final String EMPTY_FOOD_LIST_DATE = "The food list on that date is empty...";
    public static final String EMPTY_GOAL_LIST = "The goal list is empty...";
    public static final String FOOD_LIST_HEADER = "Here is the list of your food:";
    public static final String EXERCISE_LIST_HEADER = "Here is the list of your exercises:";
    public static final String GOAL_LIST_HEADER = "Here is the list of your goals:";
    public static final String CALORIE_CONSUMED_HEADER = "Total calorie consumed:";
    public static final String CALORIE_BURNT_HEADER = "Total calorie burnt:";
    public static final String NET_CALORIE_HEADER = "Net calorie:";
    public static final String BMI_HEADER = "Your BMI is: ";
    public static final String USER_PROFILE_HEADER = "User profile:";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSE_SQUARE_BRACKET = "] ";
    public static final String FOOD_HEADER = "Food: ";
    public static final String QUANTITY_HEADER = "Quantity: ";
    public static final String EXERCISE_HEADER = "Exercise: ";
    public static final String SPACE_FORMATTING = "\n    ";
    public static final String CAL_HEADER = "Total Calorie(s): ";
    public static final String DATE_HEADER = "Date: ";
    public static final String BURNT_CAL_HEADER = "Burnt Cal: ";
    public static final String INTENSITY_CAL_HEADER = "Intensity: ";
    public static final String EMPTY_STRING = "";
    public static final String ERROR_INVALID_DATE = "Sorry, please enter a correct date in the format dd/MM/yyyy";
    public static final String NO_RECORDS_FOUND = "No records found!";

    //EditProfileCommand class messages
    public static final String EDIT_NAME = "name";
    public static final String EDIT_HEIGHT = "height";
    public static final String EDIT_WEIGHT = "weight";
    public static final String EDIT_AGE = "age";
    public static final String EDIT_GENDER = "gender";
    public static final String EDIT_FITNESS = "fitness";
    public static final String MISSING_FILE = "Theres no file";
    public static final String NAME_ECHO_HEADER = "Your current name is: ";
    public static final String HEIGHT_ECHO_HEADER = "Your current height (in m) is: ";
    public static final String WEIGHT_ECHO_HEADER = "Your current weight (in kg) is: ";
    public static final String AGE_ECHO_HEADER = "Your current age is: ";
    public static final String GENDER_ECHO_HEADER = "Your current gender is: ";
    public static final String INPUT_FITNESS_LEVEL = "Please indicate your fitness level to be used for determining "
            + "intensity of exercises.\n(0 for Unfit; 1 for Normal; 2 for Fit): ";
    public static final String FITNESS_ECHO_HEADER = "Your current fitness level is: ";

    public static final String SYMBOL_YES = "Y";
    public static final String SYMBOL_NO = "N";
    public static final String SYMBOL_EXERCISE = "E";
    public static final String SYMBOL_FOOD = "F";
    public static final String KEYWORD_FOOD_MORE_THAN = "Eat more than ";
    public static final String KEYWORD_FOOD_LESS_THAN = "Eat less than ";
    public static final String KEYWORD_EXERCISE_MORE_THAN = "Burn more than ";
    public static final String KEYWORD_EXERCISE_LESS_THAN = "Burn less than ";
    public static final String ECHO_ADDED_GOAL = "Okay! The following goal has been added: \n\t[";

    public static final String ECHO_ADDED_EXERCISE = "The following exercise has been added:";
    public static final String ECHO_ADDED_FOOD = "The following food has been added:\n";
    public static final String EXERCISE_NAME_HEADER = "Name of Exercise: ";
    public static final String FOOD_NAME_HEADER = "Name of Food: ";
    public static final String VIEW_FOOD_TIP = "Tip: You may also view food entries by day using the format "
            + "\"view food dd/MM/yyyy\"";
    public static final String VIEW_EXERCISE_TIP = "Tip: You may also view exercise entries by day using the format "
            + "\"view exercise dd/MM/yyyy\"";
    public static final String VIEW_SUMMARY_TIP = "Tip: You may also view summary by day using the format "
            + "\"view summary dd/MM/yyyy\"";
    public static final String ADD_SMART_FOOD_GOAL_TIP = "Tip: You may also add a smart food goal using the format "
            + "\"" + FORMAT_SMART_FOOD_GOAL + "\"";
    public static final String ADD_SMART_EXERCISE_GOAL_TIP = "Tip: You may also add a smart food goal using the format "
            + "\"" + FORMAT_SMART_EXERCISE_GOAL + "\"";
}
