package seedu.financeit.goaltracker;

import java.time.DateTimeException;
import seedu.financeit.common.Constants;
import seedu.financeit.common.Goal;
import seedu.financeit.manualtracker.Ledger;
import seedu.financeit.manualtracker.LedgerList;
import seedu.financeit.manualtracker.ManualTracker;
import seedu.financeit.manualtracker.subroutine.Entry;
import seedu.financeit.manualtracker.subroutine.EntryList;
import seedu.financeit.parser.InputParser;
import seedu.financeit.ui.UiManager;
import java.time.Month;

import java.util.Scanner;


public class GoalTracker {
    private static String[] cmdPacket;
    private static String[] editPacket;
    private static Goal goalSetter;
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


    public static void main() {
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Goals Tracker");
        UiManager.printWithStatusIcon(Constants.PrintType.INSTRUCTION, "Input the following commands "
                        + "to set goals for either expense or income.");
        System.out.println("[1]. expense <amount> for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[2]. income <amount> for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[3]. To display goals for respective months, enter the following commands"
                + " display expense/income for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[4]. edit expense/income <amount> for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[5]. Enter <exit> to leave Goals Tracker");

        exitTracker = false;
        input = UiManager.handleInput();
        cmdPacket = new InputParser().parseGoalCommand(input.toLowerCase());
        editPacket = new InputParser().parseEditCommand(input.toLowerCase());
        while (!exitTracker) {
            switch (cmdPacket[0]) {
            case "expense":
                setExpenseGoal();
                break;
            case "income":
                setIncomeGoal();
                break;
            case "display":
                displayGoal();
                break;
            case "edit":
                editGoal();
                break;
            case "exit":
                exitTracker = true;
                break;
            default:
                System.out.println("Invalid Command");
                input = UiManager.handleInput();
                cmdPacket = new InputParser().parseGoalCommand(input.toLowerCase());
                break;
            }
        }
    }

