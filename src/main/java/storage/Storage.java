package storage;

import event.Event;
import event.Assignment;
import event.Class;
import event.PersonalEvent;
import exception.CreatingFileException;
import exception.LoadingException;
import exception.WritingFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
     * @param events the list of Events provided by a variable from a EventList object
     * @throws WritingFileException represents the file is not correctly written
     */
    public void writeFile(ArrayList<Event> events) throws WritingFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Event event : events) {
                fw.write(event.fileString());
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
    public ArrayList<Event> loadEvents() throws LoadingException {
        ArrayList<Event> events = new ArrayList<Event>();
        File dataFile = new File(filePath);
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String[] words = s.nextLine().split(REGEX_IN_FILE);
                switch (words[0]) {
                    case "C":
                        events.add(new Class(words[2],LocalDateTime.parse(words[3])));
                        if (Integer.parseInt(words[1]) == 1) {
                            events.get(events.size() - 1).markAsDone();
                        }
                        break;
                    case "A":
                        try {
                            events.add(new Assignment(words[2], LocalDateTime.parse(words[3])));
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new LoadingException();
                        }
                        if (Integer.parseInt(words[1]) == 1) {
                            events.get(events.size() - 1).markAsDone();
                        }
                        break;
                    case "P":
                        try {
                            events.add(new PersonalEvent(words[2], LocalDateTime.parse(words[3])));
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new LoadingException();
                        }
                        if (Integer.parseInt(words[1]) == 1) {
                            events.get(events.size() - 1).markAsDone();
                        }
                        break;
                    default:
                        throw new LoadingException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new LoadingException();
        }
        return events;
    }
}
