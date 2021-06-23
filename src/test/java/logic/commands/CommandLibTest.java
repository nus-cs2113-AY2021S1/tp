package logic.commands;

import logic.commands.diet.dietmanager.DietSessionClear;
import logic.commands.diet.dietmanager.DietSessionCreate;
import logic.commands.diet.dietmanager.DietSessionDelete;
import logic.commands.diet.dietmanager.DietSessionEdit;
import logic.commands.diet.dietmanager.DietSessionHelp;
import logic.commands.diet.dietmanager.DietSessionList;
import logic.commands.diet.dietmanager.DietSessionWrong;
import logic.commands.diet.dietsession.FoodItemAdd;
import logic.commands.diet.dietsession.FoodItemClear;
import logic.commands.diet.dietsession.FoodItemDelete;
import logic.commands.diet.dietsession.FoodItemHelp;
import logic.commands.diet.dietsession.FoodItemList;
import logic.commands.diet.dietsession.FoodItemWrong;
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
import logic.commands.workout.workoutmanager.DeleteWS;
import logic.commands.workout.workoutmanager.ListWS;
import logic.commands.workout.workoutmanager.NewWS;
import logic.commands.workout.workoutmanager.WrongWS;
import logic.commands.workout.workoutsession.WorkoutSessionAdd;
import logic.commands.workout.workoutsession.WorkoutSessionDelete;
import logic.commands.workout.workoutsession.WorkoutSessionEnd;
import logic.commands.workout.workoutsession.WorkoutSessionHelp;
import logic.commands.workout.workoutsession.WorkoutSessionList;
import logic.commands.workout.workoutsession.WorkoutSessionSearch;
import logic.commands.workout.workoutsession.WorkoutSessionWrong;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ui.CommonUi.EMPTY_STRING;

class CommandLibTest {

