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

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Shows the reminders for today.";

    /**
     * Default constructor of RemindEvent. No arguments are expected as we are only looking at reminders today.
     */
    public RemindCommand() {

    }

    @Override
    public String execute() {
        ArrayList<Reminder> reminders = timetable.getReminders();
        StringBuilder result = new StringBuilder("Reminders:" + InterfaceManager.LS);
        if (reminders.size() == 0) {
            result.append("No reminders today!");
        }
        String lineSep = "";
        for (Reminder reminder : reminders) {
            result.append(lineSep).append(reminder.toString());
            lineSep = InterfaceManager.LS;
        }
        return result.toString();
    }
}
