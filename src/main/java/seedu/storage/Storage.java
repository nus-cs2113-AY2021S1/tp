package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.data.TaskMap;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidReminderException;
import seedu.task.Priority;
import seedu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import static seedu.messages.Messages.NO_SUCH_FILE;

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
    public TaskMap loadTasks() throws IOException {
        // If both dir and file are newly created, return empty taskMap.
        if (!createFile()) {
            TaskMap taskMap = readTasksFromFile();
            calReader(taskMap);
            return taskMap;
        }
        return new TaskMap();
    }

    /**
     * Create a directory if the "data" directory does not exists.
     *
     * @return true if directory is created at the point of execution.
     */
    private boolean createFile() throws IOException {
        File directory = new File(DIRECTORY_NAME);
        File data = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        if (!directory.exists()) {
            boolean directoryCreated = directory.mkdir();
            assert directoryCreated;
            return data.createNewFile();
        }

        if (!data.exists()) {
            return data.createNewFile();
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
        Type type = new TypeToken<Task>() {
        }.getType();
        while (scanner.hasNextLine()) {
            tasks.addTask(gson.fromJson(scanner.nextLine(), type));
        }
        restartReminders(tasks);
        return tasks;
    }


    public void calReader(TaskMap taskMap) {
        try {
            calenderChecker(taskMap);
        } catch (IOException | ParseException e) {
            System.err.println("Directory not there!" + e.getMessage());
        } catch (InvalidDatetimeException | InvalidReminderException e) {
            System.err.println("Invalid data format!" + e.getMessage());
        }
    }

    public void calenderChecker(TaskMap taskMap)
        throws IOException, ParseException, InvalidDatetimeException, InvalidReminderException {
        File dirFile = new File(DIRECTORY_NAME);
        Priority priority;
        if (dirFile.isDirectory()) {
            File calfile = new File(DIRECTORY_NAME + "/" + TIMETABLE);
            String output = lineExtractor(calfile);
            String[] splitInputs = output.split("UID:");
            LocalTime startTime;
            LocalTime endTime;
            Task task;
            LocalTime[] taskDuration = new LocalTime[2];
            int repeatCount = 0;
            ArrayList<LocalDate> exceptionDates;
            ArrayList<LocalDate> dates;
            String taskDescription;
            for (int i = 1; i < splitInputs.length; i++) {
                if (splitInputs[i].contains("RRULE:")) {
                    repeatCount = countExtractor(splitInputs[i]);
                } else {
                    repeatCount = 1;
                }
                if (repeatCount == 1) {
                    priority = Priority.HIGH;
                } else {
                    priority = Priority.MEDIUM;
                }
                taskDescription = descriptionExtractor(splitInputs[i]);
                exceptionDates = exceptionExtractor(splitInputs[i]);
                dates = dateExtractor(splitInputs[i], repeatCount);
                taskDuration = timeExtractor(splitInputs[i]);
                startTime = taskDuration[0];
                endTime = taskDuration[1];
                taskPrinter(taskMap, dates, startTime, endTime, taskDescription, repeatCount, priority);
            }
        } else {
            System.out.println(NO_SUCH_FILE);
        }
    }

    public void taskPrinter(TaskMap taskMap, ArrayList<LocalDate> dates,
                            LocalTime startTime, LocalTime endTime,
                            String description, int repeatCount,
                            Priority priority) throws InvalidReminderException, InvalidDatetimeException {
        Task task;
        for (int i = 0; i < repeatCount; i++) {
            task = new Task(description, dates.get(i), startTime, endTime, priority);
            taskMap.addTask(task);
        }
    }


    private void restartReminders(TaskMap tasks) {
        for (Task t : tasks.getValues()) {
            if (t.getReminder().getIsOn()) {
                t.reminder.startReminder(t);
            }
        }
    }


    private void readTasksFromTimetable(TaskMap taskMap)
        throws FileNotFoundException, ParseException, InvalidReminderException, InvalidDatetimeException {
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

    private void addTaskToTaskmap(TaskMap taskMap, Task task)
        throws InvalidReminderException, InvalidDatetimeException {
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
    public static String lineExtractor(File textFile) throws IOException {
        Scanner myReader = new Scanner(textFile);
        String taskData = "";
        while (myReader.hasNextLine()) {
            taskData += ("\n" + myReader.nextLine());
        }
        return taskData;
    }


    public static int countExtractor(String splitted) {
        String[] splitCount = splitted.split("COUNT=");
        String[] lineSplit = splitCount[1].split(";");
        int count = Integer.parseInt(lineSplit[0]);
        return count;
    }

    public static String descriptionExtractor(String splitted) {
        String[] splitCount = splitted.split("SUMMARY:");
        String[] lineSplit = splitCount[1].split("\n");
        return lineSplit[0];
    }
    public static ArrayList<LocalDate> exceptionExtractor(String splitted) throws ParseException {
        ArrayList<LocalDate> exceptionDates = new ArrayList<LocalDate>();
        String[] splitCount = splitted.split("\n");
        for (String i : splitCount) {
            if (i.contains("EXDATE:")) {
                String exDate;
                exDate = i.split("EXDATE:")[1];
                LocalDate startDate = LocalDate.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                exceptionDates.add(startDate);
            }
        }
        return exceptionDates;
    }

    public static ArrayList<LocalDate> dateExtractor(String splitString, int count) throws ParseException {
        String[] splitCount = splitString.split("\n");
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        int tempIndex = 0;
        LocalDate startDate;
        for (String i : splitCount) {
            if (i.contains("DTSTART:")) {
                String exDate;
                exDate = i.split("DTSTART:")[1];
                startDate = LocalDate.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                dates.add(startDate);
                tempIndex++;
            }
        }
        for (int i = 1; i < count; i++) {
            LocalDate nextWeekDate = dates.get(i - 1).plusDays(7);
            boolean isNotException = exceptionChecker(dates, nextWeekDate);
            if (isNotException) {
                dates.add(nextWeekDate);
                tempIndex++;
            }
        }
        return dates;
    }

    public static boolean exceptionChecker(ArrayList<LocalDate> exceptionDates, LocalDate nextWeekDate)
            throws ParseException {
        for (LocalDate i : exceptionDates) {
            if (i.isEqual(nextWeekDate)) {
                return false;
            }
        }
        return true;
    }

    public static LocalTime[] timeExtractor(String splitted) throws ParseException {
        LocalTime startTime;
        LocalTime endTime;
        LocalTime[] taskDuration = new LocalTime[2];
        String[] splitCount = splitted.split("\n");
        for (String i : splitCount) {
            if (i.contains("DTSTART:")) {
                String exDate;
                exDate = i.split("DTSTART:")[1];
                LocalTime gmtTime = LocalTime.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                startTime = (gmtTime).plusHours(8);
                taskDuration[0] = startTime;
            }
            if (i.contains("DTEND:")) {
                String exDate;
                exDate = i.split("DTEND:")[1];
                LocalTime gmtTime = LocalTime.parse(exDate, DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
                endTime = (gmtTime).plusHours(8);
                taskDuration[1] = endTime;
            }
        }
        return taskDuration;
    }
}