    @Test
    void getCommandTest_dietManagerUnrecognisedCommand_returnDietSessionWrong() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("unregconised") instanceof DietSessionWrong);
    }

    @Test
    void getCommandTest_listDietSession_returnListCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("list") instanceof DietSessionList);
    }

    @Test
    void getCommandTest_deleteDietSession_returnDeleteCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("delete") instanceof DietSessionDelete);
    }

    @Test
    void getCommandTest_newDietSession_returnNewCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("new") instanceof DietSessionCreate);
    }

    @Test
    void getCommandTest_clearDietSession_returnClearCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("clear") instanceof DietSessionClear);
    }

    @Test
    void getCommandTest_editDietSession_returnEditCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("edit") instanceof DietSessionEdit);
    }

    @Test
    void getCommandTest_helpDietSession_returnHelpCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietManagerCl();
        assertTrue(cl.getCommand("help") instanceof DietSessionHelp);
    }

    @Test
    void getCommandTest_DietManagerUnrecognisedCommand_returnFoodItemWrong() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("unregconised") instanceof FoodItemWrong);
    }

    @Test
    void getCommandTest_listFoodItem_returnListCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("list") instanceof FoodItemList);
    }

    @Test
    void getCommandTest_deleteFoodItem_returnDeleteCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("delete") instanceof FoodItemDelete);
    }

    @Test
    void getCommandTest_newFoodItem_returnNewCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("add") instanceof FoodItemAdd);
    }

    @Test
    void getCommandTest_clearFoodItem_returnClearCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("clear") instanceof FoodItemClear);
    }

    @Test
    void getCommandTest_helpFoodItem_returnHelpCommand() {
        CommandLib cl = new CommandLib();
        cl.initDietSessionCl();
        assertTrue(cl.getCommand("help") instanceof FoodItemHelp);
    }

    @Test
    void getCommandTest_WorkoutManagerUnrecognisedCommand_returnNull() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCl();
        assertTrue(cl.getCommand("unregconised") instanceof WrongWS);
    }

    @Test
    void getCommandTest_listWS_returnListCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCl();
        assertTrue(cl.getCommand("list") instanceof ListWS);
    }

    @Test
    void getCommandTest_deleteWS_returnDeleteCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCl();
        assertTrue(cl.getCommand("delete") instanceof DeleteWS);
    }

    @Test
    void getCommandTest_newWS_returnNewCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCl();
        assertTrue(cl.getCommand("new") instanceof NewWS);
    }

    @Test
    void getCommandTest_byeWS_returnByeCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutManagerCl();
        assertTrue(cl.getCommand("end") instanceof ByeWS);
    }

    //@@author yujinyang1998
    @Test
    void getCommandTest_WorkoutSessionUnrecognisedCommand_returnNull() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("unregconised") instanceof WorkoutSessionWrong);
    }

    @Test
    void getCommandTest_workoutSessionList_returnListCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("list") instanceof WorkoutSessionList);
    }

    @Test
    void getCommandTest_workoutSessionDelete_returnDeleteCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("delete") instanceof WorkoutSessionDelete);
    }

    @Test
    void getCommandTest_workoutSessionAdd_returnAddCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("add") instanceof WorkoutSessionAdd);
    }

    @Test
    void getCommandTest_workoutSessionEnd_returnEndCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("end") instanceof WorkoutSessionEnd);
    }

    @Test
    void getCommandTest_workoutSessionSearch_returnSearchCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("search") instanceof WorkoutSessionSearch);
    }

    @Test
    void getCommandTest_workoutSessionHelp_returnHelpCommand() {
        CommandLib cl = new CommandLib();
        cl.initWorkoutSessionCl();
        assertTrue(cl.getCommand("help") instanceof WorkoutSessionHelp);
    }

    //@@author tienkhoa16
    @Test
    void getCommandTest_profileSessionAddCommand_returnProfileAdd() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("add") instanceof ProfileAdd);
    }

    @Test
    void getCommandTest_profileSessionDeleteCommand_returnProfileDelete() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("delete") instanceof ProfileDelete);
    }

    @Test
    void getCommandTest_profileSessionEditCommand_returnProfileEdit() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("edit") instanceof ProfileEdit);
    }

    @Test
    void getCommandTest_profileSessionEndCommand_returnProfileEnd() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("end") instanceof ProfileEnd);
    }

    @Test
    void getCommandTest_profileSessionHelpCommand_returnProfileHelp() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("help") instanceof ProfileHelp);
    }

    @Test
    void getCommandTest_profileSessionViewCommand_returnProfileView() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("view") instanceof ProfileView);
    }

    @Test
    void getCommandTest_profileSessionUnrecognisedCommand_returnProfileWrong() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand("unregconised") instanceof ProfileWrong);
    }

    @Test
    void getCommandTest_profileInputNull_returnWrong() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand(null) instanceof ProfileWrong);
    }

    @Test
    void getCommandTest_profileInputEmpty_returnWrong() {
        CommandLib cl = new CommandLib();
        cl.initProfileSessionCl();
        assertTrue(cl.getCommand(EMPTY_STRING) instanceof ProfileWrong);
    }

    @Test
    void getCommandTest_mainHelpCommand_returnHelp() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand("help") instanceof MainHelp);
    }

    @Test
    void getCommandTest_mainDietCommand_returnToDiet() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand("diet") instanceof ToDiet);
    }

    @Test
    void getCommandTest_mainProfileCommand_returnToProfile() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand("profile") instanceof ToProfile);
    }

    @Test
    void getCommandTest_mainWorkoutCommand_returnToWorkout() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand("workout") instanceof ToWorkout);
    }

    @Test
    void getCommandTest_mainUnrecognisedCommand_returnWrong() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand("unregconised") instanceof MainWrong);
    }

    @Test
    void getCommandTest_mainInputNull_returnWrong() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand(null) instanceof MainWrong);
    }

    @Test
    void getCommandTest_mainInputEmpty_returnWrong() {
        CommandLib cl = new CommandLib();
        cl.initMainMenuCl();
        assertTrue(cl.getCommand(EMPTY_STRING) instanceof MainWrong);
    }
}
