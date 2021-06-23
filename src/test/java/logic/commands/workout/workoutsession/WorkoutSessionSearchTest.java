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
class WorkoutSessionSearchTest {

    @Test
    void execute_validInput_success() throws SchwIoException {
        String[] searchInputs = {"search", "ben"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionSearch workoutSessionSearch = new WorkoutSessionSearch();
        CommandResult actual = workoutSessionSearch.execute(searchInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("No matching result has been found.");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_emptyInput_success() throws SchwIoException {
        String[] searchInputs = {"search"};
        ExerciseList exerciseList = new ExerciseList();
        WorkoutSessionStorage workoutSessionStorage = new WorkoutSessionStorage();
        boolean[] hasEndedWorkoutSessions = {false};
        WorkOutManagerStorage workOutManagerStorage = new WorkOutManagerStorage();
        String filePath = workOutManagerStorage.createfile();

        WorkoutSessionSearch workoutSessionSearch = new WorkoutSessionSearch();
        CommandResult actual = workoutSessionSearch.execute(searchInputs, exerciseList, filePath, workoutSessionStorage,
                hasEndedWorkoutSessions);
        CommandResult expected = new CommandResult("Wrong format, please enter in the format:\n"
                + "\t search [NAME_OF_MOVE]");
        assertTrue(EqualsBuilder.reflectionEquals(expected, actual));
    }

    @Test
    void execute_nullParams_assert() {
        WorkoutSessionSearch workoutSessionSearch = new WorkoutSessionSearch();
        assertThrows(AssertionError.class, () -> {
            workoutSessionSearch.execute(null, null, null, null, null);
        });
    }
}