package logic.commands.diet.dietsession;

import logic.commands.CommandResult;
import models.Food;
import org.junit.jupiter.api.Test;
import storage.diet.DietStorage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author zsk612
public class FoodItemClearTest {

    ArrayList<Food> foodList = new ArrayList<>();
    DietStorage storage = new DietStorage();
    FoodItemClear foodItemClear = new FoodItemClear();

    @Test
    public void execute_clearWithYes_clearSuccessful() {
        Food temp = new Food("Noodles", 300);
        foodList.add(temp);
        String input = "";
        String userOutput = "Alright, your food items have been cleared.";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("YES".getBytes());
        System.setIn(in);
        CommandResult result = foodItemClear.execute(input, foodList, storage, -1);
        assertEquals(userOutput, result.getFeedbackMessage());
    }

    @Test
    public void execute_clearWithoutYes_deleteAborted() {
        Food temp = new Food("Noodles", 300);
        foodList.add(temp);
        String input = "";
        String userOutput = "You have aborted clear operation.";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("NO".getBytes());
        System.setIn(in);
        CommandResult result = foodItemClear.execute(input, foodList, storage, 1);
        assertEquals(userOutput, result.getFeedbackMessage());
    }
}
