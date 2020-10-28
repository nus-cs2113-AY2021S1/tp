package seedu.financeit.ui;


import seedu.financeit.common.Constants;
import seedu.financeit.utils.ReminderGenerator;

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
     * Prints a list of reminders for recurring entries that are
     * upcoming 5 days from the current date
     */
    public static void printReminders() {
        ArrayList<String> remindersToPrint = ReminderGenerator.generateListOfRemindersAsStrings();
        UiManager.printWithStatusIcon(Constants.PrintType.REMINDER,
                remindersToPrint.toArray(new String[0]));
    }

}
