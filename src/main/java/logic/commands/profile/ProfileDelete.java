package logic.commands.profile;

import exceptions.SchwarzeneggerException;
import exceptions.profile.InvalidSaveFormatException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import models.Profile;
import storage.profile.ProfileStorage;

import static logic.commands.ExecutionResult.FAILED;
import static logic.commands.ExecutionResult.OK;
import static ui.profile.ProfileUi.MESSAGE_DELETE_NOTHING;
import static ui.profile.ProfileUi.MESSAGE_DELETE_PROFILE;
import static ui.workout.workoutmanager.WorkoutManagerUi.CLEAR_ABORTED;

//@@author tienkhoa16

/**
 * A representation of the command for deleting user profile.
 */
public class ProfileDelete extends Command {

    /**
     * Overrides execute method of class Command to execute the delete profile command requested by user's input.
     *
     * @param commandArgs User's input arguments.
     * @param storage Profile Storage to load and save data.
     * @return Result of command execution.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String commandArgs, ProfileStorage storage) throws SchwarzeneggerException {
        assert commandArgs != null : "command args cannot be null";
        assert storage != null : "profile storage cannot be null";

        super.execute(commandArgs, storage);

        if (!commandArgs.isEmpty()) {
            ui.showWarning("\"delete\" command does not take in parameters");
        }

        try {
            Profile profile = storage.loadData();

            if (!ui.checkConfirmation("Profile Menu", "clear your profile")) {
                return new CommandResult(CLEAR_ABORTED, ExecutionResult.ABORTED);
            }

            storage.saveData(null);
            return new CommandResult(MESSAGE_DELETE_PROFILE, OK);
        } catch (InvalidSaveFormatException e) {
            return new CommandResult(MESSAGE_DELETE_NOTHING, FAILED);
        }
    }
}
