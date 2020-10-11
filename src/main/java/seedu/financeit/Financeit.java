package seedu.financeit;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.financetools.FinanceTools;
import seedu.financeit.goaltracker.GoalTracker;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.RegexMatcher;
import seedu.financeit.utils.SaveManager;

public class Financeit {

    public static void main(String[] args) {
        System.out.println(RegexMatcher.alphabetMatcher("abc").find());
        while (true) {
            try {
                SaveManager.load();
            } catch (Exception m) {
                MenuPrinter.prompt = "An exception has occurred: " + m;
            }
            MenuPrinter.printMainMenu();
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input);
            UiManager.refreshPage();
            switch (packet.getCommandString()) {
            case "manual":
                ManualTracker.main();
                break;
            case "auto": //AutoTracker.main();
                break;
            case "acc": //AccSummary.main();
                break;
            case "goal": //GoalTracker.main();
                GoalTracker.main();
                break;
            case "financial": //FinancialCalculator.main();
                FinanceTools.main();
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
