package seedu.financeit.datatrackers.goaltracker;

import seedu.financeit.common.Common;
import seedu.financeit.data.Goal;
import seedu.financeit.datatrackers.manualtracker.Ledger;
import seedu.financeit.datatrackers.manualtracker.LedgerList;
import seedu.financeit.datatrackers.manualtracker.ManualTracker;
import seedu.financeit.datatrackers.entrytracker.Entry;
import seedu.financeit.datatrackers.entrytracker.EntryList;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.TablePrinter;
import seedu.financeit.ui.UiManager;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

/**
 * This is the class that will handle the setting of goals
 * for expense and income for each and every individual months.
 */

public class GoalTracker {
    private static String[] cmdPacket = null;
    private static String[] editPacket;
    private static Goal goalToSet;
    private static Scanner scanner = new Scanner(System.in);
    private static int expenseGoal = 0;
    private static int incomeGoal = 0;
    private static String input;
    private static boolean exitTracker = false;
    private static TotalGoalList totalGoalList = new TotalGoalList();
    private static Entry entryData;
    private static String entryCategory;
    private static double totalExpenses = 0.0;
    private static double expenses = 0.0;
    private static double incomes = 0.0;
    private static double totalIncomes = 0.0;
    private static Month month;
    private static Month ledgerMonth;
    private static boolean isExist = false;
    private static boolean isEnd = false;

    /**
     * This main() function will handle the user input and based on the input will call out
     * to another method/function.
     */

    //@@author dixoncwc
    public static void execute() {
        exitTracker = false;
        while (!exitTracker) {
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Welcome to Goals Tracker");
            displayCommandList();
            input = UiManager.handleInput();
            cmdPacket = InputParser.getInstance().parseGoalCommand(input.toLowerCase());
            editPacket = InputParser.getInstance().parseEditCommand(input.toLowerCase());
            switch (cmdPacket[0]) {
            case "expense":
                setExpenseGoal(cmdPacket);
                break;
            case "income":
                setIncomeGoal(cmdPacket);
                break;
            case "display":
                handleDisplayGoal();
                break;
            case "edit":
                editGoal(editPacket);
                break;
            case "exit":
                exitTracker = true;
                break;
            default:
                System.out.println("Invalid Command");
                cmdPacket = InputParser.getInstance().parseGoalCommand(input.toLowerCase());
                break;
            }
        }
    }

    public static void displayCommandList() {
        TablePrinter.setTitle("List of Commands");
        TablePrinter.addRow("No.;Command            ;Input Format                                                ");
        TablePrinter.addRow("1.;Expense Goal;expense {amount} for {Month in number form (e.g 01 = Jan)}");
        TablePrinter.addRow("2.;Income Goal;income {amount} for {Month in number form (e.g 01 = Jan)}");
        TablePrinter.addRow("3.;Display Expense/Income goal for individual month;display expense/income for "
                + "{Month in number form (e.g 01 = Jan)}");
        TablePrinter.addRow("4.;Edit Expense/Income Goal;edit expense/income {amount} for "
                + "{Month in number form (e.g 01 = Jan)}");
        TablePrinter.addRow("5.;Exit Goals Tracker;{exit}");
        TablePrinter.printList();

    }

