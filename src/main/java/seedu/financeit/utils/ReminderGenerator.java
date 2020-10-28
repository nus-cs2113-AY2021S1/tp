package seedu.financeit.utils;

import seedu.financeit.common.Constants;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;

import java.util.ArrayList;
import java.util.HashMap;

public class ReminderGenerator {
    /**
     * Prints a reminder for upcoming recurring entries, within X days from
     * current system date. X is currently set to 5
     */
    public static ArrayList<String> generateListOfRemindersAsStrings() {
        final int REMIND_DAYS_IN_ADVANCE = 5;
        int currentDay = RunHistory.getCurrentDayOfMonth();
        int lastDayOfMonth = RunHistory.getNumOfDaysInCurrentMonth();
        int dayToRemindUntil = currentDay + REMIND_DAYS_IN_ADVANCE;
        boolean isOverflowToNextMonth = false;

        //If REMIND_DAYS_IN_ADVANCE days from now is next month,
        //it is an overflow to start of next month
        if(dayToRemindUntil > lastDayOfMonth) {
            //Day of next month that reminder should cover until
            dayToRemindUntil -= lastDayOfMonth;
            isOverflowToNextMonth = true;
        }

        //All entries that should be put in the reminder based on date
        ArrayList<RecurringEntry> entriesToRemind = filterEntriesToRemind(currentDay,
                lastDayOfMonth, dayToRemindUntil, isOverflowToNextMonth);

        //Strings that are directly printed by UiManager
        ArrayList<String> remindersToPrint = generateRemindersToPrint(
                entriesToRemind, dayToRemindUntil, isOverflowToNextMonth);

        return remindersToPrint;
    }

    static ArrayList<RecurringEntry> filterEntriesToRemind(
            int currentDay, int lastDayOfMonth, int dayToRemindUntil, boolean isOverflowToNextMonth) {

        //Grab a reference to recurring entry list so we can filter
        RecurringEntryList allRecurringEntries = RecurringTracker.getEntries();
        ArrayList<RecurringEntry> entriesToRemind;
        if(isOverflowToNextMonth) {
            //Add entries from current day until end of this month
            entriesToRemind = allRecurringEntries.getEntriesFromDayXtoY(currentDay, lastDayOfMonth);
            //Add entries from start of next month until last day to remind until
            entriesToRemind.addAll(allRecurringEntries.getEntriesFromDayXtoY(1, dayToRemindUntil));
        }
        else {
            //Add entries from this month only
            entriesToRemind = allRecurringEntries.getEntriesFromDayXtoY(currentDay, dayToRemindUntil);
        }
        return entriesToRemind;
    }

    static ArrayList<String> generateRemindersToPrint(
            ArrayList<RecurringEntry> entriesToRemind, int dayToRemindUntil,
            boolean isOverflowToNextMonth) {
        //Strings that should be printed by UiManager
        ArrayList<String> remindersToPrint = new ArrayList<>();
        String untilNextMonth = isOverflowToNextMonth ? "of next month" : "";
        //Top level entry - status message
        remindersToPrint.add("Recurring entries coming up in 5 days' time, from today until "
                + DateTimeHelper.dayAsOrdinal(dayToRemindUntil) + untilNextMonth);

        if(entriesToRemind.size() == 0) {
            remindersToPrint.add("No upcoming entries!");
        }

        for(RecurringEntry entry: entriesToRemind) {
            String reminderString = convertEntryToReminderString(entry);
            remindersToPrint.add(reminderString);
        }

        return remindersToPrint;
    }

    /**
     * Converts a RecurringEntry into a reminder String
     *
     * @param entry RecurringEntry to convert
     * @return A formatted String containing the necessary details to
     *         display as a reminder on the Main Menu
     */
    static String convertEntryToReminderString(RecurringEntry entry) {
        HashMap<String,Object> details = entry.getDetailsForReminder();
        int dayOfEntry = (int) details.get("day");
        Constants.EntryType entryType = (Constants.EntryType) details.get("entryType");
        boolean isAuto = (boolean) details.get("isAuto");
        String description = (String) details.get("description");

        String describeWhetherAuto = isAuto ? "Automatic" : "Manual";

        //Different phrases depending on whether entry is an expenditure or income
        String phraseForEntryType = entryType.equals(Constants.EntryType.EXP) ?
                " payment ": " transfer into account";

        String reminderString = DateTimeHelper.dayAsOrdinal(dayOfEntry) + " | " + describeWhetherAuto
                + phraseForEntryType + " | " + description;

        return reminderString;
    }

    }
