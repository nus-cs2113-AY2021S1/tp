package seedu.commands;

import seedu.data.TaskMap;
import seedu.task.Task;

public class CommandResult {
    private final String message;
    private final TaskMap tasks;
    private final Task task;

    public CommandResult(String message) {
        this(message, null, null);
    }

    public CommandResult(String message, TaskMap tasks) {
        this(message, tasks, null);
    }

    public CommandResult(String message, TaskMap tasks, Task task) {
        this.message = message;
        this.tasks = tasks;
        this.task = task;
    }

    public String getMessage() {
        return message;
    }

    public TaskMap getTasks() {
        return tasks;
    }

    public Task getTask() {
        return task;
    }
}
