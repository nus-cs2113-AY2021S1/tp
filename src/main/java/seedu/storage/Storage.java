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
import java.util.Scanner;
import seedu.parser.icalParser;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static seedu.messages.Messages.NO_SUCH_FILE;
public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "data.json";
    private final Gson gson = new Gson();
    private final String pathName = "./data";
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
            CalReader(taskMap);
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
        return tasks;
    }
    public void CalReader(TaskMap taskMap) {
        try {
            calenderChecker(taskMap);
        } catch (IOException | ParseException e) {
            System.err.println("Directory not there!" + e.getMessage());
        }
    }
    public void calenderChecker(TaskMap taskMap) throws IOException, ParseException {
        String inputPathName="nusmods_calendar.ics";
        File dirFile = new File(this.pathName);
        Priority priority;
        if (dirFile.isDirectory()) {
            File calfile = new File(pathName + "/"+inputPathName);
            String output=icalParser.lineExtractor(calfile);
            String[] splitInputs = output.split("UID:");
            LocalTime startTime;
            LocalTime endTime;
            Task task;
            LocalTime[] Duration = new LocalTime[2];
            int repeatCount=0;
            ArrayList<LocalDate> Exc;
            ArrayList<LocalDate> Dates;
            String taskDescription;
            for(int i=1;i<splitInputs.length;i++)
            {
                if(splitInputs[i].contains("RRULE:")) {
                    repeatCount=icalParser.countExtractor(splitInputs[i]);
                }
                else {
                    repeatCount=1;
                }
                if(repeatCount==1)
                {
                    priority = Priority.HIGH;
                }
                else {
                    priority=Priority.MEDIUM;
                }
                taskDescription=icalParser.descriptionExtractor(splitInputs[i]);
                Exc =icalParser.exceptionExtractor(splitInputs[i]);
                Dates=icalParser.dateExtractor(splitInputs[i], Exc,repeatCount);
                Duration=icalParser.timeExtractor(splitInputs[i]);
                startTime=Duration[0];
                endTime=Duration[1];
                taskPrinter(taskMap,Dates,startTime,endTime,taskDescription,repeatCount,priority);
            }
        }
        else {
            System.out.println(NO_SUCH_FILE);
        }
    }
    public void taskPrinter (TaskMap taskMap,ArrayList<LocalDate> Dates,LocalTime startTime,LocalTime endTime,String description,int repeatCount,Priority priority)
    {
        Task task;
        for(int i=0;i<repeatCount;i++)
        {
            task = new Task(Dates.get(i),startTime,endTime,description,priority);
            taskMap.addTask(task);
        }
    }


}
