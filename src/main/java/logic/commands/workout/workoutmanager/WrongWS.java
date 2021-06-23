package logic.commands.workout.workoutmanager;

import logic.commands.Command;
import logic.commands.CommandResult;
import exceptions.InvalidCommandWordException;
import exceptions.SchwarzeneggerException;

//@@author wgzesg
/**
 * A representation of the command for wrong commands in workout manager.
 */
public class WrongWS extends Command {

    /**
     * Notifies user invalid input command.
     *
     * @param args User's input.
     * @return Status MISSING and COMMAND_NOT_FOUND feedback.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        throw new InvalidCommandWordException();
    }
}
