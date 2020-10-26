package fitr.storage;

import fitr.Calorie;
import fitr.Food;
import fitr.exception.InvalidFileFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FoodStorageTest {
    @Test
    public void loadFoodList_validFoodData_success() throws Exception {
        FoodStorage foodStorage = new FoodStorage("src/test/data/StorageTest/ValidFoodData.txt");
        ArrayList<Food> expectedFoodList = getValidFoodList();
        ArrayList<Food> actualFoodList = foodStorage.loadFoodList();

        assertEquals(expectedFoodList.get(0).getFoodName(), actualFoodList.get(0).getFoodName());
        assertEquals(expectedFoodList.get(0).getCalories(), actualFoodList.get(0).getCalories());
        assertEquals(expectedFoodList.get(0).getAmountOfFood(), actualFoodList.get(0).getAmountOfFood());
        assertEquals(expectedFoodList.get(0).getDate(), actualFoodList.get(0).getDate());

        assertEquals(expectedFoodList.get(1).getFoodName(), actualFoodList.get(1).getFoodName());
        assertEquals(expectedFoodList.get(1).getCalories(), actualFoodList.get(1).getCalories());
        assertEquals(expectedFoodList.get(1).getAmountOfFood(), actualFoodList.get(1).getAmountOfFood());
        assertEquals(expectedFoodList.get(1).getDate(), actualFoodList.get(1).getDate());
    }

    @Test
    public void loadFoodList_invalidFoodData_exceptionThrown() throws Exception {
        FoodStorage foodStorage = new FoodStorage("src/test/data/StorageTest/InvalidFoodData.txt");
        assertThrows(InvalidFileFormatException.class, foodStorage::loadFoodList);
    }

    private ArrayList<Food> getValidFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("Test Food 1", new Calorie(100), 1, "23/10/2020"));
        foodList.add(new Food("Test Food 2", new Calorie(50), 5, "22/10/2020"));
        return foodList;
    }
}
