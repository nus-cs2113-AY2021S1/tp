package seedu.financeit.data;

import java.time.Month;

/**
 * This is the class to set and get individual data.
 */
public class Goal {
    private int incomeGoal = 0;
    private int expenseGoal = 0;
    private Month incomeMonth;
    private Month expenseMonth;
    private String category;

    /**
     * This is the function that will set the input into variable.
     *
     * @param goal     is the amount
     * @param category whether expense or income
     * @param month    states for which month the goal is for
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

    public String getCategory() {
        return this.category;
    }

    /**
     * Calling this function will output the expense month.
     * stored
     *
     * @return
     */
    public Month getExpenseMonth() {
        return this.expenseMonth;
    }

    /**
     * This function set the expense month.
     * based on the month entered by the user
     *
     * @param expenseMonth The month that for expense goal
     */
    public void setExpenseMonth(Month expenseMonth) {
        this.expenseMonth = expenseMonth;
    }

    /**
     * Calling this function will output the income month.
     * stored
     *
     * @return
     */
    public Month getIncomeMonth() {
        return this.incomeMonth;
    }

    /**
     * This function set the income month.
     * based on the month entered by the user
     *
     * @param incomeMonth The month that is for income goal
     */
    public void setIncomeMonth(Month incomeMonth) {
        this.incomeMonth = incomeMonth;
    }

    /**
     * Calling this function will output the.
     * income goal stored
     *
     * @return
     */
    public int getIncomeGoal() {
        return this.incomeGoal;
    }

    /**
     * This function will stored the income goal.
     * based on the goal being entered by the user
     *
     * @param incomeGoal The amount that is set as the income goal
     */
    public void setIncomeGoal(int incomeGoal) {
        assert this.incomeGoal != -1;
        this.incomeGoal = incomeGoal;
    }

    /**
     * Calling this function will output the expense goal.
     * stored
     *
     * @return
     */
    public int getExpenseGoal() {
        return this.expenseGoal;
    }

    /**
     * This function will stored the expense goal.
     * based on the goal being entered by user
     *
     * @param expenseGoal The amount that is set as the expense goal
     */
    public void setExpenseGoal(int expenseGoal) {
        assert this.expenseGoal != -1;
        this.expenseGoal = expenseGoal;
    }
}
