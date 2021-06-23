package logic.commands.main;

import exceptions.InvalidCommandWordException;
import exceptions.SchwarzeneggerException;
import logic.commands.Command;
import logic.commands.CommandResult;

//@@author tienkhoa16

/**
 * A representation of invalid command in Main Menu.
 */
public class MainWrong extends Command {

    /**
     * Notifies user invalid input command.
     *
     * @param args User's input.
     * @return Invalid input command message.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        assert args != null : "arguments cannot be null";
        super.execute(args);
        throw new InvalidCommandWordException();
    }
}
