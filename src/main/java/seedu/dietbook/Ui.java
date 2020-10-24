package seedu.dietbook;

import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.person.Gender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a text user interface.
 * A <code>Ui</code> objects deals with user interaction by showing users the appropriate messages after a
 * valid command is executed or when an error occurs.
 */
public class Ui {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Constructs a <code>Ui</code> object.
     */
    public Ui() {
    }

    // Methods in the Ui class are organised according to their function in the order of: system related,
    // database related, person related, food list related, calculator related and other helper methods.

    // Methods required to print system related commands or messages.

    /**
     * Prints the welcome message from DietBook when it is fist booted up.
     */
    public void printWelcomeMessage() {
        String logo = " _______  __ ______ ________ _______   ______   ______  __    __" + LINE_SEPARATOR
                + "|   __  \\|  |   ___|__    __|   __  \\ /  __  \\ /  __  \\|  | /  /" + LINE_SEPARATOR
                + "|  |  |  |  |  |___   |  |  |  |__|  |  |  |  |  |  |  |  |/  /"  + LINE_SEPARATOR
                + "|  |  |  |  |   ___|  |  |  |   __  <|  |  |  |  |  |  |     /" + LINE_SEPARATOR
                + "|  |__|  |  |  |___   |  |  |  |__|  |  |__|  |  |  |  |  |\\  \\" + LINE_SEPARATOR
                + "|_______/|__|______|  |__|  |_______/ \\______/ \\______/|__| \\__\\" + LINE_SEPARATOR;
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
        assert name != null : "Name should not be null";
        assert trimStringGetLength(name) > 0 : "Name should not be an empty string";
        print("Hi " + trimString(name) + "!" + LINE_SEPARATOR
                + "Before we get started, I would like to know about about you so that I can make more "
                + LINE_SEPARATOR
                + "accurate calculations for you :). Therefore, could you please share with me the "
                + "following:" + LINE_SEPARATOR
                + "- Your gender either F for " + Gender.FEMALE.getDescription() + " or M for "
                + Gender.MALE.getDescription() + "." + LINE_SEPARATOR
                + "- Your age which is a positive integer." + LINE_SEPARATOR
                + "- Your height in cm." + LINE_SEPARATOR
                + "- Your original weight in kg." + LINE_SEPARATOR
                + "- Your target weight in kg, or your original weight if that is also your target weight."
                + LINE_SEPARATOR
                + "- Your activity level, represented by a number from 1 to 5." + LINE_SEPARATOR
                + "  1 = " + ActivityLevel.NONE.getDescription() + LINE_SEPARATOR
                + "  2 = " + ActivityLevel.LOW.getDescription() + LINE_SEPARATOR
                + "  3 = " + ActivityLevel.MEDIUM.getDescription() + LINE_SEPARATOR
                + "  4 = " + ActivityLevel.HIGH.getDescription() + LINE_SEPARATOR
                + "  5 = " + ActivityLevel.EXTREME.getDescription() + LINE_SEPARATOR + LINE_SEPARATOR
                + "Please input your details in the following format:" + LINE_SEPARATOR
                + "  info g/GENDER a/AGE h/HEIGHT o/ORIGINAL_WEIGHT t/TARGET_WEIGHT l/ACTIVITY_LEVEL"
                        + LINE_SEPARATOR
                + "  Example: info g/F a/21 h/165 o/65 t/55 l/2");
    }

