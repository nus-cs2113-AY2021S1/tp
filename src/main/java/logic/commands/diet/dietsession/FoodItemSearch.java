package logic.commands.diet.dietsession;

import logic.commands.Command;
import models.Food;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static ui.CommonUi.LS;

//@@author zsk612
/**
 * A representation of the command for search commands in diet session.
 */
public class FoodItemSearch extends Command {

    DietSessionUi ui = new DietSessionUi();

    /**
     * Overrides execute for search command to search food items.
     *
     * @param input user input for command
     * @param foodList arraylist that stored all the food items
     * @param storage storage for diet session
     * @param index Integer variable that shows the index of the session
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     */
    @Override
    public CommandResult execute(String input, ArrayList<Food> foodList, DietStorage storage, Integer index) {
        String result = "";
        try {
            StringBuilder searchResult = new StringBuilder();
            searchResult.append(DietSessionUi.MESSAGE_SEARCH_PROMPT);
            String formattedList = formatList(foodList, input.trim());
            searchResult.append(formattedList);
            result = searchResult.toString().trim();
            logger.log(Level.INFO, "Listed all searched foods in Diet Session");
        } catch (NullPointerException e) {
            result = DietSessionUi.MESSAGE_NO_FOOD;
            logger.log(Level.WARNING, "No item in food list for search");
        }
        return new CommandResult(result, ExecutionResult.OK);
    }

    private String formatList(ArrayList<Food> foodList, String searchTag) {

        ArrayList<String> foodNames = (ArrayList<String>) foodList.stream()
                .map(Food::getName).collect(Collectors.toList());
        int descriptionMaxLenInt = Math.max(10,
                foodNames.stream().max(Comparator.comparingInt(String::length)).get().length());

        String descriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt + 1) + "s";

        String returnString = String.format("%-8s", "Index") + String.format(descriptionFormat, "Food")
                + String.format("%-9s", "Calories") + LS;

        StringBuilder infoBuilder = new StringBuilder(returnString);

        String listDescriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt) + "s %-9s ";
        int numberOfResults = 0;
        for (int i = 0; i < foodList.size(); i++) {
            String foodName = foodList.get(i).getName();
            Double calories = foodList.get(i).getCalories();
            if (foodName.contains(searchTag)) {
                String rowContent = String.format(listDescriptionFormat, foodName, calories);
                String row = String.format("%-8s",  ++numberOfResults) + rowContent + LS;
                infoBuilder.append(row);
            }
        }
        String foodItemSearchSize = "\n\t You have " + numberOfResults + " record(s)" + LS;
        infoBuilder.append(foodItemSearchSize);
        if (numberOfResults == 0) {
            infoBuilder.setLength(0);
            infoBuilder.append("Sorry, there is nothing found in your food list.");
        }
        returnString = infoBuilder.toString().trim();
        return returnString;
    }
}
