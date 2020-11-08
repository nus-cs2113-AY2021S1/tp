package seedu.data;

import seedu.task.DateSorter;
import seedu.task.PrioritySorter;
import seedu.task.Task;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Container for storing tasks in (Integer, Task) pairs.
 */
public class TaskMap {
    public static final int MAX_NUM_TASKS = 10000;
    private LinkedHashMap<Integer, Task> tasksMap;

    /**
     * Creating a new TaskMap to store tasks.
     */
    public TaskMap() {
        tasksMap = new LinkedHashMap<>(MAX_NUM_TASKS);
    }

    /**
     * Creating a TaskMap from an existing collection of task objects.
     * @param tasks collection of task objects.
     */
    public TaskMap(Collection<Task> tasks) {
        tasksMap = new LinkedHashMap<>(MAX_NUM_TASKS);
        for (Task task : tasks) {
            tasksMap.put(task.getTaskID(), task);
        }
    }

    /**
     * Get a collection of task objects from a TaskMap.
     * @return collection of task objects.
     */
    public Collection<Task> getValues() {
        return tasksMap.values();
    }

    /**
     * Adds a task to a TaskMap.
     * @param task to be added.
     */
    public void addTask(Task task) {
        tasksMap.put(task.getTaskID(), task);
    }

    /**
     * Get the size of a TaskMap.
     * @return size of the TaskMap.
     */
    public int size() {
        return tasksMap.size();
    }

    /**
     * Get the task from TaskMap by key.
     * @param key unique identifier of a task.
     * @return the task with the key as unique identifier.
     */
    public Task get(Integer key) {
        return tasksMap.get(key);
    }

    /**
     * Sort a TaskMap by date of tasks.
     * @return new TaskMap which sort tasks by date.
     */
    public TaskMap sortListByDate() {
        // Sort by date
        List<Task> sorted = tasksMap.values().stream()
            .sorted(new DateSorter())
            .collect(Collectors.toList());
        return new TaskMap(sorted);
    }

    /**
     * Sort a TaskMap by priority of tasks.
     * @return new TaskMap which sort tasks by priority.
     */
    public TaskMap sortListByPriority() {
        // Sort by priority, same priority then date
        List<Task> sorted = tasksMap.values().stream()
            .sorted(new DateSorter())
            .sorted(new PrioritySorter())
            .collect(Collectors.toList());
        return new TaskMap(sorted);
    }

    /**
     * Search task in TaskMap by description.
     * @param toSearch string to be searched.
     * @return new TaskMap with tasks' description contain toSearch.
     */
    public TaskMap searchByDescription(String toSearch) {
        List<Task> found = tasksMap.values().stream()
            .filter(task -> task.getDescription().toLowerCase().contains(toSearch.toLowerCase()))
            .collect(Collectors.toList());
        return new TaskMap(found);
    }

    /**
     * Search task in TaskMap by date.
     * @param date of task.
     * @return new TaskMap with tasks on date.
     */
    public TaskMap searchByDate(LocalDate date) {
        List<Task> found = tasksMap.values().stream()
            .filter(task -> task.getDate().equals(date))
            .collect(Collectors.toList());
        return new TaskMap(found);
    }

    /**
     * Empty TaskMap.
     */
    public void clear() {
        tasksMap.clear();
    }

    /**
     * Delete a task from TaskMap by key.
     * @param key unique identifier of a task.
     */
    public void delete(Integer key) {
        if (tasksMap.size() > 0) {
            tasksMap.get(key).reminder.offReminder();
        }
        tasksMap.remove(key);
    }

}
