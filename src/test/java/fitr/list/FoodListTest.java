package fitr.list;

import org.junit.jupiter.api.Test;
import fitr.calorie.Calorie;
import fitr.food.Food;

import java.util.ArrayList;

import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodListTest {
    @Test
    public void addFoodToEmptyList_validFood_success() {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Rice", new Calorie(100), 1, getCurrentDate()));
        assertEquals(1, foodList.getSize());
    }

    @Test
    public void addFoodToNonEmptyList_validFood_success() {
        FoodList foodList = new FoodList(getTestFoodList());
        foodList.addFood(new Food("Rice", new Calorie(100), 1, getCurrentDate()));
        assertEquals(4, foodList.getSize());
    }

    private ArrayList<Food> getTestFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Meat", new Calorie(100), 1, getCurrentDate()));
        foodList.add(new Food("Fish", new Calorie(100), 2, getCurrentDate()));
        foodList.add(new Food("Noodles", new Calorie(200), 1, getCurrentDate()));
        return foodList;
    }
}
