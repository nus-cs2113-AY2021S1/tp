package seedu.commands;

import seedu.data.TaskMap;
import seedu.exceptions.InvalidCommandException;
import seedu.ui.DisplayMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.messages.Messages.LIST_MESSAGE;

public class List extends Command {
    public static final String COMMAND_WORD = "list";

    private static final Pattern COMMAND_PATTERN = Pattern.compile(
        "^list(?<dateFlag> -d)?"
                + "(?<priorityFlag> -p)?"
                + "(?<displayByWeek> -w)?"
                + "(?<displayByMonth> -m)?$");

    private final boolean dateFlag;
    private final boolean priorityFlag;
    private final boolean displayByWeek;
    private final boolean displayByMonth;
    private DisplayMode displayMode = DisplayMode.ALL;


    public List(String rawInput) throws InvalidCommandException {
        Matcher matcher = COMMAND_PATTERN.matcher(rawInput);
        if (matcher.find()) {
            dateFlag = " -d".equals(matcher.group("dateFlag"));
            priorityFlag = " -p".equals(matcher.group("priorityFlag"));
            displayByWeek = " -w".equals(matcher.group("displayByWeek"));
            displayByMonth = " -m".equals(matcher.group("displayByMonth"));
        } else {
            throw new InvalidCommandException();
        }
    }

    @Override
    public CommandResult execute(TaskMap tasks) {
        assert !(dateFlag && priorityFlag);

        // TODO Check flag condition
        if (dateFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByDate());
        } else if (priorityFlag) {
            return new CommandResult(LIST_MESSAGE, tasks.sortListByPriority());
        }
        if (displayByWeek || displayByMonth) {
            if (displayByWeek) {
                displayMode = DisplayMode.WEEK;
            } else {
                displayMode = DisplayMode.MONTH;
            }
            return new CommandResult(LIST_MESSAGE, tasks, displayMode);
        }
        return new CommandResult(LIST_MESSAGE, tasks);
    }
}
