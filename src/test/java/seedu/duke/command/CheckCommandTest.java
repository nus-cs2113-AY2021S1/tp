package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.event.EventList;
import seedu.duke.event.Personal;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    void execute_eventInTimeRange_printEventsInTimeRange() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add events to data
        String personalInput = "personal Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Execute check command
        String inputString = "04/05/20; 13:00; 05/05/20; 14:00";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        EventList personalList =  data.getEventList("Personal");
        String expectedString = "Here is a list of your coinciding events:" + System.lineSeparator()
                + "1. [P][X] Go out for dinner on 2020-05-05, 12:00" + System.lineSeparator()
                + "_________________________________";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_eventOutsideTimeRange_printEventsInTimeRange() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add events to data
        String personalInput = "personal Go out for dinner; 05/05/20; 12:00";
        Command addCommand = new AddCommand(personalInput);
        addCommand.execute(data, ui, storage);

        // Execute check command
        String inputString = "04/05/20; 13:00; 04/05/20; 14:00";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command checkCommand  = new CheckCommand(inputString);
        checkCommand.execute(data, ui, storage);

        String expectedString = "You have no coinciding events!" + System.lineSeparator()
                + "_________________________________";
        assertEquals(expectedString, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}