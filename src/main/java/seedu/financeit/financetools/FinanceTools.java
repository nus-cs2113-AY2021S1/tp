package seedu.financeit.financetools;

import seedu.financeit.Financeit;
import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.ParamHandler;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class FinanceTools {

    public static double handleCompoundInterest(CommandPacket packet) {
        CompoundInterest tool = new CompoundInterest();
        tool.setRequiredParams(
                "/amount",
                "/ir",
                "/period"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateCompoundInterest());
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }
        return 0;
    }

    public static double handleMilesCredit(CommandPacket packet) {
        MilesCredit tool = new MilesCredit();
        tool.setRequiredParams(
            "/amount",
            "/miles"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateMiles());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static double handleCashback(CommandPacket packet) {
        Cashback tool = new Cashback();
        tool.setRequiredParams(
            "/amount",
            "/cap",
            "/cashback"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateCashback());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static double handleSimpleInterest(CommandPacket packet) {
        SimpleInterest tool = new SimpleInterest();
        tool.setRequiredParams(
            "/amount",
            "/ir"
        );
        try {
            tool.handlePacket(packet);
            return (tool.calculateSimpleInterest());
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        }  finally {
            if (!tool.getHasParsedAllRequiredParams()) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
            }
        }
        return 0;
    }

    public static void main() {

        boolean endTracker = true;

        while (endTracker) {
            printMenu();
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input.toLowerCase());
            switch (packet.getCommandString()) {
            case "simplecalc":
                System.out.print("Total Interest Earned: ");
                System.out.println('$' + Double.toString(handleSimpleInterest(packet)));
                break;
            case "cashbackcalc":
                System.out.print("Total Cashback Earned: ");
                System.out.println('$' + Double.toString(handleCashback(packet)));
                break;
            case "milescalc":
                System.out.print("Total Miles Earned: ");
                System.out.println('$' + Double.toString(handleMilesCredit(packet)));
                break;
            case "compoundcalc":
                System.out.println("Compound Interval: Yearly");
                System.out.print("Total Interest Earned: $");
                System.out.printf("%.2f", handleCompoundInterest(packet));
                System.out.println();
                break;
            case "exit":
                System.out.println("Exiting Finance Tools ...");
                endTracker = false;
                break;
            default:
                System.out.println("Invalid Command");
                break;
            }
        }
    }

    public static void printMenu() {
        TablePrinter.setTitle("Finance Tools");
        TablePrinter.addRow("No; Finance Tool                  ");
        TablePrinter.addRow("1; Simple Interest Calculator");
        TablePrinter.addRow("2; Compound Interest Calculator");
        TablePrinter.addRow("3; Cashback Calculator");
        TablePrinter.addRow("4; Miles Credit Calculator");
        TablePrinter.printList();
    }
}
