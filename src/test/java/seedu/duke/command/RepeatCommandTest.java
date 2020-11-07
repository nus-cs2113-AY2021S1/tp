package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.exception.MissingDeadlineRepeatException;
import seedu.duke.exception.MissingRepeatListException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class RepeatCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private UserData data = new UserData();
    private Ui ui = new Ui();
    private Storage storage = new Storage("data", ui);

    @BeforeEach
    void setupEventLists() throws DukeException {
        // Add Personal event to data
        String personalInput = "personal party; 09/10/2000; 1300";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        personalInput = "personal surprise";
        addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        personalInput = "personal hello; 29/02/2020";
        addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        personalInput = "personal leap ahead; 31/01/2019";
        addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Add zoom event to data
        String zoomInput = "zoom Math class; zoom.com; 09/10/2000; 1300";
        addCommand = new AddCommand(zoomInput);
        addCommand.execute(data, ui, storage);

        //Add Timetable Event to Data
        String timeTableInput = "timetable Science class; S17; 17/10/2000; 1500";
        addCommand = new AddCommand(timeTableInput);
        addCommand.execute(data, ui, storage);


    }


    @Test
    void repeat_personalEventMonthly_personalEventRepeatedMonthly() throws DukeException {


        // Create Repeat Command
        String inputString = "personal; 1; monthly; 4;";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[P][X] party on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating monthly for 4 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //check the dates reported and erase previous output
        inputString = "personal; 1;";
        outputStreamCaptor.reset();

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[P][X] party on 2000-10-09, 13:00 is also on:"
                        + System.lineSeparator()
                        + "    1. 2000-11-09 13:00 [X]" + System.lineSeparator()
                        + "    2. 2000-12-09 13:00 [X]" + System.lineSeparator()
                        + "    3. 2001-01-09 13:00 [X]" + System.lineSeparator()
                        + "    4. 2001-02-09 13:00 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_personalEventMonthlyNoTime_personalEventRepeatedMonthly() throws DukeException {


        // Create Repeat Command
        String inputString = "personal; 3; monthly; 4;";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[P][X] hello on 2020-02-29" + System.lineSeparator()
                        + "is now repeating monthly for 4 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //check the dates reported and erase previous output
        inputString = "personal; 3;";
        outputStreamCaptor.reset();

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[P][X] hello on 2020-02-29 is also on:"
                        + System.lineSeparator()
                        + "    1. 2020-03-29 [X]" + System.lineSeparator()
                        + "    2. 2020-04-29 [X]" + System.lineSeparator()
                        + "    3. 2020-05-29 [X]" + System.lineSeparator()
                        + "    4. 2020-06-29 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_personalEventMonthlyLeapYearFinalDay_personalEventRepeatedMonthly() throws DukeException {


        // Create Repeat Command
        String inputString = "personal; 4; monthly; 24;";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[P][X] leap ahead on 2019-01-31" + System.lineSeparator()
                        + "is now repeating monthly for 24 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //check the dates reported and erase previous output
        inputString = "personal; 4;";
        outputStreamCaptor.reset();

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[P][X] leap ahead on 2019-01-31 is also on:"
                        + System.lineSeparator()
                        + "    1. 2019-02-28 [X]" + System.lineSeparator()
                        + "    2. 2019-03-31 [X]" + System.lineSeparator()
                        + "    3. 2019-04-30 [X]" + System.lineSeparator()
                        + "    4. 2019-05-31 [X]" + System.lineSeparator()
                        + "    5. 2019-06-30 [X]" + System.lineSeparator()
                        + "    6. 2019-07-31 [X]" + System.lineSeparator()
                        + "    7. 2019-08-31 [X]" + System.lineSeparator()
                        + "    8. 2019-09-30 [X]" + System.lineSeparator()
                        + "    9. 2019-10-31 [X]" + System.lineSeparator()
                        + "    10. 2019-11-30 [X]" + System.lineSeparator()
                        + "    11. 2019-12-31 [X]" + System.lineSeparator()
                        + "    12. 2020-01-31 [X]" + System.lineSeparator()
                        + "    13. 2020-02-29 [X]" + System.lineSeparator()
                        + "    14. 2020-03-31 [X]" + System.lineSeparator()
                        + "    15. 2020-04-30 [X]" + System.lineSeparator()
                        + "    16. 2020-05-31 [X]" + System.lineSeparator()
                        + "    17. 2020-06-30 [X]" + System.lineSeparator()
                        + "    18. 2020-07-31 [X]" + System.lineSeparator()
                        + "    19. 2020-08-31 [X]" + System.lineSeparator()
                        + "    20. 2020-09-30 [X]" + System.lineSeparator()
                        + "    21. 2020-10-31 [X]" + System.lineSeparator()
                        + "    22. 2020-11-30 [X]" + System.lineSeparator()
                        + "    23. 2020-12-31 [X]" + System.lineSeparator()
                        + "    24. 2021-01-31 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_personalPrintRepeatNoExist_MissingRepeatListExceptionThrown() throws DukeException {


        try {
            // Create Repeat Command
            String inputString = "personal; 4;";
            System.setOut(new PrintStream(outputStreamCaptor));

            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
            fail("This command should have thrown an exception");
        } catch (MissingRepeatListException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("The wrong exception type was thrown");
        }

    }

    @Test
    void repeat_zoomEventWeekly_zoomEventRepeatedWeekly() throws DukeException {

        //creating repeat command
        String inputString = "zoom; 1; weekly; 3;";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[Z][X] Math class, Link: zoom.com on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating weekly for 3 times." + System.lineSeparator(),
                outputStreamCaptor.toString());
        //check the dates reported and erase previous output
        inputString = "zoom; 1;";
        outputStreamCaptor.reset();

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[Z][X] Math class, Link: zoom.com on 2000-10-09, 13:00 is also on:"
                        + System.lineSeparator()
                        + "    1. 2000-10-16 13:00 [X]" + System.lineSeparator()
                        + "    2. 2000-10-23 13:00 [X]" + System.lineSeparator()
                        + "    3. 2000-10-30 13:00 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());

    }

    @Test
    void repeat_timeTableEventDaily_timeTableEventRepeatedDaily() throws DukeException {

        //creating repeat command
        String inputString = "timetable; 1; daily; 3;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[T][X] Science class, Location: S17 on 2000-10-17, 15:00" + System.lineSeparator()
                        + "is now repeating daily for 3 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //check the dates reported and erase previous output
        inputString = "timetable; 1;";
        outputStreamCaptor.reset();

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[T][X] Science class, Location: S17 on 2000-10-17, 15:00 is also on:"
                        + System.lineSeparator()
                        + "    1. 2000-10-18 15:00 [X]" + System.lineSeparator()
                        + "    2. 2000-10-19 15:00 [X]" + System.lineSeparator()
                        + "    3. 2000-10-20 15:00 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_personalEventRepeatDailyMonthlyChange_personalEventDailyToMonthly() throws DukeException {

        //create repeat command for daily
        String inputString = "personal; 1; daily; 4;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[P][X] party on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating daily for 4 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //clear screen and now test changing repeat from daily 4 to monthly 2
        outputStreamCaptor.reset();
        inputString = "personal; 1; monthly; 2;";

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[P][X] party on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating monthly for 2 times." + System.lineSeparator(),
                outputStreamCaptor.toString());

        //clear screen and check if monthly repeats were successfully recorded
        outputStreamCaptor.reset();
        inputString = "personal; 1;";

        repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);

        assertEquals("[P][X] party on 2000-10-09, 13:00 is also on:"
                        + System.lineSeparator()
                        + "    1. 2000-11-09 13:00 [X]" + System.lineSeparator()
                        + "    2. 2000-12-09 13:00 [X]" + System.lineSeparator(),
                outputStreamCaptor.toString());



    }

    @Test
    void repeat_repeatingEventNoDeadline_missingDeadlineExceptionThrown() {
        //create repeat command for event with no deadline
        String inputString = "personal; 2; daily; 4;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);



        assertThrows(MissingDeadlineRepeatException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
        });




    }

    @Test
    void repeat_repeatingEventWrongIndex_indexOutOfBoundsExceptionThrown() {
        //create repeat command for an index which does not exist
        String inputString = "personal; 5; daily; 4;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);



        assertThrows(InvalidIndexException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
        });


    }

    @Test
    void repeat_repeatingEventWrongUnit_invalidTimeUnitExceptionThrown() {
        //create repeat command for event using wrong time unit
        String inputString = "personal; 1; fortnightly; 4;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);



        assertThrows(InvalidTimeUnitException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
        });

    }

    @Test
    void repeat_tooLittleArgumentsProvided_wrongNumberOfArgumentsExceptionThrown() {
        //create repeat command for event with too little information
        String inputString = "personal;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);


        assertThrows(WrongNumberOfArgumentsException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
        });

    }

    @Test
    void repeat_repeatingEventWrongType_invalidEventListTypeExceptionThrown() {

        //create repeat command for an event type that does not exist
        String inputString = "holiday; 5; daily; 4;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);



        assertThrows(InvalidListException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
        });
    }

    @Test
    void repeat_userTypeWrongNumber_NumberFormatExceptionThrown() {

        //create repeat command using wrong number writing format
        String inputString = "personal; 1; weekly; three;";
        PrintStream outputLoc = new PrintStream(outputStreamCaptor);
        System.setOut(outputLoc);


        assertThrows(WrongNumberFormatException.class, () -> {
            Command repeatCommand = RepeatCommand.parse(inputString);
            repeatCommand.execute(data, ui, storage);
        });

    }


}