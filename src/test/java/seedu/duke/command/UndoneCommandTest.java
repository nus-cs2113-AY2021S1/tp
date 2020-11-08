package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidEventDateException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.MissingSemicolonException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UndoneCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final UserData data = new UserData();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("data", ui);

    @BeforeEach
    void setUp() throws DukeException {
        // Add Personal events to data
        String personalInput = "personal; Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);
        data.getEventList("Personal").getEventByIndex(0).markAsDone();

        personalInput = "personal; Stay at home; 04/05/20";
        addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);
        data.getEventList("Personal").getEventByIndex(1).markAsDone();

        // Add Zoom event to data
        String zoomInput = "zoom; CS2113T tutorial; zoom.com/blahblah; 03/10/2020; 1330";
        addCommand = new AddCommand(zoomInput);
        addCommand.execute(data, ui, storage);
        data.getEventList("Zoom").getEventByIndex(0).markAsDone();

        // Repeat Zoom event
        String repeatZoomInput = "zoom; 1; weekly; 1";
        Command repeatCommand = RepeatCommand.parse(repeatZoomInput);
        repeatCommand.execute(data, ui, storage);
        data.getEventList("Zoom").getEventByIndex(0).getRepeatEventList().get(0).markAsDone();

        //Add Timetable Event to Data
        String timeTableInput = "timetable; Science class; S17; 4/5/2020; 3 pm";
        addCommand = new AddCommand(timeTableInput);
        addCommand.execute(data, ui, storage);
        data.getEventList("Timetable").getEventByIndex(0).markAsDone();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void execute_markValidEventsAsUndone_eventsMarkedUndone() throws DukeException {
        // First valid event
        String inputStringOne = "personal; 1";
        Command undoneCommand  = UndoneCommand.parse(inputStringOne);
        undoneCommand.execute(data, ui, storage);

        String expectedString = "You have successfully marked this event as undone!" + System.lineSeparator()
                + "[P][X] Go out for dinner on 2020-05-05, 12:00";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
        String actualStatus = data.getEventList("Personal").getEventByIndex(0).getStatus();
        assertEquals("X", actualStatus);

        // Second valid event
        String inputStringTwo = "zoom; 1; 10/10/2020";
        undoneCommand  = UndoneCommand.parse(inputStringTwo);
        undoneCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                + "You have successfully marked this event as undone!" + System.lineSeparator()
                + "[Z][X] CS2113T tutorial, Link: zoom.com/blahblah on 2020-10-10, 13:30";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
        actualStatus = data.getEventList("Zoom").getEventByIndex(0).getRepeatEventList().get(0).getStatus();
        assertEquals("X", actualStatus);

        // Third valid event
        String inputStringThree = "timetable; 1; extra accidental entries";
        undoneCommand  = UndoneCommand.parse(inputStringThree);
        undoneCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                + "You have successfully marked this event as undone!" + System.lineSeparator()
                + "[T][X] Science class, Location: S17 on 2020-05-04, 15:00";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
        actualStatus = data.getEventList("Timetable").getEventByIndex(0).getStatus();
        assertEquals("X", actualStatus);

        // Fourth valid event
        String inputStringFour = "zoom; 1; 3/10/2020";
        undoneCommand  = UndoneCommand.parse(inputStringFour);
        undoneCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                + "You have successfully marked this event as undone!" + System.lineSeparator()
                + "[Z][X] CS2113T tutorial, Link: zoom.com/blahblah on 2020-10-03, 13:30";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
        actualStatus = data.getEventList("Zoom").getEventByIndex(0).getStatus();
        assertEquals("X", actualStatus);
    }

    @Test
    void execute_markInvalidEventsAsUndone_correspondingExceptionThrown() {
        // Event index exceeds max event index
        String inputStringOne = "personal; 3";
        Exception firstE = assertThrows(InvalidIndexException.class, () -> {
            Command undoneCommand  = UndoneCommand.parse(inputStringOne);
            undoneCommand.execute(data, ui, storage);
        });

        String expectedStringOne = "Error, no such index is available!";
        String actualStringOne = firstE.getMessage();
        assertEquals(expectedStringOne, actualStringOne);

        // Event does not fall on the provided date
        String inputStringTwo = "zoom; 1; 5/10/2020";
        Exception secondE = assertThrows(InvalidEventDateException.class, () -> {
            Command undoneCommand  = UndoneCommand.parse(inputStringTwo);
            undoneCommand.execute(data, ui, storage);
        });

        String expectedStringTwo = "This event does not occur on this date.";
        String actualStringTwo = secondE.getMessage();
        assertEquals(expectedStringTwo, actualStringTwo);

        // Semicolons not used to separate fields
        String inputStringThree = "personal 1";
        Exception thirdE = assertThrows(MissingSemicolonException.class, () -> {
            Command undoneCommand  = UndoneCommand.parse(inputStringThree);
            undoneCommand.execute(data, ui, storage);
        });

        String expectedStringThree = "Remember to separate input fields with a ';'.";
        String actualStringThree = thirdE.getMessage();
        assertEquals(expectedStringThree, actualStringThree);

        // Event index not given
        String inputStringFour = "zoom;";
        Exception fourthE = assertThrows(WrongNumberOfArgumentsException.class, () -> {
            Command undoneCommand  = UndoneCommand.parse(inputStringFour);
            undoneCommand.execute(data, ui, storage);
        });

        String expectedStringFour = "Event type or index is missing.";
        String actualStringFour = fourthE.getMessage();
        assertEquals(expectedStringFour, actualStringFour);

        // Event index is not an integer
        String inputStringFive = "zoom; a";
        Exception fifthE = assertThrows(WrongNumberFormatException.class, () -> {
            Command undoneCommand  = UndoneCommand.parse(inputStringFive);
            undoneCommand.execute(data, ui, storage);
        });

        String expectedStringFive = "Event index given is not an integer.";
        String actualStringFive = fifthE.getMessage();
        assertEquals(expectedStringFive, actualStringFive);
    }
}