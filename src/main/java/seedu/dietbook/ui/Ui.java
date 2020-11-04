package seedu.dietbook.ui;

import seedu.dietbook.exception.DietException;

import java.time.LocalDateTime;

/**
 * Represents a manager that deals with user interaction and communicating with the logic component.
 * A <code>Ui</code> objects deals with user input, output and communication with the logic component.
 */
public class Ui {

    private final UiInput uiInput;
    private final UiHelper uiHelper;
    private final UiOutput uiOutput;
    private final UiMessage uiMessage;

    /**
     * Constructs a <code>Ui</code> object.
     */
    public Ui() {
        uiInput = new UiInput();
        uiHelper = new UiHelper();
        uiOutput = new UiOutput();
        uiMessage = new UiMessage();
    }

    // Methods in the Ui class are organised according to their function in the order of: system related,
    // database related, person related, food list related, nutritional related and other helper methods.

    // Methods required to print system related commands or messages.

    /**
     * Returns the non-empty user input that has been trimmed.
     *
     * @return The non-empty user input that has been trimmed.
     */
    public String getCommand() throws DietException {
        return uiInput.getCommand();
    }

    /**
     * Prints the welcome message from DietBook when it is first booted up.
     */
    public void printWelcomeMessage() {
        uiOutput.print(uiMessage.getWelcomeMessage());
    }

    /**
     * Prints a message asking the user to input their personal information related to dieting and health
     * which includes gender, age, height, activity level, original weight and target weight.
     *
     * @param name The name of the user.
     */
    public void printAskForUserInfoMessage(String name) {
        uiHelper.performAssertionsForStringInputs(name, "Name");

        uiOutput.print(uiMessage.getAskForUserInfoMessage(name));
    }

    /**
     * Prints an exit message when DietBook is closed.
     *
     */
    public void printExitMessage() {
        uiOutput.print(uiMessage.getExitMessage());
    }

    /**
     * Prints an error message given what or where the error is.
     *
     * @param errorMessage Message detailing what or where the error is.
     */
    public void printErrorMessage(String errorMessage) {
        uiHelper.performAssertionsForStringInputs(errorMessage, "Error message");

        uiOutput.print(uiMessage.getErrorMessage(errorMessage));
    }

    /**
     * Prints a message that notifies the user that DietBook has been initialised.
     */
    public void printInitialisationCompleteMessage() {
        uiOutput.print(uiMessage.getInitialisationCompleteMessage());
    }

    /**
     * Prints a message informing the user that DietBook has successfully saved all their data.
     */
    public void dataSuccessfullySavedMessage() {
        uiOutput.print(uiMessage.getDataSuccessfullySavedMessage());
    }

    /**
     * Prints the welcome back message when user reboots up DietBook after the first initialisation.
     *
     * @param name The name of the user.
     */
    public void printWelcomeBackMessage(String name) {
        uiHelper.performAssertionsForStringInputs(name, "Name");

        uiOutput.print(uiMessage.getWelcomeBackMessage(name));
    }

    /**
     * Prints a string representation of a list of the commands that users can use.
     */
    public void printHelpCommandMessage() {
        uiOutput.print(uiMessage.getHelpCommandMessage());
    }

    // Methods required to print database related commands or messages.

    /**
     * Prints all the food in the database sorted by the canteen and then the store it is found.
     *
     * @param foodDatabase The string representation of all the food items stored in the database.
     */
    public void printDatabase(String foodDatabase) {
        uiHelper.performAssertionsForStringInputs(foodDatabase,"Database");

        uiOutput.print(uiMessage.getDatabaseMessage(foodDatabase));
    }

    // Methods required to print user information related commands and messages.

    /**
     * Prints all the information related to the user.
     *
     * @param personInfo The user's personal information.
     */
    public void printPersonInfo(String personInfo) {
        uiHelper.performAssertionsForStringInputs(personInfo,"Person information");

        uiOutput.print(uiMessage.getPersonInfoMessage(personInfo));
    }

    /**
     * Prints all the updated information related to the user.
     *
     * @param personInfo The user's personal information.
     */
    public void printEditedPersonInfo(String personInfo) {
        uiHelper.performAssertionsForStringInputs(personInfo,"Updated person information");

        uiOutput.print(uiMessage.getEditedPersonInfoMessage(personInfo));
    }

    // Methods required for printing FoodList related commands and messages.

