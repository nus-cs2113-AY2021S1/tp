package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import exceptions.SchwarzeneggerException;
import models.PastRecordList;

import static ui.CommonUi.clearMsg;
import static ui.workout.workoutmanager.WorkoutManagerUi.CLEAR_ABORTED;

//@@author wgzesg
/**
 * A representation of the command for clear commands in workout manager.
 */
public class ClearWS extends Command {

    /**
     * Executes all workout sessions.
     *
     * @param args User's input.
     * @return Status OK and feedback message if the execution is affirmed.
     *         Status ABORTED if the execution is withdrawn.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        if (!ui.checkConfirmation("Workout Menu", "clear all records")) {
            return new CommandResult(CLEAR_ABORTED, ExecutionResult.ABORTED);
        }
        PastRecordList.getInstance().clear();
        logger.info("Cleared successfully");
        String content = clearMsg("past workout records have");
        return new CommandResult(content, ExecutionResult.OK);
    }
}
