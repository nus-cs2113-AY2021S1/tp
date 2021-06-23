package logic.commands.workout.workoutsession;

import logic.commands.Command;
import exceptions.workout.workoutsession.AddFormatException;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import models.Exercise;
import storage.workout.WorkoutSessionStorage;
import ui.workout.workoutsession.WorkoutSessionUi;
import logic.parser.WorkoutSessionParser;
import models.ExerciseList;

import java.io.IOException;

//@@author yujinyang1998
/**
 * A representation of the command for adding an exercise.
 */
public class WorkoutSessionAdd extends Command {

    /**
     * Adds an exercise to the exercise list.
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
        assert (inputs != null && exerciseList != null && filePath != null && workoutSessionStorage != null
                && hasEndedWorkoutSessions != null) : "File Corrupted";
        String result = "";
        try {
            exerciseList.exerciseList.add(WorkoutSessionParser.addParser(inputs));
            workoutSessionStorage.writeToStorage(filePath, exerciseList);
            Exercise addedExercise = exerciseList.exerciseList.get(exerciseList.exerciseList.size() - 1);
            result = WorkoutSessionUi.addExerciseSuccess(addedExercise);
        } catch (NumberFormatException e) {
            return new CommandResult(WorkoutSessionUi.ADD_FORMAT_ERROR);
        } catch (IOException e) {
            return new CommandResult(WorkoutSessionUi.PRINT_ERROR);
        } catch (AddFormatException e) {
            return new CommandResult(WorkoutSessionUi.ADD_FORMAT_NEGATIVE_ERROR);
        }
        return new CommandResult(result, ExecutionResult.OK);
    }
}
