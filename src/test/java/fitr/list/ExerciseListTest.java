package fitr.list;

import org.junit.jupiter.api.Test;
import fitr.Calorie;
import fitr.Exercise;

import java.util.ArrayList;

import static fitr.common.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseListTest {
    @Test
    public void addExerciseToEmptyList_validExercise_success() {
        ExerciseList exerciseList = new ExerciseList();
        exerciseList.addExercise(new Exercise("Push ups", new Calorie(100), getCurrentDate()));
        assertEquals(1, exerciseList.getSize());
    }

    @Test
    public void addExerciseToNonEmptyList_validExercise_success() {
        ExerciseList exerciseList = new ExerciseList(getTestExerciseList());
        exerciseList.addExercise(new Exercise("Push ups", new Calorie(100), getCurrentDate()));
        assertEquals(4, exerciseList.getSize());
    }

    private ArrayList<Exercise> getTestExerciseList() {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Crunches", new Calorie(100), getCurrentDate()));
        exerciseList.add(new Exercise("Squats", new Calorie(100), getCurrentDate()));
        exerciseList.add(new Exercise("Run", new Calorie(300), getCurrentDate()));
        return exerciseList;
    }
}
