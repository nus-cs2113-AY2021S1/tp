package seedu.duke.command.taskcommand;

import seedu.duke.card.Subject;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

/**
 * A class that allows the user to list down the tasks they input into the <code>TaskList</code>.
 */
public class ListTaskCommand extends TaskCommand {

    public void execute(Subject subject) {
        Ui.printTaskList(subject);
    }

    public boolean isExit() {
        return false;
    }

}
