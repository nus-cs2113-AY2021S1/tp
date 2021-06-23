package logic.commands.workout.workoutsession;

import logic.commands.Command;
import exceptions.InvalidCommandWordException;
import logic.commands.CommandResult;
import storage.workout.WorkoutSessionStorage;
import models.ExerciseList;

//@@author yujinyang1998
/**
 * A representation of invalid command in Workout Session.
 */
public class WorkoutSessionWrong extends Command {
    /**
     * Notifies user invalid input command.
     *
     * @param inputs Array of user's input.
     * @param exerciseList List of exercise.
     * @param filePath Path to data file.
     * @param workoutSessionStorage Workout Session Storage to load and save data.
     * @param hasEndedWorkoutSessions Array of booleans indicating if user has ended workout sessions.
     * @throws InvalidCommandWordException If the user input does not match the ones stated in help.
     */
    @Override
    public CommandResult execute(String[] inputs, ExerciseList exerciseList,
                                 String filePath, WorkoutSessionStorage workoutSessionStorage,
                                 boolean[] hasEndedWorkoutSessions) throws InvalidCommandWordException {
        assert (exerciseList != null && filePath != null && workoutSessionStorage != null
                && hasEndedWorkoutSessions != null) : "File Corrupted";
        throw new InvalidCommandWordException();
    }
}
