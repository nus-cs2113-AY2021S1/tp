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

import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

/**
 * This is the class that will handle the setting of goals
 * for expense and income for each and every individual months.
 */

public class GoalTracker {
    private static String[] cmdPacket;
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
     * This function will allow user to edit their respective goal
     * for respective months.
     */
    public static void editGoal(String[] userInput) {
        isExist = false;
        try {
            month = Month.of(Integer.parseInt(userInput[4]));
            if (userInput[1].equals("expense")) {
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

            } else if (userInput[1].equals("income")) {
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

    public static TotalGoalList getTotalGoalList() {
        return totalGoalList;
    }

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

    /**
     * This function will handle and parser the user command to differentiate whether
     * user want to display for expense for income.
     */
    private static void handleDisplayGoal() {
        try {
            if (cmdPacket[1].equals("expense")) {
                ledgerMonth = null;
                displayExpenseGoal();
            } else if (cmdPacket[1].equals("income")) {
                ledgerMonth = null;
                displayIncomeGoal();
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter either expense "
                    + "or income");
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
            handleExpenseGoal();
        } else {
            handleIncomeGoal();
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
     * This function allows user to display their expense goal.
     * for indicated month
     */
    public static void displayExpenseGoal() {
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
        printExpenseGoal();
    }

    /**
     * This function will handle the expense goal by
     * summing up the total expense being made by the
     * user based on the indicated month. Lastly printing
     * the expense goal status.
     */
    public static void handleExpenseGoal() {
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
        printExpenseGoal(); // call print method to display the goal status for the month
    }

    /**
     * This function will print the expense goal status for the
     * indicated month.
     */
    public static void printExpenseGoal() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                try {
                    if (ledgerMonth == null) {
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                            expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                            break;
                        } else {
                            expenseGoal = 0;
                        }
                    } else if (ledgerMonth != null) {
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(ledgerMonth)) {
                            expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                            break;
                        } else {
                            expenseGoal = 0;
                        }
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }
            if (expenseGoal == 0 && ledgerMonth != null) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a expense "
                        + "goal for " + ledgerMonth);
            } else if (expenseGoal == 0 && ledgerMonth == null) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a expense "
                        + "goal for " + month);
            }

            double goalDifference = expenseGoal - totalExpenses;
            if (ledgerMonth == null) {
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
                        printExpenseGoal();
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
                        printExpenseGoal();
                    }
                }
            } else {
                if (goalDifference < 0) {
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                            + " You have spent $" + totalExpenses + " / $" + expenseGoal + " for " + ledgerMonth
                            + ". You have exceeded your expense budget");
                } else {
                    UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                            + "You have spent $" + totalExpenses + " / $" + expenseGoal + " You have not reached your "
                            + "expense budget for " + ledgerMonth + " You still have $" + goalDifference
                            + " to spend.");
                }
            }
            month = null;
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for expense.");
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
    }

    /**
     * This function allows user to display their income goal.
     * for indicated month
     */
    public static void displayIncomeGoal() {
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
        printIncomeGoal();
    }

    /**
     * This function will handle the income goal by
     * summing up the total income being saved by the
     * user based on the indicated month. Lastly printing
     * the income goal status
     */
    public static void handleIncomeGoal() {
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
        printIncomeGoal(); // call print method to display the goal status for the month
    }

    /**
     * This function will print the income goal status for the.
     * indicated month
     */
    public static void printIncomeGoal() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                try {
                    if (ledgerMonth == null) {
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                            incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                            break;
                        } else {
                            incomeGoal = 0;
                        }
                    } else {
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(ledgerMonth)) {
                            incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                            break;
                        } else {
                            incomeGoal = 0;
                        }
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }

            if (incomeGoal == 0 && ledgerMonth != null) {
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a income "
                        + "goal for " + ledgerMonth);
            } else if (incomeGoal == 0 && ledgerMonth == null) {
                if (cmdPacket != null) {
                    month = Month.of(Integer.parseInt(cmdPacket[3]));
                }
                UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "You did not set a income "
                        + "goal for " + month);
            }

            double goalDifference = incomeGoal - totalIncomes;
            if (ledgerMonth == null) {
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
                        printIncomeGoal();
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
                        printIncomeGoal();
                    }
                }
            } else {
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
            month = null;
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Common.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for income.");
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Common.PrintType.ERROR_MESSAGE, "Please enter a valid "
                    + "int month ");
        }
    }
}
