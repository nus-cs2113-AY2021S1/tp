package ui.workout.workoutmanager;

import ui.CommonUi;

import static ui.profile.ProfileUi.MESSAGE_HELP_FOR_MORE_INFO;

//@@author wgzesg
/**
 * A class that is responsible for user interface in Workout Manager.
 */
public class WorkoutManagerUi extends CommonUi {

    public static String CLEAR_ABORTED = "You have aborted the action!";
    public static String DELETE_SUCCESS = "You have deleted that record!";
    public static String EDIT_SUCCESS = "Congratulations! You have successfully edited the workout!";
    public static String NEW_SUCCESS = "Congratulations! You have finished today's workout!";
    public static String START_NEW_SESSION = "Starting Workout Session!";

    public static String MESSAGE_NOT_A_NUMBER = "Index must be a number. Your input is not a number!";
    public static String MESSAGE_INSUFFICIENT_ARGUMENT = "Insufficient arguments are given! "
            + "Please follow this format:" + LS + "%s" + LS + MESSAGE_HELP_FOR_MORE_INFO;
    public static String MESSAGE_OUT_OF_ARRAY = "The record specified is not found. Please check the index!";
}
