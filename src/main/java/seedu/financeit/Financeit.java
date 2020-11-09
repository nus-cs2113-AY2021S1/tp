package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
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
import seedu.financeit.utils.storage.GoalTrackerSaver;
import seedu.financeit.utils.storage.ManualTrackerSaver;
import seedu.financeit.utils.storage.RecurringTrackerSaver;
import seedu.financeit.utils.storage.SaveHandler;
import seedu.financeit.utils.storage.SaveManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

//@@author Feudalord
public class Financeit {
    static String prompt = "";

    public static void main(String[] args) {
        String input = "";
        CommandPacket packet = null;
        Level mode = Level.WARNING;
        LoggerCentre.getInstance().setLevel(mode);
        LoggerCentre.createLog();
        LoggerCentre.loggerSystemMessages.info("\n\n\nLogging from Load states......\n\n\n");
        //Grabs the System DateTime and stores it. Used for reminders
        RunHistory.setCurrentRunDateTime();

        ManualTrackerSaver.getInstance("./data", "./data/saveMt.txt");
        GoalTrackerSaver.getInstance("./data", "./data/saveGt.txt");
        RecurringTrackerSaver.getInstance("./data", "./data/saveAt.txt");
        load();

        //Loads the dateTime when the program was last ran
        loadLastRunDateTime();

        //Updates last run dateTime to current dateTime
        saveCurrentRunDateTimeAsLastRun();

        UiManager.refreshPage();
        UiManager.printLogo();
        LoggerCentre.loggerSystemMessages.info("\n\n\nLogging from user operations......\n\n\n");
        try {
            while (true) {
                ReminderPrinter.printReminders();    //Print reminder for all upcoming recurring entries
                printMainMenu();
                input = UiManager.handleInput();
                packet = InputParser.getInstance().parseInput(input);
                UiManager.refreshPage();
                switch (packet.getCommandString()) {
                case "manual":
                    ManualTracker.execute();
                    break;
                case "recur":
                    RecurringTracker.execute();
                    break;
                case "goal":
                    GoalTracker.execute();
                    break;
                case "financial":
                    FinanceTools.execute();
                    break;
                case "saver":
                    SaveManager.main();
                    break;
                case "exit":
                    save();
                    UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG,
                            "Exiting the program. Have a nice day!");
                    return;
                default:
                    prompt = "Invalid Command";
                    break;
                }
            }
        } catch (Exception e) {
            LoggerCentre.loggerSystemMessages.info("\n\n\nUnknown error......\n\n\n");
            System.out.println("An unknown error has occurred. Refer to the latest log messages.");
            writeStackTraceToLog(e);
        }
    }

    public static void writeStackTraceToLog(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        LoggerCentre.loggerSystemMessages.severe(sw.toString());
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
        TablePrinter.addRow("[3]; Goals Tracker; goal");
        TablePrinter.addRow("[4]; Financial Calculator; financial");
        TablePrinter.addRow("[5]; Save Manager; saver");
        TablePrinter.addRow("[6]; Quit The Program; exit");
        TablePrinter.printList();
    }

    public static void load() {
        GoalTrackerSaver.getInstance().load();
        ManualTrackerSaver.getInstance().load();
        RecurringTrackerSaver.getInstance().load();
    }

    public static void save() {
        GoalTrackerSaver.getInstance().save();
        ManualTrackerSaver.getInstance().save();
        RecurringTrackerSaver.getInstance().save();
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
