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

class DeleteCommandTest {
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
        String deleteLesson = "/mon /1";

        assertThrows(NotLoggedInException.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand(deleteLesson);
            deleteCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_invalidDay_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String deleteLesson = "/hey /2";

        assertThrows(WhereGotTimeException.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand(deleteLesson);
            deleteCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_noSuchClass_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String deleteLesson = "/fri /2";

        assertThrows(WhereGotTimeException.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand(deleteLesson);
            deleteCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_invalidIndex_expectWhereGotTimeException() {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String deleteLesson = "/fri /a";

        assertThrows(WhereGotTimeException.class, () -> {
            DeleteCommand deleteCommand = new DeleteCommand(deleteLesson);
            deleteCommand.execute(users, ui, nowUser);
        });
    }


    @Test
    void execute_successfulDeletion() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();
        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);

        String addInput = "/CS2113 /Mon /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String deleteLesson = "/mon /1";

        DeleteCommand deleteCommand = new DeleteCommand(deleteLesson);
        deleteCommand.execute(users, ui, nowUser);

        System.setOut(new PrintStream(outContent));

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("Got it! I've added the following event on mon");
        pw.println("CS2113 LT14 0900-1200");
        pw.println("Noted. I have removed this class from your timetable:");
        pw.println("CS2113 LT14 0900-1200");
        pw.println("Now you have 0 class(es) for mon in the timetable.");
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