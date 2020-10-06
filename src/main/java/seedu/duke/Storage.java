package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.Todo;
import seedu.duke.task.Lab;
import seedu.duke.task.Tutorial;
import seedu.duke.task.Lecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the local file used to store the task list data.
 */
public class Storage {

    private static final int TYPE = 0;
    private static final int DESCRIPTION = 2;
    private static final int DATE = 3;
    private static final int TIME = 4;
    private static final int IS_DONE = 1;
    private static ArrayList<Task> taskArrayList;
    private static String filePath;
    private static LocalDate date;
    public static int countFileTasks = 0;

    /**
     * Constructor of the Storage class.
     * Initialize file f and file path, if f does not exists, creat a new file f.
     *
     * @param filePath the path that is the destination of the file.
     * @throws IOException throws Exception when fail to create a file
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    /**
     * Creates a new storage file if the file does not exists.
     *
     * @param output Storage file.
     */
    private static void createFile(File output) {
        try {
            if (output.exists()) {
                return;
            }
            if (!output.getParentFile().exists()) {
                output.getParentFile().mkdirs();
            }
            output.createNewFile();
        } catch (IOException e) {
            Ui.printFileCreateErrorMessage(e);
        }
    }

    /**
     * Write the data from taskList into file.
     *
     * @param tasks the taskList that the data is stored during running the program.
     */
    public static void writeToFile(TaskList tasks) {
        try {
            File output = new File(filePath);
            createFile(output);
            FileWriter fw = new FileWriter(output);
            taskArrayList = tasks.getTaskList();
            for (Task task: taskArrayList) {
                fw.write(task.printIntoFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    /**
     * Read data from file and store the data into the taskList.
     *
     * @param tasks A taskList that store the data read from file.
     */
    public static int readFromFile(TaskList tasks) throws FileNotFoundException {
        File input = new File(filePath);
        createFile(input);

        try {
            Scanner sc = new Scanner(input);
            Task task;
            while (sc.hasNext()) {
                String[] taskInFile = sc.nextLine().split("\\|");
                if (taskInFile[TYPE].equals("T")) {
                    task = new Todo(taskInFile[DESCRIPTION]);
                } else if (taskInFile[TYPE].equals("D")) {
                    date = LocalDate.parse(taskInFile[DATE].trim());
                    task = new Deadline(taskInFile[DESCRIPTION], date);
                } else if (taskInFile[TYPE].equals("E")) {
                    date = LocalDate.parse(taskInFile[DATE].trim());
                    task = new Event(taskInFile[DESCRIPTION], date);
                } else if (taskInFile[TYPE].equals("LEC")) {
                    task = new Lecture(taskInFile[DESCRIPTION], taskInFile[DATE], taskInFile[TIME]);
                } else if (taskInFile[TYPE].equals("TUT")) {
                    task = new Tutorial(taskInFile[DESCRIPTION], taskInFile[DATE], taskInFile[TIME]);
                } else {
                    task = new Lab(taskInFile[DESCRIPTION], taskInFile[DATE], taskInFile[TIME]);
                }
                countFileTasks++;
                if (taskInFile[IS_DONE].equals("true")) {
                    task.markAsDone();
                }
                tasks.addTask(task);
            }
        } catch (Exception e) {
            System.out.println("Wrong format of date");
        }
        return countFileTasks;
    }
}
