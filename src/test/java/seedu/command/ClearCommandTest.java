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

class ClearCommandTest {
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
        String clearDay = "/mon";

        assertThrows(NotLoggedInException.class, () -> {
            ClearCommand clearCommand = new ClearCommand(clearDay);
            clearCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_invalidDay_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String clearDay = "/hey";

        assertThrows(WhereGotTimeException.class, () -> {
            ClearCommand clearCommand = new ClearCommand(clearDay);
            clearCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_singleDay_emptyTimetable_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String clearDay = "/fri";

        assertThrows(WhereGotTimeException.class, () -> {
            ClearCommand clearCommand = new ClearCommand(clearDay);
            clearCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_allDays_emptyTimetable_expectWhereGotTimeException() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String clearDay = "/all";

        ClearCommand clearCommand = new ClearCommand(clearDay);
        clearCommand.execute(users, ui, nowUser);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Your timetable is empty. There is nothing to clear.");

        String expected = sw.toString().replaceAll("\r", "");

        assertEquals(expected, outContent.toString().replaceAll("\r", ""));
    }

    @Test
    void execute_successfulClearing() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String addInput = "/CS2113 Lec /Mon /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String clearDay = "/mon";

        ClearCommand clearCommand = new ClearCommand(clearDay);
        clearCommand.execute(users, ui, nowUser);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Got it! I've added the following event on mon");
        pw.println("CS2113 Lec LT14 0900-1200");
        pw.println("I have removed this class from your mon timetable:");
        pw.println("\t1. CS2113 Lec LT14 0900-1200");
        pw.println("Your mon timetable has been cleared.");
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