package seedu.commands;

import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.task.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.LIST_MESSAGE;

public class List extends Command {
    public static final String COMMAND_WORD = "list";

    private static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^list(?<dateFlag> -d)?"
                + "(?<priorityFlag> -p)?$");

    private final boolean dateFlag;
    private final boolean priorityFlag;


    public List(String rawInput) throws InvalidCommandException {
        Matcher matcher = COMMAND_PATTERN.matcher(rawInput);
        if (matcher.find()) {
            dateFlag = " -d".equals(matcher.group("dateFlag"));
            priorityFlag = " -p".equals(matcher.group("priorityFlag"));
        } else {
            throw new InvalidCommandException();
        }
    }

    @Override
    public CommandResult execute(TaskMap tasks) {
        assert !(dateFlag && priorityFlag);
        if (dateFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByDate());
        } else if (priorityFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByPriority());
        }
        return new CommandResult(LIST_MESSAGE, tasks);
    }
}
