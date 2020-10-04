package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.TaskList;

/**
 * Lists all event type of tasks (such as lecture, lab, tutorial and events)
 * in the task list to the user.
 */
public class PrintEventsCommand extends Command {
    public PrintEventsCommand(String userInput) {
        super(userInput);
    }

    /**
     * Lists all event type of tasks (such as lecture, lab, tutorial and events)
     * in the task list to the user.
     *
     * @param taskList the task list to list from.
     * @param storage  not required.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.printEventsListView(taskList);
    }
}
