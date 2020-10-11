package seedu.duke.finance;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FinanceList {
    public static ArrayList<FinanceLog>  financeLogs = new ArrayList<FinanceLog>();

    /**
     * Add one log into the finance list.
     * @param fl the new finance log user wants to add
     * @return the output when it successfully add one log
     */
    public static String addLog(FinanceLog fl) {
        financeLogs.add(fl);
        String output = "Got it! I've added this to the list.\n";
        String output1 = fl.toString();
        String output2;
        if (fl.getSize() > 1) {
            output2 = "There are " + fl.getSize() + " logs in the list now.\n";
        } else {
            output2 = "There is " + fl.getSize() + " log in the list now.\n";
        }
        output = output + output1 + output2;
        return output;
    }

    /**
     * Delete one particular log from the list.
     * @param index the index of the log which user wants to delete
     * @return the output when successfully delete a log
     */
    public static String dellog(int index) {
        try {
            String output1 = "Got it! I've removed this from list.\n";
            financeLogs.remove(index - 1);
            FinanceLog.finSize--;
            String output2;
            if (FinanceLog.finSize > 1) {
                output2 = "There are " + FinanceLog.finSize + " logs in the list now.\n";
            } else {
                output2 = "There is " + FinanceLog.finSize + " log in the list now.\n";
            }
            String output = output1 + output2;
            return output;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Your index input does not exist in the list");
        }
        return null;
    }

    /**
     * Show the all the logs in the list and shows the total budget amount.
     * @return the finance list
     */
    public static String summary() {
        if (FinanceLog.getSize() == 0) {
            return "Sorry, your finance list is empty.\n";
        }
        String output = "Here is the list:\n";
        for (int i = 0;i < FinanceLog.getSize();i++) {
            output = output.concat("\t"+(i+1)+"." +financeLogs.get(i).getLog()+" $"+
                    financeLogs.get(i).getLogVal()+"\n");
        }
        output = output.concat("Total budget: $"+String.format("%.2f",FinanceLog.getSum())+"\n");
        return output;
    }
}