    /**
     * Prints a message that notifies the user that DietBook has been initialised and shows a list of user
     * commands that the user can input.
     */
    public void printTutorialMessage() {
        print("Thank you! DietBook has been initialised and you may start by entering any of the commands "
                + "listed below." + LINE_SEPARATOR + LINE_SEPARATOR
                + "To add a food from the database: add n/FOOD_NAME x/PORTION_SIZE" + LINE_SEPARATOR
                + "To view all food in the database: data" + LINE_SEPARATOR
                + "To add you own food: add x/PORTION_SIZE n/FOOD_NAME k/CALORIE [c/CARBOHYDRATE] "
                + "[p/PROTEIN] [f/FAT]" + LINE_SEPARATOR
                + "To view all food in DietBook: list" + LINE_SEPARATOR
                + "To delete a food from DietBook: delete INDEX" + LINE_SEPARATOR
                + "To delete all food items from the DietBook: clear" + LINE_SEPARATOR
                + "To show user information: userinfo" + LINE_SEPARATOR
                + "To calculate carbohydrate intake: calculate carbohydrate" + LINE_SEPARATOR
                + "To calculate calorie intake: calculate calorie" + LINE_SEPARATOR
                + "To calculate protein intake: calculate protein" + LINE_SEPARATOR
                + "To calculate fat intake: calculate fat" + LINE_SEPARATOR
                + "To calculate all nutritional intake: calculate all" + LINE_SEPARATOR
                + "To exit DietBook: exit");
    }

    /**
     * Prints a message to show that the food specified has been added to the food list.
     *
     * @param newFood The string representation of the new food item that was added to the food list.
     */
    public void printNewFood(String newFood) {
        assert newFood != null : "String representation of the food that was added should not be null";
        assert trimStringGetLength(newFood) > 0 : "String representation of the food that was added should not "
                + "be an empty string";
        print("Got it! I've added this food item:" + LINE_SEPARATOR
                + "  " + trimString(newFood));
    }

