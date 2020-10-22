package seedu.duke.storage;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;
import seedu.duke.exception.InvalidListException;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Saves and loads the events list to and from an external txt file.
 */
public class Storage {

    private Path fileDirectoryPath;
    private Path filePersonalPath;
    private Path fileZoomPath;
    private Path fileTimeTablePath;
    private Path fileGoalPath;

    private Ui ui;


    /**
     * Creates a new storage manager that can load and save files to and from a given filepath.
     *
     * @param initPath is the name of the filepath which files are saved to and loaded from
     */
    public Storage(String initPath, Ui ui) {

        //firstly, make string representation of storage files





        //Directory words only contain info on making the folder
        //File words contain the info on how to make the file itself
        String[] pathDirectoryWords = initPath.split(",");
        fileDirectoryPath = createPath(pathDirectoryWords);

        String personal = initPath + ",personal.txt";
        String[] personalWords = personal.split(",");
        filePersonalPath = createPath(personalWords);

        String zoom = initPath + ",zoom.txt";
        String[] zoomWords = zoom.split(",");
        fileZoomPath = createPath(zoomWords);

        String goal = initPath + ",goal.txt";
        String[] goalWords = goal.split(",");
        fileGoalPath = createPath(goalWords);

        String timeTable = initPath + ",timetable.txt";
        String[] timeTableWords = timeTable.split(",");
        fileTimeTablePath = createPath(timeTableWords);

        initialiseFolder();
        this.ui = ui;

    }

    /**
     * Creates a folder for the events list if it does not exist yet.
     */
    protected void initialiseFolder() {
        if (!Files.exists(fileDirectoryPath)) {
            try {
                Files.createDirectory(fileDirectoryPath);
            } catch (IOException e) {
                System.out.println("IO exception encountered when creating data directory.");
            }
        }
    }

    private void initialiseFile(Path fileText, String fileType) {

        if (!Files.exists(fileText)) {
            try {

                Files.createFile(fileText);
                System.out.println("File Created: " + fileType);
            } catch (IOException e) {
                ui.printErrorMessage("IO exception error! File cannot be created on system!");
            }
        }
    }


    public void saveAll(UserData data) {
        saveFile(filePersonalPath, data, "Personal");
        saveFile(fileTimeTablePath, data, "Timetable");
        saveFile(fileZoomPath, data, "Zoom");
        saveFile(fileGoalPath, data, "Goal");


    }

    public void saveFile(Path fileName, UserData data, String fileType) {

        try {
            //firstly, form a temporary List of strings to store the data
            ArrayList<String> toBeWritten = new ArrayList<>();

            if (fileType.equals("Goal")) { //special case for goal
                Goal entry = data.getGoal();
                if (entry == null) { //nothing to write
                    return;
                }
                toBeWritten.add(entry.toString());
                Files.write(fileName, toBeWritten);
                return;
            }

            //next, read out event by event and process it into a storable string
            EventList listOfEvents = data.getEventList(fileType);
            ArrayList<Event> events = listOfEvents.getEvents();

            for (Event event:events) {
                String entry = StorageParser.eventToString(event, fileType);
                toBeWritten.add(entry);
            }

            Files.write(fileName, toBeWritten);

        } catch (InvalidListException e) {
            System.out.println("Error! List invalid type. Should not happen");
        } catch (IOException e) {
            ui.printErrorMessage("Error! File cannot be written to");
        }

    }

    /**
     * Loads every single data file into the program.
     *
     * @param data UserData structure with all the user information stored
     */
    public void loadAll(UserData data) {
        loadFile(filePersonalPath, data, "Personal");
        loadFile(fileZoomPath, data, "Zoom");
        loadFile(fileTimeTablePath, data, "Timetable");
        loadFile(fileGoalPath, data, "Goal");

        ui.printStorageLoadMessage();

    }

    /**
     * Loads events from an external txt file.
     *
     * @param fileName is the file to load events from
     * @throws FileNotFoundException if no file with the given fileName is found
     */
    public void loadFile(Path fileName, UserData data, String fileType) {

        try {

            //First, extract out all the file information
            this.initialiseFile(fileName, fileType);
            List<String> fileLines = Files.readAllLines(fileName);

            //Next, line by line reform the event

            //special case for goal
            if (fileType.equals("Goal")) {
                if (fileLines.size() != 0) {
                    Goal prevGoal = new Goal(fileLines.get(0));
                    data.setGoal(prevGoal);
                }
                return;
            }
            for (int i = 0; i < fileLines.size(); i++) {
                String line = fileLines.get(i);
                Event activity = StorageParser.stringToEvent(line,fileType);
                if (activity == null) {
                    continue;
                }
                data.addToEventList(fileType, activity);
            }

            //finally, store the information in the correct list
        } catch (IOException e) {
            //do nothing
            ui.printStorageLoadingErrorMessage();
        } catch (InvalidListException e) {
            //do nothing for now
            System.out.println("Error, invalid list");
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

    /**
     * Function gives a string containing the Directory location.
     *
     * @return String containing directory location
     */
    public String getDirectoryString() {
        return fileDirectoryPath.toString();
    }


}
