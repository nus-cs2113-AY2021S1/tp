package seedu.trippie;

public class Expense {
    private final String expenseName;
    private final Float expenseCost;
    private final int expenseDayBought;

    public Expense(String expenseName, Float expenseCost, int expenseDayBought) {
        this.expenseName = expenseName;
        this.expenseCost = expenseCost;
        this.expenseDayBought = expenseDayBought;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Float getExpenseCost() {
        return expenseCost;
    }

    public int getExpenseDayBought() {
        return expenseDayBought;
    }

    public String getExpense() {
        return "Day " + getExpenseDayBought() + ": " + getExpenseName() + " - $"
                + String.format("%.2f",getExpenseCost());
    }
}
