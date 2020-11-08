package seedu.dietbook.saveload;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.dietbook.food.Food;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FoodPortionDateSaveLoadManagerTest {
    FoodPortionDateSaveLoadManager test;

    @BeforeEach
    private void setUp(){
        test = new FoodPortionDateSaveLoadManager();
        LocalDateTime testDateTime1 = LocalDateTime.of(2020, 10, 12, 16, 30);
        Food testFood1 = new Food("Banana", 100, 50, 30, 20);
        int testPortionSize1 = 10;

        LocalDateTime testDateTime2 = LocalDateTime.of(2036, 9, 16, 10, 40);
        Food testFood2 = new Food("Solenoid", 200, 20, 40, 30);
        int testPortionSize2 = 5;

        test.readySaver(10);
        test.setFood(testFood1, 1);
        test.setDateTime(testDateTime1, 1);
        test.setPortionSize(testPortionSize1, 1);
        test.setFood(testFood2, 10);
        test.setDateTime(testDateTime2, 10);
        test.setPortionSize(testPortionSize2, 10);
        test.save("testFoodFile");
    }

    @Test
    private void data_loadentry1_returnsCorrectSavedData() throws FileNotFoundException, IllegalAccessException {
        test.load("testFoodFile");
        assertEquals("Banana", test.getFood(1).getName());
        assertEquals(100, test.getFood(1).getCalorie());
        assertEquals(50, test.getFood(1).getProtein());
        assertEquals(30, test.getFood(1).getProtein());
        assertEquals(20, test.getFood(1).getFat());
        assertEquals(10, test.getPortionSize(1));
        assertEquals(2020, test.getYear(1));
        assertEquals(10, test.getMonth(1));
        assertEquals(12, test.getDay(1));
        assertEquals(16, test.getHour(1));
        assertEquals(30, test.getMinute(1));
    }

    @Test
    private void data_loadentry2_returnsCorrectSavedData() throws FileNotFoundException, IllegalAccessException {
        test.load("testFoodFile");
        assertEquals("Solenoid", test.getFood(10).getName());
        assertEquals(200, test.getFood(10).getCalorie());
        assertEquals(20, test.getFood(10).getProtein());
        assertEquals(40, test.getFood(10).getProtein());
        assertEquals(30, test.getFood(10).getFat());
        assertEquals(5, test.getPortionSize(10));
        assertEquals(2036, test.getYear(10));
        assertEquals(9, test.getMonth(10));
        assertEquals(16, test.getDay(10));
        assertEquals(10, test.getHour(10));
        assertEquals(40, test.getMinute(10));
    }
}