    /**
     * This function will parse the command to check whether it is for income.
     * or expense goal
     */
    public static void editGoal(String[] userInput) {
        isExist = false;
        try {
            month = Month.of(Integer.parseInt(userInput[4]));
            if (userInput[1].equals("expense")) {
                editExpenseGoal(userInput);
            } else if (userInput[1].equals("income")) {
                editIncomeGoal(userInput);
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter either expense "
                    + "or income");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please check that you have "
                    + "entered a correct int amount or int month.");
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month");
        }
    }

    /**
     * This function will edit the user's expense goal for specific months.
     *
     */
    public static void editExpenseGoal(String[] userInput) {
        expenseGoal = Integer.parseInt(userInput[2]);
        for (int i = 0; i < totalGoalList.getListSize(); i++) {
            try {
                if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                    isExist = true;
                    goalToSet = new Goal(expenseGoal, "Expense", month);
                    totalGoalList.getGoal().set(i, goalToSet);
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You have successfully"
                            + " edited your expense goal for " + month + " to $" + expenseGoal);

                }

            } catch (NullPointerException e) { // This NullPointerException occurs when the first object in the
                continue;                      // arraylist is not expense so it will continue the for loop.
            }
        }
        if (!isExist) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not have "
                    + "any existing goal for " + month);
        }
    }

    /**
     * This function will edit the user's income goal for specific months.
     *
     */
    public static void editIncomeGoal(String[] userInput) {
        incomeGoal = Integer.parseInt(userInput[2]);
        for (int i = 0; i < totalGoalList.getListSize(); i++) {
            try {
                if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                    isExist = true;
                    goalToSet = new Goal(incomeGoal, "Income", month);
                    totalGoalList.getGoal().set(i, goalToSet);
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You have successfully"
                            + " edited your income goal for " + month + " to $" + incomeGoal);
                    execute();
                }
            } catch (NullPointerException e) {
                continue;
            }
        }
        if (!isExist) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not have "
                    + "any existing goal for " + month);
        }
    }

    public static TotalGoalList getTotalGoalList() {
        return totalGoalList;
    }

    //@@author Feudalord
    /**
     * This function is for save and load of the expense and income goals
     * for respective months.
     *
     * @param amount   the amount of income/expense goal
     * @param category Type of goal
     * @param mon      month of goal
     */
    public static void setGoals(String amount, String category, String mon) {
        try {
            month = Month.valueOf(mon);
            expenseGoal = Integer.parseInt(amount);
            goalToSet = new Goal(expenseGoal, category, month);
            totalGoalList.addGoal(goalToSet);
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid month");
        }
    }

    //@@author dixoncwc
    /**
     * This function will handle and parser the user command to differentiate whether
     * user want to display for expense for income.
     */
    private static void handleDisplayGoal() {
        try {
            if (cmdPacket[1].equals("expense")) {
                ledgerMonth = null;
                handleCurrentExpenses();
            } else if (cmdPacket[1].equals("income")) {
                ledgerMonth = null;
                handleCurrentIncomes();
            } else {
                throw new IllegalArgumentException("Please enter only expense or income");
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter either expense "
                    + "or income");
        } catch (IllegalArgumentException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * This function will handle the setting of expense goal
     * for the indicated months.
     */
    public static void setExpenseGoal(String[] userInput) {
        try {
            isExist = false;
            month = Month.of(Integer.parseInt(userInput[3]));
            expenseGoal = Integer.parseInt(userInput[1]);
            if (expenseGoal < 0) {
                throw new IllegalArgumentException("Please enter only Positive Numbers");
            } else {
                for (int i = 0; i < totalGoalList.getListSize(); i++) {
                    try {
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                            isExist = true;
                            break;
                        }
                    } catch (NullPointerException e) {
                        continue;
                    }
                }
                if (isExist == true) {
                    UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You have an existing "
                            + "expense goal for " + month);
                } else {
                    goalToSet = new Goal(expenseGoal, "Expense", month);
                    totalGoalList.addGoal(goalToSet);
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You have set $" + expenseGoal
                            + " as your Expense Goals for " + month);
                }
            }
            month = null;
        } catch (DateTimeException e) { // This exception occurs if they did not enter correct format for the month
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid month");
        } catch (NumberFormatException e) { // This exception occurs when there is invalid input
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid amount/date");
        } catch (IndexOutOfBoundsException e) { // This exception occurs when the userInput is missing params
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You are missing params");
        } catch (IllegalArgumentException e) {
            expenseGoal = 0;
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, e.getMessage());
        }

    }

    /**
     * This function will handle the setting of income
     * goals for indicated month.
     */
    public static void setIncomeGoal(String[] userInput) {
        try {
            isExist = false;
            month = Month.of(Integer.parseInt(userInput[3]));
            incomeGoal = Integer.parseInt(userInput[1]);
            if (incomeGoal < 0) {
                throw new IllegalArgumentException("Please enter only Positive Numbers");
            } else {
                for (int i = 0; i < totalGoalList.getListSize(); i++) {
                    try {
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                            isExist = true;
                            break;
                        }
                    } catch (NullPointerException e) {
                        continue;
                    }
                }
                if (isExist == true) {
                    UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You have an existing "
                            + "income goal for " + month);
                } else {
                    goalToSet = new Goal(incomeGoal, "Income", month);
                    totalGoalList.addGoal(goalToSet);
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You have set $" + incomeGoal
                            + " as your Income Goals for " + month);
                }
            }
            month = null;
        } catch (DateTimeException e) { // This exception occurs if they did not enter correct format for the month
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid month");
        } catch (NumberFormatException e) { // This exception occurs when there is invalid input
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid amount/date");
        } catch (IndexOutOfBoundsException e) { // This exception occurs when the command is missing params
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You are missing params");
        } catch (IllegalArgumentException e) {
            incomeGoal = 0;
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * This function will be called in EntryTracker class when there is a
     * new entry being stored into a ledger. The purpose is to parse the
     * entry to categories whether it is expense or income.
     *
     * @param entry entry that user enter into ledger
     * @class EntryTracker is the class that handle ledger
     */
    public static void targetGoalTracker(Entry entry) {
        entryData = entry;
        entryCategory = entryData.getEntryType().toString();
        if (entryCategory.equals("Expense")) {
            handleNewExpenses();
        } else {
            handleNewIncomes();
        }
    }

    /**
     * This function is called when a ledger is being created
     * or opened by date.
     * The purpose is to store the ledgerMonth which the entry is being added to
     *
     * @param ledger ledger that is sent from manual tracker
     * @class ManualTracker is the class that handle ledger
     */
    public static void storeLedgerDate(Ledger ledger) {
        ledgerMonth = ledger.getDate().getMonth();
    }

    /**
     * This function will handle the total current
     * expenses being made by the user for the month.
     */
    public static void handleCurrentExpenses() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalExpenses = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtCurrIndex(i);
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            if (ledger.getDate().getMonth().equals(month)) {
                EntryList entryList = ledger.entryList;
                int entrySize = entryList.getItemsSize();
                for (int x = 0; x < entrySize; x++) {
                    Entry entry = (Entry) entryList.getItemAtCurrIndex(x);
                    if (entry.getEntryType().toString().equals("Expense")) {
                        expenses = entry.getAmount();
                        totalExpenses += expenses;
                    } else {
                        totalExpenses += 0;
                    }
                }
            }
        }
        handleCurrentExpenseGoalStatus();
    }

    /**
     * This function will handle the current expense
     * goal based on the month that user want to display.
     * Lastly calling the function to print out the current
     * expense status.
     */
    public static void handleCurrentExpenseGoalStatus() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                try {
                    if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                        expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                        break;
                    } else {
                        expenseGoal = 0;
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }
            if (expenseGoal == 0) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a expense "
                        + "goal for " + month);
            }
            printCurrentExpenseGoalStatus();
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
    }

    /**
     * This function will print out the current expense status
     * with regards to the goal the user set.
     */
    public static void printCurrentExpenseGoalStatus() {
        double goalDifference = expenseGoal - totalExpenses;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "This is your current "
                    + "expense goal status for " + month + ". You have spent $" + totalExpenses + " / $"
                    + expenseGoal + ". You have exceeded your " + "expense budget.");
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Enter y to exit "
                    + "DisplayGoal. ");
            input = UiManager.handleInput();
            if (input.equals("y")) {
                return;
            } else {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter y");
                printCurrentExpenseGoalStatus();
            }
        } else {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "This is your current "
                    + "expense goal status for " + month + ". You have spent $" + totalExpenses + " / $"
                    + expenseGoal + ". You still have $" + goalDifference + " to spend");
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Enter y to exit "
                    + "DisplayGoal. ");
            input = UiManager.handleInput();
            if (input.equals("y")) {
                return;
            } else {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter y");
                printCurrentExpenseGoalStatus();
            }
        }
    }

    /**
     * This function will handle the expense by
     * summing up the total expense being made by the
     * user based on the indicated month. Next calling
     * the function to handle the expense goal.
     */
    public static void handleNewExpenses() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalExpenses = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtCurrIndex(i);
            if (ledger.getDate().getMonth().equals(ledgerMonth)) {
                EntryList entryList = ledger.entryList; // Get all the entry from the respective month ledger
                int entrySize = entryList.getItemsSize();
                for (int x = 0; x < entrySize; x++) {
                    Entry entry = (Entry) entryList.getItemAtCurrIndex(x);
                    if (entry.getEntryType().toString().equals("Expense")) {
                        expenses = entry.getAmount();
                        totalExpenses += expenses;  // For each expenses entry we sum up the total
                    } else {
                        totalExpenses += 0;
                    }
                }
            }
        }
        handleNewExpenseGoalStatus(); // call print method to display the goal status for the month
    }

    /**
     * This function will handle the expense goal based on
     * the month the entry is being made. Lastly calling
     * the function to print out the new status of the
     * expense goal.
     */
    public static void handleNewExpenseGoalStatus() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                try {
                    if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(ledgerMonth)) {
                        expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                        break;
                    } else {
                        expenseGoal = 0;
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }
            if (expenseGoal == 0) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a expense "
                        + "goal for " + ledgerMonth);
            }
            printNewExpenseGoalStatus();
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
    }

    /**
     * This function will print out the new expense goal
     * status.
     */
    public static void printNewExpenseGoalStatus() {
        double goalDifference = expenseGoal - totalExpenses;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                    + " You have spent $" + totalExpenses + " / $" + expenseGoal + " for " + ledgerMonth
                    + ". You have reached/exceeded your expense budget");
        } else {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                    + "You have spent $" + totalExpenses + " / $" + expenseGoal + " You have not reached your "
                    + "expense budget for " + ledgerMonth + ", You still have $" + goalDifference
                    + " to spend.");
        }
    }

    /**
     * This function will handle the total current
     * income being saved by the user for the month.
     */
    public static void handleCurrentIncomes() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalIncomes = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtCurrIndex(i);
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            if (ledger.getDate().getMonth().equals(month)) {
                EntryList entryList = ledger.entryList;
                int entrySize = entryList.getItemsSize();
                for (int x = 0; x < entrySize; x++) {
                    Entry entry = (Entry) entryList.getItemAtCurrIndex(x);
                    if (entry.getEntryType().toString().equals("Income")) {
                        incomes = entry.getAmount();
                        totalIncomes += incomes;
                    } else {
                        totalIncomes += 0;
                    }
                }
            }
        }
        handleCurrentIncomeGoalStatus();
    }

    /**
     * This function will handle the current income
     * goal based on the month that user want to display.
     * Lastly calling the function to print out the current
     * income status.
     */
    public static void handleCurrentIncomeGoalStatus() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                try {
                    if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                        incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                        break;
                    } else {
                        incomeGoal = 0;
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }
            if (incomeGoal == 0) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a income "
                        + "goal for " + month);
            }
            printCurrentIncomeGoalStatus();
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
    }

    /**
     * This function will print out the current income status
     * with regards to the goal the user set.
     */
    public static void printCurrentIncomeGoalStatus() {
        double goalDifference = incomeGoal - totalIncomes;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "This is your current "
                    + "income goal status for " + month + ". You have saved $" + totalIncomes + " / $"
                    + incomeGoal + ". You have met your " + "revenue goal.");
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Enter y to exit "
                    + "DisplayGoal. ");
            input = UiManager.handleInput();
            if (input.equals("y")) {
                return;
            } else {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter y");
                printCurrentIncomeGoalStatus();
            }
        } else {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "This is your current "
                    + "income goal status for " + month + ". You have saved $" + totalIncomes + " / $"
                    + incomeGoal + ". You have not met your " + "revenue goal. You are $" + goalDifference
                    + " away from your goal.");
            UiManager.printWithStatusIcon(Common.PrintType.SYS_MSG, "Enter y to exit "
                    + "DisplayGoal. ");
            input = UiManager.handleInput();
            if (input.equals("y")) {
                return;
            } else {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter y");
                printCurrentIncomeGoalStatus();
            }
        }
    }

    /**
     * This function will handle the income by
     * summing up the total income being saved by the
     * user based on the indicated month. Next calling
     * the function handle the income goal.
     */
    public static void handleNewIncomes() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalIncomes = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtCurrIndex(i);
            if (ledger.getDate().getMonth().equals(ledgerMonth)) {
                EntryList entryList = ledger.entryList; // Get all entry for the respective month ledger
                int entrySize = entryList.getItemsSize();
                for (int x = 0; x < entrySize; x++) {
                    Entry entry = (Entry) entryList.getItemAtCurrIndex(x);
                    if (entry.getEntryType().toString().equals("Income")) {
                        incomes = entry.getAmount();
                        totalIncomes += incomes;    // For each income entry we sum up the total
                    } else {
                        totalIncomes += 0;
                    }
                }
            }
        }
        handleNewIncomeGoalStatus();
    }

    /**
     * This function will handle the income goal based on
     * the month the entry is being made. Lastly calling
     * the function to print out the new status of the
     * income goal.
     */
    public static void handleNewIncomeGoalStatus() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                try {
                    if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(ledgerMonth)) {
                        incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                        break;
                    } else {
                        incomeGoal = 0;
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }
            if (incomeGoal == 0) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a income "
                        + "goal for " + ledgerMonth);
            }
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
        printNewIncomeGoalStatus();
    }

    /**
     * This function will print out the new income goal
     * status.
     */
    public static void printNewIncomeGoalStatus() {
        double goalDifference = incomeGoal - totalIncomes;
        if (goalDifference < 0) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                    + " You have saved $" + totalIncomes + " / $" + incomeGoal + " for " + ledgerMonth
                    + ". You have met your revenue goal.");
        } else {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                    + "You have saved $" + totalIncomes + " / $" + incomeGoal + " You have not met your "
                    + "revenue goal. for " + ledgerMonth + " You are $" + goalDifference
                    + " away from your goal.");
        }
    }

}
