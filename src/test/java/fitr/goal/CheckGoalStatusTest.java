package fitr.goal;

import fitr.calorie.Calorie;
import fitr.exercise.Exercise;
import fitr.food.Food;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;
import org.junit.jupiter.api.Test;
import static fitr.goal.CheckGoalStatus.checkGoalStatus;
import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckGoalStatusTest {
    ExerciseList exerciseList = new ExerciseList();
    FoodList foodList = new FoodList();
    User user = new User("John Doe", 22, 1.70, 80, "Male", 0);

    @Test
    public void checkExerciseGoalStatusTest_correctResult() {
        exerciseList.addExercise(new Exercise("run", new Calorie(400), getCurrentDate()));
        Goal goal = new Goal(getCurrentDate(), "exercise", "Burn more than 500 cal.");
        String status = checkGoalStatus("N", goal, foodList, exerciseList, user);
        assertEquals("80.0", status);
        exerciseList.addExercise(new Exercise("run", new Calorie(200), getCurrentDate()));
        status = checkGoalStatus("N", goal, foodList, exerciseList, user);
        assertEquals("Y", status);
    }

    @Test
    public void checkFoodGoalStatusTest_correctResult() {
        foodList.addFood(new Food("apple", new Calorie(100), getCurrentDate()));
        Goal goal = new Goal(getCurrentDate(), "food", "Eat less than 200 cal.");
        String status = checkGoalStatus("N", goal, foodList, exerciseList, user);
        assertEquals("Y", status);
        foodList.addFood(new Food("ice cream", new Calorie(200), getCurrentDate()));
        status = checkGoalStatus("N", goal, foodList, exerciseList, user);
        assertEquals("N", status);
    }
}
