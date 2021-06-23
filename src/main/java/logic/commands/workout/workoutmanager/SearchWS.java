package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import exceptions.SchwarzeneggerException;
import models.PastRecordList;

//@@author wgzesg
/**
 * A representation of the command for search commands in workout manager.
 */
public class SearchWS extends Command {

    /**
     * Searches workout session.
     *
     * @param args User's input.
     * @return Status OK and information to be printed.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {

        super.execute(args);
        String formattedInfo = PastRecordList.getInstance().search(args);
        return new CommandResult(formattedInfo, ExecutionResult.OK);
    }
}
