package seedu.duke.command.taskcommand;

import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * A class that allows the user to list down the tasks they input into the <code>TaskList</code>.
 */
public  class ListTaskCommand extends TaskCommand {

    public void execute(TaskList taskList) {
        Ui.printTaskList(taskList.getList());
    }

    public boolean isExit() {
        return false;
    }

}
