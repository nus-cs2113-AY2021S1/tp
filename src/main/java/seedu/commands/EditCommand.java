package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Pattern;

import static seedu.messages.Messages.EDIT_MESSAGE;

public class EditCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "edit";
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^(?<key>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?"
                    + "( r/(?<reminder>\\w+\\s*))?"
                    + "( t-(?<t>\\d{4}))?$");
    private final Integer key;
    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;
    private final String reminder;
    private final String reminderTime;

    public EditCommand(String keyString, String description, String date, String startTime, String endTime,
                       String priority, String reminder, String reminderTime)
            throws InvalidTaskNumberException, InvalidCommandException {
        try {
            key = Integer.parseInt(keyString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.reminder = reminder;
        this.reminderTime = reminderTime;

        if (startTime != null && endTime != null) {
            if (Integer.parseInt(startTime) >= Integer.parseInt(endTime)) {
                throw new InvalidCommandException();
            }
        }
    }

    public CommandResult execute(Model model)
            throws InvalidTaskNumberException, InvalidPriorityException,
            InvalidDatetimeException, InvalidReminderException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }

        Task editedTask = new Task(key, task.getDescription(), task.getDate(), task.getStartTime(),
                task.getEndTime(), task.getPriority(), task.newReminder());

        // Set field
        if (description != null) {
            editedTask.setDescription(description);
        }
        if (date != null) {
            editedTask.setDate(date);
        }
        if (startTime != null) {
            editedTask.setStartTime(startTime);
        }
        if (endTime != null) {
            editedTask.setEndTime(endTime);
        }
        if (priority != null) {
            editedTask.setPriority(priority);
        }
        if (reminder != null) {
            editedTask.setReminder(reminder, reminderTime);
        }
        editedTask.reminder.startReminder(editedTask);
        tasks.delete(key);
        tasks.addTask(editedTask);
        model.pushAndUpdate(tasks);
        return new CommandResult(EDIT_MESSAGE, editedTask);
    }
}
