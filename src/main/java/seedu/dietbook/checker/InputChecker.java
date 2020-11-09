package seedu.dietbook.checker;

import seedu.dietbook.exception.DietException;
import seedu.dietbook.parser.Parser;

import java.time.LocalDateTime;

//@@author tikimonarch
/**
 * InputChecker class of the program.
 * This class checks the validity of the user input and throws an exception if input is not as intended/expected.
 *
 * @author tikimonarch
 */
public class InputChecker {
    /**
     * The value limits are based on current limits observed in th world.
     */
    public static final int AGE_CAP = 150;
    public static final int FOOD_CAP = 100000;
    public static final int HEIGHT_CAP = 300;
    public static final int WEIGHT_CAP = 500;
    public static final String[] PARAM_FITNESS = {"1","2","3","4","5"};
    public static final String[] PARAM_ADD = {"n/","x/","k/"};
    public static final String[] FULL_PARAM_ADD = {"n/","x/","k/","c/","p/","f/"};
    public static final String[] PARAM_CALCULATE = {"fat", "carb","protein", "calorie", "all"};
    public static final String[] SINGLE_COMMAND = {"clear", "data","exit", "help", "recommend", "userinfo"};
    public static final String[] PARAM_GENDER = {"M","F","O"};
    public static final String[] PARAM_INFO = {"g/","a/","h/","f/","o/","t/","c/"};
    public static final String[] PARAM_EDIT_INFO = {"n/","g/","a/","h/","f/","o/","t/","c/"};

    /**
     * Takes in user input and command to check for any expected parameters after the command.
     *
     * @param userInput user input.
     * @param command command in user input.
     * @throws DietException when at least one parameter is expected but not present.
     */
    public static void checkEmpty(String userInput, String command) throws DietException {
        if (userInput.split(command).length < 2
                || userInput.split(command)[1].trim().equals("")) {
            throw new DietException("Error! Missing command parameters!");
        }
    }

    /**
     * Takes in user input to check for single word commands.
     *
     * @param input user input.
     * @throws DietException when a single word command has "options" attached.
     */
    public static void checkSingleCommand(String input) throws DietException {
        boolean isSingleCommand = false;
        for (String command: SINGLE_COMMAND) {
            if (input.trim().equals(command)) {
                isSingleCommand = true;
            }
        }
        if (!isSingleCommand) {
            throw new DietException("Error! This command has no option!");
        }
    }

    /**
     * Takes in processed user input to check for options specified with an empty field.
     *
     * @param input user input.
     * @throws DietException when an option is specified but its field is empty.
     */
    public static void checkEmptyOption(String[] input, String param) throws DietException {
        if (input.length > 1) {
            if ((input[1].trim().length() > 1 && input[1].trim().charAt(1) == '/') || input[1].trim().equals("")) {
                throw new DietException("Error! Option '" + param + "' specified with empty field!");
            }
        } else {
            throw new DietException("Error! Option specified with empty field!");
        }
    }

    /**
     * Takes in user input to check if at least 1 option is present.
     *
     * @param input user input.
     * @throws DietException when an option is not present.
     */
    public static void checkForOption(String input) throws DietException {
        String parameter = Parser.getCommandParam(input);
        boolean isValidOption = false;
        if (parameter.contains("/")) {
            String checker = parameter.substring(parameter.indexOf("/") - 1,parameter.indexOf("/") + 1);
            for (String param: PARAM_EDIT_INFO) {
                if (checker.equals(param)) {
                    isValidOption = true;
                }
            }
            if (!isValidOption) {
                throw new DietException("Error! No such option '" + checker + "'!");
            }
        } else {
            throw new DietException("Error! No option present!");
        }
    }

