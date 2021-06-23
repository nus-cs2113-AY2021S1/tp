package logic.commands.diet.dietsession;

import logic.commands.CommandResult;
import models.Food;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;
import ui.diet.dietsession.DietSessionUi;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author zsk612
public class FoodItemDeleteTest {

    ArrayList<Food> foodList = new ArrayList<>();
    DietStorage storage = new DietStorage();
    FoodItemDelete foodItemDelete = new FoodItemDelete();

    @Test
    public void execute_correctFoodItemIndex_deleteSuccessful() {
        Food temp = new Food("Noodles", 300);
        foodList.add(temp);
        String input = "1";
        String userOutput = "You have deleted " + temp.toString() + " from your list!";
        CommandResult result = foodItemDelete.execute(input, foodList, storage, -1);
        assertEquals(userOutput, result.getFeedbackMessage());
    }

    @Test
    public void execute_wrongFoodItemIndex_returnWrongIndexWarning() {
        Food temp = new Food("Noodles", 300);
        foodList.add(temp);
        String input = "10";
        String userOutput = DietSessionUi.MESSAGE_NO_SUCH_INDEX;
        CommandResult result = foodItemDelete.execute(input, foodList, storage, -1);
        assertEquals(userOutput, result.getFeedbackMessage());
    }

    @Test
    public void execute_indexNotNumber_returnWrongIndexFormatWarning() {
        Food temp = new Food("Noodles", 300);
        foodList.add(temp);
        String input = "haha";
        String userOutput = DietSessionUi.MESSAGE_DELETE_WRONG_FORMAT;
        CommandResult result = foodItemDelete.execute(input, foodList, storage, -1);
        assertEquals(userOutput, result.getFeedbackMessage());
    }
}
