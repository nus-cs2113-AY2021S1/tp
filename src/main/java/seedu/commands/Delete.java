package seedu.commands;

import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidTaskNumberException;
import seedu.task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.DELETE_MESSAGE;

public class Delete extends Command {
    public static final String COMMAND_WORD = "delete";
    private final Integer key;
    public static final Pattern COMMAND_PATTERN = Pattern.compile(
            "^delete (?<key>\\d+)$");

    public Delete(String keyString) throws InvalidTaskNumberException {
        try{
            key = Integer.parseInt(keyString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    @Override
    public CommandResult execute(TaskMap tasks) throws InvalidTaskNumberException {
        Task task = tasks.get(key);
        if (task == null) {
            throw new InvalidTaskNumberException();
        }
        tasks.delete(key);
        return new CommandResult(DELETE_MESSAGE);
    }
}
