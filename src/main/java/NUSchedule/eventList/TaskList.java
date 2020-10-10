package NUSchedule.TaskList;

import NUSchedule.Exception.DoneUndefinedTaskException;
import NUSchedule.Task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Contains the list of the tasks and provides ways to delete/add/mark as done tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Construct the tasks as an empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * When loading from the file, create the list as provided in the file.
     *
     * @param tasks provided by <code>Storage::load()</code>
     * @see NUSchedule.Storage.Storage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param taskToBeAdded may be Class/Event/Assignment based on the usage
     */
    public void addTask(Task taskToBeAdded) {
        tasks.add(taskToBeAdded);
    }

    /**
     * Mark a task as done.
     *
     * @param taskIndex the index of the task being done
     * @throws DoneUndefinedTaskException the task is not defined but the user want to mark it as done
     */
    public void doneTask(int taskIndex) throws DoneUndefinedTaskException {
        try {
            tasks.get(taskIndex).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DoneUndefinedTaskException(taskIndex);
        }
    }

    /**
     * Gives the access to the list of tasks.
     *
     * @return the <code>ArrayList<task></code> object to be printed
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns how many tasks are in the list.
     *
     * @return the size of the ArrayList in the TaskList object
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Remove the task indicated by the user.
     */
    public void remove(int taskIndex) {
        tasks.remove(taskIndex);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param taskIndex index of the task to return
     * @return the task at the specified position in this list
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Filter the task list to find the tasks contain the information looking for by the user.
     *
     * @param filterString the keyword that the user is looking for
     * @return the filtered list. this list contains only the tasks that satisfy the requirement
     */
    public ArrayList<Task> filterWith(String filterString) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                .filter(s -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredTaskList;
    }
    /**
     * Filter the task list to find the tasks happen on the date looking for by the user.
     *
     * @param date the date that the user is looking for
     * @return the filtered list. this list contains only the tasks that satisfy the requirement
     */
    public ArrayList<Task> filterDateWith(LocalDate date) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) tasks.stream()
                .filter(s -> s.getDate().isEqual(date))
                .collect(toList());

        return filteredTaskList;
    }
}
