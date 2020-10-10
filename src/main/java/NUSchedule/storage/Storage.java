package NUSchedule.storage;


import NUSchedule.event.Event;
import NUSchedule.exception.CreatingFileException;
import NUSchedule.exception.LoadingException;
import NUSchedule.exception.WritingFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates the folder and file path if it's not already created, and
 * prepare the data in the file to be used.
 */
public class Storage {
    public static final String REGEX_IN_FILE = "//";
    private final String filePath;

    /**
     * Set the <code>filepath </code> according to the user input.
     *
     * @param filePath is the path of the file
     */
    public Storage(String filePath) throws CreatingFileException {
        this.filePath = filePath;
        createFolderAndFIle(filePath);
    }

    /**
     * Creates the folder and file if not already crated.
     *
     * @param filePath the String of the relative path
     */
    private static void createFolderAndFIle(String filePath) throws CreatingFileException {
        File dataFile = new File(filePath);
        File directory = dataFile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new CreatingFileException(filePath);
        }
    }


    /**
     * Save the data of the Event list to the file.
     *
     * @param Events the list of Events provided by a variable from a EventList object
     * @throws WritingFileException represents the file is not correctly written
     */
    public void writeFile(ArrayList<Event> Events) throws WritingFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Event Event : Events) {
                fw.write(Event.fileString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new WritingFileException();
        }
    }

    /**
     * Prepares the data in the file as an ArrayList, which is used to construct the EventList.
     *
     * @return the Events in an ArrayList
     * @throws LoadingException represents the <code>Events</code> is not correctly created
     */
//    public ArrayList<Event> load() throws LoadingException {
//todo to be implement
//    }
}
