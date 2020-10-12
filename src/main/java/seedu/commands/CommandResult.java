package seedu.commands;

import seedu.data.TaskList;
import seedu.task.Task;

public class CommandResult {
    private final String message;
    private final TaskList tasks;
    private final Task task;

    public CommandResult(String message) {
        this(message, null, null);
    }

    public CommandResult(String message, TaskList tasks) {
        this(message, tasks, null);
    }

    public CommandResult(String message, TaskList tasks, Task task) {
        this.message = message;
        this.tasks = tasks;
        this.task = task;
    }

    public String getMessage() {
        return message;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Task getTask() {
        return task;
    }
}
