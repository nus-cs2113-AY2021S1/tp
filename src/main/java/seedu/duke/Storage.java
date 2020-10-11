package seedu.duke;

import seedu.duke.calendar.CalendarItem;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Activity;
import seedu.duke.calendar.event.Event;
import seedu.duke.calendar.event.Lab;
import seedu.duke.calendar.event.Lecture;
import seedu.duke.calendar.event.Tutorial;
import seedu.duke.calendar.task.Deadline;
import seedu.duke.calendar.task.Exam;
import seedu.duke.calendar.task.Task;
import seedu.duke.calendar.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the local file used to store the task list data.
 */
public class Storage {

    private static final int TYPE = 0;
    private static final int TASK_IS_DONE = 1;
    private static final int TASK_DESCRIPTION = 2;
    private static final int TASK_DATE = 3;

    private static final int EVENT_MODULE_CODE = 2;
    private static final int DETAILS = 2;
    private static final int EVENT_IS_OVER = 1;
    private static final int EVENT_DATE = 3;
    private static final int EVENT_TIME = 4;
    private static final int EVENT_VENUE = 5;

    private static ArrayList<CalendarItem> taskArrayList;
    private static String filePath;
    public static int countFileTasks = 0;

    /**
     * Constructor of the Storage class.
     * Initialize file f and file path, if f does not exists, creat a new file f.
     *
     * @param filePath the path that is the destination of the file.
     */
    public Storage(String filePath) {
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
     * @param calendarList the calendar list that the data is stored during running the program.
     */
    public static void writeToFile(CalendarList calendarList) {
        try {
            File output = new File(filePath);
            createFile(output);
            FileWriter fw = new FileWriter(output);
            taskArrayList = calendarList.getCalendarList();
            for (CalendarItem item : taskArrayList) {
                fw.write(item.printIntoFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    /**
     * Read data from file and store the data into the taskList.
     *
     * @param calendarList A taskList that store the data read from file.
     */
    public static void readFromFile(CalendarList calendarList) throws FileNotFoundException {
        LocalDate date;
        LocalTime time;
        File input = new File(filePath);
        createFile(input);
        Scanner sc = new Scanner(input);
        CalendarItem item = null;
        while (sc.hasNext()) {
            String[] taskInFile = sc.nextLine().split("\\|");
            switch (taskInFile[TYPE]) {
            case "T":
                item = new Todo(taskInFile[TASK_DESCRIPTION]);
                break;
            case "D":
                date = LocalDate.parse(taskInFile[TASK_DATE].trim());
                item = new Deadline(taskInFile[TASK_DESCRIPTION], date);
                break;
            case "ACT":
                date = LocalDate.parse(taskInFile[EVENT_DATE].trim());
                time = LocalTime.parse(taskInFile[EVENT_TIME].trim());
                item = new Activity(taskInFile[DETAILS], date, time, taskInFile[EVENT_VENUE]);
                break;
            case "LEC":
                date = LocalDate.parse(taskInFile[EVENT_DATE].trim());
                time = LocalTime.parse(taskInFile[EVENT_TIME].trim());
                item = new Lecture(taskInFile[EVENT_MODULE_CODE], date, time, taskInFile[EVENT_VENUE]);
                break;
            case "TUT":
                date = LocalDate.parse(taskInFile[EVENT_DATE].trim());
                time = LocalTime.parse(taskInFile[EVENT_TIME].trim());
                item = new Tutorial(taskInFile[EVENT_MODULE_CODE], date, time, taskInFile[EVENT_VENUE]);
                break;
            case "LAB":
                date = LocalDate.parse(taskInFile[EVENT_DATE].trim());
                time = LocalTime.parse(taskInFile[EVENT_TIME].trim());
                item = new Lab(taskInFile[EVENT_MODULE_CODE], date, time, taskInFile[EVENT_VENUE]);
                break;
            case "EXAM":
                date = LocalDate.parse(taskInFile[EVENT_DATE].trim());
                time = LocalTime.parse(taskInFile[EVENT_TIME].trim());
                item = new Exam(taskInFile[EVENT_MODULE_CODE], date, time, taskInFile[EVENT_VENUE]);
                break;
            default:
                System.out.println("Invalid file command input");
            }
            countFileTasks++;
            if (taskInFile[TASK_IS_DONE].equals("true")) {
                if (item instanceof Task) {
                    ((Task) item).markAsDone();
                }
            }
            if (item instanceof Task) {
                calendarList.addTask((Task) item);
            } else if (item instanceof Event) {
                calendarList.addEvent((Event) item);
            }
        }
    }
}
