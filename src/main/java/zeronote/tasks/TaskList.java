package zeronote.tasks;

import zeronote.exceptions.TaskIndexOutOfBoundsException;
import zeronote.exceptions.ZeroNoteException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskArrayList;

    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    //@@author chuckiex3
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    //@@author Lusi711
    /**
     * Deletes a task with the specified index from this TaskList.
     *
     * @param index the index of the task to be deleted.
     * @return the task that is deleted.
     * @throws IndexOutOfBoundsException when the index is out of the bounds of the TaskList.
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        return taskArrayList.remove(index);
    }

    //@@author chuckiex3
    public int getNumberOfTasks() {
        return taskArrayList.size();
    }

    //@@author Lusi711
    public Task getTask(int index) throws TaskIndexOutOfBoundsException {
        if (index > taskArrayList.size()) {
            throw new TaskIndexOutOfBoundsException(String.valueOf(index));
        }
        return taskArrayList.get(index);
    }

    //@@author yAOwzers
    public Task markAsDone(int i) throws ZeroNoteException {
        if (i > taskArrayList.size()) {
            throw new TaskIndexOutOfBoundsException(String.valueOf(taskArrayList.size()));
        }
        Task task = this.taskArrayList.get(i - 1);
        task.markAsDone();
        return task;
    }

    //@@author longngng
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    //@@author neilbaner
    public String serialize() {
        StringBuilder serialized = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        serialized.append(getNumberOfTasks());
        serialized.append(lineSeparator);
        for (Task t : taskArrayList) {
            serialized.append(t.serialize());
        }
        return serialized.toString();
    }
}
