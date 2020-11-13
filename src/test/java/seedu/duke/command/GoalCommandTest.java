package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final Ui ui = new Ui();
    private Storage store = new Storage("goalStoreTest", ui);

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_oneString_printChangeGoal() throws DukeException {
        String inputString = "Fly like a butterfly";
        UserData data = new UserData();
        Ui ui = new Ui();
        Command command = new GoalCommand(inputString);
        command.execute(data, ui, store);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("Goal changed to: " + inputString);
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void execute_blankInput_printGoal() throws DukeException {
        String inputString = "";
        UserData data = new UserData();
        Ui ui = new Ui();
        Command command = new GoalCommand(inputString);
        command.execute(data, ui, store);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have no goal! Why not set one now?");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void execute_twoSeparateStringThenBlankInput_printChangeGoal() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        String inputString1 = "Fly like a butterfly";
        Command command = new GoalCommand(inputString1);
        command.execute(data, ui, store);
        String inputString2 = "Float like a bumblebee";
        command = new GoalCommand(inputString2);
        command.execute(data, ui, store);
        String blankString = "";
        command = new GoalCommand(blankString);
        command.execute(data, ui, store);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("Goal changed to: " + inputString1);
        printWriter.println("Goal changed to: " + inputString2);
        printWriter.println("Goal: " + inputString2);
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}