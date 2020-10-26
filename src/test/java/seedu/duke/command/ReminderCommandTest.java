package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReminderCommandTest {
    UserData data = new UserData();
    Ui ui = new Ui();
    Storage storage = new Storage("data", ui);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_withoutEvents_printNoEvents() throws DukeException {
        ReminderCommand testWithEvents = new ReminderCommand();
        testWithEvents.execute(data, ui, storage);
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have no events today!");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void execute_withEvents_printEventsToday() throws DukeException {
        LocalDate current = LocalDate.now();

        String input = "timetable wakeup; " + current.toString() + "; 1000";
        Command addTimetableEvent = new AddCommand(input);
        addTimetableEvent.execute(data, ui, storage);

        ReminderCommand testWithEvents = new ReminderCommand();
        testWithEvents.execute(data, ui, storage);
        LocalDate printDate = DateTimeParser.dateParser(current.toString());
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[T][✕] wakeup on " + printDate + ", 10:00");
        printWriter.println("You have the following events today: ");
        printWriter.println("[T][✕] wakeup on " + printDate + ", 10:00");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void execute_withRepeatedEvents_printEventsToday() throws DukeException {
        LocalDate current = LocalDate.now();
        LocalDate last = current.minusWeeks(1);

        String input = "timetable wakeup; " + last.toString() + "; 1000";
        Command addTimetableEvent = new AddCommand(input);
        addTimetableEvent.execute(data, ui, storage);
        String repeatInput = "timetable 1 weekly 1";
        Command repeatCommand = RepeatCommand.parse(repeatInput);
        repeatCommand.execute(data, ui, storage);
        ReminderCommand testWithEvents = new ReminderCommand();
        testWithEvents.execute(data, ui, storage);
        LocalDate printLastDate = DateTimeParser.dateParser(last.toString());
        LocalDate printDate = DateTimeParser.dateParser(current.toString());
        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[T][✕] wakeup on " + printLastDate + ", 10:00");
        printWriter.println("[T][✕] wakeup on " + printLastDate + ", 10:00");
        printWriter.println("is now repeating weekly for 1 times.");
        printWriter.println("_________________________________");
        printWriter.println("You have the following events today: ");
        printWriter.println("[T][✕] wakeup on " + printDate + ", 10:00");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }
}