    /**
     * Prints all the food items in the food list in the order that they were added or a message stating
     * that the food list is empty if there are no food items.
     *
     * @param allFood The string representation of all the food items in the food list.
     */
    public void printFoodList(String allFood) {
        uiHelper.performAssertionsForNullStringInputs(allFood,
                "String representation of all food in food list");

        if (uiHelper.isEmptyString(allFood)) {
            uiOutput.print(uiMessage.getEmptyFoodListMessage());
        } else {
            uiOutput.print(uiMessage.getFoodListMessage(allFood));
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
        uiHelper.performAssertionsForNullStringInputs(foods,
                "String representation of food items in the food list recorded during the "
                        + "time period given");
        uiHelper.performAssertionsForTimePeriod(start, end);

        if (uiHelper.isEmptyString(foods)) {
            uiOutput.print(uiMessage.getEmptyFoodListMessage(start, end));
        } else {
            uiOutput.print(uiMessage.getFoodListMessage(foods, start, end));
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
        uiHelper.performAssertionsForStringInputs(newFood,
                "String representation of the food that was added");

        uiOutput.print(uiMessage.getNewFoodMessage(newFood));
    }

    /**
     * Prints a message to show that the food specified has been deleted from the food list.
     *
     * @param deletedFood The string representation of the food that was deleted from the food list.
     */
    public void printDeletedFood(String deletedFood) {
        uiHelper.performAssertionsForStringInputs(deletedFood,
                "String representation of the food that was deleted");

        uiOutput.print(uiMessage.getDeletedFoodMessage(deletedFood));
    }

    /**
     * Prints a message to show that the food list has been successfully cleared and is now empty.
     */
    public void printClearFoodListMessage() {
        uiOutput.print(uiMessage.getClearFoodListMessage());
    }

    // Methods required to print nutritional intake and recommendation related commands and messages.

    /**
     * Prints the daily recommended calorie intake of the user based on the user's personal information.
     *
     * @param calorieRecommendation The daily recommended calorie intake of the user.
     */
    public void printCalorieRecommendation(String name, int calorieRecommendation) {
        uiHelper.performAssertionsForStringInputs(name, "Name");
        uiHelper.performAssertionsForCalorieRecommendation(calorieRecommendation);

        uiOutput.print(uiMessage.getCalorieRecommendationMessage(name, calorieRecommendation));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user.
     *
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     */
    public void printCarbIntake(int carbIntake) {
        uiOutput.print(uiMessage.getOneIntakeMessage(carbIntake, "carbohydrate",
                "g"));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user given a certain time period.
     *
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded during the
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printCarbIntake(int carbIntake, LocalDateTime start, LocalDateTime end) {
        String stringCarbIntake = uiMessage.getOneIntakeMessage(carbIntake,"carbohydrate", "g");
        uiOutput.print(uiMessage.getIntakeWithTimeMessage(stringCarbIntake, start, end));
    }

    /**
     * Prints the total amount of carbohydrates consumed by the user given a start date.
     *
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded from the
     *     start date till now.
     * @param start Starting date time to calculate from.
     */
    public void printCarbIntake(int carbIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCarbIntake(carbIntake, start, end);
    }

    /**
     * Prints the total amount of calories consumed by the user.
     *
     * @param calorieIntake The total amount of calories of all the food in the food list.
     */
    public void printCalorieIntake(int calorieIntake) {
        uiOutput.print(uiMessage.getOneIntakeMessage(calorieIntake, "calorie", "kcal"));
    }

    /**
     * Prints the total amount of calories consumed by the user given a certain time period.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded during the
     *     time period given.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printCalorieIntake(int calorieIntake, LocalDateTime start, LocalDateTime end) {
        String stringCalorieIntake = uiMessage.getOneIntakeMessage(calorieIntake,"calorie",
                "kcal");
        uiOutput.print(uiMessage.getIntakeWithTimeMessage(stringCalorieIntake, start, end));
    }

    /**
     * Prints the total amount of calories consumed by the user given a start date.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded from the
     *     start date till now.
     * @param start Starting date time to calculate from.
     */
    public void printCalorieIntake(int calorieIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printCalorieIntake(calorieIntake, start, end);
    }

    /**
     * Prints the total amount of proteins consumed by the user.
     *
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     */
    public void printProteinIntake(int proteinIntake) {
        uiOutput.print(uiMessage.getOneIntakeMessage(proteinIntake, "protein", "g"));
    }

    /**
     * Prints the total amount of proteins consumed by the user given a certain time period.
     *
     * @param proteinIntake The total amount of proteins of food in the food list recorded during the
     *     time period given.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printProteinIntake(int proteinIntake, LocalDateTime start, LocalDateTime end) {
        String stringProteinIntake = uiMessage.getOneIntakeMessage(proteinIntake, "protein", "g");
        uiOutput.print(uiMessage.getIntakeWithTimeMessage(stringProteinIntake, start, end));
    }

    /**
     * Prints the total amount of proteins consumed by the user given a start date.
     *
     * @param proteinIntake The total amount of proteins of food in the food list recorded from the
     *     start date till now.
     * @param start Starting date time to calculate from.
     */
    public void printProteinIntake(int proteinIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printProteinIntake(proteinIntake, start, end);
    }

    /**
     * Prints the total amount of fats consumed by the user.
     *
     * @param fatIntake The total amount of fats of all the food in the food list.
     */
    public void printFatIntake(int fatIntake) {
        uiOutput.print(uiMessage.getOneIntakeMessage(fatIntake, "fat", "g"));
    }

    /**
     * Prints the total amount of fats consumed by the user given a certain time period.
     *
     * @param fatIntake The total amount of fats of food in the food list recorded during the
     *     time period given.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printFatIntake(int fatIntake, LocalDateTime start, LocalDateTime end) {
        String stringFatIntake = uiMessage.getOneIntakeMessage(fatIntake,"fat", "g");
        uiOutput.print(uiMessage.getIntakeWithTimeMessage(stringFatIntake, start, end));
    }

    /**
     * Prints the total amount of fats consumed by the user given a start date.
     *
     * @param fatIntake The total amount of fats of food in the food list recorded from the start date till
     *     now.
     * @param start Starting date time to calculate from.
     */
    public void printFatIntake(int fatIntake, LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        printFatIntake(fatIntake, start, end);
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user.
     *
     * @param carbIntake The total amount of carbohydrates of all the food in the food list.
     * @param calorieIntake The total amount of calories of all the food in the food list.
     * @param proteinIntake The total amount of proteins of all the food in the food list.
     * @param fatIntake The total amount of fats of all the food in the food list.
     */
    public void printAllIntake(int calorieIntake, int carbIntake, int proteinIntake,
                               int fatIntake) {
        uiOutput.print(uiMessage.getAllIntakeMessage(calorieIntake, carbIntake, proteinIntake,
                fatIntake));
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user given a
     * certain time period.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded during the
     *     time period given.
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded during the
     *     time period given.
     * @param proteinIntake The total amount of proteins of food in the food list recorded during the
     *     time period given.
     * @param fatIntake The total amount of fats of food in the food list recorded during the
     *     time period given.
     * @param start Starting date time of the time period given.
     * @param end Ending date time of the time period given.
     */
    public void printAllIntake(int calorieIntake, int carbIntake, int proteinIntake,
                               int fatIntake, LocalDateTime start, LocalDateTime end) {
        String allIntake = uiMessage.getAllIntakeMessage(calorieIntake, carbIntake, proteinIntake, fatIntake);
        uiOutput.print(uiMessage.getIntakeWithTimeMessage(allIntake, start, end));
    }

    /**
     * Prints the total amount of calories, carbohydrates, fats and proteins consumed by the user given a
     * start date.
     *
     * @param calorieIntake The total amount of calories of food in the food list recorded from the start
     *     date till now.
     * @param carbIntake The total amount of carbohydrates of food in the food list recorded from the start
     *     date till now.
     * @param proteinIntake The total amount of proteins of food in the food list recorded from the start
     *     date till now.
     * @param fatIntake The total amount of fats of food in the food list recorded from the start date till
     *     now.
     * @param start Starting date time to calculate from.
     */
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
        uiOutput.print(uiMessage.getOneIntakeAndFoodsMessage(carbIntake, "carbohydrate",
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
        String carbIntakeAndFoodsWithoutTime = uiMessage.getOneIntakeAndFoodsMessage(carbIntake,
                "carbohydrate", "g", recalculatedFoods);
        uiOutput.print(uiMessage.getIntakeAndFoodsWithTimeMessage(carbIntakeAndFoodsWithoutTime, start, end));
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
        uiOutput.print(uiMessage.getOneIntakeAndFoodsMessage(calorieIntake, "calorie", "kcal",
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
        String calorieIntakeAndFoodsWithoutTime = uiMessage.getOneIntakeAndFoodsMessage(calorieIntake,
                "calorie", "kcal", recalculatedFoods);
        uiOutput.print(uiMessage.getIntakeAndFoodsWithTimeMessage(calorieIntakeAndFoodsWithoutTime, start, end));
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
        uiOutput.print(uiMessage.getOneIntakeAndFoodsMessage(proteinIntake, "protein", "g",
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
        String proteinIntakeAndFoodsWithoutTime = uiMessage.getOneIntakeAndFoodsMessage(proteinIntake,
                "protein", "g", recalculatedFoods);
        uiOutput.print(uiMessage.getIntakeAndFoodsWithTimeMessage(proteinIntakeAndFoodsWithoutTime, start, end));
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
        uiOutput.print(uiMessage.getOneIntakeAndFoodsMessage(fatIntake, "fat", "g",
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
        String fatIntakeAndFoodsWithoutTime = uiMessage.getOneIntakeAndFoodsMessage(fatIntake,
                "fat", "g", recalculatedFoods);
        uiOutput.print(uiMessage.getIntakeAndFoodsWithTimeMessage(fatIntakeAndFoodsWithoutTime, start, end));
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
        uiOutput.print(uiMessage.getAllIntakeAndFoodsMessage(calorieIntake, carbIntake, proteinIntake,
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
        String allIntakeAndFoodsWithoutTime = uiMessage.getAllIntakeAndFoodsMessage(calorieIntake,
                carbIntake, proteinIntake, fatIntake, recalculatedFoods);
        uiOutput.print(uiMessage.getIntakeAndFoodsWithTimeMessage(allIntakeAndFoodsWithoutTime, start, end));
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
}
