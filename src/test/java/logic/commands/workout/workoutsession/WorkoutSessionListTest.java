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
class WorkoutSessionListTest {

    @Test
    void execute_validInput_emptyList() throws SchwIoException {
        String[] input = {"list"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionList workoutSessionList = new WorkoutSessionList();

        CommandResult actual = workoutSessionList.execute(input, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("List is empty. Please enter something.");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_nullParams_assert() {
        WorkoutSessionList workoutSessionList = new WorkoutSessionList();
        assertThrows(AssertionError.class, () -> {
            workoutSessionList.execute(null, null, null, null, null);
        });
    }
}