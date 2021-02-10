package seedu.trippie.data;

/**
 * Represents the individual expenses made by the user.
 * Stores the name of the item, its cost and the day it is bought.
 */
public class Expense {
    private final String expenseName;
    private final Float expenseCost;
    private final int expenseDayBought;

    public Expense(String expenseName, Float expenseCost, int expenseDayBought) {
        this.expenseName = expenseName;
        this.expenseCost = expenseCost;
        this.expenseDayBought = expenseDayBought;
    }

    /**
     * Returns the name of the expense.
     *
     * @return String corresponding to the name of the expense.
     */
    public String getExpenseName() {
        return expenseName;
    }

    /**
     * Returns the expense cost of the item bought.
     *
     * @return Float corresponding to the expense cost.
     */
    public Float getExpenseCost() {
        return expenseCost;
    }

    /**
     * Returns the day which the item was bought on.
     *
     * @return Integer of day.
     */
    public int getExpenseDayBought() {
        return expenseDayBought;
    }

    /**
     * Returns a full description of the item bought.
     *
     * @return String of description.
     */
    @Override
    public String toString() {
        return "Day " + getExpenseDayBought() + ": " + getExpenseName() + " - "
                + String.format("%.2f",getExpenseCost());
    }

}
