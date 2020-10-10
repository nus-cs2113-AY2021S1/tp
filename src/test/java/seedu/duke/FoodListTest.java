package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodListTest {
    @Test
    public void addFoodToEmptyList_validFood_success() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Rice", new Calorie(100), 1));
        assertEquals(1, foodList.getSize());
    }

    @Test
    public void addFoodToNonEmptyList_validFood_success() {
        FoodList foodList = new FoodList(getTestFoodList());
        foodList.addFood(new Food("Rice", new Calorie(100), 1));
        assertEquals(4, foodList.getSize());
    }

    private ArrayList<Food> getTestFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Meat", new Calorie(100), 1));
        foodList.add(new Food("Fish", new Calorie(100), 2));
        foodList.add(new Food("Noodles", new Calorie(200), 1));
        return foodList;
    }
}