    public static void editGoal() {
        try {
            month = Month.of(Integer.parseInt(editPacket[4]));
            if (editPacket[1].equals("expense")) {
                expenseGoal = Integer.parseInt(editPacket[2]);
                for (int i = 0; i < totalGoalList.getListSize(); i++) {
                    try {
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                            goalSetter = new Goal(expenseGoal, "Expense", month);
                            totalGoalList.getGoal().set(i, goalSetter);
                            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have successfully"
                                    + " edited your expense goal for " + month);
                            main();
                        } else {

                        }
                    } catch (NullPointerException e) {
                        continue;
                    }
                }

                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You did not have "
                        + "any existing goal for " + month);
                main();

            } else if (editPacket[1].equals("income")) {
                incomeGoal = Integer.parseInt(editPacket[2]);
                for (int i = 0; i < totalGoalList.getListSize(); i++) {
                    try {
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                            goalSetter = new Goal(incomeGoal, "Income", month);
                            totalGoalList.getGoal().set(i, goalSetter);
                            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have successfully"
                                    + " edited your income goal for " + month);
                            main();
                        }
                    } catch (NullPointerException e) {
                        continue;
                    }
                }
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You did not have "
                        + "any existing goal for " + month);
                main();
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter either expense "
                    + "or income");
            main();
        }
    }

    public static int getExpenseGoal() {
        return expenseGoal;
    }

    public static int getIncomeGoal() {
        return incomeGoal;
    }

    public static void setGoals(int expense, int income) {
        if(expense != 0) {
            try {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                expenseGoal = expense;
                goalSetter = new Goal(expenseGoal, "Expense", month);
                totalGoalList.addGoal(goalSetter);
            } catch (DateTimeException e) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                        + " a valid month");
           }
        }
        if (income != 0) {
            try {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                incomeGoal = income;
                goalSetter = new Goal(incomeGoal, "Income", month);
                totalGoalList.addGoal(goalSetter);
            } catch (DateTimeException e) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                        + " a valid month");
            }
        }
    }

    private static void displayGoal() {
        try {
            if (cmdPacket[1].equals("expense")) {
                ledgerMonth = null;
                handleExpenseGoal();
            } else if (cmdPacket[1].equals("income")) {
                ledgerMonth = null;
                handleIncomeGoal();
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter either expense "
            + "or income");
            main();
        }
    }

    private static void setExpenseGoal() {
        try {
            isExist = false;
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            expenseGoal = Integer.parseInt(cmdPacket[1]);
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
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You have an existing "
                        + "expense goal for " + month);
                main();
            } else {
                goalSetter = new Goal(expenseGoal, "Expense", month);
                totalGoalList.addGoal(goalSetter);
                UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have set $" + expenseGoal
                        + " as your Expense Goals for " + month);
                main();
            }
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid month");
            main();
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid amount/date");
            main();
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You are missing params");
            main();
        }
    }

    private static void setIncomeGoal() {
        try {
            isExist = false;
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            incomeGoal = Integer.parseInt(cmdPacket[1]);
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
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You have an existing "
                        + "income goal for " + month);
                main();
            } else {
                goalSetter = new Goal(incomeGoal, "Income", month);
                totalGoalList.addGoal(goalSetter);
                UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have set $" + incomeGoal
                        + " as your Income Goals for " + month);
                main();
            }
        } catch (DateTimeException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid month");
            main();
        } catch (NumberFormatException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Invalid input. Please enter "
                    + " a valid amount/date");
            main();
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You are missing params");
            main();
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

    public static void storeLedgerDate(Ledger ledger) {
        ledgerMonth = ledger.getDate().getMonth();
    }

    public static void handleExpenseGoal() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalExpenses = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtIndex(i);
            if (ledgerMonth == null) {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                if (ledger.getDate().getMonth().equals(month)) {
                    EntryList entryList = ledger.entryList;
                    int entrySize = entryList.getItemsSize();
                    for (int x = 0; x < entrySize; x++) {
                        Entry entry = (Entry) entryList.getItemAtIndex(x);
                        if (entry.getEntryType().toString().equals("Expense")) {
                            expenses = entry.getAmount();
                            totalExpenses += expenses;
                        } else {
                            totalExpenses += totalExpenses;
                        }
                    }
                }
            } else if (ledgerMonth != null) {
                if (ledger.getDate().getMonth().equals(ledgerMonth)) {
                    EntryList entryList = ledger.entryList;
                    int entrySize = entryList.getItemsSize();
                    for (int x = 0; x < entrySize; x++) {
                        Entry entry = (Entry) entryList.getItemAtIndex(x);
                        if (entry.getEntryType().toString().equals("Expense")) {
                            expenses = entry.getAmount();
                            totalExpenses += expenses;
                        } else {
                            totalExpenses += totalExpenses;
                        }
                    }
                }
            }
        }
        printExpenseGoal();
    }

    public static void printExpenseGoal() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                try {
                    if (ledgerMonth == null) {
                        month = Month.of(Integer.parseInt(cmdPacket[3]));
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(month)) {
                            expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                            break;
                        } else {
                            expenseGoal = 0;
                        }
                    } else if (ledgerMonth != null) {
                        if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(ledgerMonth)) {
                            expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                        } else {
                            expenseGoal = 0;
                        }
                    }
                } catch (NullPointerException e) {
                   continue;
                }
            }
            if (expenseGoal == 0) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You did not set a expense "
                + "goal for " + month);
            }

            double goalDifference = expenseGoal - totalExpenses;
            if (ledgerMonth == null) {
                if (goalDifference < 0) {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "This is your current "
                            + "expense goal status for " + month + ". You have spent $" + totalExpenses + " / $"
                            + expenseGoal + ". You have exceeded your " + "expense budget.");
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Do you want to exit "
                            + "DisplayGoal? y/n");
                    input = UiManager.handleInput();
                    if (input.equals("y")) {
                        main();
                    } else if (input.equals("n")) {
                        return;
                    } else {
                        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter y/n");
                    }
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "This is your current "
                            + "expense goal status for " + month + ". You have spent $" + totalExpenses + " / $"
                            + expenseGoal + ". You still have $" + goalDifference + " to spend");
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Do you want to exit "
                            + "DisplayGoal? y/n");
                    input = UiManager.handleInput();
                    if (input.equals("y")) {
                        main();
                    } else if (input.equals("n")) {
                        return;
                    } else {
                        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter y/n");
                    }
                }
            } else {
                if (goalDifference < 0) {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                            + " You have spent $" + totalExpenses + " / $" + expenseGoal + " for " + ledgerMonth
                            + ". You have exceeded your expense budget");
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Expense Budget Updated, "
                            + "You have spent $" + totalExpenses + " / $" + expenseGoal + " You have not reached your "
                            + "expense budget for " + ledgerMonth + " You still have $" + goalDifference
                            + " to spend.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for expense.");
        }
    }

    public static void handleIncomeGoal() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        totalIncomes = 0;
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtIndex(i);
            if (ledgerMonth == null) {
                month = Month.of(Integer.parseInt(cmdPacket[3]));
                if (ledger.getDate().getMonth().equals(month)) {
                    EntryList entryList = ledger.entryList;
                    int entrySize = entryList.getItemsSize();
                    for (int x = 0; x < entrySize; x++) {
                        Entry entry = (Entry) entryList.getItemAtIndex(x);
                        if (entry.getEntryType().toString().equals("Income")) {
                            incomes = entry.getAmount();
                            totalIncomes += incomes;
                        } else {
                            totalIncomes += totalIncomes;
                        }
                    }
                }
            } else {
                if (ledger.getDate().getMonth().equals(ledgerMonth)) {
                    EntryList entryList = ledger.entryList;
                    int entrySize = entryList.getItemsSize();
                    for (int x = 0; x < entrySize; x++) {
                        Entry entry = (Entry) entryList.getItemAtIndex(x);
                        if (entry.getEntryType().toString().equals("Income")) {
                            incomes = entry.getAmount();
                            totalIncomes += incomes;
                        } else {
                            totalIncomes += totalIncomes;
                        }
                    }
                }
            }
        }
        printIncomeGoal();
    }

    public static void printIncomeGoal() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                try {
                    if (ledgerMonth == null) {
                        month = Month.of(Integer.parseInt(cmdPacket[3]));
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(month)) {
                            incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                            break;
                        } else {
                            incomeGoal = 0;
                        }
                    } else {
                        if (totalGoalList.getGoal().get(i).getIncomeMonth().equals(ledgerMonth)) {
                            incomeGoal = totalGoalList.getGoal().get(i).getIncomeGoal();
                        } else {
                            incomeGoal = 0;
                        }
                    }
                } catch (NullPointerException e) {
                    continue;
                }
            }

            if (incomeGoal == 0) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You did not set a income "
                        + "goal for " + month);
            }

            double goalDifference = incomeGoal - totalIncomes;
            if (ledgerMonth == null) {
                if (goalDifference < 0) {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "This is your current, "
                            + "income goal status for " + month + ". You have saved $" + totalIncomes + " / $"
                            + incomeGoal + ". You have met your " + "revenue goal.");
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Do you want to exit "
                            + "DisplayGoal? y/n");
                    input = UiManager.handleInput();
                    if (input.equals("y")) {
                        main();
                    } else if (input.equals("n")) {
                        return;
                    } else {
                        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter y/n");
                    }
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "This is your current, "
                            + "income goal status for " + month + ". You have saved $" + totalIncomes + " / $"
                            + incomeGoal + ". You have not met your " + "revenue goal. You are $" + goalDifference
                             + " away from your goal.");
                    UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Do you want to exit "
                            + "DisplayGoal? y/n");
                    input = UiManager.handleInput();
                    if (input.equals("y")) {
                        main();
                    } else if (input.equals("n")) {
                        return;
                    } else {
                        UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "Please enter y/n");
                    }
                }
            } else {
                if (goalDifference < 0) {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                            + " You have saved $" + totalIncomes + " / $" + incomeGoal + " for " + ledgerMonth
                            + ". You have met your revenue goal.");
                } else {
                    UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "Revenue Goal Updated, "
                            + "You have saved $" + totalIncomes + " / $" + incomeGoal + " You have not met your "
                            + "revenue goal. for " + ledgerMonth + " You are $" + goalDifference
                            + " away from your goal.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for income.");
        }
    }
}
