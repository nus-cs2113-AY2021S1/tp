package fitr;

import fitr.Calorie;
import fitr.Exercise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Calorie tempCalorie = new Calorie(500);

    @Test
    public void getCaloriesBurnt_validExerciseAndCalorie_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie);
        assertEquals(500,tempCalorie.get());
    }

    @Test
    public void getNameOfExercise_validExerciseAndCalorie_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie);
        assertEquals("exercise",tempExercise.getNameOfExercise());
    }


}
