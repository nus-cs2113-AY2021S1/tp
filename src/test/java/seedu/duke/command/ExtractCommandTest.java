package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ExtractCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void execute_extractAndCreateEvent_printSuccessfulAdd() throws DukeException {
        UserData data = new UserData();
        Ui ui = new Ui();
        Storage storage = new Storage("data", ui);

        // Add timetable event to data
        String extractInput = "CG2271 Quiz; Hi class, please note there will be a quiz on 4th October 2020 or Oct 5 2020 at 4pm";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        Command extractCommand = new ExtractCommand(extractInput);
        extractCommand.execute(data, ui, storage);

        /*String input = "2";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            //Scanner scanner = new Scanner(System.in);
            //System.out.println(scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }*/


        assertEquals("_________________________________ " + System.lineSeparator()
                        + "We have detected 2 dates in this text body!" + System.lineSeparator()
                        + "Please select the date you want for this event from the list below!" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator()
                        + "1. 2020-10-04" + System.lineSeparator()
                        + "2. 2020-10-05" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator()
                        + "One time slot detected and chosen: 16:00" + System.lineSeparator()
                        + "You have successfully added this event to your list!" + System.lineSeparator()
                        + "[P][✕] CG2271 Quiz on 2020-10-05, 16:00" + System.lineSeparator()
                        + "_________________________________",
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}