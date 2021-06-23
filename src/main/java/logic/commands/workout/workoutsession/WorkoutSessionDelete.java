package logic.commands.workout.workoutsession;

import logic.commands.Command;
import exceptions.SchwarzeneggerException;
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
 * A representation of the command for deleting an exercise.
 */
public class WorkoutSessionDelete extends Command {

    /**
     * Deletes an exercise from the exercise list.
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
        String result = "";
        try {
            int removeIndex = WorkoutSessionParser.deleteParser(inputs);
            Exercise deletedExercise = exerciseList.exerciseList.get(removeIndex - 1);
            exerciseList.exerciseList.remove(removeIndex - 1);
            workoutSessionStorage.writeToStorage(filePath, exerciseList);
            result = WorkoutSessionUi.deleteExerciseSuccess(deletedExercise);
        } catch (IOException e) {
            return new CommandResult(WorkoutSessionUi.PRINT_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new CommandResult(WorkoutSessionUi.DELETE_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(WorkoutSessionUi.DELETE_INDEX_ERROR);
        } catch (SchwarzeneggerException e) {
            return new CommandResult(WorkoutSessionUi.DELETE_FORMAT_ERROR);
        }
        return new CommandResult(result, ExecutionResult.OK);
    }
}
