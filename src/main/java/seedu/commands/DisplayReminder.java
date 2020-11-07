package seedu.commands;

import static seedu.messages.Messages.REMINDER_MESSAGE;
import seedu.task.Task;

/**
 * This class creates a command for displaying the reminder task when the
 * time set for the reminder is reached.
 */
public class DisplayReminder extends Command {

    private Task task;

    public DisplayReminder(Task task) {
        this.task = task;
    }

    public CommandResult execute() {
        return new CommandResult(REMINDER_MESSAGE, task);
    }
}