    /**
     * Takes in user input to check if all option specified are at least one of the expected option.
     *
     * @param input user input.
     * @param paramList the expected list of options.
     * @throws DietException when an option is not of the expected.
     */
    public static void checkValidOptions(String input, String[] paramList) throws DietException {
        String parameter = Parser.getCommandParam(input);
        long noOfOptions = parameter.chars().filter(num -> num == '/').count();
        int slashTracker = parameter.indexOf("/");
        boolean isValidOption;
        for (int i = 0; i < noOfOptions; i++) {
            isValidOption = false;
            String checker = parameter.substring(slashTracker - 1, slashTracker + 1);
            for (String param: paramList) {
                if (checker.equals(param)) {
                    isValidOption = true;
                }
            }
            if (!isValidOption) {
                throw new DietException("Error! No such option '" + checker + "'!");
            }
            slashTracker = parameter.indexOf("/", slashTracker + 1);
        }
    }

    /**
     * Takes in the parameter in which number is expected and checks for validity.
     *
     * @param number number to be checked.
     * @param param the parameter of the number being checked.
     * @throws DietException when a number is not valid.
     */
    public static void checkValidNumber(String number, String param) throws DietException {
        try {
            int check = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new DietException("Invalid value for option '" + param + "'!");
        }
    }

    /**
     * Takes in user input to check for repeated options.
     *
     * @param command command part of user input.
     * @param options option part of user input command.
     * @throws DietException when there are options repeatedly specified.
     */
    public static void checkRepeatedOption(String command, String options) throws DietException {
        String[] paramList = FULL_PARAM_ADD;
        if (command.equals("info")) {
            paramList = PARAM_INFO;
        }
        for (String param: paramList) {
            int countOccurrence = options.length() - options.replace(param, "").length();
            if (countOccurrence > 2) {
                throw new DietException("There are repeated options!");
            }
        }
    }

