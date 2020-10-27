package seedu.dietbook.command;

import seedu.dietbook.exception.DietException;
import seedu.dietbook.Manager;
import seedu.dietbook.Ui;

/**
 * The command parent class for all commands.
 * All commands have an execute method that takes in objects <code>Manager</code> and <code>Ui</code>.
 * Each child command class is self-explanatory.
 *
 * @author tikimonarch
 */

public abstract class Command {
    public static int commandCount = 1;
    
    public abstract void execute(Manager manager, Ui ui) throws DietException;
}
