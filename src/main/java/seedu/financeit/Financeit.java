package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.manualtracker.ManualTracker;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.financetools.FinanceTools;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.ReminderPrinter;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.LoggerCentre;
import seedu.financeit.utils.RunHistory;
import seedu.financeit.utils.storage.AutoTrackerSaver;
import seedu.financeit.utils.storage.GoalTrackerSaver;
import seedu.financeit.utils.storage.ManualTrackerSaver;
import seedu.financeit.utils.storage.SaveManager;
import seedu.financeit.utils.storage.SaveHandler;

import java.util.logging.Level;

//@@author Feudalord
public class Financeit {
    static String prompt = "";

    public static void main(String[] args) {
        String input = "";
        CommandPacket packet = null;
        Level mode = Level.OFF;
        LoggerCentre.getInstance().setLevel(mode);
        RunHistory.setCurrentRunDateTime();    //Grabs the System DateTime and stores it. Used for reminders
        ManualTrackerSaver.getInstance("./data", "./data/saveMt.txt");
        GoalTrackerSaver.getInstance("./data", "./data/saveGt.txt");
        AutoTrackerSaver.getInstance("./data", "./data/saveAt.txt");
        load();
        loadLastRunDateTime();                 //Loads the dateTime when the program was last ran
        saveCurrentRunDateTimeAsLastRun();     //Updates last run dateTime to current dateTime
        while (true) {
            UiManager.refreshPage();
            UiManager.printLogo();
            ReminderPrinter.printReminders();    //Print reminder for all upcoming recurring entries
            printMainMenu();
            input = UiManager.handleInput();
            packet = InputParser.getInstance().parseInput(input);
            UiManager.refreshPage();
            switch (packet.getCommandString()) {
            case "manual":
                ManualTracker.main();
                break;
            case "recur":
                RecurringTracker.main();
                break;
            case "acc": //AccSummary.main();
                break;
            case "goal": //GoalTracker.main();
                GoalTracker.main();
                break;
            case "financial": //FinancialCalculator.main();
                FinanceTools.main();
                break;
            case "saver":
                SaveManager.main();
                break;
            case "logger":
                mode = (mode == Level.OFF) ? Level.ALL : Level.OFF;
                prompt = (mode == Level.OFF)
                        ? "Logger is off."
                        : "Logger is on.";
                LoggerCentre.getInstance().setLevel(mode);
                LoggerCentre.saveLog();
                break;
            case "exit":
                save();
                return;
            default:
                prompt = "Invalid Command";
                break;
            }
        }
    }

    public static void status() {
        System.out.println("Status: " + prompt);
        prompt = "";
    }

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

    public static void load() {

        try {
            GoalTrackerSaver.getInstance().load();
        } catch (Exception m) {
            System.out.println("Goal Tracker failed to load: " + m);
        }

        try {
            ManualTrackerSaver.getInstance().load();
        } catch (Exception m) {
            System.out.println("Manual Tracker failed to load: " + m);
        }

        try {
            AutoTrackerSaver.getInstance().load();
        } catch (Exception m) {
            System.out.println("Auto Tracker failed to load: " + m);
        }
    }

    public static void save() {

        try {
            GoalTrackerSaver.getInstance().save();
        } catch (Exception m) {
            System.out.println("Goal Tracker failed to save: " + m);
        }

        try {
            ManualTrackerSaver.getInstance().save();
        } catch (Exception m) {
            System.out.println("Manual Tracker failed to save: " + m);
        }

        try {
            AutoTrackerSaver.getInstance().save();
        } catch (Exception m) {
            System.out.println("Auto Tracker failed to save: " + m);
        }
    }

    public static void loadLastRunDateTime() {
        try {
            String lastRunDateTime = SaveHandler.takeString("LastRunDateTime");
            RunHistory.setLastRunDateTime(lastRunDateTime);
        } catch (Exception m) {
            System.out.println("Failed to load last run time: " + m);
        }
    }

    public static void saveCurrentRunDateTimeAsLastRun() {
        try {
            String currentDateTime = RunHistory.getCurrentRunDateTime().toString();
            SaveHandler.putString(currentDateTime, "LastRunDateTime");
        } catch (Exception m) {
            System.out.println("Failed to save current run time: " + m);
        }
    }
}
