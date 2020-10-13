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

    @Test
    public void getCaloricBurnRate_validExerciseWithoutSpecifiedDuration_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie);
        assertEquals(500,tempExercise.getCaloricBurnRate());
    }

    @Test
    public void getCaloricBurnRate_validExerciseWithSpecifiedDuration_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie,2);
        assertEquals(250,tempExercise.getCaloricBurnRate());
    }

    @Test
    public void getDurationOfExercise_validExerciseWithSpecifiedDuration_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie,2);
        assertEquals(2,tempExercise.getDuration());
    }

    @Test
    public void getDurationOfExercise_validExerciseWithoutSpecifiedDuration_success() {
        Exercise tempExercise = new Exercise("exercise",tempCalorie);
        assertEquals(1,tempExercise.getDuration());
    }


}