    /**
     * Prints all the food items in the food list in the order that they were added or a message stating
     * that the food list is empty if there are no food items.
     *
     * @param allFood The string representation of all the food items in the food list.
     */
    public void printFoodList(String allFood) {
        assert allFood != null : "String representation of all food in food list should not be null";
        if (trimStringGetLength(allFood) < 1) {
            print("DietBook is currently empty.");
        } else {
            print("Here are the food items in DietBook:" + LINE_SEPARATOR + trimString(allFood));
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
    public void printFoodListGivenTimePeriod(String foods, LocalDateTime start, LocalDateTime end) {
        assert foods != null : "String representation of food items in the food list recorded during the "
                + "time period given should not be null";
        assert start != null : "Starting date time of the time period should not be null";
        assert end != null : "Ending date time of the time period should not be null";
        assert !start.isAfter(end) : "Starting date time should not be later than ending date time "
                + "of the time period";
        String stringStart = stringDateTime(start);
        String stringEnd = stringDateTime(end);
        if (trimStringGetLength(foods) < 1) {
            print("No food item was recorded in DietBook between " + stringStart + " and " + stringEnd + ".");
        } else {
            print("Here are the food items recorded in DietBook between " + stringStart + " and " + stringEnd
                    + " :" + LINE_SEPARATOR + trimString(foods));
        }
    }

    /**
     * Returns a string representation of the date time in the format dd MMM yyyy HHmm.
     *
     * @param dateTime The date time that needs to be converted into a String.
     * @return Returns a string representation of the date and time in the format dd MMM yyyy HHmm.
     */
    public String stringDateTime(LocalDateTime dateTime) {
        assert dateTime != null : "Date time to be converted into string should not be null";
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    /**
     * Prints all the food in the database sorted by the canteen and then the store it is found.
     *
     * @param foodDatabase The string representation of all the food items stored in the database.
     */
    public void printDatabase(String foodDatabase) {
        assert foodDatabase != null : "Food database should not be null";
        assert trimStringGetLength(foodDatabase) > 0 : "Food database should not be empty";
        print("Here are the food items in the database:" + LINE_SEPARATOR + trimString(foodDatabase));
    }

    /**
     * Prints all the information related to the user.
     *
     * @param personInfo The user's personal information.
     */
    public void printPersonInfo(String personInfo) {
        assert personInfo != null : "Person information should not be null";
        assert trimStringGetLength(personInfo) > 0 : "Person information should not be an empty string";
        print("Here is your information:" + LINE_SEPARATOR
                + trimString(personInfo));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user.
     *
     * @param carbohydrateIntake The total amount of carbohydrates of all the food in the food list.
     */
    public void printCarbohydrateIntake(int carbohydrateIntake) {
        assert carbohydrateIntake >= 0 : "Total carbohydrate intake should be equals to or greater than 0";
        print("Total carbohydrate intake: " + carbohydrateIntake + "g");
    }

    /**
     * Prints the total amount of calories consumed by the user.
     *
     * @param calorieIntake The total amount of calories of all the food in the food list.
     */
    public void printCalorieIntake(int calorieIntake) {
        assert calorieIntake >= 0 : "Total calorie intake should be equals to or greater than 0";
        print("Total calorie intake: " + calorieIntake + "kcal");
    }

    /**
     * Prints the total amount of proteins consumed by the user.
     *
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     */
    public void printProteinIntake(int proteinIntake) {
        assert proteinIntake >= 0 : "Total protein intake should be equals to or greater than 0 ";
        print("Total protein intake: " + proteinIntake + "g");
    }

    /**
     * Prints the total amount of fats consumed by the user.
     *
     * @param fatIntake The total amount of fats of all the food in the food list.
     */
    public void printFatIntake(int fatIntake) {
        assert fatIntake >= 0 : "Total fat intake should be equals to or greater than 0";
        print("Total fat intake: " + fatIntake + "g");
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user.
     *
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param carbohydrateIntake The total amount of carbohydrates of all the food in the food list.
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param fatIntake The total amount of fats of all the food in the food list.
     */
    public void printAllNutrientIntake(int calorieIntake, int carbohydrateIntake, int proteinIntake,
                                       int fatIntake) {
        assert carbohydrateIntake >= 0 : "Total carbohydrate intake should be equals to or greater than 0";
        assert calorieIntake >= 0 : "Total calorie intake should be equals to or greater than 0";
        assert proteinIntake >= 0 : "Total protein intake should be equals to or greater than 0 ";
        assert fatIntake >= 0 : "Total fat intake should be equals to or greater than 0";

        print("Total calorie intake: " + calorieIntake + "kcal" + LINE_SEPARATOR
                + "Total carbohydrate intake: " + carbohydrateIntake + "g" + LINE_SEPARATOR
                + "Total protein intake: " + proteinIntake + "g" + LINE_SEPARATOR
                + "Total fat intake: " + fatIntake + "g");
    }

    /**
     * Prints a message to show that the food specified has been deleted from the food list.
     *
     * @param deletedFood The string representation of the food that was deleted from the food list.
     */
    public void printDeletedFood(String deletedFood) {
        assert deletedFood != null : "String representation of the food that was deleted should not be null";
        assert trimStringGetLength(deletedFood) > 0 : "String representation of the food that was deleted should"
                + " not be an empty string";
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

    /**
     * Prints an exit message when DietBook is closed.
     *
     * @param name The name of the user.
     */
    public void printExitMessage(String name) {
        assert name != null : "Name should not be null";
        assert trimStringGetLength(name) > 0 : "Name should not be an empty string";
        print("Bye " + trimString(name) + "! Hope to see you again soon!");
    }

    /**
     * Prints an error message given what or where the error is.
     *
     * @param errorMessage Message detailing what or where the error is.
     */
    public void printErrorMessage(String errorMessage) {
        assert errorMessage != null : "Error message should not be null";
        assert trimStringGetLength(errorMessage) > 0 : "Error message should not be an empty string";
        print(":( Oh no..." + trimString(errorMessage));
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
        assert string != null : "String to trim and have length determined should not be null";
        return trimString(string).length();
    }

    /**
     * Returns a string that has been trimmed for leading and trailing spaces.
     *
     * @param string The string to be trimmed for leading and trailing spaces.
     * @return A string that has been trimmed for leading and trailing spaces.
     */
    public String trimString(String string) {
        assert string != null : "String to trim should not be null";
        return string.trim();
    }

    /**
     * Prints the given message to the user.
     *
     * @param message The message to show the user.
     */
    public void print(String message) {
        assert message != null : "Message to print should not be null";
        assert trimStringGetLength(message) > 0 : "Message to print should not be an empty string";
        String divider =
                "__________________________________________________________________________________________"
                + "____________________";

        System.out.println(divider + LINE_SEPARATOR
                + trimString(message) + LINE_SEPARATOR
                + divider);

    }
}
