package seedu.commands;

import seedu.data.TaskMap;
import seedu.ui.DisplayMode;

public class CommandResult {
    private final String message;
    private TaskMap tasks = null;
    private DisplayMode displayMode = DisplayMode.ALL;
    private boolean isExit = false;

    public CommandResult(String message) {
        this.message = message;
    }

    public CommandResult(String message, TaskMap tasks) {
        this.message = message;
        this.tasks = tasks;
    }

    public CommandResult(String message, TaskMap tasks, DisplayMode displayMode) {
        this.message = message;
        this.tasks = tasks;
        this.displayMode = displayMode;
    }

    public String getMessage() {
        return message;
    }

    public TaskMap getTasks() {
        return tasks;
    }

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
