package seedu.dietbook.calculator;

import org.junit.jupiter.api.Test;
import seedu.dietbook.food.Food;
import seedu.dietbook.list.FoodList;
import seedu.dietbook.person.ActivityLevel;
import seedu.dietbook.person.Gender;
import seedu.dietbook.person.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void calculateCalorie_foodListOfThreeItems_sumOfCalorie() {
        FoodList foodList = new FoodList();
        foodList.addFood(2, new Food("chicken rice", 666, 55, 30, 0));
        foodList.addFood(2, new Food("pancake", 150, 16, 0, 0));
        foodList.addFood(2, new Food("bao", 290, 0, 16, 0));
        CalculatorData data = new CalculatorData();
        data.inputData(foodList);
        Calculator calculator = new Calculator(data);
        assertEquals(2 * (666 + 150 + 290), calculator.calculateCalorie());
    }

    @Test
    void calculateCarb_foodListOfThreeItems_sumOfCarb() {
        FoodList foodList = new FoodList();
        foodList.addFood(3, new Food("chicken rice", 666, 55, 30, 0));
        foodList.addFood(3, new Food("pancake", 150, 16, 0, 0));
        foodList.addFood(3, new Food("bao", 290, 0, 16, 0));
        CalculatorData data = new CalculatorData(foodList);
        Calculator calculator = new Calculator(data);
        assertEquals(3 * (55 + 16), calculator.calculateCarb());
    }

    @Test
    void calculateProtein_foodListOfThreeItems_sumOfProtein() {
        FoodList foodList = new FoodList();
        foodList.addFood(1, new Food("chicken rice", 666, 55, 30, 0));
        foodList.addFood(1, new Food("pancake", 150, 16, 0, 0));
        foodList.addFood(1, new Food("bao", 290, 0, 16, 0));
        CalculatorData data = new CalculatorData(foodList);
        Calculator calculator = new Calculator(data);
        assertEquals(30 + 16, calculator.calculateProtein());
    }

    @Test
    void calculateFat_foodListOfThreeItems_sumOfFat() {
        FoodList foodList = new FoodList();
        foodList.addFood(1, new Food("chicken rice", 666, 55, 30, 0));
        foodList.addFood(1, new Food("pancake", 150, 16, 0, 0));
        foodList.addFood(1, new Food("bao", 290, 0, 16, 0));
        CalculatorData data = new CalculatorData(foodList);
        Calculator calculator = new Calculator(data);
        assertEquals(0, calculator.calculateFat());
    }

    @Test
    void calculateRecomendedCalorieIntake_aPerson_recomendationOfCalorieIntake() {
        Person harry = new Person("Harry", Gender.MALE, 19, 182, 66, 69, 75, ActivityLevel.LOW);
        Person erica = new Person("Erica", Gender.FEMALE, 20, 168, 52, 50, 45, ActivityLevel.MEDIUM);
        Calculator calculator = new Calculator(new CalculatorData());
        assertEquals(2728, calculator.calculateRecomendation(harry));
        assertEquals(1752, calculator.calculateRecomendation(erica));
    }
}
