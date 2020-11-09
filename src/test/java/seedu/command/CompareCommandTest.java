package seedu.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.exception.NotLoggedInException;
import seedu.exception.WhereGotTimeException;
import seedu.ui.Ui;
import seedu.user.User;
import seedu.user.UserList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CompareCommandTest {
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
        String command = "compare";

        assertThrows(NotLoggedInException.class, () -> {
            CompareCommand compareCommand = new CompareCommand(command);
            compareCommand.execute(users, ui, nowUser);
        });
    }

    @Test
    void execute_invalidIndex() throws WhereGotTimeException {
        UserList users = new UserList();
        Ui ui = new Ui();

        User nowUser = new User("devtest", "123123");
        users.addUser(nowUser);
        String addInput = "/CS2113 Lec /Mon /0900-1200 /LT14";
        Command addCommand = new AddCommand(addInput);
        addCommand.execute(users, ui, nowUser);

        String command = "compare";
        String userIndex = "x";

        InputStream in = new ByteArrayInputStream(userIndex.getBytes());
        System.setIn(in);

        assertThrows(WhereGotTimeException.class, () -> {
            CompareCommand compareCommand = new CompareCommand(command);
            compareCommand.execute(users, ui, nowUser);
        });
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}