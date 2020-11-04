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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    private Ui ui;

    private Storage storage;

    private UserData data;

    private Parser currentParse;
    private InputStream stdin = System.in;
    private ByteArrayInputStream inStream;



    public void setupComponents(String inputString) {


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
        String inputString = "add personal birthday; 03/01/2001; 0000; | list personal | delete personal 1\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs = currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }

            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "Here is a list of your Personal events:" + System.lineSeparator()
                            + "1. [P][X] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "You have successfully deleted this event!" + System.lineSeparator()
                            + "[P][X] birthday on 2001-01-03, 00:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail("Should not have any errors in executing commands");
        } finally {
            System.setIn(stdin);
        }

    }

    @Test
    public void multiParser_singleValidCommand_singleCommandsRun() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputString = "add personal birthday; 03/01/2001; 0000;\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs = currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }

            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] birthday on 2001-01-03, 00:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        } catch (DukeException e) {
            fail("Should not have any errors in executing commands");
        } finally {
            System.setIn(stdin);
        }

    }

    @Test
    public void multiParser_singleErrorCommand_singleCommandsRunWithException() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputString = "add personal birthday; 03/01/2001; 0000; | delete 1\r\n";

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs = currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }


        } catch (DukeException e) {
            assertTrue(true);
        } finally {
            System.setIn(stdin);
            assertEquals("_________________________________" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] birthday on 2001-01-03, 00:00" + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

    }

    /*
    @Test
    public void multiParser_extractNoteCommand_commandRunOneAtATime() {

        System.setOut(new PrintStream(outputStreamCaptor));
        String inputExtract = "extract meeting; |";
        String inputEventDetail = "Dont forget your meeting on 27 Jan 2021 at 9am\r\nextractend\r\n";
        String inputNote = "note personal; 1;\r\n";
        String inputNoteDetail = "Meeting is fun!\r\n;\r\n";
        String inputString = inputExtract + inputNote + inputEventDetail + inputNoteDetail;

        try {
            setupComponents(inputString);
            String userInput = ui.receiveCommand();
            ArrayList<String> allCommandInputs = currentParse.multiParse(userInput);
            for (String commInputs : allCommandInputs) {
                ui.printDividerLine();
                Command c = currentParse.parse(commInputs);
                c.execute(data, ui, storage);
            }


        } catch (DukeException e) {
            fail("No exception should occur");
        } finally {
            System.setIn(stdin);
            assertEquals("_________________________________" + System.lineSeparator()
                            + "Copy and paste or enter the body of the text you want to extract from!"
                            + System.lineSeparator()
                            + "At the end of your text, press enter to go to the next line,"
                            + enter \'extractend\' with no quotation marks and press enter once more."
                            + System.lineSeparator()
                            + "_________________________________" + System.lineSeparator()
                            + "One date detected and chosen: 2021-01-27" + System.lineSeparator()
                            + "One timing detected and chosen: 09:00" + System.lineSeparator()
                            + "You have successfully added this event to your list!" + System.lineSeparator()
                            + "[P][X] meeting on 2021-01-27, 09:00" + System.lineSeparator(),
                    outputStreamCaptor.toString());
        }

    } */

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}
