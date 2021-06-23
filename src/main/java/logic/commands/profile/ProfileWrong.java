package logic.commands.profile;

import exceptions.InvalidCommandWordException;
import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;
import storage.profile.ProfileStorage;

//@@author tienkhoa16

/**
 * A representation of invalid command in Profile Menu.
 */
public class ProfileWrong extends Command {

    /**
     * Notifies user invalid input command.
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
        throw new InvalidCommandWordException();
    }
}
