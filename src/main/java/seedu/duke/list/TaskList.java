package seedu.duke.list;

import seedu.duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Saves the Tasks in a <code>TaskList</code> into a <code>File</code>.
     *
     * @param filename of the <code>File</code> to write to
     * @throws IOException if there is an error writing to the file
     */
    public void saveTask(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (Task task : this.taskList) {
        }
        fileWriter.close();
    }


}

