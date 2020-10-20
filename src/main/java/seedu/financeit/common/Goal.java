package seedu.financeit.common;

import java.time.Month;

/**
 * This is the class to set and get individual data
 */
public class Goal {
    private int incomeGoal = 0;
    private int expenseGoal = 0;
    private Month incomeMonth;
    private Month expenseMonth;
    private String category;

    /**
     * This is the function that will set the input into variable
     * @param goal
     * @param category
     * @param month
     */
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

    /**
     * This function set the expense month
     * based on the month entered by the user
     * @param expenseMonth
     */
    public void setExpenseMonth(Month expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    /**
     * Calling this function will output the expense month
     * stored
     * @return
     */
    public Month getExpenseMonth() {
        return this.expenseMonth;
    }

    /**
     * This function set the income month
     * based on the month entered by the user
     * @param incomeMonth
     */
    public void setIncomeMonth(Month incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    /**
     * Calling this function will output the income month
     * stored
     * @return
     */
    public Month getIncomeMonth() {
        return this.incomeMonth;
    }

    /**
     * This function will stored the income goal
     * based on the goal being entered by the user
     * @param incomeGoal
     */
    public void setIncomeGoal(int incomeGoal) {
        assert this.incomeGoal != -1;
        this.incomeGoal = incomeGoal;
    }

    /**
     * Calling this function will output the
     * income goal stored
     * @return
     */
    public int getIncomeGoal() {
        return this.incomeGoal;
    }

    /**
     * This function will stored the expense goal
     * based on the goal being entered by user
     * @param expenseGoal
     */
    public void setExpenseGoal(int expenseGoal) {
        assert this.expenseGoal != -1;
        this.expenseGoal = expenseGoal;
    }

    /**
     * Calling this function will output the expense goal
     * stored
     * @return
     */
    public int getExpenseGoal() {
        return this.expenseGoal;
    }
}
