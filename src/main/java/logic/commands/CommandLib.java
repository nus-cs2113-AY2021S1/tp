package logic.commands;

import logic.commands.diet.dietmanager.DietSessionClear;
import logic.commands.diet.dietmanager.DietSessionCreate;
import logic.commands.diet.dietmanager.DietSessionDelete;
import logic.commands.diet.dietmanager.DietSessionEdit;
import logic.commands.diet.dietmanager.DietSessionHelp;
import logic.commands.diet.dietmanager.DietSessionList;
import logic.commands.diet.dietmanager.DietSessionSearch;
import logic.commands.diet.dietmanager.DietSessionWrong;
import logic.commands.diet.dietsession.FoodItemAdd;
import logic.commands.diet.dietsession.FoodItemClear;
import logic.commands.diet.dietsession.FoodItemDelete;
import logic.commands.diet.dietsession.FoodItemHelp;
import logic.commands.diet.dietsession.FoodItemList;
import logic.commands.diet.dietsession.FoodItemSearch;
import logic.commands.diet.dietsession.FoodItemWrong;
import logic.commands.main.MainEnd;
import logic.commands.main.MainHelp;
import logic.commands.main.MainWrong;
import logic.commands.main.ToDiet;
import logic.commands.main.ToProfile;
import logic.commands.main.ToWorkout;
import logic.commands.profile.ProfileAdd;
import logic.commands.profile.ProfileDelete;
import logic.commands.profile.ProfileEdit;
import logic.commands.profile.ProfileEnd;
import logic.commands.profile.ProfileHelp;
import logic.commands.profile.ProfileView;
import logic.commands.profile.ProfileWrong;
import logic.commands.workout.workoutmanager.ByeWS;
import logic.commands.workout.workoutmanager.ClearWS;
import logic.commands.workout.workoutmanager.DeleteWS;
import logic.commands.workout.workoutmanager.EditWS;
import logic.commands.workout.workoutmanager.HelpWS;
import logic.commands.workout.workoutmanager.ListWS;
import logic.commands.workout.workoutmanager.NewWS;
import logic.commands.workout.workoutmanager.SearchWS;
import logic.commands.workout.workoutmanager.WrongWS;
import logic.commands.workout.workoutsession.WorkoutSessionAdd;
import logic.commands.workout.workoutsession.WorkoutSessionDelete;
import logic.commands.workout.workoutsession.WorkoutSessionEnd;
import logic.commands.workout.workoutsession.WorkoutSessionHelp;
import logic.commands.workout.workoutsession.WorkoutSessionList;
import logic.commands.workout.workoutsession.WorkoutSessionSearch;
import logic.commands.workout.workoutsession.WorkoutSessionWrong;

import java.util.Hashtable;

import static seedu.duke.Constants.COMMAND_WORD_ADD;
import static seedu.duke.Constants.COMMAND_WORD_CLEAR;
import static seedu.duke.Constants.COMMAND_WORD_DELETE;
import static seedu.duke.Constants.COMMAND_WORD_DIET;
import static seedu.duke.Constants.COMMAND_WORD_EDIT;
import static seedu.duke.Constants.COMMAND_WORD_END;
import static seedu.duke.Constants.COMMAND_WORD_HELP;
import static seedu.duke.Constants.COMMAND_WORD_LIST;
import static seedu.duke.Constants.COMMAND_WORD_NEW;
import static seedu.duke.Constants.COMMAND_WORD_PROFILE;
import static seedu.duke.Constants.COMMAND_WORD_SEARCH;
import static seedu.duke.Constants.COMMAND_WORD_VIEW;
import static seedu.duke.Constants.COMMAND_WORD_WORKOUT;
import static seedu.duke.Constants.COMMAND_WORD_WRONG;

//@@author wgzesg
/**
 * A class for extracting the correct Command object for each command word.
 */
public class CommandLib {

    public Hashtable<String, Command> library;

    /**
     * Constructs CommandLib object.
     */
    public CommandLib() {
        library = new Hashtable<>();
    }

