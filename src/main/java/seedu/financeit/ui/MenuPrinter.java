package seedu.financeit.ui;

import seedu.financeit.common.Constants;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntry;
import seedu.financeit.datatrackers.recurringtracker.RecurringEntryList;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.utils.DateTimeHelper;
import seedu.financeit.utils.RunHistory;

import java.util.ArrayList;
import java.util.HashMap;


public class MenuPrinter {
    public static String prompt = "";

    public static void printMainMenu() {
        status();
        TablePrinter.setTitle("Welcome to Main Menu");
        TablePrinter.addRow("No.; Feature                                           ; Commands                    ");
        TablePrinter.addRow("[1]; Manual Income/Expense Tracker; manual");
        TablePrinter.addRow("[2]; Recurring Income/Expense Tracker; recur");
        TablePrinter.addRow("[3]; Account Summary; acc");
        TablePrinter.addRow("[4]; Goals Tracker; goal");
        TablePrinter.addRow("[5]; Financial Calculator; financial");
        TablePrinter.addRow("[6]; Save Manager; saver");
        TablePrinter.addRow("[7]; Toggle Log On or Off; logger");
        TablePrinter.addRow("[8]; Quit The Program; exit");
        TablePrinter.printList();
    }

    public static void status() {
        System.out.println("Status: " + prompt);
        prompt = "";
    }

    /**
     * Prints a reminder for upcoming recurring entries, within X days from
     * current system date. X is currently set to 5
     */
    public static void printReminder() {
        final int REMIND_DAYS_IN_ADVANCE = 5;
        int currentDay = RunHistory.getCurrentDayOfMonth();
        int dayToRemindUntil = currentDay + REMIND_DAYS_IN_ADVANCE;
        int numOfDaysInCurrentMonth = RunHistory.getNumOfDaysInCurrentMonth();
        String untilNextMonth = "";
        boolean isOverflowToNextMonth = false;

        //Overflow to start of next month
        if(dayToRemindUntil > numOfDaysInCurrentMonth) {
            dayToRemindUntil -= numOfDaysInCurrentMonth;
            untilNextMonth = " of next month";
            isOverflowToNextMonth = true;
        }

        //Grab a reference to recurring entry list so we can filter
        RecurringEntryList allRecurringEntries = RecurringTracker.getEntries();

        //All entries that should be put in the reminder based on date
        ArrayList<RecurringEntry> entriesToRemind = new ArrayList<>();
        if(isOverflowToNextMonth) {
            //Entries for end of this month
            entriesToRemind = allRecurringEntries.getEntriesFromDayXtoY(currentDay, numOfDaysInCurrentMonth);
            //Entries for start of next month
            entriesToRemind.addAll(allRecurringEntries.getEntriesFromDayXtoY(1, dayToRemindUntil));
        }
        else {
            //Only this month
            entriesToRemind = allRecurringEntries.getEntriesFromDayXtoY(currentDay, dayToRemindUntil);
        }
        //Strings that should be printed by UiManager
        ArrayList<String> remindersToPrint = new ArrayList<>();

        //Top level entry - status message
        remindersToPrint.add("Recurring entries coming up in 5 days' time, from today until "
                + DateTimeHelper.dayAsOrdinal(dayToRemindUntil) + untilNextMonth);

        if(entriesToRemind.size() == 0) {
            remindersToPrint.add("No upcoming entries!");
        }

        //Convert entries that should be reminded into a reminder string for printing
        for(RecurringEntry entry: entriesToRemind) {
            HashMap<String,Object> details = entry.getDetailsForReminder();
            int dayOfEntry = (int) details.get("day");
            Constants.EntryType entryType = (Constants.EntryType) details.get("entryType");
            boolean isAuto = (boolean) details.get("isAuto");
            String description = (String) details.get("description");

            String describeWhetherAuto = isAuto ? "Automatic" : "Manual";

            //Different phrases depending on whether entry is an expenditure or income
            String phraseForEntryType = entryType.equals(Constants.EntryType.EXP) ?
                    " payment ": " transfer into account";

            remindersToPrint.add(DateTimeHelper.dayAsOrdinal(dayOfEntry) + " | " + describeWhetherAuto
                    + phraseForEntryType + " | " + description);
        }

        UiManager.printWithStatusIcon(Constants.PrintType.REMINDER,
                remindersToPrint.toArray(new String[0]));
    }
}
