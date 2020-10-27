package fitr;

import org.junit.jupiter.api.Test;

import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Calorie tempCalorie = new Calorie(500);

    @Test
    public void getCaloriesBurnt_validExerciseAndCalorie_success() {
        Exercise tempExercise = new Exercise("exercise", tempCalorie, getCurrentDate());
        assertEquals(500, tempCalorie.get());
    }

    @Test
    public void getNameOfExercise_validExerciseAndCalorie_success() {
        Exercise tempExercise = new Exercise("exercise", tempCalorie, getCurrentDate());
        assertEquals("exercise", tempExercise.getNameOfExercise());
    }


}
