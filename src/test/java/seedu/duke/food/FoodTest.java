package seedu.duke.food;

import seedu.duke.food.Food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class FoodTest {
    private Food testFood;

    @BeforeEach
    public void setUp(){
        testFood = new Food("Kobe Beef", 480,50,40,30);
    }

    @Test
    public void footTest(){
        assertEquals(testFood.getCalorie(), 480);
        assertEquals(testFood.getCarbohydrate(), 50);
        assertEquals(testFood.getProtein(), 40);
        assertEquals(testFood.getFats(),30);
    }
}