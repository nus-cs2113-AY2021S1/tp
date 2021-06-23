package logic.commands.main;

import exceptions.EndException;
import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;

//@@author tienkhoa16

/**
 * A representation of the command for exiting The Schwarzenegger.
 */
public class MainEnd extends Command {

    /**
     * Overrides execute method of class Command to execute end command requested by user's input.
     *
     * @param args User's input.
     * @return Result of command execution.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);

        if (!args.isEmpty()) {
            ui.showWarning("\"end\" command does not take in parameters");
        }

        throw new EndException();
    }
}
