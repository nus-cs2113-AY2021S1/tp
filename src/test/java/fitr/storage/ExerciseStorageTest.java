package fitr.storage;

import fitr.Calorie;
import fitr.Exercise;
import fitr.exception.InvalidFileFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static fitr.DateManager.getCurrentDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExerciseStorageTest {
    @Test
    public void loadExerciseList_validExerciseData_success() throws Exception {
        ExerciseStorage exerciseStorage = new ExerciseStorage("src/test/data/StorageTest/ValidExerciseData.txt");
        ArrayList<Exercise> expectedExerciseList = getValidExerciseList();
        ArrayList<Exercise> actualExerciseList = exerciseStorage.loadExerciseList();

        assertEquals(expectedExerciseList.get(0).getNameOfExercise(), actualExerciseList.get(0).getNameOfExercise());
        assertEquals(expectedExerciseList.get(0).getCalories(), actualExerciseList.get(0).getCalories());

        assertEquals(expectedExerciseList.get(1).getNameOfExercise(), actualExerciseList.get(1).getNameOfExercise());
        assertEquals(expectedExerciseList.get(1).getCalories(), actualExerciseList.get(1).getCalories());
    }

    @Test
    public void loadExerciseList_invalidExerciseData_exceptionThrown() throws Exception {
        ExerciseStorage exerciseStorage = new ExerciseStorage("src/test/data/StorageTest/InvalidExerciseData.txt");
        assertThrows(InvalidFileFormatException.class, exerciseStorage::loadExerciseList);
    }

    private ArrayList<Exercise> getValidExerciseList() {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Test Exercise 1", new Calorie(500), getCurrentDate()));
        exerciseList.add(new Exercise("Test Exercise 2", new Calorie(100), getCurrentDate()));
        return exerciseList;
    }
}
