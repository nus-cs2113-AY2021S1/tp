package fitr;

import fitr.Calorie;
import fitr.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodTest {
    Calorie tempCalorie = new Calorie(500);

    @Test
    public void getCaloriesConsumed_validFoodAndCalorie_success() {
        Food tempExercise = new Food("food",tempCalorie);
        assertEquals(500,tempCalorie.get());
    }

    @Test
    public void getNameOfFood_validFoodAndCalorie_success() {
        Food tempExercise = new Food("exercise",tempCalorie);
        assertEquals("exercise",tempExercise.getFoodName());
    }

    @Test
    public void getCaloricConsumptionRate_validFoodWithoutSpecifiedAmount_success() {
        Food tempExercise = new Food("exercise",tempCalorie);
        assertEquals(500,tempExercise.getCaloricRate());
    }

    @Test
    public void getCaloricConsumptionRate_validFoodWithSpecifiedAmount_success() {
        Food tempExercise = new Food("exercise",tempCalorie,2);
        assertEquals(250,tempExercise.getCaloricRate());
    }

    @Test
    public void getAmountOfFoodSpecified_validFoodWithSpecifiedAmount_success() {
        Food tempExercise = new Food("exercise",tempCalorie,2);
        assertEquals(2,tempExercise.getAmountOfFood());
    }

    @Test
    public void getAmountOfFoodUnspecified_validFoodWithSpecifiedAmount_success() {
        Food tempExercise = new Food("exercise",tempCalorie);
        assertEquals(1,tempExercise.getAmountOfFood());
    }
}
