package seedu.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void execute_userNotLoggedIn_expectNotLoggedInException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = null;
        String findClasses = "/Lec";

        assertThrows(NotLoggedInException.class, () -> {
            FindCommand findCommand = new FindCommand(findClasses);
            findCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_noClassesFound() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String findClasses = "/Lec";

        FindCommand findCommand = new FindCommand(findClasses);
        findCommand.execute(users, ui, nowUser);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("None of your classes contain the keyword \"Lec\".");

        String expected = sw.toString().replaceAll("\r", "");

        assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    void execute_classesFound() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String addInput = "/CS2113 Lec /Mon /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String addInput2 = "/CS1231 Lec /Thu /1000-1200 /LT17";
        addCommand = new AddCommand(addInput2);
        addCommand.execute(users, ui, nowUser);

        String addInput3 = "/CG2027 Lec /Wed /1400-1600 /LT15";
        addCommand = new AddCommand(addInput3);
        addCommand.execute(users, ui, nowUser);

        String addInput4 = "/CS2113 Tut /Fri /1300-1400 /E4-04-08";
        addCommand = new AddCommand(addInput4);
        addCommand.execute(users, ui, nowUser);

        String addInput5 = "/CS1231 Tut /Tue /1300-1500 /E4-03-07";
        addCommand = new AddCommand(addInput5);
        addCommand.execute(users, ui, nowUser);

        String findClasses = "/CS2113";

        FindCommand findCommand = new FindCommand(findClasses);
        findCommand.execute(users, ui, nowUser);

        System.setOut(new PrintStream(outContent));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Got it! I've added the following event on mon");
        pw.println("CS2113 Lec LT14 0900-1200");
        pw.println("Got it! I've added the following event on thu");
        pw.println("CS1231 Lec LT17 1000-1200");
        pw.println("Got it! I've added the following event on wed");
        pw.println("CG2027 Lec LT15 1400-1600");
        pw.println("Got it! I've added the following event on fri");
        pw.println("CS2113 Tut E4-04-08 1300-1400");
        pw.println("Got it! I've added the following event on tue");
        pw.println("CS1231 Tut E4-03-07 1300-1500");
        pw.println("Here are the class(es) in your timetable that matches the keyword \"CS2113\":");
        pw.println("1. (Monday) CS2113 Lec LT14 0900-1200");
        pw.println("2. (Friday) CS2113 Tut E4-04-08 1300-1400");
        pw.close();

        String expected = sw.toString().replaceAll("\r", "");

        assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}