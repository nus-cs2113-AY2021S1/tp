package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Saves the current task list locally before terminating the programme.
 */
public class ExitCommand extends Command {

    public ExitCommand(String userInput) {
        super(userInput);
    }

    /**
     * Saves the current task list locally.
     *
     * @param taskList the task list to be saved.
     * @param storage  the storage to be saved to.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        storage.writeToFile(taskList);
        Ui.printExitMessage();
    }

    /**
     * Sets the isExit flag to true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
