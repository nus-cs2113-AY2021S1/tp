package seedu.duke.finance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinanceTest2 {
    @Test
    void testSummary() {
        String non = FinanceList.addLog(new FinanceLog("write",12.5));
        non = FinanceList.addLog(new FinanceLog("buy iphone12",1299));
        non = FinanceList.addLog(new FinanceLog("super chat",23.24));
        String expected = "Here is the list:\n"
                + "\t1.write $12.5\n"
                + "\t2.buy iphone12 $1299.0\n"
                + "\t3.super chat $23.24\n"
                + "Total budget: $1334.74\n";
        String actual = FinanceList.summary();
        assertEquals(expected,actual);
    }
}
