package seedu.duke.storage;

import seedu.duke.event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Saves and loads the events list to and from an external txt file.
 */
public class Storage {
    public Path fileNamePath;
    public Path fileDirectoryPath;

    /**
     * Creates a new storage manager that can load and save files to and from a given filepath.
     *
     * @param initPath is the name of the filepath which files are saved to and loaded from
     */
    public Storage(String initPath) {
        String locationOfFileName = initPath + ",data.txt";

        //Directory words only contain info on making the folder
        //File words contain the info on how to make the file itself
        String[] pathDirectoryWords = initPath.split(",");
        String[] pathFileWords = initPath.split(",");

        fileDirectoryPath = createPath(pathDirectoryWords);
        fileNamePath = createPath(pathFileWords);
    }

    /**
     * Creates a folder for the events list if it does not exist yet.
     */
    protected void initialiseFolder() {
        if (!Files.exists(fileNamePath)) {
            try {
                Files.createDirectory(fileDirectoryPath);
            } catch (IOException e) {
                System.out.println("IO exception encountered when creating data directory.");
            }
        }
    }

    /**
     * Saves events to an external txt file.
     *
     * @param fileName is the file to save events to
     * @param eventsList is the events list to be saved to an external txt file
     * @throws IOException if the named file does not exist and cannot be created, or cannot be opened
     */
    public void saveFile(String fileName, ArrayList<Event> eventsList) throws IOException {
        FileWriter fw = new FileWriter(fileName);
    }

    /**
     * Loads events from an external txt file.
     *
     * @param fileName is the file to load events from
     * @throws FileNotFoundException if no file with the given fileName is found
     */
    public void loadFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<String> eventInfo;
        ArrayList<Event> loadedEventsList = new ArrayList<>();
    }

    private Path createPath(String[] pathName) {

        String origin = System.getProperty("user.dir");
        Path newPath = Paths.get(origin, pathName);
        return newPath;
    }
}
