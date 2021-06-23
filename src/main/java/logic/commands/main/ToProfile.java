package logic.commands.main;

import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;
import profile.ProfileSession;

import static seedu.duke.Constants.PATH_TO_PROFILE_FILE;
import static seedu.duke.Constants.PATH_TO_PROFILE_FOLDER;

//@@author tienkhoa16

/**
 * A representation of the command for redirecting to Profile Menu from Main Menu.
 */
public class ToProfile extends Command {

    /**
     * Executes redirecting to Profile Menu command from Main Menu.
     *
     * @param args User's input.
     * @return Redirecting to Profile Menu message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);

        if (!args.isEmpty()) {
            ui.showWarning("\"profile\" command does not take in parameters");
        }

        ProfileSession profileSession = ProfileSession.getInstance(PATH_TO_PROFILE_FOLDER, PATH_TO_PROFILE_FILE);
        profileSession.run();
        return new CommandResult("", ExecutionResult.OK);
    }
}
