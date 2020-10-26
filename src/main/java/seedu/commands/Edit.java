package seedu.commands;

import seedu.data.Model;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.EDIT_MESSAGE;

public class Edit extends ModificationCommand {
    public static final String COMMAND_WORD = "edit";
    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^edit (?<key>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private final Integer key;
    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;

    public Edit(String rawInput) throws InvalidCommandException, InvalidTaskNumberException {
        Matcher matcher = COMMAND_PATTERN.matcher(rawInput);
        if (matcher.find()) {
            try {
                key = Integer.parseInt(matcher.group("key"));
            } catch (NumberFormatException e) {
                throw new InvalidTaskNumberException();
            }
            description = matcher.group("description");
            date = matcher.group("date");
            startTime = matcher.group("st");
            endTime = matcher.group("et");
            priority = matcher.group("priority");
        } else {
            throw new InvalidCommandException();
        }
    }

    public CommandResult execute(Model model)
        throws InvalidTaskNumberException, InvalidPriorityException, InvalidDatetimeException {
        TaskMap tasks = model.getTaskMap();
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }

        Task editedTask = new Task(key, task.getDescription(), task.getDate(), task.getStartTime(),
            task.getEndTime(), task.getPriority());

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
        tasks.delete(key);
        tasks.addTask(editedTask);
        model.pushAndUpdate(tasks);
        return new CommandResult(EDIT_MESSAGE);
    }
}
