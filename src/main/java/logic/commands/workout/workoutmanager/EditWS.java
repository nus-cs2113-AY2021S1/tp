package logic.commands.workout.workoutmanager;

import exceptions.InsufficientArgumentException;
import exceptions.SchwarzeneggerException;
import exceptions.workout.workoutmanager.NotANumberException;
import exceptions.workout.workoutmanager.OutOfArrayException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import models.PastRecordList;
import workout.workoutsession.WorkoutSession;

import static ui.workout.workoutmanager.WorkoutManagerUi.EDIT_SUCCESS;
import static ui.workout.workoutmanager.WorkoutManagerUi.START_NEW_SESSION;

//@@author wgzesg
/**
 * A representation of the command for edit commands in workout manager.
 */
public class EditWS extends Command {

    /**
     * Edits a record at a given index.
     *
     * @param args User's input.
     * @return Status OK and feedback message if file is edit.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        int index;
        try {
            index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            logger.warning("Number format exception caught");
            throw new NotANumberException();
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Insufficient arguments given!");
            throw new InsufficientArgumentException("edit [INDEX]");
        }

        String filePath;
        try {
            filePath = PastRecordList.getInstance().edit(index);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Index Out Of Bounds Exception caught");
            throw new OutOfArrayException();
        }
        WorkoutSession ws = new WorkoutSession(filePath, false, index);
        logger.info("editing workout session created");

        ui.showToUser(START_NEW_SESSION);
        ws.workoutSessionStart();

        logger.info("edited successfully");
        return new CommandResult(EDIT_SUCCESS, ExecutionResult.OK);
    }

}
