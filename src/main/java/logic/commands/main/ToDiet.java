package logic.commands.main;

import diet.dietmanager.DietManager;
import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;
import logic.commands.ExecutionResult;

//@@author tienkhoa16

/**
 * A representation of the command for redirecting to Diet Menu from Main Menu.
 */
public class ToDiet extends Command {

    /**
     * Executes redirecting to Diet Menu command from Main Menu.
     *
     * @param args User's input.
     * @return Redirecting to Diet Menu message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);

        if (!args.isEmpty()) {
            ui.showWarning("\"diet\" command does not take in parameters");
        }

        DietManager dietManager = new DietManager();
        dietManager.start();
        return new CommandResult("", ExecutionResult.OK);
    }
}
