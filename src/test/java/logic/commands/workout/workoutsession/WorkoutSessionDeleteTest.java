package logic.commands.workout.workoutsession;

import exceptions.workout.workoutmanager.SchwIoException;
import logic.commands.CommandResult;
import models.ExerciseList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import storage.workout.WorkOutManagerStorage;
import storage.workout.WorkoutSessionStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author yujinyang1998
class WorkoutSessionDeleteTest {

    @Test
    void execute_validInput_success() throws SchwIoException {
        String[] addInputs = {"add", "bench", "/n", "3244", "/w", "4324"};
        String[] deleteInputs = {"delete", "1"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        workoutSessionAdd.execute(addInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        WorkoutSessionDelete workoutSessionDelete = new WorkoutSessionDelete();
        CommandResult actual = workoutSessionDelete.execute(deleteInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("You have deleted bench from your list!\n"
                + "\t [Repetitions: 3244 || Weight: 4324.0]");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_invalidInput_formatError() throws SchwIoException {
        String[] addInputs = {"add", "bench", "/n", "3244", "/w", "4324"};
        String[] deleteInputs = {"delete", "fsdg"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        workoutSessionAdd.execute(addInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        WorkoutSessionDelete workoutSessionDelete = new WorkoutSessionDelete();
        CommandResult actual = workoutSessionDelete.execute(deleteInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Wrong format, please enter in the format:\n"
                + "\t delete [INDEX]");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_invalidInput_indexError() throws SchwIoException {
        String[] addInputs = {"add", "bench", "/n", "3244", "/w", "4324"};
        String[] deleteInputs = {"delete", "10"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        workoutSessionAdd.execute(addInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        WorkoutSessionDelete workoutSessionDelete = new WorkoutSessionDelete();
        CommandResult actual = workoutSessionDelete.execute(deleteInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Index does not exist. Please refer to the list.");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_nullParams_assert() {
        WorkoutSessionDelete workoutSessionDelete = new WorkoutSessionDelete();
        assertThrows(AssertionError.class, () -> {
            workoutSessionDelete.execute(null, null, null, null, null);
        });
    }

}