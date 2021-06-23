package ui.diet.dietsession;

import ui.CommonUi;

//@@author zsk612
/**
 * A class that contains templates of message to show to user.
 */
public class DietSessionUi extends CommonUi {

    public static final String DIET_INPUT_PROMPT_EDIT = "Diet Menu > Diet Session ";
    public static final String DIET_INPUT_PROMPT_NEW = "Diet Menu > New Diet Session";
    public static final String MESSAGE_ADD_WRONG_FORMAT = "Wrong format, please enter in the format:\n\t "
            + "add [FOOD_NAME] /c [CALORIES]";
    public static final String MESSAGE_CLEAR_ABORTED = "You have aborted clear operation.";
    public static final String MESSAGE_CLEAR_SUCCESS = "Alright, your food items have been cleared.";
    public static final String MESSAGE_DELETE_WRONG_FORMAT = "Wrong format, please enter in the format:\n\t "
            + "delete [INDEX]";
    public static final String MESSAGE_HIGH_CALORIES = "Your calories for this food item seems a little high, "
            + "so I've set it to 200,000.\n\t ";
    public static final String MESSAGE_NEGATIVE_CALORIES = "Please enter a positive number for calories!";
    public static final String MESSAGE_NO_FOOD = "Sorry, there is nothing in your food list.";
    public static final String MESSAGE_NO_FOOD_NAME = "Please enter food name!";
    public static final String MESSAGE_NO_SUCH_INDEX = "Sorry, there is no food item at that index.";
    public static final String MESSAGE_SEARCH_PROMPT = "Here are the search results: \n\t ";
    public static final String MESSAGE_WRONG_CALORIES = "Please input a number for calories.";

    /**
     * Prints help message.
     *
     * @param helpMessage appends all the help messages
     */
    public static void printHelp(StringBuilder helpMessage) {

        helpMessage.append(helpFormatter("Add", "add [FOOD_NAME] /c [CALORIES]",
                "Add a new food item"));
        helpMessage.append(helpFormatter("List", "list",
                "Show all food items"));
        helpMessage.append(helpFormatter("Delete", "delete [INDEX]",
                "Delete the food item at the input index"));
        helpMessage.append(helpFormatter("Search", "search [FOOD_NAME]",
                "Search the diet session for food with the name specified"));
        helpMessage.append(helpFormatter("Clear", "clear",
                "Clear all food items"));
        helpMessage.append(helpFormatter("End", "end",
                "Go back to the Diet Menu."));
    }

    /**
     * Prints opening messages for diet sessions.
     */
    public void printOpening() {
        showToUser("Starting Diet Session!");
    }
}