    /**
     * Takes in user input to check if date format is present.
     *
     * @param userInput user input.
     * @return boolean whereby true if date present, false otherwise.
     */
    public static boolean checkDate(String userInput) throws DietException {
        String[] processedInput = userInput.split("\\s+");
        if (processedInput[processedInput.length - 1].contains("T")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes in string format of date time to check if date format is correct.
     *
     * @param dateString string form of a potential date time.
     * @throws DietException if date format is wrong.
     */
    public static void checkDateValidity(String dateString) throws DietException {
        try {
            LocalDateTime.parse(dateString);
        } catch (Exception e) {
            throw new DietException("Wrong date time format!");
        }
    }

    /**
     * Takes in a date time object and see if it is a future date.
     *
     * @param time a date time class object.
     * @throws DietException if date is in the future.
     */
    public static void checkFutureDate(LocalDateTime time) throws DietException {
        if (time.isAfter(LocalDateTime.now())) {
            throw new DietException("The date cannot be in the future!");
        }
    }

    /**
     * Takes in the start and end date time objects and see if the end time is earlier than start time.
     *
     * @param startTime a date time class object, the start time.
     * @param endTime a date time class object, the end time.
     * @throws DietException if end date time is before the start date time.
     */
    public static void checkEndDate(LocalDateTime startTime, LocalDateTime endTime) throws DietException {
        if (startTime.isAfter(endTime)) {
            throw new DietException("The start date, time cannot be later than the end date, time!");
        }
    }

    /**
     * Takes in user input to check if the expected number and type of parameter for the add command is present.
     *
     * @param userInput user input.
     * @throws DietException when expected parameters are missing.
     */
    public static void checkAddParam(String userInput) throws DietException {
        for (String param: PARAM_ADD) {
            if (!userInput.contains(param)) {
                throw new DietException("Missing or incorrect add statement");
            }
        }
    }

    /**
     * Takes in user input to check if the expected number of parameter is present for the calculate command.
     *
     * @param param parameter part of user input.
     * @throws DietException when number of parameter is not as expected.
     */
    public static void checkCalculateParam(String[] param) throws DietException {
        if (param.length > 3) {
            throw new DietException("Incorrect calculate statement");
        }
    }

    /**
     * Takes in user input to check if the expected number and type of parameter for the info command is present.
     *
     * @param userInput user input.
     * @throws DietException when expected parameters are missing.
     */
    public static void checkInfoParam(String userInput) throws DietException {
        for (String param: PARAM_INFO) {
            if (!userInput.contains(param)) {
                throw new DietException("Missing or incorrect info statement");
            }
        }
    }

    /**
     * Takes in user input to check if the expected number of parameter is present for the list command.
     *
     * @param param parameter part of user input.
     * @throws DietException when number of parameter is not as expected.
     */
    public static void checkList(String[] param) throws DietException {
        if (param.length > 3) {
            throw new DietException("Incorrect list statement");
        }
    }

    /**
     * Takes in an integer from food to check if the value is within the logical limit.
     *
     * @param foodValue integer value of attributes.
     * @throws DietException when value is not within the limit.
     */
    public static void checkFoodLimit(int foodValue) throws DietException {
        if (foodValue < 0) {
            throw new DietException("Portion size, carb, protein or fat value cannot be less than 0!");
        } else if (foodValue > FOOD_CAP) {
            throw new DietException("Portion size, carb, protein or fat value cannot be more than 100,000!");
        }
    }

    /**
     * Takes in user input to check if the nutrient type is of the expected input.
     *
     * @param userInput user input.
     * @throws DietException when it is not one of the expected nutrient type.
     */
    public static void checkNutrientType(String userInput) throws DietException {
        boolean checkContain = false;
        for (String param: PARAM_CALCULATE) {
            if (userInput.contains(param)) {
                checkContain = true;
            }
        }
        if (!checkContain) {
            throw new DietException("Incorrect nutrient type!");
        }
    }

    /**
     * Takes in user input to check if the fitness level is of the expected input.
     *
     * @param userInput user input.
     * @throws DietException when it is not one of the expected fitness level.
     */
    public static void checkFitness(String userInput) throws DietException {
        boolean checkContain = false;
        for (String param: PARAM_FITNESS) {
            if (userInput.equals(param)) {
                checkContain = true;
            }
        }
        if (!checkContain) {
            throw new DietException("No such fitness level!");
        }
    }

    /**
     * Takes in user input to check if the gender is of the expected input.
     *
     * @param userInput user input.
     * @throws DietException when it is not one of the expected gender input.
     */
    public static void checkGender(String userInput) throws DietException {
        boolean checkContain = false;
        for (String param: PARAM_GENDER) {
            if (userInput.contains(param)) {
                checkContain = true;
            }
        }
        if (!checkContain) {
            throw new DietException("Please key in the specified gender characters.");
        }
    }

    /**
     * Takes in an integer age to check if the value is within the logical limit.
     *
     * @param age integer value of age.
     * @throws DietException when value is not within the limit.
     */
    public static void checkAgeLimit(int age) throws DietException {
        if (age < 0) {
            throw new DietException("Age value cannot be less than 0!");
        } else if (age > AGE_CAP) {
            throw new DietException("Age value cannot be more than " + AGE_CAP + "!");
        }
    }

    /**
     * Takes in an integer height to check if the value is within the logical limit.
     *
     * @param height integer value of height.
     * @throws DietException when value is not within the limit.
     */
    public static void checkHeightLimit(int height) throws DietException {
        if (height < 1) {
            throw new DietException("Height value cannot be less than 1");
        } else if (height > HEIGHT_CAP) {
            throw new DietException("Height value cannot be more than " + HEIGHT_CAP + "!");
        }
    }

    /**
     * Takes in an integer weight to check if the value is within the logical limit.
     *
     * @param weight integer value of weight.
     * @throws DietException when value is not within the limit.
     */
    public static void checkWeightLimit(int weight) throws DietException {
        if (weight < 1) {
            throw new DietException("Weight value cannot be less than 1!");
        } else if (weight > WEIGHT_CAP) {
            throw new DietException("Weight value cannot be more than " + WEIGHT_CAP + "!");
        }
    }

}
