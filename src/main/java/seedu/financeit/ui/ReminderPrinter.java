package seedu.financeit.ui;

import seedu.financeit.common.Constants;
import seedu.financeit.utils.ReminderGenerator;

import java.util.ArrayList;

public class ReminderPrinter {
    /**
     * Prints a list of reminders for recurring entries that are
     * upcoming 5 days from the current date
     */
    public static void printReminders() {
        ArrayList<String> remindersToPrint = ReminderGenerator.generateListOfRemindersAsStrings();
        UiManager.printWithStatusIcon(Constants.PrintType.REMINDER,
                remindersToPrint.toArray(new String[0]));
    }

}
