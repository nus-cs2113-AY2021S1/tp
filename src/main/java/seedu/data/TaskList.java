package seedu.data;

import seedu.task.DateSorter;
import seedu.task.PrioritySorter;
import seedu.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTasks(ArrayList<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public TaskList sortListByDate() {
        // Sort by date
        List<Task> sorted = tasks.stream()
                .sorted(new DateSorter())
                .collect(Collectors.toList());
        return new TaskList((ArrayList<Task>) sorted);
    }

    public TaskList sortListByPriority() {
        // Sort by priority, same priority then date
        List<Task> sorted = tasks.stream()
                .sorted(new DateSorter())
                .sorted(new PrioritySorter())
                .collect(Collectors.toList());
        return new TaskList((ArrayList<Task>) sorted);
    }

    public TaskList searchDescription(String toSearch) {
        List<Task> found = Collections.singletonList(tasks.stream()
            .filter(task -> task.getDescription().contains(toSearch))
            .findAny()
            .orElse(null));
        return new TaskList((ArrayList<Task>) found);
    }

    public void clear() {
        tasks.clear();
    }

}
