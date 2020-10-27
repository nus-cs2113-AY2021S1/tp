package seedu.dietbook.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import seedu.dietbook.food.Food;


class FoodListTest {

    private FoodList list;
    private Food food;

    @BeforeEach
    protected void setUp() {
        this.list = new FoodList();

        Food food = new Food("Kobe Beef", 480,50,40,30);
        this.food = food;

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
        assertEquals(entry.toString(), list.delete(1));
    }

    @Test
    void dateComparisonTest() {
        DatedFoodEntry entry = new DatedFoodEntry(2, food);
        DatedFoodEntry pastEntry = new DatedFoodEntry(2, food, LocalDateTime.MIN);

        assertTrue(entry.compareTo(pastEntry) > 0);

    }

    @Test
    void dateFilterAfterTest() {
        LocalDateTime timeNow = LocalDateTime.now();
        
        assertTrue(list.getFoodsAfterDateTime(timeNow).size() == 0);
        assertEquals(list.getFoodsAfterDateTime(LocalDateTime.MIN).toString(), 
                list.getFoods().toString());

        // add new entries:
        if (! LocalDateTime.now().isAfter(timeNow)) { // Execution is too fast that now() = timeNow.
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Unexpected Interruption");
            }
        }
        list.addFood(1, food);
        assertEquals(food.toString(), list.getFoodsAfterDateTime(timeNow).get(0).toString());

    }

    @Test
    void dateFilterRangeTest() {
        LocalDateTime timeNow = LocalDateTime.now();

        if (! LocalDateTime.now().isAfter(timeNow)) { // Execution is too fast that now() = timeNow.
            try {
                TimeUnit.SECONDS.sleep(1);
                timeNow = LocalDateTime.now();
            } catch (InterruptedException e) {
                System.out.println("Unexpected Interruption");
            }
        }

        assertTrue(list.getFoodsInDateTimeRange(timeNow, LocalDateTime.MAX).size() == 0);

        assertEquals(list.getPortionedFoodsInDateTimeRange(LocalDateTime.MIN, timeNow).toString(),
                 list.getPortionedFoods().toString());
    }

}
