package seedu.dietbook;

import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.person.Gender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a text user interface.
 * A <code>Ui</code> objects deals with user interaction by taking in user inputs and showing users the
 * appropriate messages after a valid command is executed or when an error occurs.
 */
public class Ui {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a <code>Ui</code> object.
     */
    public Ui() {
    }

    // Methods in the Ui class are organised according to their function in the order of: system related,
    // database related, person related, food list related, calculator related and other helper methods.

    // Methods required to print system related commands or messages.

    /**
     * Reads in and returns the user input.
     *
     * @return The user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message from DietBook when it is first booted up.
     */
    public void printWelcomeMessage() {
        String logo = getLogo();
        print(logo + LINE_SEPARATOR
                + "Hello! Welcome to DietBook!" + LINE_SEPARATOR
                + "I am Diet, your guide to using DietBook. What is your name?" + LINE_SEPARATOR
                + "Please input in the following format:" + LINE_SEPARATOR
                + "  name YOUR_NAME" + LINE_SEPARATOR
                + "  Example: name Jack");
    }

    /**
     * Prints a message asking the user to input their personal information related to dieting and health
     * which includes gender, age, height, activity level, original weight and target weight.
     *
     * @param name The name of the user.
     */
    public void printAskForUserInfoMessage(String name) {
        performAssertionsForStringInputs(name, "Name");

        print("Hi " + trimString(name) + "!" + LINE_SEPARATOR
                + "Before we get started, I would like to know about about you so that I can make more "
                + LINE_SEPARATOR
                + "accurate calculations for you :). Therefore, could you please share with me the "
                + "following:" + LINE_SEPARATOR
                + "- Your gender either F for " + Gender.FEMALE.getDescription() + " or M for "
                + Gender.MALE.getDescription() +  " or O for " + Gender.OTHERS.getDescription() + "."
                + LINE_SEPARATOR
                + "- Your age which is a positive integer." + LINE_SEPARATOR
                + "- Your height in cm." + LINE_SEPARATOR
                + "- Your original weight in kg, the weight when you first started using DietBook or "
                + "you current weight." + LINE_SEPARATOR
                + "- Your current weight in kg." + LINE_SEPARATOR
                + "- Your target weight in kg, or your current weight if that is also your target weight."
                + LINE_SEPARATOR
                + "- Your activity level, represented by a number from 1 to 5." + LINE_SEPARATOR
                + "  1 = " + ActivityLevel.NONE.getDescription() + LINE_SEPARATOR
                + "  2 = " + ActivityLevel.LOW.getDescription() + LINE_SEPARATOR
                + "  3 = " + ActivityLevel.MEDIUM.getDescription() + LINE_SEPARATOR
                + "  4 = " + ActivityLevel.HIGH.getDescription() + LINE_SEPARATOR
                + "  5 = " + ActivityLevel.EXTREME.getDescription() + LINE_SEPARATOR + LINE_SEPARATOR
                + "Please input your details in the following format:" + LINE_SEPARATOR
                + "  info g/GENDER a/AGE h/HEIGHT o/ORIGINAL_WEIGHT c/CURRENT_WEIGHT t/TARGET_WEIGHT "
                + "l/ACTIVITY_LEVEL" + LINE_SEPARATOR
                + "  Example: info g/F a/21 h/165 o/65 c/65 t/55 l/2");
    }

    /**
     * Prints an exit message when DietBook is closed.
     *
     */
    public void printExitMessage() {
        print("Bye! Hope to see you again soon!");
    }

    /**
     * Prints an error message given what or where the error is.
     *
     * @param errorMessage Message detailing what or where the error is.
     */
    public void printErrorMessage(String errorMessage) {
        performAssertionsForStringInputs(errorMessage,"Error message");

        print(":( " + trimString(errorMessage));
    }

    /**
     * Prints a message that notifies the user that DietBook has been initialised.
     */
    public void printInitialisationCompleteMessage() {
        print("Thank you! DietBook has been initialised " + getStartMessage());
    }


    /**
     * Prints the welcome back message when user reboots up DietBook after the first initialisation.
     *
     * @param name The name of the user.
     */
    public void printWelcomeBackMessage(String name) {
        performAssertionsForStringInputs(name, "Name");

        print(getLogo() + LINE_SEPARATOR + "Welcome back to DietBook " + trimString(name) + "!" + LINE_SEPARATOR
                + "All your previous data has been successfully loaded " + getStartMessage());
    }

