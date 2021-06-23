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
 * A representation of the command for list commands in diet session.
 */
public class FoodItemList extends Command {

    /**
     * Overrides execute for list command to list food items.
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
            double totalCalories = 0;
            StringBuilder listResult = new StringBuilder();
            if (foodList.size() > 0) {
                for (int i = 0; i < foodList.size(); i++) {
                    totalCalories += foodList.get(i).getCalories();
                }
                String totalMealCalories = "\n" + LS + "Your total calories for this meal is " + totalCalories + ".";
                String formattedList = formatList(foodList);
                listResult.append(formattedList);
                listResult.append(totalMealCalories);
                result = listResult.toString().trim();
                logger.log(Level.INFO, "Listed all foods in Diet Session");
            } else {
                listResult.append(DietSessionUi.MESSAGE_NO_FOOD);
                result = listResult.toString().trim();
            }
        } catch (NullPointerException e) {
            result = DietSessionUi.MESSAGE_NO_FOOD;
            logger.log(Level.WARNING, "No item in food list");
        }
        return new CommandResult(result, ExecutionResult.OK);
    }

    private String formatList(ArrayList<Food> foodList) {

        ArrayList<String> foodNames = (ArrayList<String>) foodList.stream()
                .map(Food::getName).collect(Collectors.toList());
        int descriptionMaxLenInt = Math.max(10,
                foodNames.stream().max(Comparator.comparingInt(String::length)).get().length());

        String descriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt + 1) + "s";

        String returnString = String.format("%-8s", "Index") + String.format(descriptionFormat, "Food")
                + String.format("%-9s", "Calories") + LS;

        StringBuilder infoBuilder = new StringBuilder(returnString);

        String listDescriptionFormat = "%-" + String.format("%d", descriptionMaxLenInt) + "s %-9s ";
        for (int i = 0; i < foodList.size(); i++) {
            String rowContent = String.format(listDescriptionFormat, foodList.get(i).getName(),
                    foodList.get(i).getCalories());
            String row = String.format("%-8s", i + 1) + rowContent + LS;
            infoBuilder.append(row);
        }
        returnString = infoBuilder.toString().trim();
        return returnString;
    }
}
