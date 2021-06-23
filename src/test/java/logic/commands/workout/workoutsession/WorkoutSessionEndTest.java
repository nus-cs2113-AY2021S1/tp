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
class WorkoutSessionEndTest {

    @Test
    void execute_validInput_success() throws SchwIoException {
        String[] input = {"end"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionEnd workoutSessionEnd = new WorkoutSessionEnd();

        CommandResult actual = workoutSessionEnd.execute(input, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_nullParams_assert() {
        WorkoutSessionEnd workoutSessionEnd = new WorkoutSessionEnd();
        assertThrows(AssertionError.class, () -> {
            workoutSessionEnd.execute(null, null, null, null, null);
        });
    }
}