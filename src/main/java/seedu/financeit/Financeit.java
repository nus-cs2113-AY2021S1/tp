package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.RegexMatcher;
import seedu.financeit.utils.SaveManager;

public class Financeit {

    public static void main(String[] args) {
        System.out.println(RegexMatcher.alphabetMatcher("abc").find());
        while (true) {
            MenuPrinter.printMainMenu();
            try {
                SaveManager.load();
            } catch (Exception m) {
                MenuPrinter.prompt = "An exception has occurred: " + m;
            }
            CommandPacket input = UiManager.handleInput();
            UiManager.refreshPage();
            switch (input.getCommandString()) {
                case "manual":
                    ManualTracker.main();
                    break;
                case "auto": //AutoTracker.main();
                    break;
                case "acc": //AccSummary.main();
                    break;
                case "goal": //GoalTracker.main();
                    break;
                case "financial": //FinancialCalculator.main();
                    break;
                case "exit":
                    try {
                        SaveManager.save();
                    } catch (Exception m) {
                        System.out.println("An exception has occurred: " + m);
                    }
                    return;
                default:
                    MenuPrinter.prompt = "Invalid Command";
                    break;

            }
        }
    }
}

// This prints a table in case we need it
