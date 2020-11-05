package fitr.list;

import fitr.exercise.StandardExercise;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardExerciseListTest {
    ArrayList<Double> duration = new ArrayList<>();
    ArrayList<Integer> sets = new ArrayList<>();

    @Test
    public void addExerciseToEmptyList_validExercise_success() {
        StandardExerciseList exerciseList = new StandardExerciseList();
        exerciseList.addExercise(new StandardExercise("Push ups", 2.4, duration, sets));
        assertEquals(1, exerciseList.getSize());
    }

    @Test
    public void addExerciseToNonEmptyList_validExercise_success() {
        StandardExerciseList exerciseList = new StandardExerciseList(getTestExerciseList());
        exerciseList.addExercise(new StandardExercise("Push ups", 2.4, duration, sets));
        assertEquals(4, exerciseList.getSize());
    }

    private ArrayList<StandardExercise> getTestExerciseList() {
        ArrayList<StandardExercise> exerciseList = new ArrayList<>();
        exerciseList.add(new StandardExercise("Crunches", 1, duration, sets));
        exerciseList.add(new StandardExercise("Squats", 2, duration, sets));
        exerciseList.add(new StandardExercise("Run", 3, duration, sets));
        return exerciseList;
    }
}
