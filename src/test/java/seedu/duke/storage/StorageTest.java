package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.data.UserData;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StorageTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();

        Storage store = new Storage("storagetester", ui);
        UserData data = new UserData();
        store.loadAll(data);
        String inputString = "personal";
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void storageLoadAll_LoadFilesFromDirectory_allFilesLoaded() throws DukeException {
        Storage store = new Storage("storagetester", ui);
        UserData data = new UserData();
        Ui ui = new Ui();
        store.loadAll(data);
        String inputString = "personal";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("The file has successfully been loaded!" + System.lineSeparator()
                        + "Here is a list of your Personal events:" + System.lineSeparator()
                        + "1. [P][✕] stuff on 2010-01-01, 12:00" + System.lineSeparator()
                        + "   Repeated weekly for 4 times." + System.lineSeparator()
                        + "2. [P][✓] birthday celebration on 2010-01-01, 12:00" + System.lineSeparator()
                        + "3. [P][✕] others" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "zoom";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Zoom events:" + System.lineSeparator()
                        + "1. [Z][✕] math, Link: www.zoom.com/blah" + System.lineSeparator()
                        + "   Repeated daily for 4 times." + System.lineSeparator()
                        + "2. [Z][✓] computing, Link: www.zoom.com/hello" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "timetable";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Timetable events:" + System.lineSeparator()
                        + "1. [T][✕] math, Location: S17 on 2010-01-01, 12:00" + System.lineSeparator()
                        + "   Repeated monthly for 4 times." + System.lineSeparator()
                        + "2. [T][✓] computing, Location: COM2 on 2010-01-01, 12:00" + System.lineSeparator()
                        + "_________________________________" + System.lineSeparator(),
                outputStreamCaptor.toString());


    }

    @Test
    void storageSaveAll_saveFilesIntoComputer_allFilesSaved() {
        try {
            String[] modelPersonalLoc = {"storagetestermodelans", "personal.txt"};
            String[] modelZoomLoc = {"storagetestermodelans", "zoom.txt"};
            String[] modelTimetableLoc = {"storagetestermodelans", "timetable.txt"};

            Path personalPath = createPath(modelPersonalLoc);
            Path zoomPath = createPath(modelZoomLoc);
            Path timetablePath = createPath(modelTimetableLoc);

            final List<String> personalModel = Files.readAllLines(personalPath);
            final List<String> zoomModel = Files.readAllLines(zoomPath);
            final List<String> timetableModel = Files.readAllLines(timetablePath);

            Storage store = new Storage("storagetester", ui);
            UserData data = new UserData();
            Ui ui = new Ui();
            store.loadAll(data);
            store.saveAll(data);

            String[] actualPersonalLoc = {"storagetester", "personal.txt"};
            String[] actualZoomLoc = {"storagetester", "zoom.txt"};
            String[] actualTimetableLoc = {"storagetester", "timetable.txt"};

            Path actualPersonalPath = createPath(actualPersonalLoc);
            Path actualZoomPath = createPath(actualZoomLoc);
            Path actualTimetablePath = createPath(actualTimetableLoc);

            List<String> personalActual = Files.readAllLines(actualPersonalPath);
            List<String> zoomActual = Files.readAllLines(actualZoomPath);
            List<String> timetableActual = Files.readAllLines(actualTimetablePath);

            assertEquals(personalActual, personalModel);
            assertEquals(zoomActual, zoomModel);
            assertEquals(timetableActual, timetableModel);


        } catch (IOException e) {
            fail("IO error! File was not written to");
        }






    }

    /**
     * Function accepts a string and creates a path object originating from the user directory.
     *
     * @param pathName is a string array which accepts in the path name words, each word represents a folder
     * @return Path object indicating the location of the pathName keyed in initially.
     */
    private Path createPath(String[] pathName) {

        String origin = System.getProperty("user.dir");
        Path newPath = Paths.get(origin, pathName);
        return newPath;
    }


}