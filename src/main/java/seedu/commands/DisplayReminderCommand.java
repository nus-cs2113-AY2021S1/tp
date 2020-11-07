package seedu.commands;

import static seedu.messages.Messages.REMINDER_MESSAGE;
import seedu.task.Task;

public class DisplayReminderCommand extends Command {

    private Task task;

    public DisplayReminderCommand(Task task) {
        this.task = task;
    }

    public CommandResult execute() {
        return new CommandResult(REMINDER_MESSAGE, task);
    }
}
