package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.GoalCommand;
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

        Storage store = new Storage("src,test,storagetester", ui);
        UserData data = new UserData();
        store.loadAll(data);
        String inputString = "personal";
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void storageLoadAll_LoadFilesFromDirectory_allFilesLoaded() throws DukeException {
        Storage store = new Storage("src,test,storagetester", ui);
        UserData data = new UserData();
        Ui ui = new Ui();
        store.loadAll(data);
        String inputString = "personal";
        System.setOut(new PrintStream(outputStreamCaptor));

        Command listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("The file has successfully been loaded!" + System.lineSeparator()
                        + "Here is a list of your Personal events:" + System.lineSeparator()
                        + "1. [P][X] stuff on 2010-01-01, 12:00 is also on:" + System.lineSeparator()
                        + "    1. 2010-01-08 12:00 [X]" + System.lineSeparator()
                        + "    2. 2010-01-15 12:00 [X]" + System.lineSeparator()
                        + "    3. 2010-01-22 12:00 [O]" + System.lineSeparator()
                        + "    4. 2010-01-29 12:00 [X]" + System.lineSeparator()
                        + "2. [P][O] birthday celebration on 2010-01-01, 12:00" + System.lineSeparator()
                        + "3. [P][X] others" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "zoom";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Zoom events:" + System.lineSeparator()
                        + "1. [Z][X] math, Link: www.zoom.com/blah on 2010-01-01, 12:00 is also on:"
                        + System.lineSeparator()
                        + "    1. 2010-01-02 12:00 [X]" + System.lineSeparator()
                        + "    2. 2010-01-03 12:00 [X]" + System.lineSeparator()
                        + "    3. 2010-01-04 12:00 [O]" + System.lineSeparator()
                        + "    4. 2010-01-05 12:00 [X]" + System.lineSeparator()
                        + "2. [Z][O] computing, Link: www.zoom.com/hello on 2010-01-01, 12:00" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        inputString = "timetable";
        listCommand = ListCommand.parse(inputString);
        listCommand.execute(data, ui, store);

        assertEquals("Here is a list of your Timetable events:" + System.lineSeparator()
                        + "1. [T][X] math, Location: S17 on 2010-01-01, 12:00 is also on:" + System.lineSeparator()
                        + "    1. 2010-02-01 12:00 [X]" + System.lineSeparator()
                        + "    2. 2010-03-01 12:00 [X]" + System.lineSeparator()
                        + "    3. 2010-04-01 12:00 [O]" + System.lineSeparator()
                        + "    4. 2010-05-01 12:00 [X]" + System.lineSeparator()
                        + "2. [T][O] computing, Location: COM2 on 2010-01-01, 12:00" + System.lineSeparator(),
                outputStreamCaptor.toString());

        outputStreamCaptor.reset();

        GoalCommand goalCheck = new GoalCommand("");
        goalCheck.execute(data, ui, store);

        assertEquals("Goal: hello there" + System.lineSeparator(),
                outputStreamCaptor.toString());



    }

    @Test
    void storageSaveAll_saveFilesIntoComputer_allFilesSaved() {
        try {
            String[] modelPersonalLoc = {"src", "test", "storagetestermodelans", "personal.txt"};
            String[] modelZoomLoc = {"src", "test", "storagetestermodelans", "zoom.txt"};
            String[] modelTimetableLoc = {"src", "test", "storagetestermodelans", "timetable.txt"};
            String[] modelGoalLoc = {"src", "test", "storagetestermodelans", "goal.txt"};

            Path personalPath = createPath(modelPersonalLoc);
            Path zoomPath = createPath(modelZoomLoc);
            Path timetablePath = createPath(modelTimetableLoc);
            Path goalPath = createPath(modelGoalLoc);

            final List<String> personalModel = Files.readAllLines(personalPath);
            final List<String> zoomModel = Files.readAllLines(zoomPath);
            final List<String> timetableModel = Files.readAllLines(timetablePath);
            final List<String> goalModel = Files.readAllLines(goalPath);

            Storage store = new Storage("src,test,storagetester", ui);
            UserData data = new UserData();
            Ui ui = new Ui();
            store.loadAll(data);
            store.saveAll(data);

            String[] actualPersonalLoc = {"src", "test", "storagetester", "personal.txt"};
            String[] actualZoomLoc = {"src", "test", "storagetester", "zoom.txt"};
            String[] actualTimetableLoc = {"src", "test", "storagetester", "timetable.txt"};
            String[] actualGoalLoc = {"src", "test", "storagetester", "goal.txt"};

            Path actualPersonalPath = createPath(actualPersonalLoc);
            Path actualZoomPath = createPath(actualZoomLoc);
            Path actualTimetablePath = createPath(actualTimetableLoc);
            Path actualGoalPath = createPath(actualGoalLoc);

            final List<String> personalActual = Files.readAllLines(actualPersonalPath);
            final List<String> zoomActual = Files.readAllLines(actualZoomPath);
            final List<String> timetableActual = Files.readAllLines(actualTimetablePath);
            final List<String> goalActual = Files.readAllLines(actualGoalPath);

            assertEquals(personalActual, personalModel);
            assertEquals(zoomActual, zoomModel);
            assertEquals(timetableActual, timetableModel);
            assertEquals(goalActual, goalModel);


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