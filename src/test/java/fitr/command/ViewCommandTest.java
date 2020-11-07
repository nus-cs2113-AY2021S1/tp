package fitr.command;

import fitr.calorie.Calorie;
import fitr.exercise.Exercise;
import fitr.food.Food;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static fitr.command.ViewCommand.viewExerciseByDate;
import static fitr.command.ViewCommand.viewFoodByDate;

public class ViewCommandTest {
    @Test
    public void viewCommandTest() {
        Command view = new ViewCommand("food");
        assertFalse(view.isExit());
    }

    @Test
    public void viewExerciseByDateTest() {
        ExerciseList exerciseList = new ExerciseList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        exerciseList.addExercise(new Exercise("run", new Calorie(360), LocalDate.parse("26/10/2020", formatter)));
        exerciseList.addExercise(new Exercise("push ups", new Calorie(200), LocalDate.parse("27/10/2020", formatter)));
        exerciseList = viewExerciseByDate(exerciseList, "27/10/2020", false);
        assertEquals("push ups", exerciseList.getExercise(0).getNameOfExercise());
    }

    @Test
    public void viewFoodByDateTest() {
        FoodList foodList = new FoodList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        foodList.addFood(new Food("apple", new Calorie(360), LocalDate.parse("26/10/2020", formatter)));
        foodList.addFood(new Food("orange", new Calorie(200), LocalDate.parse("27/10/2020", formatter)));
        foodList = viewFoodByDate(foodList, "27/10/2020", false);
        assertEquals("orange", foodList.getFood(0).getFoodName());
    }
}
