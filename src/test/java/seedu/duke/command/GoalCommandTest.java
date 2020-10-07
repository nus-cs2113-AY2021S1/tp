package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GoalCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_oneString_printChangeGoal() {
        String inputString = "Fly like a butterfly";
        UserData data = new UserData();
        Ui ui = new Ui();
        Command command = new GoalCommand(inputString);
        command.execute(data, ui, null);
        assertEquals("Goal changed to: " + inputString, outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_nullInput_printGoal() {
        UserData data = new UserData();
        Ui ui = new Ui();
        Command command = new GoalCommand(null);
        command.execute(data, ui, null);
        assertEquals("You have no goal! Why not set one now?", outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_twoSeparateStringThenNullInput_printChangeGoal() {
        UserData data = new UserData();
        Ui ui = new Ui();
        String inputString1 = "Fly like a butterfly";
        Command command = new GoalCommand(inputString1);
        command.execute(data, ui, null);
        String inputString2 = "Float like a bumblebee";
        command = new GoalCommand(inputString2);
        command.execute(data, ui, null);
        command = new GoalCommand(null);
        command.execute(data, ui, null);
        assertEquals(
                "Goal changed to: " + inputString1 +
                "\r\nGoal changed to: " + inputString2 +
                "\r\nGoal: " + inputString2, outputStreamCaptor.toString().trim());
    }

    @Test

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}