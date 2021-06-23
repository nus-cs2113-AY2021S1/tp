package logic.commands.diet.dietsession;

import logic.commands.CommandResult;
import models.Food;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static profile.Constants.CALORIES_UPPER_BOUND;

//@@author zsk612
public class FoodItemAddTest {

    ArrayList<Food> foodList = new ArrayList<>();
    DietStorage storage = new DietStorage();
    FoodItemAdd foodItemAdd = new FoodItemAdd();

    @Test
    public void execute_correctFoodItem_addSuccessful() {
        String input = "Noodles /c 300";
        StringBuilder userOutput = new StringBuilder();
        Food temp = new Food("Noodles", 300);
        userOutput.append("Yay! You have added " + temp.toString());
        CommandResult result = foodItemAdd.execute(input, foodList, storage, -1);
        assertEquals(userOutput.toString(), result.getFeedbackMessage());
    }

    @Test
    public void execute_exceedMaxCalories_returnMaxCalories() {
        String input = "Noodles /c 1000000000000";
        StringBuilder userOutput = new StringBuilder();
        Food temp = new Food("Noodles", CALORIES_UPPER_BOUND);
        userOutput.append(DietSessionUi.MESSAGE_HIGH_CALORIES);
        userOutput.append("Yay! You have added " + temp.toString());
        CommandResult result = foodItemAdd.execute(input, foodList, storage, -1);
        assertEquals(userOutput.toString(), result.getFeedbackMessage());
    }

    @Test
    public void execute_noFoodName_returnNoNameWarning() {
        String input = "/c 300";
        String expected = DietSessionUi.MESSAGE_NO_FOOD_NAME;
        CommandResult result = foodItemAdd.execute(input, foodList, storage, -1);
        assertEquals(expected, result.getFeedbackMessage());
    }

    @Test
    public void execute_wrongCaloriesNumber_returnNumberFormatWarning() {
        String input = "Noodles /c haha";
        String expected = DietSessionUi.MESSAGE_WRONG_CALORIES;
        CommandResult result = foodItemAdd.execute(input, foodList, storage, -1);
        assertEquals(expected, result.getFeedbackMessage());
    }

    @Test
    public void execute_negativeCaloriesNumber_returnNegativeCaloriesWarning() {
        String input = "Noodles /c -100";
        String expected = DietSessionUi.MESSAGE_NEGATIVE_CALORIES;
        CommandResult result = foodItemAdd.execute(input, foodList, storage, -1);
        assertEquals(expected, result.getFeedbackMessage());
    }
}
