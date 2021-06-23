package logic.commands.workout.workoutsession;

import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import models.ExerciseList;
import storage.workout.WorkoutSessionStorage;
import ui.workout.workoutsession.WorkoutSessionUi;

//@@author yujinyang1998

/**
 * A representation of the command for displaying help message for Workout Session.
 */
public class WorkoutSessionHelp extends Command {

    /**
     * Gets ready a help message for Workout Session.
     *
     * @param inputs Array of user's input.
     * @param exerciseList List of exercise.
     * @param filePath Path to data file.
     * @param workoutSessionStorage Workout Session Storage to load and save data.
     * @param hasEndedWorkoutSessions Array of booleans indicating if user has ended workout sessions.
     * @return Status OK and information to be printed.
     */
    @Override
    public CommandResult execute(String[] inputs, ExerciseList exerciseList,
                                 String filePath, WorkoutSessionStorage workoutSessionStorage,
                                 boolean[] hasEndedWorkoutSessions) {
        assert (inputs != null && exerciseList != null && filePath != null
                && workoutSessionStorage != null && hasEndedWorkoutSessions != null) : "File Corrupted";
        String result = WorkoutSessionUi.printHelp();
        return new CommandResult(result, ExecutionResult.OK);
    }
}
