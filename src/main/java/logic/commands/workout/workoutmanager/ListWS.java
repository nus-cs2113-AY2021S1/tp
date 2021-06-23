package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import exceptions.SchwarzeneggerException;
import models.PastRecordList;

import static logic.commands.ExecutionResult.OK;

//@@author wgzesg
/**
 * A representation of the command for list commands in workout manager.
 */
public class ListWS extends Command {

    /**
     * Lists past record of workout sessions.
     *
     * @param args User's input.
     * @return Status OK and all past records in a table.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        String formattedInfo = PastRecordList.getInstance().list(args);
        logger.info("Listed successfully");
        return new CommandResult(formattedInfo, OK);
    }
}
