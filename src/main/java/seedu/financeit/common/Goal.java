package seedu.financeit.common;

public class Goal {
    private int incomeGoal = 0;
    private int expenseGoal = 0;

    public void setIncomeGoal(int incomeGoal) {
        this.incomeGoal = incomeGoal;
    }

    public int getIncomeGoal() {
        return this.incomeGoal;
    }

    public void setExpenseGoal(int expenseGoal) {
        this.expenseGoal = expenseGoal;
    }

    public int getExpenseGoal() {
        return this.expenseGoal;
    }
}
