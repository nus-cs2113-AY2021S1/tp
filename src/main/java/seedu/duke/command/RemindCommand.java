package seedu.duke.command;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;

/**
 * Sets the reminder status of an Event.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind-e";

    private static final String COMMAND_USAGE = COMMAND_WORD + ": Set a reminder for an event. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX";

    /**
     * Gets how the command is expected to be used.
     *
     * @return String representation of how the command is to be used.
     */
    public static String getCommandUsage() {
        return COMMAND_USAGE;
    }

    private int index;
    private boolean isToRemind;

    /**
     * Constructs a RemindCommand to set the reminder status of an Event.
     *
     * @param index of the Event.
     * @param isToRemind the reminder status of the Event.
     */
    public RemindCommand(int index, boolean isToRemind) {
        this.index = index;
        this.isToRemind = isToRemind;
    }

    @Override
    public String execute() {
        return null;
    }
}
