package seedu.duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    private Ui ui;

    private Storage storage;

    private UserData data;

    private Parser currentParse;
    private InputStream stdin = System.in;
    private ByteArrayInputStream inStream;


    @BeforeEach
    public void setupComponents() {

        String inputString = "add personal birthday; 03/01/2001; 0000; | list personal | delete personal 1\r\n";
        inStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(inStream);
        Scanner scan = new Scanner(System.in);

        ui = new Ui();
        storage = new Storage("mainTest", ui);
        data = new UserData();
        currentParse = new Parser();

    }

    @Test
    public void multiParser_multipleValidCommands_allCommandsRun() {

        System.setOut(new PrintStream(outputStreamCaptor));

        try {
            //setting input stream to be the string


            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs= currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }

            assertTrue(true);
        } catch (DukeException e) {
            fail("Should not have any errors in executing commands");
        } finally {
            System.setIn(stdin);
        }


    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
