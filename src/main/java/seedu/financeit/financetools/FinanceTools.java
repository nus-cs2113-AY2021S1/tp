package seedu.financeit.financetools;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Common;
import seedu.financeit.common.exceptions.FolderNotFoundException;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import seedu.financeit.utils.storage.AccountSaver;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents all features in FinanceTools.
 */
public abstract class FinanceTools {

    public static void main() {
        ArrayList<String> commands = new ArrayList<String>();
        ArrayList<String> infoText = new ArrayList<String>();
        String filePath = "./data/info.txt";
        // Retrieve account data
        try {
            AccountSaver.readFileContents(filePath, infoText);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (FolderNotFoundException e) {
            System.out.println(e.getMessage());
        }

        boolean continueProgram = true;
        String outputAmount;

        UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Welcome to Finance Tools!");

        while (continueProgram) {
            UiManager.printWithStatusIcon(Common.PrintType.DIRECTORY, "[ MAIN_MENU -> FINANCE_TOOLS_MENU ]");
            UiManager.printInputPromptMessage();
            String input = UiManager.handleInput();
            CommandPacket packet = InputParser.getInstance().parseInput(input);
            commands.add(input);
            switch (packet.getCommandString()) {
            case "simple":
                outputAmount = Double.toString(Math.round(Handler.handleSimpleInterest(packet) * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $" + outputAmount);
                commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "cashb":
                outputAmount = Double.toString(Math.round(Handler.handleCashback(packet) * 100.00) / 100.00);
                System.out.println("Total Cashback Earned: $" + outputAmount);
                commands.add("Total Cashback Earned: $" + outputAmount);
                break;
            case "miles":
                outputAmount = Double.toString(Math.round(Handler.handleMilesCredit(packet) * 100.00) / 100.00);
                System.out.println("Total Miles Earned: " + outputAmount);
                commands.add("Total Miles Earned: " + outputAmount);
                break;
            case "cyearly":
                outputAmount = Double.toString(Math.round(Handler.handleYearlyCompoundInterest(packet)
                        * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $" + outputAmount);
                commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "cmonthly":
                outputAmount = Double.toString(Math.round(Handler.handleMonthlyCompoundInterest(packet)
                        * 100.00) / 100.00);
                System.out.println("Total Interest Earned: $" + outputAmount);
                commands.add("Total Interest Earned: $" + outputAmount);
                break;
            case "store":
                Handler.handleAccountStorage(packet, filePath, infoText);
                System.out.println("Information Updated");
                break;
            case "commands":
                Handler.printCommandList();
                break;
            case "history":
                for (int i = 0; i < commands.size(); i++) {
                    System.out.printf("%d) ", i + 1);
                    System.out.println(commands.get(i));
                }
                break;
            case "info":
                if (infoText.size() == 0) {
                    System.out.println("There is no account in the list");
                }
                for (int i = 0; i < infoText.size(); i++) {
                    System.out.printf("====Account No." + "%d" + "====\n", i + 1);
                    System.out.println(infoText.get(i));
                }
                break;
            case "clearinfo":
                infoText.removeAll(infoText);
                try {
                    AccountSaver.updateFile(infoText, filePath);
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("All account(s) cleared in list");
                break;
            case "exit":
                System.out.println("Exiting Finance Tools ...");
                continueProgram = false;
                break;
            default:
                System.out.println("Invalid Command");
                break;
            }
        }
    }
}
