package seedu.notus.command;

import seedu.notus.data.timetable.Reminder;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;

//@@author prachi2023
/**
 * Returns all the reminders that should occur today.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Shows the reminders for today.";

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Here are the reminders for today!";
    private static final String COMMAND_UNSUCCESSFUL_MESSAGE = "No reminders today!";


    /**
     * Default constructor of RemindEvent. No arguments are expected as we are only looking at reminders today.
     */
    public RemindCommand() {

    }

    @Override
    public String execute() {
        ArrayList<Reminder> reminders = timetable.getReminders();
        if (reminders.size() == 0) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        } else {
            return Formatter.formatReminders(COMMAND_SUCCESSFUL_MESSAGE, reminders);
        }
    }
}
