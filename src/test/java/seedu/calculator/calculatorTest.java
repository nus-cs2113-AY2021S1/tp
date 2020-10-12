package seedu.calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class calculatorTest {

    @Test
    void calculateCalorie() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        calculator calculator = new calculator(foodList);
        assertEquals(666 + 150 + 290, calculator.calculateCalorie());
    }

    @Test
    void calculateCarbs() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        calculator calculator = new calculator(foodList);
        assertEquals(55 + 16, calculator.calculateCarbs());
    }

    @Test
    void calculateProtein() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        calculator calculator = new calculator(foodList);
        assertEquals(30 + 16, calculator.calculateProtein());
    }

    @Test
    void calculateFats() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        calculator calculator = new calculator(foodList);
        assertEquals(0, calculator.calculateFats());
    }
}