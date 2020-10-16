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


    public static void main() {
        UiManager.printWithStatusIcon(Constants.PrintType.SYS_MSG, "Welcome to Goals Tracker");
        UiManager.printWithStatusIcon(Constants.PrintType.INSTRUCTION, "Input the following commands "
                        + "to set goals for either expense or income.");
        System.out.println("[1]. expense <amount> for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[2]. income <amount> for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[3]. To display goals for respective months, enter the following commands"
                + " display expense/income for <Month in number form (e.g 01 -> Jan)>");
        System.out.println("[4]. Enter <exit> to leave Goals Tracker");

        exitTracker = false;
        input = UiManager.handleInput();
        cmdPacket = new InputParser().parseGoalCommand(input.toLowerCase());
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
                handleExpenseGoal();
            } else {
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
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            expenseGoal = Integer.parseInt(cmdPacket[1]);
            goalSetter = new Goal(expenseGoal, "Expense", month);
            totalGoalList.addGoal(goalSetter);
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have set $" + expenseGoal
                    + " as your Expense Goals for " + month);
            main();
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
            month = Month.of(Integer.parseInt(cmdPacket[3]));
            incomeGoal = Integer.parseInt(cmdPacket[1]);
            goalSetter = new Goal(incomeGoal, "Income", month);
            totalGoalList.addGoal(goalSetter);
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You have set $" + incomeGoal
                    + " as your Income Goals for " + month);
            main();
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
        System.out.println(ledgerMonth);
        LedgerList ledgerList = ManualTracker.getLedgerList();
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = 0; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtIndex(i);
            if (ledger.getDate().getMonth().equals(ledgerMonth)) {
                EntryList entryList = ledger.entryList;
                int entrySize = entryList.getItemsSize();
                for (int x = 0; x < entrySize; x++) {
                  //  System.out.println(entryList.getItems());
                    Entry entry = (Entry) entryList.getItemAtIndex(x);
                    expenses = entry.getAmount();
                  // expenses = getExpenseGoal();
                    totalExpenses += expenses;
                }
            }
        }
        printExpenseGoal();
    }

    public static void printExpenseGoal() {
        try {
            for (int i = 0; i < totalGoalList.getListSize(); i++) {
                if (totalGoalList.getGoal().get(i).getExpenseMonth().equals(ledgerMonth)) {
                    expenseGoal = totalGoalList.getGoal().get(i).getExpenseGoal();
                }
            }

            if (expenseGoal == 0) {
                UiManager.printWithStatusIcon(Constants.PrintType.ERROR_MESSAGE, "You did not set a goal"
                + " for " + ledgerMonth);
            }

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
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for expense.");
        }

        totalExpenses = 0;
    }

    public static void handleIncomeGoal() {
        LedgerList ledgerList = ManualTracker.getLedgerList();
        int ledgerSize = ledgerList.getItemsSize();
        for (int i = ledgerSize - 1; i < ledgerSize; i++) {
            Ledger ledger = (Ledger) ledgerList.getItemAtIndex(i);
            EntryList entryList = ledger.entryList;
            int entrySize = entryList.getItemsSize();
            for (int x = entrySize - 1; x < entrySize; x++) {
                incomes = entryData.getAmount();
                totalIncomes += incomes;
            }
        }
        printIncomeGoal();
    }

    public static void printIncomeGoal() {
        try {
            incomeGoal = totalGoalList.getGoal().get(0).getIncomeGoal();
            if (incomeGoal == 0) {
                incomeGoal = totalGoalList.getGoal().get(1).getIncomeGoal();
            }
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
        } catch (IndexOutOfBoundsException e) {
            UiManager.printWithStatusIcon(Constants.PrintType.GOAL_STATUS, "You did not set "
                    + "a goal for income.");
        }
    }
}
