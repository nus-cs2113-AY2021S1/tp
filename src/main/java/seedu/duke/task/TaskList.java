package seedu.duke.task;

import seedu.duke.common.Messages;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ItemList<Task> {

    /**
     * Constructs a task list with the given tasks.
     *
     * @param tasks an ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.items = tasks;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    @Override
    public void addTodo(String description) {
        Task newTask = new Task(description);
        items.add(newTask);
        Ui.dukePrint(Messages.MESSAGE_ADDTASK + newTask.toString() + Messages.MESSAGE_STATUS_FIRST
                + items.size() + Messages.MESSAGE_STATUS_LAST);
    }
}
