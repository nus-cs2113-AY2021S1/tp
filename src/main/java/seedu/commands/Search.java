package seedu.commands;

import seedu.data.TaskList;
import seedu.exceptions.InvalidCommandException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.SEARCH_FOUND_MESSAGE;
import static seedu.messages.Messages.SEARCH_NOT_FOUND_MESSAGE;

public class Search extends Command {
    public static final String COMMAND_WORD = "search";
    // Search by description, can extend to other attributes
    private static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^search (?<toSearch>(\\w+\\s*)+\\w*)$");

    private final String toSearch;

    public Search(String rawInput) throws InvalidCommandException {
        Matcher matcher = COMMAND_PATTERN.matcher(rawInput);
        if (matcher.find()) {
            toSearch = matcher.group("toSearch");
        } else {
            throw new InvalidCommandException();
        }
    }

    @Override
    public CommandResult execute(TaskList tasks) {
        TaskList found = tasks.searchDescription(toSearch);
        if (found.size() > 0) {
            return new CommandResult(SEARCH_FOUND_MESSAGE, found);
        } else {
            return new CommandResult(SEARCH_NOT_FOUND_MESSAGE);
        }
    }
}
