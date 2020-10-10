package seedu.duke.commands.sprintcommands;

import seedu.duke.commands.Command;
import seedu.duke.data.Project;
import seedu.duke.ui.TextUi;

import java.util.Hashtable;

public abstract class SprintCommand extends Command {
    /**
     * Creates a new Sprint command with arguments.
     */

    public SprintCommand(Hashtable<String, String> parameters) {
        super(parameters);
    }

    /**
     * Abstract method that execute the command.
     *
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public abstract boolean execute(Project proj, TextUi ui);

}
