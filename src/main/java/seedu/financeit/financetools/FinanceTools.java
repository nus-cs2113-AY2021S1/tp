package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class FinanceTools {

    public static double handleSimpleInterest(CommandPacket packet) {
        SimpleInterest tool = null;
        try {
            tool = new SimpleInterest(packet);
            return (tool.calculateSimpleInterest());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }  finally {
            if (tool == null) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static void main() {

        while (true) {
            printMenu();
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input.toLowerCase());
            switch (packet.getCommandString()) {
            case "simplecalc":
                System.out.print("Total Interest Earned: ");
                System.out.println('$' + Double.toString(handleSimpleInterest(packet)));
                break;
            default:
                MenuPrinter.prompt = "Invalid Command";
                break;
            }
        }
    }

    public static void printMenu() {
        TablePrinter.setTitle("Welcome to finance");
        TablePrinter.addRow("No; Finance Tool                  ");
        TablePrinter.addRow("1; Simple Interest Calculator");
        TablePrinter.printList();
    }
}
