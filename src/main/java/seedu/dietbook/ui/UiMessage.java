package seedu.dietbook.ui;

import seedu.dietbook.person.FitnessLevel;
import seedu.dietbook.person.Gender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a storage that stores all the messages that Ui can utilise and print out.
 * A <code>UiMessage</code> objects contains all the necessary methods required to retrieve the output
 * messages.
 */
public class UiMessage {

    private final UiHelper uiHelper;

    private final Logger logger;

    public UiMessage() {
        uiHelper = new UiHelper();
        logger = Logger.getLogger(UiMessage.class.getName());
        initialiseLogger();
    }

    /**
     * Initialises the logger and sets the log level.
     */
    void initialiseLogger() {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.WARNING);
    }

    String getWelcomeMessage() {
        return getLogo() + UiHelper.LINE_SEPARATOR
                + "Hello! Welcome to DietBook!" + UiHelper.LINE_SEPARATOR
                + "I am Diet, your guide to using DietBook. How may I address you?" + UiHelper.LINE_SEPARATOR
                + "Please input your name or nickname in the following format:" + UiHelper.LINE_SEPARATOR
                + "  name YOUR_NAME_OR_NICKNAME" + UiHelper.LINE_SEPARATOR
                + "  Example: name Jack";
    }

    String getAskForUserInfoMessage(String name) {
        return "Hi " + uiHelper.trimString(name) + "!" + UiHelper.LINE_SEPARATOR
                + "Before we get started, I would like to know about about you so that I can make more "
                + UiHelper.LINE_SEPARATOR
                + "accurate calculations for you :). Therefore, could you please share with me the "
                + "following:" + UiHelper.LINE_SEPARATOR
                + "- Your gender either F for " + Gender.FEMALE.getDescription() + " or M for "
                + Gender.MALE.getDescription() + " or O for " + Gender.OTHERS.getDescription() + "."
                + UiHelper.LINE_SEPARATOR
                + "- Your age which is a positive integer." + UiHelper.LINE_SEPARATOR
                + "- Your height in cm." + UiHelper.LINE_SEPARATOR
                + "- Your original weight in kg, the weight when you first started using DietBook or "
                + "you current weight." + UiHelper.LINE_SEPARATOR
                + "- Your current weight in kg." + UiHelper.LINE_SEPARATOR
                + "- Your target weight in kg, or your current weight if that is also your target weight."
                + UiHelper.LINE_SEPARATOR
                + "- Your fitness level, represented by a number from 1 to 5." + UiHelper.LINE_SEPARATOR
                + "  1 = " + FitnessLevel.NONE.getDescription() + UiHelper.LINE_SEPARATOR
                + "  2 = " + FitnessLevel.LOW.getDescription() + UiHelper.LINE_SEPARATOR
                + "  3 = " + FitnessLevel.MEDIUM.getDescription() + UiHelper.LINE_SEPARATOR
                + "  4 = " + FitnessLevel.HIGH.getDescription() + UiHelper.LINE_SEPARATOR
                + "  5 = " + FitnessLevel.EXTREME.getDescription() + UiHelper.LINE_SEPARATOR
                + UiHelper.LINE_SEPARATOR
                + "Please input your details in the following format:" + UiHelper.LINE_SEPARATOR
                + "  info g/GENDER a/AGE h/HEIGHT o/ORIGINAL_WEIGHT c/CURRENT_WEIGHT t/TARGET_WEIGHT "
                + "f/FITNESS_LEVEL" + UiHelper.LINE_SEPARATOR
                + "  Example: info g/F a/21 h/165 o/65 c/65 t/55 f/2";
    }

    String getExitMessage() {
        return "Bye! Hope to see you again soon!";
    }

    String getErrorMessage(String errorMessage) {
        return ":( " + uiHelper.trimString(errorMessage);
    }

    String getInitialisationCompleteMessage() {
        return "Thank you! DietBook has been initialised " + getStartMessage();
    }

    String getDataSuccessfullySavedMessage() {
        return "Your data has been saved successfully.";
    }

    String getWelcomeBackMessage(String name) {
        return getLogo() + UiHelper.LINE_SEPARATOR + "Welcome back to DietBook " + uiHelper.trimString(name)
                + "!" + UiHelper.LINE_SEPARATOR
                + "All your previous data has been successfully loaded " + getStartMessage();
    }

    String getHelpCommandMessage() {
        return "Listed below are the valid commands for DietBook:"
                + UiHelper.LINE_SEPARATOR + UiHelper.LINE_SEPARATOR
                + getUserInfoRelatedCommands() + UiHelper.LINE_SEPARATOR
                + getDatabaseRelatedCommands() + UiHelper.LINE_SEPARATOR
                + getFoodListRelatedCommands() + UiHelper.LINE_SEPARATOR
                + getNutritionalRelatedCommands() + UiHelper.LINE_SEPARATOR
                + getSystemRelatedCommands();
    }

    String getDatabaseMessage(String foodDatabase) {
        return "Here are the food items in the database:" + UiHelper.LINE_SEPARATOR + foodDatabase;
    }

    String getPersonInfoMessage(String personInfo) {
        return "Here is your information:" + UiHelper.LINE_SEPARATOR + personInfo;
    }

    String getEditedPersonInfoMessage(String personInfo) {
        return "Got it! I've updated your personal information:" + UiHelper.LINE_SEPARATOR + personInfo;
    }

    String getFoodListMessage(String allFood) {
        return "Here are the food items in DietBook:" + UiHelper.LINE_SEPARATOR + allFood;
    }

    String getFoodListMessage(String foods, LocalDateTime start, LocalDateTime end) {
        return "Here are the food items recorded in DietBook" + stringDateTimePeriod(start, end)
                + ":" + UiHelper.LINE_SEPARATOR + foods;
    }

    String getEmptyFoodListMessage() {
        return "DietBook is currently empty.";
    }

    String getEmptyFoodListMessage(LocalDateTime start, LocalDateTime end) {
        return "No food item was recorded in DietBook" + stringDateTimePeriod(start, end) + ".";
    }

    String getNewFoodMessage(String newFood) {
        return "Got it! I've added this food item:" + UiHelper.LINE_SEPARATOR + "  "
                + uiHelper.trimString(newFood);
    }

    String getDeletedFoodMessage(String deletedFood) {
        return "Noted. I've removed this food item:" + UiHelper.LINE_SEPARATOR  + "  "
                + uiHelper.trimString(deletedFood);
    }

    String getClearFoodListMessage() {
        return "All previous data has been deleted..." + UiHelper.LINE_SEPARATOR + "DietBook is now empty.";
    }

    String getCalorieRecommendationMessage(String name, int calorieRecommendation) {
        return "Hi " + uiHelper.trimString(name) + "!" + UiHelper.LINE_SEPARATOR
                + "Here is your daily recommended calorie intake: " + calorieRecommendation + "kcal";
    }

    String getLogo() {
        return " _______  __ ______ ________ _______   ______   ______  __    __" + UiHelper.LINE_SEPARATOR
                + "|   __  \\|  |   ___|__    __|   __  \\ /  __  \\ /  __  \\|  | /  /" + UiHelper.LINE_SEPARATOR
                + "|  |  |  |  |  |___   |  |  |  |__|  |  |  |  |  |  |  |  |/  /" + UiHelper.LINE_SEPARATOR
                + "|  |  |  |  |   ___|  |  |  |   __  <|  |  |  |  |  |  |     /" + UiHelper.LINE_SEPARATOR
                + "|  |__|  |  |  |___   |  |  |  |__|  |  |__|  |  |  |  |  |\\  \\" + UiHelper.LINE_SEPARATOR
                + "|_______/|__|______|  |__|  |_______/ \\______/ \\______/|__| \\__\\" + UiHelper.LINE_SEPARATOR;

    }

    /**
     * Returns a string stating that DietBook is ready for use.
     *
     * @return A string stating that DietBook is ready for use.
     */
    String getStartMessage() {
        return "and you may start by entering any valid commands. "
                + UiHelper.LINE_SEPARATOR
                + "If you require a list of valid commands, you can enter: help";
    }

    String getSystemRelatedCommands() {
        return "For other system related commands" + UiHelper.LINE_SEPARATOR
                + "  To view a list of valid commands: help" + UiHelper.LINE_SEPARATOR
                + "  To exit DietBook: exit";
    }

    String getNutritionalRelatedCommands() {
        return "For nutritional intake and recommendation related commands" + UiHelper.LINE_SEPARATOR
                + "  To get recommended calorie intake: recommend" + UiHelper.LINE_SEPARATOR
                + "  To calculate nutritional intake: calculate NUTRIENT_TYPE [yyyy-mm-ddTHH:mm] "
                + "[yyyy-mm-ddTHH:mm]" + UiHelper.LINE_SEPARATOR
                + "    Valid NUTRIENT_TYPE: carb, calorie, fat, protein, all" + UiHelper.LINE_SEPARATOR;
    }

    String getUserInfoRelatedCommands() {
        return "For user information related commands" + UiHelper.LINE_SEPARATOR
                + "  To view user information: userinfo" + UiHelper.LINE_SEPARATOR
                + "  To edit user information: editinfo [n/NAME] [g/GENDER] [a/AGE] [h/HEIGHT] "
                + "[o/ORIGINAL_WEIGHT] [c/CURRENT_WEIGHT] [t/TARGET_WEIGHT] [f/FITNESS_LEVEL]"
                + UiHelper.LINE_SEPARATOR;
    }

    String getFoodListRelatedCommands() {
        return "For food list related commands" + UiHelper.LINE_SEPARATOR
                + "  To add a food not in the database: add x/PORTION_SIZE n/FOOD_NAME k/CALORIE "
                + "[c/CARBOHYDRATE] [p/PROTEIN] [f/FAT] [yyyy-mm-ddTHH:mm]" + UiHelper.LINE_SEPARATOR
                + "  To view all food in DietBook: list [yyyy-mm-ddTHH:mm] [yyyy-mm-ddTHH:mm]"
                + UiHelper.LINE_SEPARATOR
                + "  To delete a food from DietBook: delete INDEX" + UiHelper.LINE_SEPARATOR
                + "  To delete all food items from the DietBook: clear" + UiHelper.LINE_SEPARATOR;
    }

    String getDatabaseRelatedCommands() {
        return "For database related commands" + UiHelper.LINE_SEPARATOR
                + "  To add a food from the database: add INDEX x/PORTION_SIZE [yyyy-mm-ddTHH:mm]"
                + UiHelper.LINE_SEPARATOR
                + "  To view all food in the database: data" + UiHelper.LINE_SEPARATOR;
    }

    /**
     * Returns a string representation of the total amount of a nutrient consumed by the user.
     *
     * @param nutrientIntake The amount of a particular type of nutrient consumed.
     * @param nutrientType A string representation of the type of nutrient consumed.
     * @param nutrientUnit A string representation of the unit of the nutrient consumed.
     * @return A string representation of the the total amount of a nutrient consumed by the user.
     */
    String getOneIntakeMessage(int nutrientIntake, String nutrientType, String nutrientUnit) {
        uiHelper.performAssertionsForStringInputs(nutrientType, "Nutrient Type");
        uiHelper.performAssertionsForStringInputs(nutrientUnit, "Nutrient Unit");
        uiHelper.performAssertionsForNutritionalIntake(nutrientIntake, nutrientType);

        return getNutritionalIntakeMessage(nutrientIntake, nutrientType, nutrientUnit);
    }

    /**
     * Returns a string representation of the total amount of a nutrient or all nutrients consumed by the
     * user during a given time period.
     *
     * @param intakeWithoutTime A string representation of the the total amount of a nutrient or
     *         all nutrients consumed by the user.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     * @return A string representation of the the total amount of a nutrient or all nutrient consumed by the
     *         user during a given time period.
     */
    String getIntakeWithTimeMessage(String intakeWithoutTime, LocalDateTime start, LocalDateTime end) {
        logger.log(Level.FINE, "Start: " + start);
        logger.log(Level.FINE, "End: " + end);
        uiHelper.performAssertionsForTimePeriod(start, end);

        String timePeriod = "Time period:" + stringDateTimePeriod(start, end);
        return timePeriod + UiHelper.LINE_SEPARATOR + UiHelper.LINE_SEPARATOR + intakeWithoutTime;
    }

    /**
     * Returns a string representation of the total amount of calories, carbohydrates, fats and proteins
     * consumed by the user.
     *
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param fatIntake The total amount of fats of all the food in the food list.
     * @return A string representation of the total amount of each of the nutrients consumed by the user.
     */
    String getAllIntakeMessage(int calorieIntake, int carbIntake, int proteinIntake, int fatIntake) {
        uiHelper.performAssertionsForNutritionalIntake(calorieIntake, "calorie");
        uiHelper.performAssertionsForNutritionalIntake(carbIntake, "carb");
        uiHelper.performAssertionsForNutritionalIntake(proteinIntake, "protein");
        uiHelper.performAssertionsForNutritionalIntake(fatIntake, "fat");

        String stringCalorieIntake = getNutritionalIntakeMessage(calorieIntake, "calorie","kcal");
        String stringCarbIntake = getNutritionalIntakeMessage(carbIntake, "carb", "g");
        String stringProteinIntake = getNutritionalIntakeMessage(proteinIntake, "protein", "g");
        String stringFatIntake = getNutritionalIntakeMessage(fatIntake, "fat", "g");

        return stringCalorieIntake + UiHelper.LINE_SEPARATOR
                + stringCarbIntake + UiHelper.LINE_SEPARATOR
                + stringProteinIntake + UiHelper.LINE_SEPARATOR
                + stringFatIntake + UiHelper.LINE_SEPARATOR;
    }

    /**
     * Returns a string with a header and recalculatedFoods or a string stating that no food items had their
     * nutritional information recalculated if calculatedFoods is an empty string.
     *
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *         DietBook.
     * @return A string with a header and recalculatedFoods or a string stating that no food items had their
     *         nutritional information recalculated if calculatedFoods is an empty string.
     */
    String getRecalculatedFoodsMessage(String recalculatedFoods) {
        String message = "No food items had their nutritional information recalculated by DietBook.";
        if (!uiHelper.isEmptyString(recalculatedFoods)) {
            message = "Food items which had their nutritional information recalculated by DietBook: "
                    + UiHelper.LINE_SEPARATOR + recalculatedFoods;
        }
        return message;
    }

    /**
     * Return a string representation of  the amount of a nutrient consumed by the user which can be either
     * the total amount consumed or amount consumed in a given time period.
     *
     * @param nutrientIntake The amount of a particular type of nutrient consumed.
     * @param nutrientType A string representation of the type of nutrient consumed.
     * @param nutrientUnit A string representation of the unit of the nutrient consumed.
     * @return The amount of a nutrient consumed by the user which can be either the total amount consumed
     *         or amount consumed in a given time period.
     */
    String getNutritionalIntakeMessage(int nutrientIntake, String nutrientType, String nutrientUnit) {
        return "Total " + nutrientType + " intake: " + nutrientIntake + nutrientUnit;
    }

    /**
     * Returns a string representation of the total amount of a nutrient consumed by the user and
     * the list of food items which had their nutritional information recalculated by DietBook if any.
     *
     * @param nutrientIntake The amount of a particular type of nutrient consumed.
     * @param nutrientType A string representation of the type of nutrient consumed.
     * @param nutrientUnit A string representation of the unit of the nutrient consumed.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *         DietBook.
     * @return A string representation of the the total amount of a nutrient consumed by the user and
     *         the list of food items which had their nutritional information recalculated by DietBook if any.
     */
    String getOneIntakeAndFoodsMessage(int nutrientIntake, String nutrientType,
                                       String nutrientUnit, String recalculatedFoods) {
        uiHelper.performAssertionsForStringInputs(nutrientType, "Nutrient Type");
        uiHelper.performAssertionsForStringInputs(nutrientUnit, "Nutrient Unit");
        uiHelper.performAssertionsForNutritionalIntake(nutrientIntake, nutrientType);
        uiHelper.performAssertionsForNullStringInputs(recalculatedFoods,
                "List of foods that had their nutritional information recalculated");

        String stringNutrientIntake = getNutritionalIntakeMessage(nutrientIntake, nutrientType, nutrientUnit);
        String message = getRecalculatedFoodsMessage(recalculatedFoods);
        return stringNutrientIntake + UiHelper.LINE_SEPARATOR + message;
    }

    /**
     * Returns a string representation of the total amount of a nutrient or all nutrientS consumed by the
     * user  during a given time period and the list of food items recorded during the same time period
     * which had their nutritional information recalculated by DietBook if any.
     *
     * @param intakeAndFoodsWithoutTime A string representation of the the total amount of a nutrient or
     *         all nutrients consumed by the user and the list of food items which had their nutritional
     *         information recalculated by DietBook if any.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     * @return A string representation of the the total amount of a nutrient or all nutrient consumed by the
     *         user during a given time period and the list of food items recorded during the same time period
     *         which had their nutritional information recalculated by DietBook if any.
     */
    String getIntakeAndFoodsWithTimeMessage(String intakeAndFoodsWithoutTime,
                                            LocalDateTime start, LocalDateTime end) {
        logger.log(Level.FINE, "Start: " + start);
        logger.log(Level.FINE, "End: " + end);
        uiHelper.performAssertionsForTimePeriod(start, end);

        String timePeriod = "Time period:" + stringDateTimePeriod(start, end);
        return timePeriod + UiHelper.LINE_SEPARATOR + UiHelper.LINE_SEPARATOR + intakeAndFoodsWithoutTime;
    }

    /**
     * Returns a string representation of the total amount of all nutrients consumed by the user and
     * the list of food items which had their nutritional information recalculated by DietBook if any.
     *
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param fatIntake The total amount of fats of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *         DietBook.
     * @return A string representation of the total amount of all nutrients consumed by the user and
     *         the list of food items which had their nutritional information recalculated by DietBook if any.
     */
    String getAllIntakeAndFoodsMessage(int calorieIntake, int carbIntake, int proteinIntake,
                                       int fatIntake, String recalculatedFoods) {
        uiHelper.performAssertionsForNutritionalIntake(carbIntake, "carbohydrate");
        uiHelper.performAssertionsForNutritionalIntake(calorieIntake, "calorie");
        uiHelper.performAssertionsForNutritionalIntake(proteinIntake, "protein");
        uiHelper.performAssertionsForNutritionalIntake(fatIntake, "fat");
        uiHelper.performAssertionsForNullStringInputs(recalculatedFoods,
                "List of foods that had their nutritional information recalculated");

        String stringCarbIntake = getNutritionalIntakeMessage(carbIntake, "carbohydrate", "g");
        String stringCalorieIntake = getNutritionalIntakeMessage(calorieIntake, "calorie", "kcal");
        String stringProteinIntake = getNutritionalIntakeMessage(proteinIntake, "protein", "g");
        String stringFatIntake = getNutritionalIntakeMessage(fatIntake, "fat", "g");
        String message = getRecalculatedFoodsMessage(recalculatedFoods);

        return stringCalorieIntake + UiHelper.LINE_SEPARATOR
                + stringCarbIntake + UiHelper.LINE_SEPARATOR
                + stringProteinIntake + UiHelper.LINE_SEPARATOR
                + stringFatIntake + UiHelper.LINE_SEPARATOR
                + message;

    }

    /**
     * Returns a string representation of the time period with date time in the format dd MMM yyyy HHmm.
     *
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     * @return The string representation of time period with date time in the format dd MMM yyyy HHmm.
     */
    public String stringDateTimePeriod(LocalDateTime start, LocalDateTime end) {
        logger.log(Level.FINE, "Start: " + start);
        logger.log(Level.FINE, "End: " + end);
        uiHelper.performAssertionsForTimePeriod(start, end);

        String stringStart = start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        return " between " + stringStart + " and " + stringEnd;
    }
}