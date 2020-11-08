package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Duke;
import seedu.duke.data.UserData;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
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

class DeleteCommandTest {
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
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void execute_deleteValidEvents_eventsAreDeleted() throws DukeException {
        // First valid event
        String inputStringOne = "personal; 1";
        Command deleteCommand  = DeleteCommand.parse(inputStringOne);
        deleteCommand.execute(data, ui, storage);

        String expectedString = "You have successfully deleted this event!" + System.lineSeparator()
                + "[P][X] Go out for dinner on 2020-05-05, 12:00";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());

        // Second valid event
        String inputStringTwo = "zoom; 1; 10/10/2020";
        deleteCommand  = DeleteCommand.parse(inputStringTwo);
        deleteCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                + "You have successfully deleted this event!" + System.lineSeparator()
                + "[Z][X] CS2113T tutorial, Link: zoom.com/blahblah on 2020-10-10, 13:30";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());

        // Third valid event
        String inputStringThree = "timetable; 1; extra accidental entries";
        deleteCommand  = DeleteCommand.parse(inputStringThree);
        deleteCommand.execute(data, ui, storage);

        expectedString = expectedString + System.lineSeparator()
                + "You have successfully deleted this event!" + System.lineSeparator()
                + "[T][X] Science class, Location: S17 on 2020-05-04, 15:00";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());

        expectedString = expectedString + System.lineSeparator()
                + "Here is a list of your Personal events:" + System.lineSeparator()
                + "1. [P][X] Stay at home on 2020-05-04" + System.lineSeparator()
                + "_________________________________" + System.lineSeparator()
                + "You have no Timetable events!" + System.lineSeparator()
                + "_________________________________" + System.lineSeparator()
                + "Here is a list of your Zoom events:" + System.lineSeparator()
                + "1. [Z][X] CS2113T tutorial, Link: zoom.com/blahblah on 2020-10-03, 13:30";
        ListCommand.parse("all").execute(data, ui, storage);
        assertEquals(expectedString, outputStreamCaptor.toString().trim());

    }

    @Test
    void execute_deleteInvalidEvents_correspondingExceptionThrown() {
        // Event index exceeds max event index
        String inputStringOne = "personal; 3";
        Exception firstE = assertThrows(InvalidIndexException.class, () -> {
            Command deleteCommand  = DeleteCommand.parse(inputStringOne);
            deleteCommand.execute(data, ui, storage);
        });

        String expectedStringOne = "Error, no such index is available!";
        String actualStringOne = firstE.getMessage();
        assertEquals(expectedStringOne, actualStringOne);

        // Event does not fall on the provided date
        String inputStringTwo = "zoom; 1; 5/10/2020";
        Exception secondE = assertThrows(DateErrorException.class, () -> {
            Command deleteCommand  = DeleteCommand.parse(inputStringTwo);
            deleteCommand.execute(data, ui, storage);
        });

        String expectedStringTwo = "This event does not occur on this date.";
        String actualStringTwo = secondE.getMessage();
        assertEquals(expectedStringTwo, actualStringTwo);

        // Semicolons not used to separate fields
        String inputStringThree = "personal 1";
        Exception thirdE = assertThrows(MissingSemicolonException.class, () -> {
            Command deleteCommand  = DeleteCommand.parse(inputStringThree);
            deleteCommand.execute(data, ui, storage);
        });

        String expectedStringThree = "Remember to separate input fields with a ';'." + System.lineSeparator()
                + "The format for delete is: \"delete <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".";
        String actualStringThree = thirdE.getMessage();
        assertEquals(expectedStringThree, actualStringThree);

        // Event index not given
        String inputStringFour = "zoom;";
        Exception fourthE = assertThrows(WrongNumberOfArgumentsException.class, () -> {
            Command deleteCommand  = DeleteCommand.parse(inputStringFour);
            deleteCommand.execute(data, ui, storage);
        });

        String expectedStringFour = "Event type or index is missing." + System.lineSeparator()
                + "The format for delete is: \"delete <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".";
        String actualStringFour = fourthE.getMessage();
        assertEquals(expectedStringFour, actualStringFour);

        // Event index is not an integer
        String inputStringFive = "zoom; a";
        Exception fifthE = assertThrows(WrongNumberFormatException.class, () -> {
            Command deleteCommand  = DeleteCommand.parse(inputStringFive);
            deleteCommand.execute(data, ui, storage);
        });

        String expectedStringFive = "Event index given is not an integer." + System.lineSeparator()
                + "The format for delete is: \"delete <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".";
        String actualStringFive = fifthE.getMessage();
        assertEquals(expectedStringFive, actualStringFive);
    }
}