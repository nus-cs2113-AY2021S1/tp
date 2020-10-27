package seedu.dietbook.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.dietbook.food.Food;


class FoodListTest {

    private FoodList list;

    @BeforeEach
    protected void setUp() {
        this.list = new FoodList();

        Food food = new Food("Kobe Beef", 480,50,40,30);
        list.addFood(3, food);
        list.addFood(2, "Sashimi", 100, 0, 30, 10);

    }

    /**
     * getPortionedList() should return list of food with scaled up nutritional values.
     * getFoods() should return a list of food (Not food entries).
     * Essentially 2 tests in 1.
     */
    @Test
    void foodPortionScaling_standardList_scaledFoodList() {
        FoodList testList = new FoodList();

        Food food = new Food("Kobe Beef", 480 * 3, 50 * 3, 40 * 3, 30 * 3);
        testList.addFood(1, food);
        testList.addFood(1, "Sashimi", 200, 0, 60, 20);

        assertEquals(testList.getFoods().toString(), list.getPortionedFoods().toString());
    }

    @Test
    void deleteItemTest() {
        Food food = new Food("Kobe Beef", 480,50,40,30);
        FoodEntry entry = new FoodEntry(3, food);
        assertEquals(list.delete(1), entry.toString());
    }

}
