package seedu.duke.command;

/**
 * Sets the reminder status of an Event.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Set a reminder for an event. Parameters: ";

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
