package seedu.duke.command;

import seedu.duke.data.timetable.Reminder;
import seedu.duke.ui.InterfaceManager;

import java.util.ArrayList;

import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_INDEX;

/**
 * Returns all the reminders that should occur today.
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
     * Default constructor of RemindEvent. No arguments are expected as we are only looking at reminders today.
     */
    public RemindCommand() {

    }

    @Override
    public String execute() {
        ArrayList<Reminder> reminders = timetable.getReminders();
        String result = "Reminders:" + InterfaceManager.LS;
        if (reminders.size() == 0) {
            result += "No reminders today!";
        } else {
            boolean first = true;
            for (Reminder reminder : reminders) {
                if (!first) {
                    result += InterfaceManager.LS;
                }
                first = false;
                result += reminder.toString();
            }
        }
        return result;
    }
}
