package seedu.financeit.ui;

import seedu.financeit.common.Constants;
import seedu.financeit.recurringtracker.RecurringEntry;
import seedu.financeit.recurringtracker.RecurringEntryList;
import seedu.financeit.recurringtracker.RecurringTracker;
import seedu.financeit.utils.DateTimeHelper;
import seedu.financeit.utils.RunHistory;

import java.util.ArrayList;


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

        //Grab a reference to recurring entry list so we can filter
        RecurringEntryList allRecurringEntries = RecurringTracker.getEntries();
        //All entries that should be put in the reminder based on date
        ArrayList<RecurringEntry> entriesToRemind = allRecurringEntries.getEntriesFromDayXtoY(currentDay, dayToRemindUntil);
        //Strings that should be printed by UiManager
        ArrayList<String> remindersToPrint = new ArrayList<>();

        remindersToPrint.add("Recurring entries coming up in 5 days' time, from today until "
                + DateTimeHelper.dayAsOrdinal(dayToRemindUntil));
        for(RecurringEntry entry: entriesToRemind) {
            int dayOfEntry = entry.getDay();
            Constants.EntryType entryType = entry.getEntryType();

            //Depending on whether entry is an expenditure or income
            String phraseForEntryType = entryType.equals(Constants.EntryType.EXP) ?
                    " payment on ": " transfer into account on ";
            String describeWhetherAuto = entry.getAuto() ? "Automatic" : "Manual";

            remindersToPrint.add(DateTimeHelper.dayAsOrdinal(dayOfEntry) + " | " + entry.getDescription()
                    + " -- " + describeWhetherAuto + phraseForEntryType);
        }

        UiManager.printWithStatusIcon(Constants.PrintType.REMINDER,
                remindersToPrint.toArray(new String[0]));
    }
}
