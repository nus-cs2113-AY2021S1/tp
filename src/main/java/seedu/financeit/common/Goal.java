package seedu.financeit.common;

public class Goal {
    private int incomeGoal = 0;
    private int expenseGoal = 0;
    private String category;

    public Goal(int goal, String category) {
        this.category = category;
        if (this.category.equals("Expense")) {
            setExpenseGoal(goal);
        } else {
            setIncomeGoal(goal);
        }
    }

    public void setIncomeGoal(int incomeGoal) {
        assert this.incomeGoal != -1;
        this.incomeGoal = incomeGoal;
    }

    public int getIncomeGoal() {
        return this.incomeGoal;
    }

    public void setExpenseGoal(int expenseGoal) {
        assert this.expenseGoal != -1;
        this.expenseGoal = expenseGoal;
    }

    public int getExpenseGoal() {
        return this.expenseGoal;
    }
}
