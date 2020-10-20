package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

public class FinanceTools {
    public static double handleMonthlyCompoundInterest(CommandPacket packet) {
        MonthlyCompoundInterest tool = new MonthlyCompoundInterest();
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
        String outputAmount;

        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Finance Tools!");

        while (endTracker) {
            UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY, "[ MAIN_MENU -> FINANCE_TOOLS_MENU ]");
            UiManager.printInputPromptMessage();
            String input = UiManager.handleInput();
            CommandPacket packet = new InputParser().parseInput(input.toLowerCase());
            switch (packet.getCommandString()) {
            case "simplecalc":
                System.out.print("Total Interest Earned: $" + handleSimpleInterest(packet));
                break;
            case "cashbackcalc":
                System.out.print("Total Cashback Earned: $" + handleCashback(packet));
                break;
            case "milescalc":
                System.out.print("Total Miles Earned: " + handleMilesCredit(packet));
                break;
            case "compoundcalc":
                System.out.printf("Compound Interval: Yearly\n"
                        + "Total Interest Earned: $" + "%.2f",handleCompoundInterest(packet));
                break;
            case "cmonthly":
                outputAmount = Double.toString(Math.round(handleMonthlyCompoundInterest(packet) * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $\n\n" + outputAmount);
                //commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "commands":
                printCommandList();
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

    public static void printCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No; Finance Tool             ;Input Format                                          ");
        TablePrinter.addRow("1; Simple Interest Calculator; simplecalc /amount {AMOUNT} /ir {INTEREST_RATE} ");
        TablePrinter.addRow("2; Compound Interest Calculator; compoundcalc /amount {AMOUNT} /ir {INTEREST_RATE} "
                + "/period {YEARS}");
        TablePrinter.addRow("3; Cashback Calculator; milescalc /amount {AMOUNT} /miles {MILES RATE}");
        TablePrinter.addRow("4; Miles Credit Calculator; cashbackcalc /amount {AMOUNT} /cashback {CASHBACK_RATE} "
                + "/cap {CASHBACK_CAP}");
        TablePrinter.printList();
    }
}
