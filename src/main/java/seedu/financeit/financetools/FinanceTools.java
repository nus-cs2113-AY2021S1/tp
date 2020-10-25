package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Constants;
import seedu.financeit.common.exceptions.FolderNotFoundException;
import seedu.financeit.common.exceptions.InfoTextIndexOutOfRangeException;
import seedu.financeit.common.exceptions.InsufficientParamsException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FinanceTools {
    public static void handleStorage(CommandPacket packet, String filePath, ArrayList<String> infoText) {
        Storage tool = new Storage();
        tool.setRequiredParams();

        try {
            tool.handlePacket(packet);
            tool.handleInfoStorage(filePath, infoText);
        } catch (AssertionError error) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    "Input failed due to param error.");
        } catch (InsufficientParamsException exception) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE,
                    exception.getMessage());
        } catch (InfoTextIndexOutOfRangeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double handleMonthlyCompoundInterest(CommandPacket packet) {
        MonthlyCompoundInterest tool = new MonthlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
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

    public static double handleYearlyCompoundInterest(CommandPacket packet) {
        YearlyCompoundInterest tool = new YearlyCompoundInterest();
        tool.setRequiredParams(
                "/a",
                "/r",
                "/p"
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
            "/a",
            "/r"
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
            "/a",
            "/r",
            "/c"
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
            "/a",
            "/r"
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
        ArrayList<String> commands = new ArrayList<String>();
        ArrayList<String> infoText = new ArrayList<String>();
        String filePath = "./data/info.txt";
        try {
            FileStorage.readFileContents(filePath, infoText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (FolderNotFoundException e) {
            System.out.println(e.getMessage());
        }

        boolean endTracker = true;
        String outputAmount;

        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Finance Tools!");

        while (endTracker) {
            UiManager.printWithStatusIcon(Constants.PrintType.DIRECTORY, "[ MAIN_MENU -> FINANCE_TOOLS_MENU ]");
            UiManager.printInputPromptMessage();
            String input = UiManager.handleInput();
            CommandPacket packet = InputParser.getInstance().parseInput(input);
            switch (packet.getCommandString()) {
            case "simple":
                outputAmount = Double.toString(Math.round(handleSimpleInterest(packet) * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $\n\n" + outputAmount);
                break;
            case "cashb":
                outputAmount = Double.toString(Math.round(handleCashback(packet) * 100.00) / 100.00);
                System.out.println("Total Cashback Earned: $\n\n" + outputAmount);
                break;
            case "miles":
                outputAmount = Double.toString(Math.round(handleMilesCredit(packet) * 100.00) / 100.00);
                System.out.println("Total Miles Earned: \n\n" + outputAmount);
                break;
            case "cyearly":
                outputAmount = Double.toString(Math.round(handleYearlyCompoundInterest(packet) * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $\n\n" + outputAmount);
                //commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "cmonthly":
                outputAmount = Double.toString(Math.round(handleMonthlyCompoundInterest(packet) * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $\n\n" + outputAmount);
                //commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "store":
                handleStorage(packet, filePath, infoText);
                System.out.println("Information Updated\n\n");
                break;
            case "commands":
                printCommandList();
                break;
            case "info":
                if (infoText.size() == 0) {
                    System.out.println("There is no account in the list");
                }
                for (int i = 0; i < infoText.size(); i++) {
                    System.out.printf("====Account No." + "%d", i + 1);
                    System.out.println("====");
                    System.out.println(infoText.get(i));
                }
                break;
            case "clearinfo":
                infoText.removeAll(infoText);
                System.out.println("All account(s) cleared in list");
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