    /**
     * Prints a string representation of a list of the commands that users can use.
     */
    public void printHelpCommandMessage() {
        print("Listed below are the valid commands for DietBook:" + LINE_SEPARATOR + LINE_SEPARATOR
                + "For user information related commands" + LINE_SEPARATOR
                + getUserRelatedCommands() + LINE_SEPARATOR
                + "For database related commands" + LINE_SEPARATOR
                + getDatabaseRelatedCommands() + LINE_SEPARATOR
                + "For food list related commands" + LINE_SEPARATOR
                + getFoodListRelatedCommands() + LINE_SEPARATOR
                + "For nutritional intake and recommendation related commands" + LINE_SEPARATOR
                + getCalculatorRelatedCommands() + LINE_SEPARATOR
                + "For other system related commands" + LINE_SEPARATOR
                + getSystemRelatedCommands());
    }

    // Methods required to print database related commands or messages.

    /**
     * Prints all the food in the database sorted by the canteen and then the store it is found.
     *
     * @param foodDatabase The string representation of all the food items stored in the database.
     */
    public void printDatabase(String foodDatabase) {
        performAssertionsForStringInputs(foodDatabase,
                "Food database");

        print("Here are the food items in the database:" + LINE_SEPARATOR + foodDatabase);
    }

    /**
     * Prints the food items in the database containing the food name of the food that user wants to
     * add sorted by the canteen and then the store it is found.
     * This method is only used if more than one food item in the database contains the food name given.
     *
     * @param matchingFoodDatabase The string representation of the food items stored in the
     *     database containing the food name given.
     */
    public void printMatchingFoodsInDatabase(String matchingFoodDatabase) {
        performAssertionsForStringInputs(matchingFoodDatabase,
                "Matching food database");

        print("Here are the matching food items in the database:" + LINE_SEPARATOR
                + matchingFoodDatabase + LINE_SEPARATOR + LINE_SEPARATOR
                + "Please re-enter with the full name of the food item above in the following format:"
                + LINE_SEPARATOR + "  add n/FOOD_NAME x/PORTION_SIZE");
    }

    // Methods required to print user information related commands and messages.

    /**
     * Prints all the information related to the user.
     *
     * @param personInfo The user's personal information.
     */
    public void printPersonInfo(String personInfo) {
        performAssertionsForStringInputs(personInfo,
                "Person information");

        print("Here is your information:" + LINE_SEPARATOR
                + personInfo);
    }

    /**
     * Prints all the updated information related to the user.
     *
     * @param personInfo The user's personal information.
     */
    public void printEditedPersonInfo(String personInfo) {
        performAssertionsForStringInputs(personInfo,
                "Updated person information");

        print("Got it! I've updated your personal information:" + LINE_SEPARATOR
                + personInfo);
    }

    // Methods required for printing FoodList related commands and messages.

    /**
     * Prints all the food items in the food list in the order that they were added or a message stating
     * that the food list is empty if there are no food items.
     *
     * @param allFood The string representation of all the food items in the food list.
     */
    public void printFoodList(String allFood) {
        performAssertionsForNullStringInputs(allFood,
                "String representation of all food in food list");

        if (trimStringGetLength(allFood) < 1) {
            print("DietBook is currently empty.");
        } else {
            print("Here are the food items in DietBook:" + LINE_SEPARATOR + allFood);
        }
    }

    /**
     * Prints food items recorded into the food list during a given time period in the order that they were
     * added or a message stating no food items were recorded during the given time period.
     *
     * @param foods The string representation of food items in the food list recorded during the time
     *     period given.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printFoodList(String foods, LocalDateTime start, LocalDateTime end) {
        performAssertionsForNullStringInputs(foods,
                "String representation of food items in the food list recorded during the time "
                        + "period given");
        performAssertionsForTimePeriod(start, end);

        if (trimStringGetLength(foods) < 1) {
            print("No food item was recorded in DietBook" + stringDateTimePeriod(start, end) + ".");
        } else {
            print("Here are the food items recorded in DietBook" + stringDateTimePeriod(start, end) + ":"
                    + LINE_SEPARATOR + foods);
        }
    }

    /**
     * Prints food items recorded into the food list after a given timing in the order that they were
     * added or a message stating no food items were recorded after the given timing till now.
     *
     * @param foods The string representation of food items in the food list recorded from the given time
     *     till now.
     * @param start Starting date time of the time period till now.
     */
    public void printFoodList(String foods, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printFoodList(foods, start, end);
    }

    /**
     * Prints a message to show that the food specified has been added to the food list.
     *
     * @param newFood The string representation of the new food item that was added to the food list.
     */
    public void printNewFood(String newFood) {
        performAssertionsForStringInputs(newFood,
                "String representation of the food that was added");

        print("Got it! I've added this food item:" + LINE_SEPARATOR
                + "  " + trimString(newFood));
    }

    /**
     * Prints a message to show that the food specified has been deleted from the food list.
     *
     * @param deletedFood The string representation of the food that was deleted from the food list.
     */
    public void printDeletedFood(String deletedFood) {
        performAssertionsForStringInputs(deletedFood,
                "String representation of the food that was deleted");

        print("Noted. I've removed this food item:" + LINE_SEPARATOR
                + "  " + trimString(deletedFood));
    }

