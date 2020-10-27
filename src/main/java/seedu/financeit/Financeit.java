package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.financetools.FinanceTools;
import seedu.financeit.datatrackers.goaltracker.GoalTracker;
import seedu.financeit.datatrackers.manualtracker.ManualTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.datatrackers.recurringtracker.RecurringTracker;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.LoggerCentre;
import seedu.financeit.utils.storage.GoalTrackerSaver;
import seedu.financeit.utils.storage.ManualTrackerSaver;
import seedu.financeit.utils.storage.RecurringTrackerSaver;
import seedu.financeit.utils.storage.SaveManager;

import java.util.logging.Level;

public class Financeit {

    public static void main(String[] args) {

        String test = "$123.45";
        test = test.replaceAll("[^\\w | .]", "");
        System.out.println(test);


        String input = "";
        CommandPacket packet = null;
        Level mode = Level.OFF;
        LoggerCentre.getInstance().setLevel(mode);
        ManualTrackerSaver mt = new ManualTrackerSaver("./data/save.txt", "./data");
        GoalTrackerSaver gt = new GoalTrackerSaver("./data/save1.txt", "./data");
        RecurringTrackerSaver at = new RecurringTrackerSaver("./data/save2.txt", "./data");
        load(gt, mt, at);
        while (true) {
            UiManager.refreshPage();
            UiManager.printLogo();
            MenuPrinter.printMainMenu();
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
                MenuPrinter.prompt = (mode == Level.OFF)
                        ? "Logger is off."
                        : "Logger is on.";
                LoggerCentre.getInstance().setLevel(mode);
                break;
            case "exit":
                save(gt, mt, at);
                return;
            default:
                MenuPrinter.prompt = "Invalid Command";
                break;
            }
        }
    }

    public static void load(GoalTrackerSaver gt, ManualTrackerSaver mt, RecurringTrackerSaver at) {

        try {
            gt.load();
        } catch (Exception m) {
            System.out.println("Goal Tracker failed to load: " + m);
        }

        try {
            mt.load();
        } catch (Exception m) {
            System.out.println("Manual Tracker failed to load: " + m);
        }

        try {
            at.load();
        } catch (Exception m) {
            System.out.println("Auto Tracker failed to load: " + m);
        }
    }

    public static void save(GoalTrackerSaver gt, ManualTrackerSaver mt, RecurringTrackerSaver at) {

        try {
            gt.save();
        } catch (Exception m) {
            System.out.println("Goal Tracker failed to save: " + m);
        }

        try {
            mt.save();
        } catch (Exception m) {
            System.out.println("Manual Tracker failed to save: " + m);
        }

        try {
            at.save();
        } catch (Exception m) {
            System.out.println("Auto Tracker failed to save: " + m);
        }
    }
}
