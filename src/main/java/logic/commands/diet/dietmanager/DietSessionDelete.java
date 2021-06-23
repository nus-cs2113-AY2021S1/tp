package logic.commands.diet.dietmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import storage.diet.DietStorage;

import java.io.File;
import java.util.logging.Level;

import static seedu.duke.Constants.PATH_TO_DIET_FOLDER;
import static ui.diet.dietmanager.DietManagerUi.DIET_DELETE_SUCCESS;
import static ui.diet.dietmanager.DietManagerUi.DIET_DELETE_WRONG_FORMAT;
import static ui.diet.dietmanager.DietManagerUi.DIET_FILE_ARRAY_OUT_OF_BOUND;
import static ui.diet.dietmanager.DietManagerUi.DIET_NO_SESSIONS_SAVED;

//@@author CFZeon
/**
 * A representation of the command for delete commands in diet manager.
 */
public class DietSessionDelete extends Command {

    /**
     * Overrides execute for delete command to delete diet sessions.
     *
     * @param input   user input for command
     * @param storage storage for diet manager
     * @return CommandResult instance for delete message
     */
    @Override
    public CommandResult execute(String input, DietStorage storage) {
        File folder = new File(PATH_TO_DIET_FOLDER);
        File[] listOfFiles = folder.listFiles();
        String result = "";
        try {
            assert !input.isEmpty() : "No files to delete or wrong folder";
            assert listOfFiles != null;
            result = DIET_DELETE_SUCCESS;
            listOfFiles[Integer.parseInt(input) - 1].delete();
            logger.log(Level.INFO, "Deleted Diet Session successfully");
        } catch (NumberFormatException e) {
            result = DIET_DELETE_WRONG_FORMAT;
            logger.log(Level.INFO, "No or wrong index for deletion in dietManager");
        } catch (IndexOutOfBoundsException e) {
            result = DIET_FILE_ARRAY_OUT_OF_BOUND;
            logger.log(Level.INFO, "No input for session index");
        } catch (NullPointerException e) {
            result = DIET_NO_SESSIONS_SAVED;
        }
        return new CommandResult(result);
    }
}
