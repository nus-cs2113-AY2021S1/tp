package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.EventList;
import seedu.duke.event.Personal;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void execute_eventInTimeRange_printEventsInTimeRange() {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data");

        // Add events to data
        String personalInput = "personal Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Execute check command
        String inputString = "check 04/05/20; 13:00; 05/05/20; 14:00";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        EventList personalList =  data.getEventList("Personal");
        assertEquals(personalList.getEvents().get(0).toString(), outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_eventOutsideTimeRange_printEventsInTimeRange() {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data");

        // Add events to data
        String personalInput = "personal Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Execute check command
        String inputString = "check 04/05/20; 13:00; 04/05/20; 14:00";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        assertEquals("There are no events going on during that period.", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}