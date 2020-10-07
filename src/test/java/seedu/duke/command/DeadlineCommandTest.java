package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineCommandTest {
    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data");

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withDate_printDeadline() {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        DeadlineCommand testDeadlineWithDateOnly = new DeadlineCommand("1; 7/10/20");
        testDeadlineWithDateOnly.execute(data, ui, storage);
        String addOutput = "You have successfully added this event to your list!\n[P][✕] sleep\n";
        String dividerline = "_________________________________\n";
        String deadlineOutput = "You have successfully updated the deadline for this event!\n";
        String event = "[P][✕] sleep on 2020-10-07";
        assertEquals(addOutput + dividerline + deadlineOutput + event,
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void execute_withDateAndTime_printDeadline() {
        String input = "personal sleep";
        Command addPersonalEvent = new AddCommand(input.split(" "));
        addPersonalEvent.execute(data, ui, storage);

        DeadlineCommand testDeadlineWithDateOnly = new DeadlineCommand("1; 7/10/20; 11:20 PM");
        testDeadlineWithDateOnly.execute(data, ui, storage);
        String addOutput = "You have successfully added this event to your list!\n[P][✕] sleep\n";
        String dividerline = "_________________________________\n";
        String deadlineOutput = "You have successfully updated the deadline for this event!\n";
        String event = "[P][✕] sleep on 2020-10-07, 23:20";
        assertEquals(addOutput + dividerline + deadlineOutput + event,
                outputStreamCaptor.toString().trim());
    }

}