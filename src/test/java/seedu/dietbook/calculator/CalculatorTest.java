package seedu.dietbook.calculator;

import org.junit.jupiter.api.Test;
import seedu.dietbook.food.Food;
import seedu.dietbook.person.FitnessLevel;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {



    @Test
    void calculateCalorie_foodListOfThreeItems_sumOfCalorie() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        Calculator calculator = new Calculator(foodList);
        assertEquals(666 + 150 + 290, calculator.calculateCalorie());
    }

    @Test
    void calculateCarb_foodListOfThreeItems_sumOfCarb() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        Calculator calculator = new Calculator(foodList);
        assertEquals(55 + 16, calculator.calculateCarb());
    }

    @Test
    void calculateProtein_foodListOfThreeItems_sumOfProtein() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        Calculator calculator = new Calculator(foodList);
        assertEquals(30 + 16, calculator.calculateProtein());
    }

    @Test
    void calculateFat_foodListOfThreeItems_sumOfFat() {
        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(new Food("chicken rice", 666, 55, 30, 0));
        foodList.add(new Food("pancake", 150, 16, 0, 0));
        foodList.add(new Food("bao", 290, 0, 16, 0));
        Calculator calculator = new Calculator(foodList);
        assertEquals(0, calculator.calculateFat());
    }

    @Test
    void calculateRecomendedCalorieIntake_aPerson_recomendationOfCalorieIntake() {
        Person harry = new Person("Harry", Gender.MALE, 19, 182, 66, 69, 75, FitnessLevel.LOW);
        Person erica = new Person("Erica", Gender.FEMALE, 20, 168, 52, 50, 45, FitnessLevel.MEDIUM);
        Calculator calculator = new Calculator(new ArrayList<Food>());
        assertEquals(2728, calculator.calculateRecomendation(harry));
        assertEquals(1752, calculator.calculateRecomendation(erica));
    }
}
