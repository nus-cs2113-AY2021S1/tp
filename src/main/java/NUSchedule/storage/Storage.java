package NUSchedule.Storage;

import NUSchedule.Exception.CreatingFileException;
import NUSchedule.Exception.LoadingException;
import NUSchedule.Exception.WritingFileException;
import NUSchedule.Task.Task;
import NUSchedule.Task.Todo;

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
     * Save the data of the task list to the file.
     *
     * @param tasks the list of tasks provided by a variable from a TaskList object
     * @throws WritingFileException represents the file is not correctly written
     */
    public void writeFile(ArrayList<Task> tasks) throws WritingFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.fileString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new WritingFileException();
        }
    }

    /**
     * Prepares the data in the file as an ArrayList, which is used to construct the TaskList.
     *
     * @return the tasks in an ArrayList
     * @throws LoadingException represents the <code>tasks</code> is not correctly created
     */
    public ArrayList<Task> load() throws LoadingException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File dataFile = new File(filePath);
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String[] words = s.nextLine().split(REGEX_IN_FILE);
                switch (words[0]) {
                    case "T":
                        tasks.add(new Todo(words[2]));
                        if (Integer.parseInt(words[1]) == 1) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                        break;
                    case "E":
                        try {
                            tasks.add(new NUSchedule.Task.PersonalEvent(words[2], LocalDateTime.parse(words[3])));
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new LoadingException();
                        }
                        if (Integer.parseInt(words[1]) == 1) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                        break;
                    case "D":
                        try {
                            tasks.add(new NUSchedule.Task.Assignment(words[2], LocalDateTime.parse(words[3])));
                        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
                            throw new LoadingException();
                        }
                        if (Integer.parseInt(words[1]) == 1) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                        break;
                    default:
                        throw new LoadingException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IndexOutOfBoundsException e) {
            throw new LoadingException();
        }
        return tasks;
    }
}
