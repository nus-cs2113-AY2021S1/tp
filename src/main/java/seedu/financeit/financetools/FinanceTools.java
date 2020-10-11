package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.ui.MenuPrinter;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class FinanceTools {

    public static double handleSimpleInterest(CommandPacket packet) {
        try {
            SimpleInterest tool = new SimpleInterest(packet);
            return (tool.calculateSimpleInterest());
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    public static void main() {

        while (true) {
            printMenu();
            CommandPacket packet = UiManager.handleInput();
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
