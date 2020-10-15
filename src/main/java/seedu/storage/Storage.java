package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.data.TaskMap;
import seedu.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "data.json";
    private final Gson gson = new Gson();

    /**
     * Write to file the latest data of tasks.
     *
     * @param tasks latest TaskList object after modification.
     */
    public void writeTasksToFile(TaskMap tasks) {
        try (FileWriter file = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME)) {
            for (Task task : tasks.getValues()) {
                file.write(gson.toJson(task.toString() + System.lineSeparator()));
            }
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    /**
     * Load data from file and add tasks to TaskList.
     */
    public void loadTasks(TaskMap tasks) {
        if (!createDirectory()) {
            try {
                readTasksFromFile(tasks);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
        }
    }

    /**
     * Create a directory if the "data" directory does not exists.
     *
     * @return true if directory is created at the point of execution.
     */
    private boolean createDirectory() {
        File directory = new File(DIRECTORY_NAME);
        boolean directoryCreated = false;
        boolean fileCreated = false;
        try {
            if (!directory.exists()) {
                directoryCreated = directory.mkdir();
                fileCreated = new File(DIRECTORY_NAME + "/" + FILE_NAME).createNewFile();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return directoryCreated && fileCreated;
    }

    /**
     * Read lines from file and process each line.
     */
    private void readTasksFromFile(TaskMap tasks) throws FileNotFoundException {
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        Scanner scanner = new Scanner(file);
        Type type = new TypeToken<Task>(){}.getType();
        while (scanner.hasNextLine()) {
            tasks.addTask(gson.fromJson(scanner.nextLine(), type));
        }
    }
}
