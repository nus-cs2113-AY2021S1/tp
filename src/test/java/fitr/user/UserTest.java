package fitr.user;

import fitr.Calorie;
import fitr.Exercise;
import fitr.Food;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    //BMI test
    public void testBmiCalculation_positiveInputs_correctResult() {
        User user = new User();
        user.setHeight(2.00);
        user.setWeight(4.00);
        assertEquals(1.00, user.getBmi());
    }

    @Test
    //user configuration test
    public void configureUserInformation_validInput_userConfigurationSuccess() {
        User user = new User();
        user.loadUserData("Bob", 20, 2.00, 8.00, "Male");
        assertEquals("Bob", user.getName());
        assertEquals(20, user.getAge());
        assertEquals(2.00, user.getHeight());
        assertEquals(8.00, user.getWeight());
        assertEquals("Male", user.getGender());
    }

    @Test
    //Calorie test
    public void testCalorieCalculation_positiveInputs_correctResult() {
        FoodList foodList = new FoodList();
        ExerciseList exerciseList = new ExerciseList();
        foodList.addFood(new Food("Chicken", new Calorie(200), 1));
        foodList.addFood(new Food("Duck", new Calorie(200), 1));
        exerciseList.addExercise(new Exercise("Squats", new Calorie(100), 1));
        exerciseList.addExercise(new Exercise("Running", new Calorie(100), 1));
        User user = new User();
        assertEquals(200, user.calculateCalorieBurnt(exerciseList).get());
        assertEquals(400, user.calculateCalorieConsumed(foodList).get());
        assertEquals(200, user.calculateCalorie(foodList, exerciseList).get());
    }
}
