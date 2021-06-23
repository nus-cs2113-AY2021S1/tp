package logic.commands;

import exceptions.InvalidCommandWordException;
import exceptions.InvalidDateFormatException;
import exceptions.SchwarzeneggerException;
import exceptions.diet.InvalidSearchDateException;
import exceptions.profile.InvalidCommandFormatException;
import logger.SchwarzeneggerLogger;
import models.ExerciseList;
import models.Food;
import storage.diet.DietStorage;
import storage.profile.ProfileStorage;
import storage.workout.WorkoutSessionStorage;
import ui.CommonUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A base class for command.
 */
public abstract class Command {
    protected static Logger logger = SchwarzeneggerLogger.getInstanceLogger();
    public CommonUi ui;

    /**
     * Constructs Command object.
     */
    public Command() {
        ui = new CommonUi();
    }

    //@@author wgzesg
    /**
     * Executes the command with given arguments.
     *
     * @param args Array of user's input.
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    public CommandResult execute(String args) throws SchwarzeneggerException {
        logger.log(Level.INFO, "Executing " + this);
        return new CommandResult();
    }

    //@@author tienkhoa16

    /**
     * Executes the command with user's input.
     *
     * @param commandArgs User's input arguments.
     * @param storage Profile Storage to load and save data.
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    public CommandResult execute(String commandArgs, ProfileStorage storage) throws SchwarzeneggerException {
        logger.log(Level.INFO, "Executing " + this);
        return new CommandResult();
    }

    //@@author

    /**
     * Executes the command with user's input.
     *
     * @param input User's input for command.
     * @param storage Diet Storage to load and save data.
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     * @throws InvalidCommandWordException If command word is invalid.
     * @throws InvalidCommandFormatException If command format is invalid.
     * @throws InvalidDateFormatException If date format is invalid.
     * @throws InvalidSearchDateException If search date is invalid.
     */
    public CommandResult execute(String input, DietStorage storage) throws InvalidCommandWordException,
            InvalidCommandFormatException, InvalidDateFormatException, InvalidSearchDateException {
        return new CommandResult();
    }

    /**
     * Executes the command with user's input.
     *
     * @param input User's input for command.
     * @param foodList List containing food in diet session.
     * @param storage Diet Storage to load and save data.
     * @param index Integer variable that shows the index of diet session
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     * @throws InvalidCommandWordException If command word is invalid.
     */
    public CommandResult execute(String input, ArrayList<Food> foodList,
                                 DietStorage storage, Integer index) throws InvalidCommandWordException {
        return new CommandResult();
    }


    //@@author yujinyang1998

    /**
     * Executes the command with user's input.
     *
     * @param inputs Array of user's input.
     * @param exerciseList List of exercise.
     * @param filePath Path to data file.
     * @param workoutSessionStorage Workout Session Storage to load and save data.
     * @param hasEndedWorkoutSessions Array of booleans indicating if user has ended workout sessions.
     * @return An object CommandResult containing the executing status and feedback message to be displayed
     *         to user.
     * @throws InvalidCommandWordException If command word is invalid.
     */
    public CommandResult execute(String[] inputs, ExerciseList exerciseList,
                                 String filePath, WorkoutSessionStorage workoutSessionStorage,
                                 boolean[] hasEndedWorkoutSessions) throws InvalidCommandWordException {
        logger.log(Level.INFO, "Executing " + this);
        return new CommandResult();
    }
    //@@author
}
