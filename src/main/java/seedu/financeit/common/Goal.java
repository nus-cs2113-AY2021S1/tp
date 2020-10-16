package seedu.financeit.common;

import java.time.Month;

public class Goal {
    private int incomeGoal = 0;
    private int expenseGoal = 0;
    private Month incomeMonth;
    private Month expenseMonth;
    private String category;

    public Goal(int goal, String category, Month month) {
        this.category = category;
        if (this.category.equals("Expense")) {
            setExpenseGoal(goal);
            setExpenseMonth(month);
        } else {
            setIncomeGoal(goal);
            setIncomeMonth(month);
        }
    }

    public void setExpenseMonth(Month expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    public Month getExpenseMonth() {
        return this.expenseMonth;
    }

    public void setIncomeMonth(Month incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    public Month getIncomeMonth() {
        return this.incomeMonth;
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
