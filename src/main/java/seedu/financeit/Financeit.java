package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.RegexMatcher;

public class Financeit {
    public static void main(String[] args) {
        System.out.println(RegexMatcher.alphabetMatcher("abc").find());
        while (true) {
            MenuPrinter.printMainMenu();
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
                return;
            default:
                MenuPrinter.prompt = "Invalid Command";
                break;
            }
        }
    }
}

// This prints a table in case we need it
