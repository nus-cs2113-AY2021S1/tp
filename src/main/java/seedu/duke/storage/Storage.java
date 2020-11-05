package seedu.duke.storage;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Goal;
import seedu.duke.exception.InvalidListException;
import seedu.duke.ui.Ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Logger logger = Logger.getLogger("storageLog");
    private static FileHandler fh;

    /**
     * Returns the path to the file specified by the user.
     *
     * @param name Name of the event type stored in the file that will be returned
     * @return path to the file storing the event type.
     */
    public Path getFileLocation(String name) {
        switch (name) {
        case "Personal":
            return filePersonalPath;
        case "Zoom":
            return fileZoomPath;
        case "Timetable":
            return fileTimeTablePath;
        case "Goal":
            return fileGoalPath;
        default:
            ui.printErrorMessage("Error! No such file exists");
            return null;

        }
    }

    /**
     * Creates a new storage manager that can load and save files to and from a given filepath.
     *
     * @param initPath is the name of the filepath which files are saved to and loaded from
     */
    public Storage(String initPath, Ui ui) {

        //firstly, make string representation of storage files
        //Directory words only contain info on making the folder
        //File words contain the info on how to make the file itself

        String logging = initPath + "logging.txt";
        try {
            fh = new FileHandler(logging);
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
            logger.fine("Logger created");
        } catch (IOException e) {
            ui.printErrorMessage("log file was not created");
        }
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

    /**
     * Creates a txt file for the event if it does not exists yet.
     *
     * @param fileText path object of the file to be created if non-existent
     * @param fileType String of the type of event data this file will store
     */
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

    /**
     * Saves All information onto the computer.
     *
     * @param data UserData object where all the current user event information is stored
     */
    public void saveAll(UserData data) {
        saveFile(filePersonalPath, data, "Personal");
        logger.fine("personal file saved");
        saveFile(fileTimeTablePath, data, "Timetable");
        logger.fine("timetable file saved");
        saveFile(fileZoomPath, data, "Zoom");
        logger.fine("zoom file saved");
        saveFile(fileGoalPath, data, "Goal");
        logger.fine("goal file saved");


    }

    /**
     * Saves information to a specified file.
     *
     * @param fileName Path object of the file for information to be saved currently
     * @param data UserData object where all the current user event information is stored
     * @param fileType Name of the information type that will be saved.
     */
    public void saveFile(Path fileName, UserData data, String fileType) {

        try {
            //firstly, form a temporary List of strings to store the data
            ArrayList<String> toBeWritten = new ArrayList<>();

            if (fileType.equals("Goal")) { //special case for goal
                goalSave(fileName, data, toBeWritten);
            } else { //special case for event
                eventSave(fileName, data, fileType, toBeWritten);
            }



        } catch (InvalidListException e) {
            System.out.println("Error! List invalid type. Should not happen");
        } catch (IOException e) {
            ui.printErrorMessage("Error! File cannot be written to");
        }

    }

    /**
     * Helper function for saving event information.
     *
     * @param fileName location on the computer where the event data is to be saved at
     * @param data UserData object containing all user information currently stored in the program
     * @param fileType String indicating what type of event we are storing
     * @param toBeWritten String ArrayList object which stores all test to be written to computer fileName
     *
     * @throws InvalidListException if there is no such event type stored in the program
     * @throws IOException if there are problems encountered while writing to the file
     */
    private void eventSave(Path fileName, UserData data, String fileType, ArrayList<String> toBeWritten)
            throws InvalidListException, IOException {
        //next, read out event by event and process it into a storable string
        EventList listOfEvents = data.getEventList(fileType);
        ArrayList<Event> events = listOfEvents.getEvents();

        for (Event event:events) {
            String entry = StorageParser.eventToString(event, fileType);
            toBeWritten.add(entry);
        }

        Files.write(fileName, toBeWritten);
    }

    /**
     * Helper function for saving goal information.
     *
     * @param fileName path of the goal.txt file to save goal information
     * @param data UserData object containing all user information currently stored in the program
     * @param toBeWritten String ArrayList object which stores all test to be written to computer fileName
     * @throws IOException if there are problems encountered while writing to the file
     */
    private void goalSave(Path fileName, UserData data, ArrayList<String> toBeWritten) throws IOException {
        Goal entry = data.getGoal();
        if (entry != null) {
            toBeWritten.add(entry.toString());
            Files.write(fileName, toBeWritten);
        } else { //nothing to modify in toBeWritten, so write a blank file
            Files.write(fileName, toBeWritten);
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

            //Extraction of goal
            if (fileType.equals("Goal")) {
                if (fileLines.size() != 0) {
                    Goal prevGoal = new Goal(fileLines.get(0));
                    data.setGoal(prevGoal);
                }
                return;
            }

            //extraction of all other events
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

    /**
     * Function loads files that are meant to be system resources such as images or text files.
     *
     * @param fileName location of the file in string form. Start from the root using the slash symbol
     * @param resource String ArrayList to store the information
     */
    public void loadSystemResources(String fileName, ArrayList<String> resource) {
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;

            while ((line = reader.readLine()) != null) {
                resource.add(line);
            }
        } catch (IOException e) {
            ui.printErrorMessage("Resource file could not be loaded!");
        }
    }

}
