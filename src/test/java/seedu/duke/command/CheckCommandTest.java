package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidTimePeriodException;
import seedu.duke.exception.MissingSemicolonException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final UserData data = new UserData();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("data", ui);

    @BeforeEach
    void setupEventList() throws DukeException {
        // Add Personal events to data
        String personalInput = "personal; Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        personalInput = "personal; Stay at home; 04/05/20";
        addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Add Zoom event to data
        String zoomInput = "zoom; CS2113T tutorial; zoom.com/blahblah; 03/10/2020; 1330";
        addCommand = new AddCommand(zoomInput);
        addCommand.execute(data, ui, storage);

        // Repeat Zoom event
        String repeatZoomInput = "zoom; 1; weekly; 1";
        Command repeatCommand = RepeatCommand.parse(repeatZoomInput);
        repeatCommand.execute(data, ui, storage);

        //Add Timetable Event to Data
        String timeTableInput = "timetable; Science class; S17; 4/5/2020; 3 pm";
        addCommand = new AddCommand(timeTableInput);
        addCommand.execute(data, ui, storage);

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void execute_someEventsInTimeRange_printEventsInTimeRange() throws DukeException {
        // Check 4 May 2020 1:15 pm to 5 May 2:30 pm
        String inputString = "04/05/20; 13:15; 05/05/20; 14:30";

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        String expectedString = "Here is a list of your coinciding events:" + System.lineSeparator()
                + "1. [P][X] Go out for dinner on 2020-05-05, 12:00" + System.lineSeparator()
                + "2. [P][X] Stay at home on 2020-05-04" + System.lineSeparator()
                + "3. [T][X] Science class, Location: S17 on 2020-05-04, 15:00";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_repeatedEventInsideTimeRange_printEventInTimeRange() throws DukeException {
        // Check 10 Oct 2020 12 pm to 10 Oct 2020 5 pm
        String inputString = "10/10/2020; 12 pm; 10/10/20; 17";

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        String expectedString = "Here is a list of your coinciding events:" + System.lineSeparator()
                + "1. [Z][X] CS2113T tutorial, Link: zoom.com/blahblah on 2020-10-10, 13:30";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_eventsOutsideTimeRange_printEventsInTimeRange() throws DukeException {
        // Check 20 Oct 2020 1 pm to current date and time
        String inputStringOne = "20/10/20; 13:00; ; ";

        Command checkCommand  = new CheckCommand(inputStringOne);
        checkCommand.execute(data, ui, storage);

        String expectedString = "You have no coinciding events!";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());

        // Check 15 Oct 2020 1 pm to current date in 2020 at current time
        String inputStringTwo = "15/10/20; 13:00; 20; ";

        checkCommand  = new CheckCommand(inputStringTwo);
        checkCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                +  "You have no coinciding events!";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_semicolonsNotUsedToSeparateFields_missingSemicolonExceptionThrown() {
        // Execute check command
        String inputString = "9/10/2020 3 pm 10/10/20 5 pm";

        Exception e = assertThrows(MissingSemicolonException.class, () -> {
            Command checkCommand  = new CheckCommand(inputString);
            checkCommand.execute(data, ui, storage);
        });

        String expectedMessage = "Remember to separate input fields with a ';'." + System.lineSeparator()
                + "The format for check is: \"check [<START_DATE>]; [<START_TIME>]; [<END_DATE>]; [<END_TIME>]\".";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void execute_notEnoughFieldsFilled_WrongNumberOfArgumentsExceptionThrown() {
        // Execute check command
        String inputString = "9/10/2020; 3 pm";

        Exception e = assertThrows(WrongNumberOfArgumentsException.class, () -> {
            Command checkCommand  = new CheckCommand(inputString);
            checkCommand.execute(data, ui, storage);
        });

        String expectedMessage = "Insufficient fields provided to check events. "
                + "Remember to put a semicolon even for blank fields." + System.lineSeparator()
                + "The format for check is: \"check [<START_DATE>]; [<START_TIME>]; [<END_DATE>]; [<END_TIME>]\".";
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void execute_invalidDateFormatGiven_DateErrorExceptionThrown() {
        // dots used instead of slashes or dashes
        String inputStringOne = "9/10.2020; 3 pm; 10.10.2020; 5 pm";

        assertThrows(DateErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringOne);
            checkCommand.execute(data, ui, storage);
        });

        // comma used instead of slashes or dashes
        String inputStringTwo = "9/10/2020; 3 pm; 10,10,2020; 5 pm";

        assertThrows(DateErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringTwo);
            checkCommand.execute(data, ui, storage);
        });

        // Excess fields for date
        String inputStringThree = "5/9/10/2020; 3 pm; 10/10/2020; 5 pm";

        assertThrows(DateErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringThree);
            checkCommand.execute(data, ui, storage);
        });
    }

    @Test
    void execute_invalidTimeFormatGiven_TimeErrorExceptionThrown() {
        // dot used in time instead of colon
        String inputStringOne = "9/10/2020; 3.00 pm; 10/10/2020; 5.00 pm";

        Exception firstE = assertThrows(TimeErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringOne);
            checkCommand.execute(data, ui, storage);
        });

        String expectedMessage = "Something is wrong with the time!" + System.lineSeparator()
                + "The accepted formats are:" + System.lineSeparator()
                + "(12 hour) hh:mm am/pm, hhmm am/pm, hh am/pm or " + System.lineSeparator()
                + "(24 hour) HH:mm, HHmm, HH.";
        String actualMessage = firstE.getMessage();
        assertEquals(expectedMessage, actualMessage);

        // time has excess fields
        String inputStringTwo = "9/10/2020; 3:00 pm; 10/10/2020; 5:00 PST pm";

        Exception secondE = assertThrows(TimeErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringTwo);
            checkCommand.execute(data, ui, storage);
        });

        actualMessage = secondE.getMessage();
        assertEquals(expectedMessage, actualMessage);

        // >12 integer used in 12 hour format
        String inputStringThree = "9/10/2020; 13 pm; 10/10/2020; 5:00 pm";

        Exception thirdE = assertThrows(TimeErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringThree);
            checkCommand.execute(data, ui, storage);
        });

        actualMessage = thirdE.getMessage();
        assertEquals(expectedMessage, actualMessage);

        // >24 integer used in 24 hour format
        String inputStringFour = "9/10/2020; 2500; 10/10/2020; 5:00 pm";

        Exception fourthE = assertThrows(TimeErrorException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringFour);
            checkCommand.execute(data, ui, storage);
        });

        actualMessage = fourthE.getMessage();
        assertEquals(expectedMessage, actualMessage);

        // start time input is after end time
        String inputStringFive = "; ; 1/1/2019; 5:00 pm";

        Exception fifthE = assertThrows(InvalidTimePeriodException.class, () -> {
            Command checkCommand  = new CheckCommand(inputStringFive);
            checkCommand.execute(data, ui, storage);
        });

        expectedMessage = "The start of the time period should be earlier than the end.";
        actualMessage = fifthE.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}