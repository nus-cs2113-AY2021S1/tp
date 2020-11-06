package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.data.TaskMap;
import seedu.task.Priority;
import seedu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "data.json";
    private static final String TIMETABLE = "nusmods_calendar.ics";
    private final Gson gson = new Gson();

    /**
     * Write to file the latest data of tasks.
     *
     * @param tasks latest TaskList object after modification.
     */
    public void writeTasksToFile(TaskMap tasks) throws IOException {
        FileWriter file = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME);
        for (Task task : tasks.getValues()) {
            file.write(gson.toJson(task) + System.lineSeparator());
        }
        file.close();
    }

    /**
     * Load data from file and add tasks to TaskList.
     */
    public TaskMap loadTasks() throws IOException, ParseException {
        // If both dir and file are newly created, return empty taskMap.
        if (!createDirectory()) {
            TaskMap taskMap = readTasksFromFile();
            readTasksFromTimetable(taskMap);
            return taskMap;
        }
        return new TaskMap();
    }

    /**
     * Create a directory if the "data" directory does not exists.
     *
     * @return true if directory is created at the point of execution.
     */
    private boolean createDirectory() throws IOException {
        File directory = new File(DIRECTORY_NAME);

        if (!directory.exists()) {
            boolean directoryCreated = directory.mkdir();
            assert directoryCreated;
            boolean fileCreated = new File(DIRECTORY_NAME + "/" + FILE_NAME).createNewFile();
            assert fileCreated;
            return true;
        }
        return false;
    }

    /**
     * Read lines from file and process each line.
     */
    private TaskMap readTasksFromFile() throws FileNotFoundException {
        TaskMap tasks = new TaskMap();
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        Scanner scanner = new Scanner(file);
        Type type = new TypeToken<Task>(){}.getType();
        while (scanner.hasNextLine()) {
            tasks.addTask(gson.fromJson(scanner.nextLine(), type));
        }
        restartReminders(tasks);
        return tasks;
    }

    private void restartReminders(TaskMap tasks) {
        for (Task t : tasks.getValues()) {
            if (t.getReminder().getIsOn()) {
                t.reminder.startReminder(t);
            }
        }
    }


    private void readTasksFromTimetable(TaskMap taskMap) throws FileNotFoundException, ParseException {
        File file = new File(DIRECTORY_NAME + "/" + TIMETABLE);
        if (file.exists()) {
            Task task;
            String description;
            LocalDate localDate = LocalDate.now();
            LocalTime startTime = LocalTime.now();
            LocalTime endTime = LocalTime.now();
            Priority priority;

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String temp;
                if (currentLine.startsWith("DTSTART:")) {
                    temp = currentLine.replace("DTSTART:", "");
                    DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
                    Date date = df.parse(temp);
                    localDate = date.toInstant().atZone(ZoneId.of("+16")).toLocalDate();
                    startTime = date.toInstant().atZone(ZoneId.of("+16")).toLocalTime();
                } else if (currentLine.startsWith("DTEND")) {
                    temp = currentLine.replace("DTEND:", "");
                    DateFormat df = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
                    Date date = df.parse(temp);
                    endTime = date.toInstant().atZone(ZoneId.of("+16")).toLocalTime();
                } else if (currentLine.startsWith("SUMMARY:")) {
                    priority = Priority.LOW;
                    description = currentLine.replace("SUMMARY:", "");
                    if (description.contains("Exam")) {
                        priority = Priority.HIGH;
                    }
                    task = new Task(description, localDate, startTime, endTime, priority);
                    addTaskToTaskmap(taskMap, task);
                }
            }
        }
    }

    private void addTaskToTaskmap(TaskMap taskMap, Task task) {
        int weeksPerSem = 13;
        int recessWeek = 7;
        int daysPerWeek = 7;
        if (task.getDescription().contains("Exam")) {
            taskMap.addTask(task);
        } else {
            for (int i = 0; i <= weeksPerSem; i++) {
                if (i == recessWeek - 1) {
                    continue;
                }
                LocalDate date = task.getDate().plusDays(i * daysPerWeek);
                Task tempTask = new Task(task.getDescription(), date,
                        task.getStartTime(), task.getEndTime(), task.getPriority());
                taskMap.addTask(tempTask);
            }
        }
    }
}
