package storage.workout;

import exceptions.workout.workoutmanager.SchwIoException;
import models.Exercise;
import models.ExerciseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author yujinyang1998
class WorkoutSessionStorageTest {

    @Test
    void readFileContents() throws SchwIoException, IOException {
        ExerciseList actualExerciseList = new ExerciseList();
        ExerciseList expectedExerciseList = new ExerciseList();

        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();

        expectedExerciseList.exerciseList.add(new Exercise("ben", 547, 567));

        workoutSessionStorage.writeToStorage(filePath, expectedExerciseList);
        workoutSessionStorage.readFileContents(filePath, actualExerciseList);
        assertEquals(expectedExerciseList.exerciseList.toString(), actualExerciseList.exerciseList.toString());
    }

    @Test
    void writeToStorage_inputNull_assert() {
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();

        assertThrows(AssertionError.class, () -> workoutSessionStorage.readFileContents(null, null));
    }

    @Test
    void readFileContents_inputNull_assert() {
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();

        assertThrows(AssertionError.class, () -> workoutSessionStorage.writeToStorage(null, null));
    }
}