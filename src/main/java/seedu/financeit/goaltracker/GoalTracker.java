package seedu.financeit.goaltracker;

import seedu.financeit.common.CommandPacket;
import seedu.financeit.common.Goal;
import seedu.financeit.common.Constants;
import seedu.financeit.goaltracker.TotalGoalList;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.manualtracker.LedgerList;


import java.util.Scanner;


public class GoalTracker {
    private static CommandPacket cmdPacket;
    private static Goal goalSetter;
    private static Scanner scanner = new Scanner(System.in);
    private static int expenseGoal = 0;
    private static int incomeGoal = 0;
    private static String input;
    private static boolean backToMenu = false;
    private static boolean exitTracker = false;
    private static TotalGoalList totalGoalList = new TotalGoalList();
    private static Entry entryData;
    private static String entryCategory;
    private static double totalExpenses = 0.0;
    private static double expenses = 0.0;
    private static double incomes = 0.0;
    private static double totalIncomes = 0.0;


    public static void main() {
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Goals Tracker");
        System.out.println("[1]. Set goals for Expenses");
        System.out.println("[2]. Set goals for Income");
        System.out.println("[3]. Enter <exit> to leave Goals Tracker");
        UiManager.printWithStatusIcon(Constants.PrintType.INSTRUCTION, "Input either expense/income "
                + "to set goals for it."
        );
        backToMenu = false;
        exitTracker = false;
        input = UiManager.handleInput();
        cmdPacket = new InputParser().parseInput(input.toLowerCase());
        while (!exitTracker) {
            switch (cmdPacket.getCommandString()) {
            case "expense":
                setExpenseGoal();
                break;
            case "income":
                setIncomeGoal();
                break;
            case "exit":
                exitTracker = true;
                break;
            default:
                System.out.println("Invalid Command");
                input = UiManager.handleInput();
                cmdPacket = new InputParser().parseInput(input.toLowerCase());
                break;
            }
        }
    }

    private static void setExpenseGoal() {
        backToMenu = false;
        while (!backToMenu && exitTracker == false) {
            System.out.println("Please enter your goals for your expenses.");
            System.out.println("However if you wish to go back to Main Menu. Please enter <back>");
            if (scanner.hasNextInt()) {
                expenseGoal = scanner.nextInt();
                System.out.println("You have set $" + expenseGoal + " as your Expense Goals");
                goalSetter = new Goal(expenseGoal, "Expense");
                totalGoalList.addGoal(goalSetter);
                backToMenu = true;
            } else if (scanner.hasNext()) {
                input = scanner.next();
                if (input.equals("back")) {
                    backToMenu = true;
                    main();
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Invalid input. Please enter "
                            + "<back> to go Main Menu or enter a number");
                }
            }
        }

        if (backToMenu == true && exitTracker == false) {
            System.out.println("Do you want to exit Expense Goal Tracker? y/n ");
            input = UiManager.handleInput();
            cmdPacket = new InputParser().parseInput(input.toLowerCase());
            if (cmdPacket.getCommandString().equals("y")) {
                main();
            } else {
                setExpenseGoal();
            }
        }
    }

    private static void setIncomeGoal() {
        backToMenu = false;
        while (!backToMenu && exitTracker == false) {
            System.out.println("Please enter your goals for your income");
            System.out.println("However if you wish to go back to Main Menu. Please enter <back>");
            if (scanner.hasNextInt()) {
                incomeGoal = scanner.nextInt();
                System.out.println("You have set $" + incomeGoal + " as your Income Goals");
                goalSetter = new Goal(incomeGoal, "Income");
                totalGoalList.addGoal(goalSetter);
                backToMenu = true;
            } else if (scanner.hasNext()) {
                input = scanner.next();
                if (input.equals("back")) {
                    backToMenu = true;
                    main();
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Invalid input. Please enter "
                            + "<back> to go Main Menu or enter a number");
                }
            }
        }

        if (backToMenu == true && exitTracker == false) {
            System.out.println("Do you want to exit Income Goal Tracker? y/n ");
            input = UiManager.handleInput();
            cmdPacket = cmdPacket = new InputParser().parseInput(input.toLowerCase());
            if (cmdPacket.getCommandString().equals("y")) {
                main();
            } else {
                setIncomeGoal();
            }
        }
    }

    public static void targetGoalTracker(Entry entry) {
        entryData = entry;
        entryCategory = entryData.getEntryType().toString();
        if (entryCategory.equals("Expense")) {
            handleExpenseGoal();
        } else {
            handleIncomeGoal();
        }
    }

    public static void handleExpenseGoal() {
        try {
            LedgerList ledgerList = ManualTracker.getLedgerList();
            int ledgerSize = ledgerList.getItemsSize();
            for (int i = ledgerSize - 1; i < ledgerSize; i++) {
                Ledger ledger = (Ledger) ledgerList.getItemFromIndex(i);
                EntryList entryList = ledger.entryList;
                int entrySize = entryList.getItemsSize();
                for (int x = entrySize - 1; x < entrySize; x++) {
                    expenses = entryData.getAmount();
                    totalExpenses += expenses;
                }
            }
            printExpenseGoal();
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for expense.");
        }
    }

    public static void printExpenseGoal() {
        expenseGoal = totalGoalList.getGoal().get(0).getExpenseGoal();
        double goalDifference = expenseGoal - totalExpenses;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                    + " You have spent $" + totalExpenses + " / $" + expenseGoal + ". You have exceeded your "
                    + "expense budget.");
        } else {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                    + "You have spent $" + totalExpenses + " / $" + expenseGoal + " You have not reached your "
                    + "expense budget." + " You still have $" + goalDifference + " to spend.");
        }
    }

    public static void handleIncomeGoal() {
        try {
            LedgerList ledgerList = ManualTracker.getLedgerList();
            int ledgerSize = ledgerList.getItemsSize();
            for (int i = ledgerSize - 1; i < ledgerSize; i++) {
                Ledger ledger = (Ledger) ledgerList.getItemFromIndex(i);
                EntryList entryList = ledger.entryList;
                int entrySize = entryList.getItemsSize();
                for (int x = entrySize - 1; x < entrySize; x++) {
                    incomes = entryData.getAmount();
                    totalIncomes += incomes;
                }
            }
            printIncomeGoal();
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for income.");
        }
    }

    public static void printIncomeGoal() {
        incomeGoal = totalGoalList.getGoal().get(0).getIncomeGoal();
        double goalDifference = incomeGoal - totalIncomes;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                    + " You have saved $" + totalIncomes + " / $" + incomeGoal + ". You have met your "
                    + "revenue goal.");
        } else {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                    + "You have saved $" + totalIncomes + " / $" + incomeGoal + " You have not met your "
                    + "revenue goal." + " You are $" + goalDifference + " away from your goal.");
        }
    }
}