    /**
     * Prints a message to show that the food list has been successfully cleared and is now empty.
     */
    public void printClearFoodListMessage() {
        print("All previous data has been deleted..." + LINE_SEPARATOR
                + "DietBook is now empty.");
    }

    // Methods required to print nutritional intake and recommendation related commands and messages.

    /**
     * Prints the daily recommended calorie intake of the user based on the user's personal information.
     *
     * @param calorieRecommendation The daily recommended calorie intake of the user.
     */
    public void printCalorieRecommendation(String name, int calorieRecommendation) {
        performAssertionsForStringInputs(name, "Name");
        performAssertionsForCalorieRecommendation(calorieRecommendation);

        print("Hi " + trimString(name) + "!" + LINE_SEPARATOR
                + "Here is your daily recommended calorie intake: " + calorieRecommendation + "kcal");
    }

    public void printCarbIntake(int carbIntake) {
        print(stringOneIntakeAndFoodsWithoutTime(carbIntake,"carbohydrate",
                "g"));
    }
    
    public void printCarbIntake(int carbIntake, LocalDateTime start, LocalDateTime end) {
        String carbIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(carbIntake,
                "carbohydrate", "g");
        print(stringIntakeAndFoodsWithTime(carbIntakeAndFoodsWithoutTime, start, end));
    }

    public void printCarbIntake(int carbIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCarbIntake(carbIntake, start, end);
    }

    public void printCalorieIntake(int calorieIntake) {
        print(stringOneIntakeAndFoodsWithoutTime(calorieIntake,"calorie","kcal"));
    }

    public void printCalorieIntake(int calorieIntake, LocalDateTime start, LocalDateTime end) {
        String calorieIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(calorieIntake,
                "calorie", "kcal");
        print(stringIntakeAndFoodsWithTime(calorieIntakeAndFoodsWithoutTime, start, end));
    }

    public void printCalorieIntake(int calorieIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCalorieIntake(calorieIntake, start, end);
    }

    public void printProteinIntake(int proteinIntake) {
        print(stringOneIntakeAndFoodsWithoutTime(proteinIntake,"protein","g"));
    }

    public void printProteinIntake(int proteinIntake, LocalDateTime start, LocalDateTime end) {
        String proteinIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(proteinIntake,
                "protein", "g");
        print(stringIntakeAndFoodsWithTime(proteinIntakeAndFoodsWithoutTime, start, end));
    }

    public void printProteinIntake(int proteinIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printProteinIntake(proteinIntake, start, end);
    }

    public void printFatIntake(int fatIntake) {
        print(stringOneIntakeAndFoodsWithoutTime(fatIntake,"fat","g"));
    }

    public void printFatIntake(int fatIntake, LocalDateTime start, LocalDateTime end) {
        String fatIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(fatIntake,
                "fat", "g");
        print(stringIntakeAndFoodsWithTime(fatIntakeAndFoodsWithoutTime, start, end));
    }

    public void printFatIntake(int fatIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printFatIntake(fatIntake, start, end);
    }

    public void printAllIntake(int calorieIntake, int carbIntake, int proteinIntake,
                               int fatIntake) {
        print(stringAllIntakeAndFoodsWithoutTime(calorieIntake, carbIntake, proteinIntake,
                fatIntake));
    }

    public void printAllIntake(int calorieIntake, int carbIntake, int proteinIntake,
                               int fatIntake, LocalDateTime start, LocalDateTime end) {
        String allIntakeAndFoodsWithoutTime = stringAllIntakeAndFoodsWithoutTime(calorieIntake,
                carbIntake, proteinIntake, fatIntake);
        print(stringIntakeAndFoodsWithTime(allIntakeAndFoodsWithoutTime, start, end));
    }

