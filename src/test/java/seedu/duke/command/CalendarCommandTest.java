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

public class CalendarCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_multipleEvents_printCalendar() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        // Add zoom events to data
        String zoomInput1 = "zoom Math class; zoom.com; 17/10/20; 1300";
        new AddCommand(zoomInput1).execute(data, ui, storage);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[Z][✕] Math class, Link: zoom.com on 2020-10-17, 13:00");
        printWriter.println("_________________________________");
        String zoomInput2 = "zoom Math class 2; zoom.com; 10/10/20; 1500";
        new AddCommand(zoomInput2).execute(data, ui, storage);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[Z][✕] Math class 2, Link: zoom.com on 2020-10-10, 15:00");
        printWriter.println("_________________________________");

        // Add timetable event
        String timetableInput = "timetable english class; school of english; 24/10/20; 8:00";
        new AddCommand(timetableInput).execute(data, ui, storage);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[T][✕] english class, Location: school of english on 2020-10-24, 08:00");
        printWriter.println("_________________________________");

        // Repeat zoom event 2
        String repeatInput = "zoom 2 weekly 3";
        RepeatCommand.parse(repeatInput).execute(data, ui, storage);
        printWriter.println("[Z][✕] Math class 2, Link: zoom.com on 2020-10-10, 15:00");
        printWriter.println("is now repeating weekly for 3 times.");
        printWriter.println("_________________________________");

        // Execute calendar command
        CalendarCommand.parse("").execute(data, ui, storage);
        printWriter.println("Calendar has 4 dates to display.");
        printWriter.println("_________________________________");
        printWriter.println("10 Oct 2020");
        printWriter.println("_________________________________");
        printWriter.println("Z | 3:00 PM | ✕ | Math class 2 | zoom.com");
        printWriter.println("_________________________________");
        printWriter.println("17 Oct 2020");
        printWriter.println("_________________________________");
        printWriter.println("Z | 1:00 PM | ✕ | Math class | zoom.com");
        printWriter.println("Z | 3:00 PM | ✕ | Math class 2 | zoom.com");
        printWriter.println("_________________________________");
        printWriter.println("24 Oct 2020");
        printWriter.println("_________________________________");
        printWriter.println("T | 8:00 AM | ✕ | english class | school of english");
        printWriter.println("Z | 3:00 PM | ✕ | Math class 2 | zoom.com");
        printWriter.println("_________________________________");
        printWriter.println("31 Oct 2020");
        printWriter.println("_________________________________");
        printWriter.println("Z | 3:00 PM | ✕ | Math class 2 | zoom.com");
        printWriter.println("_________________________________");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    void execute_eventWithoutDate_doNotPrintCalendar() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        // Add personal event without date
        String personalInput = "personal to finish project";
        new AddCommand(personalInput).execute(data, ui, storage);
        printWriter.println("You have successfully added this event to your list!");
        printWriter.println("[P][✕] to finish project");
        printWriter.println("_________________________________");

        // Execute calendar command
        CalendarCommand.parse("").execute(data, ui, storage);
        printWriter.println("Calendar has 0 dates to display.");
        printWriter.println("1 event not on the calendar because it has no date.");
        printWriter.println("_________________________________");
        printWriter.close();
        String expected = expectedStringWriter.toString();
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}