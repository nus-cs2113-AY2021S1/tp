package seedu.duke.finance;

public class FinanceLog {
    private String finLog;
    private double finLogVal;
    public static int finSize = 0;
    public static double sum = 0;

    /**
     * Create a new finance log.
     * @param log the content of a finance log
     * @param val the value of its budget
     */
    public FinanceLog(String log, double val) {
        finLog = log;
        finLogVal = val;
        finSize++;
        sum = sum + val;
    }

    /**
     * Gets the log's content.
     * @return log's content
     */
    public String getLog() {
        return finLog;
    }

    /**
     * Get the log's budget amount.
     * @return log's budget amount
     */
    public double getLogVal() {
        return finLogVal;
    }

    /**
     * Gets the size of finance log list.
     * @return the size of finance log list
     */
    public static int getSize() {
        return finSize;
    }

    /**
     * Gets the sum of total budget.
     * @return the sum of total budget
     */
    public static double getSum() {
        return sum;
    }

    /**
     * Change the finLog of this FinanceLog.
     * @param input the new finLog for this FinanceLog
     */
    public void setFinLog(String input) {
        finLog = input;
    }

    /**
     * Change the finLogVal of this FinanceLog.
     * @param input the new finLogVal for this FinanceLog
     */
    public void setFinLogVal(double input) {
        sum = sum - finLogVal + input;
        finLogVal = input;
    }

    /**
     * Form a formatted string for a finance log.
     * @return the string of a formatted finance log
     */
    public String toString() {
        return "\t" + finSize + "." + finLog + " $" + finLogVal + "\n";
    }
}
