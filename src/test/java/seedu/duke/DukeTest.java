package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.duke.finance.FinanceList;
import seedu.duke.finance.FinanceLog;

import seedu.duke.backend.Parser;
import seedu.duke.backend.UserInput;

import java.util.HashMap;

class DukeTest {
    static final String LOGO = "    ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testFinance() {
        String expected1 = "Got it! I've added this to the list.\n"
                + "\t1.write $12.5\n"
                + "There is 1 log in the list now.\n";
        String actual1 = FinanceList.addLog(new FinanceLog("write", 12.5));
        assertEquals(expected1, actual1);
        String expected2 = "Here is the list:\n"
                + "\t1.write $12.5\n"
                + "Total budget: $12.50\n";
        String actual2 = FinanceList.summary();
        assertEquals(expected2, actual2);
        String expected3 = "Got it! I've removed this from list.\n"
                + "There is 0 log in the list now.\n";
        String actual3 = FinanceList.dellog(1);
        assertEquals(expected3, actual3);
    }

    @Test
    public void testParser() {
        // Base Case
        Parser p = new Parser();
        String test1 = "foo 2";
        HashMap<String, String> arg1 = new HashMap<>();
        arg1.put("", "2");
        UserInput expected1 = new UserInput("foo", arg1);
        expected1.setCategory("");
        assertEquals(p.parse(test1).getArgs(), expected1.getArgs());
        assertEquals(p.parse(test1).getCategory(), expected1.getCategory());
        assertEquals(p.parse(test1).getCommand(), expected1.getCommand());

        // Space & Repetition test
        String test2 = "fOo 2 2 2";
        HashMap<String, String> arg2 = new HashMap<>();
        arg2.put("", "2 2 2");
        UserInput expected2 = new UserInput("fOo", arg2);
        expected2.setCategory("");
        assertEquals(p.parse(test2).getArgs(), expected2.getArgs());
        assertEquals(p.parse(test2).getCategory(), expected2.getCategory());
        assertEquals(p.parse(test2).getCommand(), expected2.getCommand());

        // Category and extra argument test
        String test3 = "hr do something /a1 2 3";
        HashMap<String, String> arg3 = new HashMap<>();
        arg3.put("", "something");
        arg3.put("a1", "2 3");
        UserInput expected3 = new UserInput("do", arg3);
        expected3.setCategory("hr");
        assertEquals(p.parse(test3).getArgs(), expected3.getArgs());
        assertEquals(p.parse(test3).getCategory(), expected3.getCategory());
        assertEquals(p.parse(test3).getCommand(), expected3.getCommand());

        // Capitalization & trimming test
        String test4 = "  eVeNt AdD     mYeVenT     9000  /  arg  some  arg /a2 /a3 another";
        HashMap<String, String> arg4 = new HashMap<>();
        arg4.put("", "mYeVenT     9000");
        arg4.put("arg", "some  arg");
        arg4.put("a2", "");
        arg4.put("a3", "another");
        UserInput expected4 = new UserInput("AdD", arg4);
        expected4.setCategory("event");
        assertEquals(p.parse(test4).getArgs(), expected4.getArgs());
        assertEquals(p.parse(test4).getCategory(), expected4.getCategory());
        assertEquals(p.parse(test4).getCommand(), expected4.getCommand());

        // Shorthand category, Accented Character and Argument Precedence test
        String test5 = "  f testCase\350  \350" +
                "/  arg  some  arg / arg /arg                                     precedence test";
        HashMap<String, String> arg5 = new HashMap<>();
        arg5.put("", "\350");
        arg5.put("arg", "precedence test");
        UserInput expected5 = new UserInput("testCase\350", arg5);
        expected5.setCategory("finance");
        assertEquals(p.parse(test5).getArgs(), expected5.getArgs());
        assertEquals(p.parse(test5).getCategory(), expected5.getCategory());
        assertEquals(p.parse(test5).getCommand(), expected5.getCommand());
    }
}
