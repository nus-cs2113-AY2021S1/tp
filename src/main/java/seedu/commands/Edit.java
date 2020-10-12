package seedu.commands;

import seedu.data.TaskList;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.EDIT_MESSAGE;

public class Edit extends Command {
    public static final String COMMAND_WORD = "edit";
    private static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^edit (?<index>\\d+)"
                    + "( des/(?<description>(\\w+\\s*)+\\w*))?"
                    + "( d/(?<date>\\d{2}-\\d{2}-\\d{4}))?"
                    + "( st/(?<st>\\d{4}))?"
                    + "( et/(?<et>\\d{4}))?"
                    + "( p/(?<priority>\\d))?$");
    private final int index;
    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final String priority;

    public Edit(String rawInput) throws InvalidCommandException {
        Matcher matcher = COMMAND_PATTERN.matcher(rawInput);
        if (matcher.find()) {
            index = Integer.parseInt(matcher.group("index"));
            description = matcher.group("description");
            date = matcher.group("date");
            startTime = matcher.group("st");
            endTime = matcher.group("et");
            priority = matcher.group("priority");
        } else {
            throw new InvalidCommandException();
        }
    }

    @Override
    public CommandResult execute(TaskList tasks)
            throws InvalidTaskNumberException, InvalidPriorityException {
        // Check range
        if (index <= 0 || index > tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.get(index - 1);
        // Set field
        if (description != null) {
            task.setDescription(description);
        }
        if (date != null) {
            task.setDate(date);
        }
        if (startTime != null) {
            task.setStartTime(startTime);
        }
        if (endTime != null) {
            task.setEndTime(endTime);
        }
        if (priority != null) {
            task.setPriority(priority);
        }
        return new CommandResult(EDIT_MESSAGE, tasks);
    }
}