    public void printAllIntake(int calorieIntake, int carbIntake, int proteinIntake, int fatIntake,
                               LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printAllIntake(calorieIntake, carbIntake, proteinIntake, fatIntake, start, end);
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user and the list of food items which had
     * their nutritional information recalculated by DietBook if any.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total carbohydrate intake.
     *
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     */
    public void printCarbIntakeAndFoods(int carbIntake, String recalculatedFoods) {
        print(stringOneIntakeAndFoodsWithoutTime(carbIntake,"carbohydrate",
                "g", recalculatedFoods));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user and a list of the foods which had
     * their nutritional information recalculated by DietBook if any, given a certain time period.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total carbohydrate
     * intake within a given time period.
     *
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded during the
     *     time period given.
     * @param recalculatedFoods The list of food items recorded during the given time period which had their
     *     nutritional information recalculated by DietBook.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printCarbIntakeAndFoods(int carbIntake, String recalculatedFoods,
                                        LocalDateTime start, LocalDateTime end) {
        String carbIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(carbIntake,
                "carbohydrate", "g", recalculatedFoods);
        print(stringIntakeAndFoodsWithTime(carbIntakeAndFoodsWithoutTime, start, end));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user and a list of the foods which had
     * their nutritional information recalculated by DietBook if any, given a start date.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total carbohydrate
     * intake given a start date.
     *
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded from the
     *     start date till now.
     * @param recalculatedFoods The list of food items recorded from the start date till now which had
     *     their nutritional information recalculated by DietBook.
     * @param start Starting date time to calculate from.
     */
    public void printCarbIntakeAndFoods(int carbIntake, String recalculatedFoods,
                                        LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCarbIntakeAndFoods(carbIntake, recalculatedFoods, start, end);
    }


    /**
     * Prints the total amount of calories consumed by the user and the list of food items which had
     * their nutritional information recalculated by DietBook if any.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total calorie intake.
     *
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     */
    public void printCalorieIntakeAndFoods(int calorieIntake, String recalculatedFoods) {
        print(stringOneIntakeAndFoodsWithoutTime(calorieIntake,"calorie","kcal",
                recalculatedFoods));
    }

    /**
     * Prints the total amount of calories consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a certain time period.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total calorie
     * intake within a given time period.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded during the
     *     time period given.
     * @param recalculatedFoods The list of food items recorded during the given time period which had their
     *     nutritional information recalculated by DietBook.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printCalorieIntakeAndFoods(int calorieIntake, String recalculatedFoods,
                                           LocalDateTime start, LocalDateTime end) {
        String calorieIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(calorieIntake,
                "calorie", "kcal", recalculatedFoods);
        print(stringIntakeAndFoodsWithTime(calorieIntakeAndFoodsWithoutTime, start, end));
    }

    /**
     * Prints the total amount of calories consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a start date.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total calorie intake,
     * given a start date.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded from the
     *     start date till now.
     * @param recalculatedFoods The list of food items recorded from the start date till now which had
     *     their nutritional information recalculated by DietBook.
     * @param start Starting date time to calculate from.
     */
    public void printCalorieIntakeAndFoods(int calorieIntake, String recalculatedFoods,
                                        LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCalorieIntakeAndFoods(calorieIntake, recalculatedFoods, start, end);
    }


    /**
     * Prints the total amount of proteins consumed by the user and the list of food items which had
     * their nutritional information recalculated by DietBook if any.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total protein intake.
     *
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     */
    public void printProteinIntakeAndFoods(int proteinIntake, String recalculatedFoods) {
        print(stringOneIntakeAndFoodsWithoutTime(proteinIntake,"protein","g",
                recalculatedFoods));
    }

    /**
     * Prints the total amount of proteins consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a certain time period.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total protein
     * intake within a given time period.
     *
     * @param proteinIntake The total amount of proteins of food in the food list recorded during the
     *     time period given.
     * @param recalculatedFoods The list of food items recorded during the given time period which had their
     *     nutritional information recalculated by DietBook.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printProteinIntakeAndFoods(int proteinIntake, String recalculatedFoods,
                                           LocalDateTime start, LocalDateTime end) {
        String proteinIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(proteinIntake,
                "protein", "g", recalculatedFoods);
        print(stringIntakeAndFoodsWithTime(proteinIntakeAndFoodsWithoutTime, start, end));
    }

    /**
     * Prints the total amount of proteins consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a start date.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total protein intake,
     * given a start date.
     *
     * @param proteinIntake The total amount of proteins of food in the food list recorded from the
     *     start date till now.
     * @param recalculatedFoods The list of food items recorded from the start date till now which had
     *     their nutritional information recalculated by DietBook.
     * @param start Starting date time to calculate from.
     */
    public void printProteinIntakeAndFoods(int proteinIntake, String recalculatedFoods,
                                           LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printProteinIntakeAndFoods(proteinIntake, recalculatedFoods, start, end);
    }


    /**
     * Prints the total amount of fats consumed by the user and the list of food items which had
     * their nutritional information recalculated by DietBook if any.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total fat intake.
     *
     * @param fatIntake The total amount of fats of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     */
    public void printFatIntakeAndFoods(int fatIntake, String recalculatedFoods) {
        print(stringOneIntakeAndFoodsWithoutTime(fatIntake,"fat","g",
                recalculatedFoods));
    }

    /**
     * Prints the total amount of fats consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a certain time period.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total fat
     * intake within a given time period.
     *
     * @param fatIntake The total amount of fats of food in the food list recorded during the
     *     time period given.
     * @param recalculatedFoods The list of food items recorded during the given time period which had their
     *     nutritional information recalculated by DietBook.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printFatIntakeAndFoods(int fatIntake, String recalculatedFoods,
                                       LocalDateTime start, LocalDateTime end) {
        String fatIntakeAndFoodsWithoutTime = stringOneIntakeAndFoodsWithoutTime(fatIntake,
                "fat", "g", recalculatedFoods);
        print(stringIntakeAndFoodsWithTime(fatIntakeAndFoodsWithoutTime, start, end));
    }

    /**
     * Prints the total amount of fats consumed by the user and a list of the foods which had their
     * nutritional information recalculated by DietBook if any, given a start date.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating total fat intake,
     * given a start date.
     *
     * @param fatIntake The total amount of fats of food in the food list recorded from the start date till
     *     now.
     * @param recalculatedFoods The list of food items recorded from the start date till now which had
     *     their nutritional information recalculated by DietBook.
     * @param start Starting date time to calculate from.
     */
    public void printFatIntakeAndFoods(int fatIntake, String recalculatedFoods,
                                           LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printFatIntakeAndFoods(fatIntake, recalculatedFoods, start, end);
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user and the
     * list of food items which had their nutritional information recalculated by DietBook if any.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating the individual intakes.
     *
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param fatIntake The total amount of fats of all the food in the food list.
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     */
    public void printAllIntakeAndFoods(int calorieIntake, int carbIntake, int proteinIntake,
                                       int fatIntake, String recalculatedFoods) {
        print(stringAllIntakeAndFoodsWithoutTime(calorieIntake, carbIntake, proteinIntake,
                fatIntake, recalculatedFoods));
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user and a
     * list of the foods which had their nutritional information recalculated by DietBook if any, given a
     * certain time period.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating the individual intakes
     * within a given time period.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded during the
     *     time period given.
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded during the
     *     time period given.
     * @param proteinIntake The total amount of proteins of food in the food list recorded during the
     *     time period given.
     * @param fatIntake The total amount of fats of food in the food list recorded during the
     *     time period given.
     * @param recalculatedFoods The list of food items recorded during the given time period which had their
     *     nutritional information recalculated by DietBook.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printAllIntakeAndFoods(int calorieIntake, int carbIntake, int proteinIntake,
                                       int fatIntake, String recalculatedFoods,
                                       LocalDateTime start, LocalDateTime end) {
        String allIntakeAndFoodsWithoutTime = stringAllIntakeAndFoodsWithoutTime(calorieIntake,
                carbIntake, proteinIntake, fatIntake, recalculatedFoods);
        print(stringIntakeAndFoodsWithTime(allIntakeAndFoodsWithoutTime, start, end));
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user and a
     * list of the foods which had their nutritional information recalculated by DietBook if any, given a
     * start date.
     * Some food items only have partial nutritional information as users did not provide all the
     * information when the food items were added. Hence, DietBook does an internal calculation for the
     * the missing information and these calculated values are used when tabulating the individual intakes,
     * given a start date.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded from the start
     *     date till now.
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded from the start
     *     date till now.
     * @param proteinIntake The total amount of proteins of food in the food list recorded from the start
     *     date till now.
     * @param fatIntake The total amount of fats of food in the food list recorded from the start date till
     *     now.
     * @param recalculatedFoods The list of food items recorded from the start date till now which had
     *     their nutritional information recalculated by DietBook.
     * @param start Starting date time to calculate from.
     */
    public void printAllIntakeAndFoods(int calorieIntake, int carbIntake, int proteinIntake,
                                       int fatIntake, String recalculatedFoods,
                                       LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printAllIntakeAndFoods(calorieIntake, carbIntake, proteinIntake, fatIntake, recalculatedFoods, start,
                end);
    }

    // Helper methods for system related commands or messages

    /**
     * Returns the string representation of the logo.
     *
     * @return The string representation of the logo.
     */
    private String getLogo() {
        String logo = " _______  __ ______ ________ _______   ______   ______  __    __" + LINE_SEPARATOR
                + "|   __  \\|  |   ___|__    __|   __  \\ /  __  \\ /  __  \\|  | /  /" + LINE_SEPARATOR
                + "|  |  |  |  |  |___   |  |  |  |__|  |  |  |  |  |  |  |  |/  /"  + LINE_SEPARATOR
                + "|  |  |  |  |   ___|  |  |  |   __  <|  |  |  |  |  |  |     /" + LINE_SEPARATOR
                + "|  |__|  |  |  |___   |  |  |  |__|  |  |__|  |  |  |  |  |\\  \\" + LINE_SEPARATOR
                + "|_______/|__|______|  |__|  |_______/ \\______/ \\______/|__| \\__\\" + LINE_SEPARATOR;
        return logo;
    }

    /**
     * Returns a string stating that DietBook is ready for use.
     *
     * @return A string stating that DietBook is ready for use.
     */
    private String getStartMessage() {
        return "and you may start by entering any valid commands. "
                + LINE_SEPARATOR
                + "If you require a list of valid commands, you can enter: help";
    }

    /**
     * Returns a string representation of a list of system related commands that users can input.
     *
     * @return A string representation of a list of system related commands that users can input.
     */
    private String getSystemRelatedCommands() {
        return "  To view a list of valid commands: help" + LINE_SEPARATOR
                + "  To exit DietBook: exit";
    }

    /**
     * Returns a string representation of a list of nutritional intake and recommendation related commands
     * that users can input.
     *
     * @return A string representation of a list of nutritional intake and recommendation related commands
     *     that users can input.
     */
    private String getCalculatorRelatedCommands() {
        return "  To get recommended calorie intake: recommend" + LINE_SEPARATOR + LINE_SEPARATOR
                + "  To calculate carbohydrate intake: calculate carbohydrate" + LINE_SEPARATOR
                + "  To calculate carbohydrate intake within a time period: calculate carbohydrate "
                + "yyyy-mm-ddTHH:mm yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To calculate carbohydrate intake from a certain date until now: calculate carbohydrate "
                + "yyy-mm-ddTHH:mm" + LINE_SEPARATOR + LINE_SEPARATOR
                + "  To calculate calorie intake: calculate calorie" + LINE_SEPARATOR
                + "  To calculate calorie intake within a time period: calculate calorie yyyy-mm-ddTHH:mm "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To calculate calorie intake from a certain date until now: calculate calorie "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR + LINE_SEPARATOR
                + "  To calculate protein intake: calculate protein" + LINE_SEPARATOR
                + "  To calculate protein intake within a time period: calculate protein yyyy-mm-ddTHH:mm "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To calculate protein intake from a certain date until now: calculate protein "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR + LINE_SEPARATOR
                + "  To calculate fat intake: calculate fat" + LINE_SEPARATOR
                + "  To calculate fat intake within a time period: calculate fat yyyy-mm-ddTHH:mm "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To calculate fat intake from a certain date until now: calculate fat "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR + LINE_SEPARATOR
                + "  To calculate all nutritional intake: calculate all" + LINE_SEPARATOR
                + "  To calculate all nutritional intake within a time period: calculate all "
                + "yyyy-mm-ddTHH:mm yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To calculate all nutritional intake from a certain date until now: calculate all "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR;
    }

    /**
     * Returns a string representation of a list of user information related commands that users can input.
     *
     * @return A string representation of a list of user information related commands that users can input.
     */
    private String getUserRelatedCommands() {
        return "  To view user information: userinfo" + LINE_SEPARATOR
                + "  To edit user information: editinfo [n/NAME] [g/GENDER] [a/AGE] [h/HEIGHT] "
                + "[o/ORIGINAL_WEIGHT] [c/CURRENT_WEIGHT] [t/TARGET_WEIGHT] [l/ACTIVITY_LEVEL]"
                + LINE_SEPARATOR;
    }

    /**
     * Returns a string representation of a list of food list related commands that users can input.
     *
     * @return A string representation of a list of food list related commands that users can input.
     */
    private String getFoodListRelatedCommands() {
        return "  To add you own food: add x/PORTION_SIZE n/FOOD_NAME k/CALORIE [c/CARBOHYDRATE] "
                + "[p/PROTEIN] [f/FAT]" + LINE_SEPARATOR
                + "  To view all food in DietBook: list" + LINE_SEPARATOR
                + "  To view all food in DietBook recorded within a time period: list yyyy-mm-ddTHH:mm "
                + "yyyy-mm-ddTHH:mm" + LINE_SEPARATOR
                + "  To view all food in DietBook recorded from a certain date until now: list "
                + "yyyy-mm-ddTHH:mm " + LINE_SEPARATOR
                + "  To delete a food from DietBook: delete INDEX" + LINE_SEPARATOR
                + "  To delete all food items from the DietBook: clear" + LINE_SEPARATOR;
    }

    /**
     * Returns a string representation of a list of database related commands that users can input.
     *
     * @return A string representation of a list of database related commands that users can input.
     */
    private String getDatabaseRelatedCommands() {
        return "  To add a food from the database: add n/FOOD_NAME x/PORTION_SIZE" + LINE_SEPARATOR
                + "  To view all food in the database: data" + LINE_SEPARATOR;
    }

    // Helper methods for calculator related commands and messages

    /**
     * Returns a string with a header and recalculatedFoods or a string stating that no food items had their
     * nutritional information recalculated if calculatedFoods is an empty string.
     *
     * @param recalculatedFoods The list of food items which had their nutritional information recalculated by
     *     DietBook.
     * @return A string with a header and recalculatedFoods or a string stating that no food items had their
     *     nutritional information recalculated if calculatedFoods is an empty string.
     */
    private String recalculatedFoodsMessage(String recalculatedFoods) {
        String message = "No food items had their nutritional information recalculated by DietBook.";
        if (trimStringGetLength(recalculatedFoods) > 0) {
            message = "Food items which had their nutritional information recalculated by DietBook: "
                    + LINE_SEPARATOR + recalculatedFoods;
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
     *     or amount consumed in a given time period.
     */
    private String stringNutritionalIntake(int nutrientIntake, String nutrientType, String nutrientUnit) {
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
     *     DietBook.
     * @return A string representation of the the total amount of a nutrient consumed by the user and
     *     the list of food items which had their nutritional information recalculated by DietBook if any.
     */
    private String stringOneIntakeAndFoodsWithoutTime(int nutrientIntake, String nutrientType,
                                                      String nutrientUnit, String recalculatedFoods) {
        performAssertionsForStringInputs(nutrientType,"Nutrient Type");
        performAssertionsForStringInputs(nutrientUnit, "Nutrient Unit");
        performAssertionsForNutritionalIntake(nutrientIntake, nutrientType);
        performAssertionsForNullStringInputs(recalculatedFoods,
                "List of foods that had their nutritional information recalculated");

        String stringNutrientIntake = stringNutritionalIntake(nutrientIntake, nutrientType, nutrientUnit);
        String message = recalculatedFoodsMessage(recalculatedFoods);
        return stringNutrientIntake + LINE_SEPARATOR + message;
    }

    private String stringOneIntakeAndFoodsWithoutTime(int nutrientIntake, String nutrientType,
                                                      String nutrientUnit) {
        performAssertionsForStringInputs(nutrientType,"Nutrient Type");
        performAssertionsForStringInputs(nutrientUnit, "Nutrient Unit");
        performAssertionsForNutritionalIntake(nutrientIntake, nutrientType);

        String stringNutrientIntake = stringNutritionalIntake(nutrientIntake, nutrientType, nutrientUnit);
        return stringNutrientIntake;
    }

    /**
     * Returns a string representation of the total amount of a nutrient or all nutrientS consumed by the
     * user  during a given time period and the list of food items recorded during the same time period
     * which had their nutritional information recalculated by DietBook if any.
     *
     * @param intakeAndFoodsWithoutTime A string representation of the the total amount of a nutrient or
     *     all nutrients consumed by the user and the list of food items which had their nutritional
     *     information recalculated by DietBook if any.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     * @return A string representation of the the total amount of a nutrient or all nutrient consumed by the
     *     user during a given time period and the list of food items recorded during the same time period
     *     which had their nutritional information recalculated by DietBook if any.
     */
    private String stringIntakeAndFoodsWithTime(String intakeAndFoodsWithoutTime,
                                                LocalDateTime start, LocalDateTime end) {
        performAssertionsForTimePeriod(start, end);

        String timePeriod = "Time period:" + stringDateTimePeriod(start, end);
        return timePeriod + LINE_SEPARATOR + LINE_SEPARATOR + intakeAndFoodsWithoutTime;
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
     *     DietBook.
     * @return A string representation of the total amount of all nutrients consumed by the user and
     *     the list of food items which had their nutritional information recalculated by DietBook if any.
     */
    private String stringAllIntakeAndFoodsWithoutTime(int calorieIntake, int carbIntake, int proteinIntake,
                                                     int fatIntake, String recalculatedFoods) {
        performAssertionsForNutritionalIntake(carbIntake, "carbohydrate");
        performAssertionsForNutritionalIntake(calorieIntake, "calorie");
        performAssertionsForNutritionalIntake(proteinIntake, "protein");
        performAssertionsForNutritionalIntake(fatIntake, "fat");
        performAssertionsForNullStringInputs(recalculatedFoods,
                "List of foods that had their nutritional information recalculated");

        String stringCarbIntake = stringNutritionalIntake(carbIntake,"carbohydrate", "g");
        String stringCalorieIntake = stringNutritionalIntake(calorieIntake,"calorie",
                "kcal");
        String stringProteinIntake = stringNutritionalIntake(proteinIntake,"protein", "g");
        String stringFatIntake = stringNutritionalIntake(fatIntake,"fat", "g");
        String message = recalculatedFoodsMessage(recalculatedFoods);

        return stringCalorieIntake + LINE_SEPARATOR
                + stringCarbIntake + LINE_SEPARATOR
                + stringProteinIntake + LINE_SEPARATOR
                + stringFatIntake + LINE_SEPARATOR
                + message;

    }

    private String stringAllIntakeAndFoodsWithoutTime(int calorieIntake, int carbIntake, int proteinIntake,
                                                      int fatIntake) {
        performAssertionsForNutritionalIntake(carbIntake, "carbohydrate");
        performAssertionsForNutritionalIntake(calorieIntake, "calorie");
        performAssertionsForNutritionalIntake(proteinIntake, "protein");
        performAssertionsForNutritionalIntake(fatIntake, "fat");


        String stringCarbIntake = stringNutritionalIntake(carbIntake,"carbohydrate", "g");
        String stringCalorieIntake = stringNutritionalIntake(calorieIntake,"calorie",
                "kcal");
        String stringProteinIntake = stringNutritionalIntake(proteinIntake,"protein", "g");
        String stringFatIntake = stringNutritionalIntake(fatIntake,"fat", "g");

        return stringCalorieIntake + LINE_SEPARATOR
                + stringCarbIntake + LINE_SEPARATOR
                + stringProteinIntake + LINE_SEPARATOR
                + stringFatIntake + LINE_SEPARATOR;

    }

    // Other helper methods

    /**
     * Prints the given message to the user.
     *
     * @param message The message to show the user.
     */
    private void print(String message) {
        performAssertionsForStringInputs(message, "Message to print");
        String divider =
                "__________________________________________________________________________________________"
                + "___________________________________________";

        System.out.println(divider + LINE_SEPARATOR
                + trimString(message) + LINE_SEPARATOR
                + divider);

    }

    /**
     * Returns a string representation of the time period with date time in the format dd MMM yyyy HHmm.
     *
     * @param start Starting date time of the time period given that needs to be converted into a String.
     * @param end Ending date time of the time period given that needs to be converted into a String.
     * @return The string representation of time period with date time in the format dd MMM yyyy HHmm.
     */
    public String stringDateTimePeriod(LocalDateTime start, LocalDateTime end) {
        performAssertionsForTimePeriod(start, end);

        String stringStart = start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        String stringEnd = end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        return " between " +  stringStart + " and " + stringEnd;
    }


    /**
     * Returns an integer representing the length of the string after it has been trimmed for leading and
     * trailing spaces.
     *
     * @param string The string to be trimmed and have its length determined.
     * @return An integer representing the length of the string after it has been trimmed for leading and
     *     trailing spaces.
     */
    public int trimStringGetLength(String string) {
        performAssertionsForNullStringInputs(string, "String to trim and have length determined");

        return trimString(string).length();
    }

    /**
     * Returns a string that has been trimmed for leading and trailing spaces.
     *
     * @param string The string to be trimmed for leading and trailing spaces.
     * @return A string that has been trimmed for leading and trailing spaces.
     */
    private String trimString(String string) {
        performAssertionsForNullStringInputs(string, "String to trim");

        return string.trim();
    }

    /**
     * Performs assertions for the string inputs.
     *
     * @param string The input value.
     * @param stringDescription A description of what the input value represents.
     */
    private void performAssertionsForStringInputs(String string, String stringDescription) {
        performAssertionsForNullStringInputs(string, stringDescription);
        assert trimStringGetLength(string) > 0 : stringDescription + " should not be an empty string";
    }

    /**
     * Performs assertions for the time inputs.
     *
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    private void performAssertionsForTimePeriod(LocalDateTime start, LocalDateTime end) {
        assert start != null : "Starting date time of the time period given should not be null";
        assert end != null : "Ending date time of the time period given should not be null";
        assert !start.isAfter(end) : "Starting date time should not be later than ending date time "
                + "of the time period";
        LocalDateTime now = LocalDateTime.now();
        assert !start.isAfter(now) : "Starting date time of the time period given should "
                + "not be in the future" + start + LocalDateTime.now();
        assert !end.isAfter(now) : "Ending date time of the time period given should not be"
                + " in the future" + end + LocalDateTime.now();
    }

    /**
     * Performs assertions for null string inputs.
     *
     * @param string The input value.
     * @param stringDescription A description of what the input value represents.
     */
    private void performAssertionsForNullStringInputs(String string, String stringDescription) {
        assert string != null : stringDescription + " should not be null";
    }

    /**
     * Performs assertions for nutritional intake inputs.
     *
     * @param nutrientIntake The nutritional intake value.
     * @param nutrientType The nutrient type.
     */
    private void performAssertionsForNutritionalIntake(int nutrientIntake, String nutrientType) {
        assert nutrientIntake >= 0 : "Total " + nutrientType + " intake should be equals to or greater than 0";
    }

    /**
     * Performs assertions for the calorie recommendation input.
     *
     * @param calorieRecommendation The recommended daily calorie intake for the user.
     */
    private void performAssertionsForCalorieRecommendation(int calorieRecommendation) {
        // A minimum daily intake of 1200 calorie is required to stay healthy.
        assert calorieRecommendation >= 1200 : "Daily calorie recommendation should be equals to or greater"
                + " than 1200";
        // Highest calorie intake for an athlete currently stands at 12000.
        assert calorieRecommendation <= 12000 : "Daily calorie recommendation should be equals to or less "
                + "than 12,000";
    }
}
