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

class ListCommandTest {
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
        String listDay = "/mon";

        assertThrows(NotLoggedInException.class, () -> {
            ListCommand listCommand = new ListCommand(listDay);
            listCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_invalidDay_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String listDay = "/day";

        assertThrows(WhereGotTimeException.class, () -> {
            ListCommand listCommand = new ListCommand(listDay);
            listCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_noClassFound() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String listDay = "/mon";

        ListCommand listCommand = new ListCommand(listDay);
        listCommand.execute(users, ui, nowUser);

        System.setOut(new PrintStream(outContent));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("There is no class in your timetable for mon!");
        pw.close();

        String expected = sw.toString().replaceAll("\r", "");

        assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    void execute_listDay() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String addInput = "/CS2113 Lec /Mon /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String listDay = "/mon";

        ListCommand listCommand = new ListCommand(listDay);
        listCommand.execute(users, ui, nowUser);

        System.setOut(new PrintStream(outContent));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Got it! I've added the following event on mon");
        pw.println("CS2113 Lec LT14 0900-1200");
        pw.println("Here are the classes in your timetable for mon, sorted according to time:");
        pw.println("    1. CS2113 Lec LT14 0900-1200");
        pw.close();

        String expected = sw.toString().replaceAll("\r", "");

        assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    void execute_listAll() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String addInput = "/CS2113 Lec /Thu /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String addInput3 = "/CG2027 Lec /Wed /1400-1600 /LT15";
        addCommand = new AddCommand(addInput3);
        addCommand.execute(users, ui, nowUser);

        String listDay = "/all";

        ListCommand listCommand = new ListCommand(listDay);
        listCommand.execute(users, ui, nowUser);

        System.setOut(new PrintStream(outContent));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Got it! I've added the following event on thu");
        pw.println("CS2113 Lec LT14 0900-1200");
        pw.println("Got it! I've added the following event on wed");
        pw.println("CG2027 Lec LT15 1400-1600");
        pw.println("There is no class in your timetable for mon!");
        pw.println();
        pw.println("There is no class in your timetable for tue!");
        pw.println();
        pw.println("Here are the classes in your timetable for wed, sorted according to time:");
        pw.println("    1. CG2027 Lec LT15 1400-1600");
        pw.println();
        pw.println("Here are the classes in your timetable for thu, sorted according to time:");
        pw.println("    1. CS2113 Lec LT14 0900-1200");
        pw.println();
        pw.println("There is no class in your timetable for fri!");
        pw.println();
        pw.println("There is no class in your timetable for sat!");
        pw.println();
        pw.println("There is no class in your timetable for sun!");
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