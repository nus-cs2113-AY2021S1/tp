package seedu.duke.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepeatCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private UserData data = new UserData();
    private Ui ui = new Ui();
    private Storage storage = new Storage("data");

    @BeforeEach
    void setupEventLists() {
        // Add Personal event to data
        String personalInput = "personal party; 09/10/2000; 1300";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        //Add Zoom event to data
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
    void repeat_personalEventMonthly_personalEventRepeatedMonthly() {


        // Create Repeat Command
        String inputString = "personal 1 monthly 4";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[P][✕] party on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating monthly for 4 times." + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_zoomEventWeekly_zoomEventRepeatedWeekly() {

        //creating repeat command
        String inputString = "zoom 1 weekly 3";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[Z][✕] Math class, Link: zoom.com on 2000-10-09, 13:00" + System.lineSeparator()
                        + "is now repeating weekly for 3 times." + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }

    @Test
    void repeat_timeTableEventDaily_timeTableEventRepeatedDaily() {

        //creating repeat command
        String inputString = "timetable 1 daily 3";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command repeatCommand = RepeatCommand.parse(inputString);
        repeatCommand.execute(data, ui, storage);
        assertEquals("[T][✕] Science class, Location: S17 on 2000-10-17, 15:00" + System.lineSeparator()
                        + "is now repeating daily for 3 times." + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());
    }


}