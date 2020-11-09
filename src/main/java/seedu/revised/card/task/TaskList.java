package seedu.revised.card.task;

import seedu.revised.card.taskcard.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a <code>Task</code> into a <code>TaskList</code>.
     *
     * @param task The <code>Task</code> to add into a <code>TaskList</code>
     */
    public void add(Task task) {
        this.taskList.add(task);

    }

    /**
     * Gets a <code>ArrayList</code> of Tasks in a <code>TaskList</code> instance.
     *
     * @return the <code>ArrayList</code> of Tasks
     */
    public List<Task> getList() {
        return this.taskList;
    }

}

