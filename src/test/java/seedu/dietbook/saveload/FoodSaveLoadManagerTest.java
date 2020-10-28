package seedu.dietbook.saveload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dietbook.food.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FoodSaveLoadManagerTest {
    private FoodSaveLoadManager testManager;
    private List<Food> inputFoodList;
    private Food food1;
    private Food food2;
    private Food food3;
    private Food food4;

    private List<Food> testFoodList;

    @BeforeEach
    private void setUp() throws FileNotFoundException {
        testManager = new FoodSaveLoadManager();
        inputFoodList = new ArrayList<>();
        food1 = new Food("Apple", 20000,20,5,1);
        food2 = new Food("Peach", 3000,50,2,3);
        food3 = new Food("Bacon", 1000,20,10,99);
        food4 = new Food("Silicon", 500,100,50,10);

        inputFoodList.add(food1);
        inputFoodList.add(food2);
        inputFoodList.add(food3);
        inputFoodList.add(food4);

        testManager.save("Victor's Food List", inputFoodList);
        testManager.load("Victor's Food List");
    }

    @Test
    private void getFoodList_WithoutLoading() throws Exception {
        testManager.clearLoader();
        assertThrows(IllegalAccessException.class, () -> testManager.getFoodList());
    }

    @Test
    private void getFoodList_FileDoesNotExist() throws Exception {
        assertThrows(FileNotFoundException.class, () -> testManager.load("Over the Moon!"));
    }

    @Test
    private void getFoodListTest() throws Exception {
        testFoodList = testManager.getFoodList();
        assertEquals(4, testFoodList.size());
        assertEquals("Apple", testFoodList.get(0).getName());
        assertEquals(20000, testFoodList.get(0).getCalorie());
        assertEquals("Silicon", testFoodList.get(3).getName());
        assertEquals(500, testFoodList.get(3).getCalorie());
    }
}
