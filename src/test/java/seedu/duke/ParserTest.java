package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.backend.Parser;
import seedu.duke.backend.UserInput;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParser() {
        // Base Case
        Parser p = new Parser();

        HashMap<String, String> arg1 = new HashMap<>();
        arg1.put("", "2");
        UserInput expected1 = new UserInput("foo", arg1);
        expected1.setCategory("");
        String test1 = "foo 2";
        assertEquals(p.parse(test1).getArgs(), expected1.getArgs());
        assertEquals(p.parse(test1).getCategory(), expected1.getCategory());
        assertEquals(p.parse(test1).getCommand(), expected1.getCommand());

        // Space & Repetition test

        HashMap<String, String> arg2 = new HashMap<>();
        arg2.put("", "2 2 2");
        UserInput expected2 = new UserInput("fOo", arg2);
        expected2.setCategory("");
        String test2 = "fOo 2 2 2";
        assertEquals(p.parse(test2).getArgs(), expected2.getArgs());
        assertEquals(p.parse(test2).getCategory(), expected2.getCategory());
        assertEquals(p.parse(test2).getCommand(), expected2.getCommand());

        // Category and extra argument test
        HashMap<String, String> arg3 = new HashMap<>();
        arg3.put("", "something");
        arg3.put("a1", "2 3");
        UserInput expected3 = new UserInput("do", arg3);
        expected3.setCategory("hr");
        String test3 = "hr do something /a1 2 3";
        assertEquals(p.parse(test3).getArgs(), expected3.getArgs());
        assertEquals(p.parse(test3).getCategory(), expected3.getCategory());
        assertEquals(p.parse(test3).getCommand(), expected3.getCommand());

        // Capitalization & trimming test
        HashMap<String, String> arg4 = new HashMap<>();
        arg4.put("", "mYeVenT     9000");
        arg4.put("arg", "some  arg");
        arg4.put("a2", "");
        arg4.put("a3", "another");
        UserInput expected4 = new UserInput("AdD", arg4);
        expected4.setCategory("event");
        String test4 = "  eVeNt AdD     mYeVenT     9000  /  arg  some  arg /a2 /a3 another";
        assertEquals(p.parse(test4).getArgs(), expected4.getArgs());
        assertEquals(p.parse(test4).getCategory(), expected4.getCategory());
        assertEquals(p.parse(test4).getCommand(), expected4.getCommand());

        // Shorthand category, Accented Character and Argument Precedence test
        HashMap<String, String> arg5 = new HashMap<>();
        arg5.put("", "\350");
        arg5.put("arg", "precedence test");
        UserInput expected5 = new UserInput("testCase\350", arg5);
        expected5.setCategory("finance");
        String test5 = "  f testCase\350  \350 "
                + "/  arg  some  arg / arg /arg                                     precedence test";
        assertEquals(p.parse(test5).getArgs(), expected5.getArgs());
        assertEquals(p.parse(test5).getCategory(), expected5.getCategory());
        assertEquals(p.parse(test5).getCommand(), expected5.getCommand());
    }
}
