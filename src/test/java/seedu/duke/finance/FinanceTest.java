package seedu.duke.finance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinanceTest {
    @Test
    void testver1Finance() {
        String expected1 = "Got it! I've added this to the list.\n"
                + "\t1.write $12.5\n"
                + "There is 1 log entry in the list now.\n";
        String actual1 = FinanceList.addLog(new FinanceLog("write", 12.5));
        assertEquals(expected1, actual1);
        String expected2 = "Here is the list:\n"
                + "\t1.write $12.5\n"
                + "Total budget: $12.50\n";
        String actual2 = FinanceList.summary();
        assertEquals(expected2, actual2);
        String expected3 = "Got it! I've removed this from list.\n"
                + "There is 0 log entry in the list now.\n";
        String actual3 = FinanceList.dellog(1);
        assertEquals(expected3, actual3);
    }
}