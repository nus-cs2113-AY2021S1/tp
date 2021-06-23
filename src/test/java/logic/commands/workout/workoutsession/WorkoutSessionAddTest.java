package logic.commands.workout.workoutsession;

import exceptions.workout.workoutmanager.SchwIoException;
import logic.commands.CommandResult;
import models.ExerciseList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import storage.workout.WorkoutSessionStorage;
import storage.workout.WorkOutManagerStorage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author yujinyang1998
class WorkoutSessionAddTest {

    @Test
    void execute_validInput_success() throws SchwIoException {
        String[] inputs = {"add", "bench", "/n", "3244", "/w", "4324"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();

        CommandResult actual = workoutSessionAdd.execute(inputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Yay! You have added bench to your list.\n"
                + "\t [Repetitions: 3244 || Weight: 4324.0]");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_invalidInput_formatError() throws SchwIoException {
        String[] inputs = {"add", "bench", "3244", "/w", "4324"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();

        CommandResult actual = workoutSessionAdd.execute(inputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Wrong format, please enter in the format:\n"
                + "\t add [NAME_OF_MOVE] /n [NUMBER_OF_REPETITIONS] /w [WEIGHT]");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_invalidInput_negativeFormatError() throws SchwIoException {
        String[] inputs = {"add", "bench", "/n", "-3244", "/w", "4324"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();

        CommandResult actual = workoutSessionAdd.execute(inputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Wrong format, please enter in the format:\n"
                + "\t add [NAME_OF_MOVE] /n [NUMBER_OF_REPETITIONS] /w [WEIGHT]\n"
                + "\t Please make sure [NUMBER_OF_REPETITIONS] and [WEIGHT] are non negative numbers.");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_nullParams_assert() {
        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        assertThrows(AssertionError.class, () -> {
            workoutSessionAdd.execute(null, null, null, null, null);
        });
    }

    @Test
    void execute_nullParams_assert1() throws SchwIoException {
        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String[] inputs = {"add", "bench", "/n", "-3244", "/w", "4324"};
        String filePath = workOutManagerStorage.createfile();
        assertThrows(AssertionError.class, () -> {
            workoutSessionAdd.execute(inputs, null, null, null, null);
        });
    }

    @Test
    void execute_nullParams_assert3() throws SchwIoException {
        WorkoutSessionAdd workoutSessionAdd = new WorkoutSessionAdd();
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String[] inputs = {"add", "bench", "/n", "-3244", "/w", "4324"};
        ExerciseList exerciseList = new ExerciseList();
        String filePath = workOutManagerStorage.createfile();
        assertThrows(AssertionError.class, () -> {
            workoutSessionAdd.execute(inputs, exerciseList, filePath, null, null);
        });
    }
}
