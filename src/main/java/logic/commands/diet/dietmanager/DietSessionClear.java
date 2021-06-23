package logic.commands.diet.dietmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import storage.diet.DietStorage;
import ui.CommonUi;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;

import static seedu.duke.Constants.PATH_TO_DIET_FOLDER;
import static ui.diet.dietmanager.DietManagerUi.CLEAR_RECORD;
import static ui.diet.dietmanager.DietManagerUi.DIET_CLEAR_MSG;
import static ui.diet.dietmanager.DietManagerUi.DIET_MENU_NAME;
import static ui.diet.dietmanager.DietManagerUi.DIET_NOTHING_TO_CLEAR_MSG;
import static ui.diet.dietmanager.DietManagerUi.EMPTY_STRING;
import static ui.workout.workoutmanager.WorkoutManagerUi.CLEAR_ABORTED;

//@@author CFZeon
/**
 * A representation of the command for clear commands in diet manager.
 */
public class DietSessionClear extends Command {
    /**
     * Overrides execute for clear command to clear all diet sessions.
     *
     * @param input   user input for command
     * @param storage storage for diet manager
     * @return CommandResult instance
     */
    @Override
    public CommandResult execute(String input, DietStorage storage) {
        String resultMessage = EMPTY_STRING;
        try {
            if (ui.checkConfirmation(DIET_MENU_NAME, CLEAR_RECORD)) {
                File folder = new File(PATH_TO_DIET_FOLDER);
                File[] listOfFiles = folder.listFiles();
                for (int index = 0; index < Objects.requireNonNull(listOfFiles).length; index++) {
                    listOfFiles[index].delete();
                }
                resultMessage = CommonUi.clearMsg(DIET_CLEAR_MSG);
                logger.log(Level.INFO, "Cleared all diet sessions");
            } else {
                resultMessage = CLEAR_ABORTED;
            }
        } catch (NullPointerException e) {
            resultMessage = DIET_NOTHING_TO_CLEAR_MSG;
            logger.log(Level.INFO, "No sessions in dietManager for deletion");
        }
        return new CommandResult(resultMessage);
    }
}