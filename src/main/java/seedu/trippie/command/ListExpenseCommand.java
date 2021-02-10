package seedu.trippie.command;

import seedu.trippie.Ui;
import seedu.trippie.data.Expense;
import seedu.trippie.data.TrippieData;
import seedu.trippie.exception.TrippieExceedBudgetException;

import java.util.List;

public class ListExpenseCommand extends Command {

    private static final String EXCEED_BUDGET_MESSAGE = "WARNING! You have exceeded your initial budget.\n"
            + "Recommended action: Increase budget limit.";
    private static final String LIST_IS_EMPTY_MESSAGE = "There is currently nothing in your Expense list.";

    private static final int BAR_SIZE = 10;
    private static final String SYMBOL_START_BOUNDARY = "[";
    private static final String SYMBOL_SPEND = "=";
    private static final String SYMBOL_REMAINING = "-";
    private static final String SYMBOL_END_BOUNDARY = "]";

    public ListExpenseCommand() {
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out expense list summary which includes total budget, list of items bought, total spending, remaining
     * budget and percentage spent bar.
     *
     * @param ui User Interface of the program
     * @param trippieData The current trip expenseList that would updated.
     */
    @Override
    public void execute(Ui ui, TrippieData trippieData) {
        List<Expense> expenses = trippieData.getCurrentTrip().getExpenseListObject().getExpenseList();
        Float exchangeRate = trippieData.getCurrentTrip().getExpenseListObject().getForExValue();
        String currencyAbbreviation = trippieData.getCurrentTrip().getExpenseListObject().getCurrencyAbbreviation();
        float budget = trippieData.getCurrentTrip().getExpenseListObject().getBudgetValue() * exchangeRate;
        System.out.println("Total budget: " + String.format("%.2f ", budget) + currencyAbbreviation
                + " (" + String.format("%.2f", budget / exchangeRate) + " SGD)");
        if (expenses.isEmpty()) {
            System.out.println(LIST_IS_EMPTY_MESSAGE);
        } else {
            int listIndex = 1;

            System.out.println("Expense List:");
            for (Expense expense : expenses) {
                System.out.println("[" + listIndex + "] " + expense.toString() + " " + currencyAbbreviation);
                listIndex++;
            }
            float totalSpending = trippieData.getCurrentTrip().getExpenseListObject().getTotalExpenses();
            System.out.println("Your current total spending is "
                    + String.format("%.2f ", totalSpending) + currencyAbbreviation
                    + " (" + String.format("%.2f", totalSpending / exchangeRate) + " SGD)");
            try {
                float remainingBudget = budget - totalSpending;
                displayRemainingBudget(remainingBudget, exchangeRate, currencyAbbreviation);
                createBudgetPercentageBar(totalSpending, budget);
            } catch (TrippieExceedBudgetException e) {
                System.out.println(EXCEED_BUDGET_MESSAGE);
            }
        }
    }

    /**
     * Displays remaining budget in both foreign and local currency if there are any left.
     *
     * @param remainingBudget Remaining amount leftover from budget after spending.
     * @param exchangeRate Exchange rate for foreign currency.
     * @param currencyAbbreviation Foreign currency abbreviation.
     */
    private void displayRemainingBudget(float remainingBudget, float exchangeRate, String currencyAbbreviation) {
        if (remainingBudget < 0) {
            System.out.print("");
        } else {
            System.out.println("Your current remaining budget is "
                    + String.format("%.2f ", remainingBudget) + currencyAbbreviation
                    + " (" + String.format("%.2f", remainingBudget / exchangeRate) + " SGD)");
        }
    }

    /**
     * Compares spending with budget to print respectively messages
     * Prints a budget percentage bar that put spending with reference to initial budget.
     *
     * @param spent The amount spent by user.
     * @param budget The budget initially set by user.
     * @throws TrippieExceedBudgetException If amount spent is more than the budget set.
     */
    public static void createBudgetPercentageBar(float spent, float budget) throws TrippieExceedBudgetException {
        if (spent == budget) {
            System.out.println("You have spent finish your budget.");
        } else if (spent > budget) {
            throw new TrippieExceedBudgetException(EXCEED_BUDGET_MESSAGE);
        } else {
            System.out.println("You are still spending within your budget.");
        }
        float spentPercentage = (100 * spent) / budget;
        float spentLength = BAR_SIZE * spentPercentage / 100;
        StringBuilder bar = new StringBuilder(SYMBOL_START_BOUNDARY);
        for (int i = 0; i < BAR_SIZE; i++) {
            if (i < (int)spentLength) {
                bar.append(SYMBOL_SPEND);
            } else {
                bar.append(SYMBOL_REMAINING);
            }
        }
        bar.append(SYMBOL_END_BOUNDARY);
        System.out.println(bar + " "
                + String.format("%.1f", spentPercentage) + "%");
    }

}
