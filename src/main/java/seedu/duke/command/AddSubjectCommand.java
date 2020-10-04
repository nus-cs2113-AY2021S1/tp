package seedu.duke.command;

import seedu.duke.list.TaskList;

/**
 * Adds an instance of the <code>Deadline</code> class into a <code>TaskList</code>.
 */
public class AddSubjectCommand extends Command {
    private String fullCommand;

    public AddSubjectCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList taskList) {

    }

    /**
     * Checks whether the the user exits the program.
     *
     * @return <code>true</code> if user exits the program
     */
    public boolean isExit() {
        return false;
    }
}