    //@@author wgzesg
    /**
     * Initializes the commandLib with main menu's commands.
     */
    public void initMainMenuCl() {
        library.put(COMMAND_WORD_WRONG, new MainWrong());
        library.put(COMMAND_WORD_HELP, new MainHelp());
        library.put(COMMAND_WORD_DIET, new ToDiet());
        library.put(COMMAND_WORD_PROFILE, new ToProfile());
        library.put(COMMAND_WORD_WORKOUT, new ToWorkout());
        library.put(COMMAND_WORD_END, new MainEnd());
    }

    //@@author tienkhoa16
    /**
     * Initializes the commandLib with profile menu's commands.
     */
    public void initProfileSessionCl() {
        library.put(COMMAND_WORD_HELP, new ProfileHelp());
        library.put(COMMAND_WORD_ADD, new ProfileAdd());
        library.put(COMMAND_WORD_DELETE, new ProfileDelete());
        library.put(COMMAND_WORD_VIEW, new ProfileView());
        library.put(COMMAND_WORD_EDIT, new ProfileEdit());
        library.put(COMMAND_WORD_END, new ProfileEnd());
        library.put(COMMAND_WORD_WRONG, new ProfileWrong());
    }

    //@@author
    /**
     * Initializes the commandLib with workout menu's commands.
     */
    public void initWorkoutManagerCl() {
        library.put(COMMAND_WORD_LIST, new ListWS());
        library.put(COMMAND_WORD_NEW, new NewWS());
        library.put(COMMAND_WORD_DELETE, new DeleteWS());
        library.put(COMMAND_WORD_END, new ByeWS());
        library.put(COMMAND_WORD_EDIT, new EditWS());
        library.put(COMMAND_WORD_CLEAR, new ClearWS());
        library.put(COMMAND_WORD_HELP, new HelpWS());
        library.put(COMMAND_WORD_WRONG, new WrongWS());
        library.put(COMMAND_WORD_SEARCH, new SearchWS());
    }

    //@@author yujinyang1998
    /**
     * Initializes the commandLib with workout session's commands.
     */
    public void initWorkoutSessionCl() {
        library.put(COMMAND_WORD_ADD, new WorkoutSessionAdd());
        library.put(COMMAND_WORD_DELETE, new WorkoutSessionDelete());
        library.put(COMMAND_WORD_LIST, new WorkoutSessionList());
        library.put(COMMAND_WORD_END, new WorkoutSessionEnd());
        library.put(COMMAND_WORD_HELP, new WorkoutSessionHelp());
        library.put(COMMAND_WORD_SEARCH, new WorkoutSessionSearch());
        library.put(COMMAND_WORD_WRONG, new WorkoutSessionWrong());
    }

    //@@author
    /**
     * Initializes the commandLib with diet manager's commands.
     */
    public void initDietManagerCl() {
        library.put(COMMAND_WORD_LIST, new DietSessionList());
        library.put(COMMAND_WORD_NEW, new DietSessionCreate());
        library.put(COMMAND_WORD_HELP, new DietSessionHelp());
        library.put(COMMAND_WORD_CLEAR, new DietSessionClear());
        library.put(COMMAND_WORD_SEARCH, new DietSessionSearch());
        library.put(COMMAND_WORD_EDIT, new DietSessionEdit());
        library.put(COMMAND_WORD_DELETE, new DietSessionDelete());
        library.put(COMMAND_WORD_WRONG, new DietSessionWrong());
    }

    //@@author wgzesg
    /**
     * Initializes the commandLib with diet session's commands.
     */
    public void initDietSessionCl() {
        library.put(COMMAND_WORD_ADD, new FoodItemAdd());
        library.put(COMMAND_WORD_DELETE, new FoodItemDelete());
        library.put(COMMAND_WORD_HELP, new FoodItemHelp());
        library.put(COMMAND_WORD_CLEAR, new FoodItemClear());
        library.put(COMMAND_WORD_SEARCH, new FoodItemSearch());
        library.put(COMMAND_WORD_LIST, new FoodItemList());
        library.put(COMMAND_WORD_WRONG, new FoodItemWrong());
    }

    //@@author wgzesg
    /**
     * Gets specific Command object based on command keyword.
     *
     * @param keyword Keyword specifying type of command.
     * @return Command object.
     */
    public Command getCommand(String keyword) {
        if (keyword == null || !library.containsKey(keyword.toLowerCase())) {
            return library.get(COMMAND_WORD_WRONG);
        } else {
            return library.get(keyword.toLowerCase());
        }
    }
}
