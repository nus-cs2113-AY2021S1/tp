package seedu.trippie.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of expenses stored in an array.
 * Stores the budget, foreign exchange rate and foreign currency abbreviation inputted by the user for the current trip.
 */
public class ExpenseList {
    private List<Expense> expenseList;
    private Float budgetValue;
    private Float forExValue;
    private String currencyAbbreviation;

    public ExpenseList() {
        expenseList = new ArrayList<>();
    }

    public ExpenseList(List<Expense> expenseList) {
        this.expenseList = new ArrayList<>(expenseList);
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = new ArrayList<>(expenseList);
    }

    public Float getBudgetValue() {
        return budgetValue;
    }

    public Float getForExValue() {
        return forExValue;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public Float getTotalExpenses() {
        float totalExpenses = 0;
        for (Expense expense: expenseList) {
            totalExpenses += expense.getExpenseCost();
        }
        return totalExpenses;
    }

    /**
     * Extract the latest day in which an item was bought.
     *
     * @return the max day to be displayed.
     */
    public int getMaxDay() {
        if (expenseList.size() == 0) {
            return 0;
        }
        int maxDay = 0;
        for (Expense expense: expenseList) {
            if (expense.getExpenseDayBought() > maxDay) {
                maxDay = expense.getExpenseDayBought();
            }
        }
        return maxDay;
    }

    public void setBudgetValue(Float budgetValue) {
        this.budgetValue = budgetValue;
    }

    public void setForExValue(Float forExValue) {
        this.forExValue = forExValue;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }
}
