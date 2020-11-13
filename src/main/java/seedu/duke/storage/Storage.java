package seedu.duke.storage;

import seedu.duke.Module;
import seedu.duke.exception.DukeException;
import seedu.duke.project.ProjectManager;
import seedu.duke.task.ProjectTask;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.task.Deadline;
import seedu.duke.tasklist.TaskList;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class Storage {
    private static final String projectRoot = System.getProperty("user.dir");
    private static final String directory = projectRoot + "/data";
    private static final String filePathOfTask = directory + "/duke.txt";
    private static final String filePathOfModule = directory + "/module.txt";
    private static final String filePathOfJson = directory + "/AllModsInfo.json";

    public Storage() {
    }

    /**
     * Create directory if the directory does not exist.
     *
     * @param directoryName directory path name
     * @return true if the directory exist or have been successfully created
     *         false if fail to create the directory
     */
    public static boolean createDirectory(String directoryName) {
        boolean result;
        File dir = new File(directoryName);
        if (!dir.exists()) {
            result = dir.mkdirs();
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Create the data file to store task list.
     *
     * @param pathName      the absolute path name of data file
     * @param directoryName the directory path name
     */
    public static void createFile(String pathName, String directoryName) {
        boolean mkdirs = createDirectory(directoryName);
        if (mkdirs) {
            File f = new File(pathName);
        }
    }

    /**
     * Load data file to current task list.
     *
     * @return loaded task list
     * @throws DukeException if the text in data file cannot recognized as a task
     */
    public ArrayList<Task> loadTask() throws DukeException {
        File loadFile = new File(this.filePathOfTask);
        ArrayList<Task> loadList = new ArrayList<>();
        if (!loadFile.exists()) {
            createFile(this.filePathOfTask, directory);
        } else {
            Scanner file = null;
            try {
                file = new Scanner(loadFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                assert file != null;
                if (!file.hasNext()) {
                    break;
                }
                String text = file.nextLine();
                Task taskToLoad = parserTask(text);
                loadList.add(taskToLoad);
            }
            file.close();
            System.out.println("Task list has been loaded successfully.");

        }
        return loadList;
    }

    /**
     * Transfer the line in data file into task to load.
     *
     * @param text each line of the data file
     * @return task need to load
     * @throws DukeException if the text in data file cannot recognized as a task
     */
    private Task parserTask(String text) throws DukeException {
        Task taskToLoad = null;
        String time;
        String modName;
        //split each line into task description, done status and deadline/event time
        String[] texts = text.trim().split(" \\| ");
        String description = texts[2];
        String status = texts[1];
        if (text.startsWith("T")) {
            //Todo
            taskToLoad = new Todo(description);
        } else if (text.startsWith("D")) {
            //Deadline
            taskToLoad = new Deadline(description);
            time = texts[3];
            ((Deadline) taskToLoad).setBy(time);
        } else if (text.startsWith("E")) {
            //Event
            taskToLoad = new Event(description);
            time = texts[3];
            String duration = texts[4];
            ((Event) taskToLoad).setAt(time);
            ((Event) taskToLoad).setDuration(Long.parseLong(duration));
        } else if (text.startsWith("P")) {
            //Project task
            String material = texts[5];
            modName = texts[3];
            time = texts[4];
            taskToLoad = new ProjectTask(modName, description, time, material);
            ProjectTask projectTask = new ProjectTask(modName, description, time, material);
            ProjectManager.projectTasks.add(projectTask);
        } else {
            throw new DukeException();
        }
        if (status.equals("1")) {
            taskToLoad.markAsDone();
        }
        return taskToLoad;
    }

    /**
     * Update done status for the task in file.
     *
     * @param index    the index of task in the list that needs to be marked as done
     * @param taskList the list contains all tasks
     * @throws IOException if cannot open, read or write the file
     */
    public void updateDoneToFile(int index, TaskList taskList) throws IOException {
        File newFile = new File(directory + "/data-new.txt");
        File f = new File(this.filePathOfTask);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        PrintWriter writer = new PrintWriter(newFile);
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            if (lineNum == index) {
                //update done status
                writer.println(taskList.get(index).text());
                writer.flush();
                lineNum++;
                continue;
            }
            lineNum++;
            writer.println(line);
            writer.flush();
        }
        reader.close();
        writer.close();
        //replace original data file with new data file
        f.delete();
        newFile.renameTo(f);
    }

    /**
     * Delete the task from data file.
     *
     * @param index the index of task in the list that needs to be deleted
     * @throws IOException if cannot open, read or write the file
     */
    public static void deleteTaskFromFile(int index) throws IOException {
        File newFile = new File(directory + "/data-new.txt");
        File f = new File(filePathOfTask);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        PrintWriter writer = new PrintWriter(newFile);
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            if (lineNum == index) {
                lineNum++;
                continue;
            }
            lineNum++;
            writer.println(line);
            writer.flush();
        }
        reader.close();
        writer.close();
        //replace original data file with new data file
        f.delete();
        newFile.renameTo(f);
    }

    /**
     * Add new line to the end of data file.
     *
     * @param textToAppend text needs to be added
     * @throws IOException if cannot open and write the file
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePathOfTask, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Load data file to current module list.
     *
     * @return loaded task list
     * @throws DukeException if the text in data file cannot recognized as a module
     */
    public ArrayList<Module> loadModule() throws DukeException {
        File loadFile = new File(this.filePathOfModule);
        ArrayList<Module> loadList = new ArrayList<>();
        if (!loadFile.exists()) {
            createFile(this.filePathOfModule, directory);
        } else {
            Scanner file = null;
            try {
                file = new Scanner(loadFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {
                assert file != null;
                if (!file.hasNext()) {
                    break;
                }
                String text = file.nextLine();
                Module moduleToLoad = parserModule(text);
                loadList.add(moduleToLoad);
            }
            file.close();
            System.out.println("Module list has been loaded successfully.");

        }
        return loadList;
    }

    /**
     * Transfer the line in data file into task to load.
     *
     * @param text each line of the data file
     * @return module need to load
     * @throws DukeException if the text in data file cannot recognized as a task
     */
    private Module parserModule(String text) throws DukeException {
        Module module = new Module();
        //split each line into task description, done status and deadline/event time
        String[] texts = text.split("\\|");
        if (texts.length == 3) {
            module.moduleCode = texts[0];
            module.lecSlot = texts[1];
            module.tutSlot = texts[2];
        } else if (texts.length == 4) {
            module.moduleCode = texts[0];
            module.lecSlot = texts[1];
            module.tutSlot = texts[2];
            module.labSlot = texts[3];
        } else if (texts.length == 5) {
            module.moduleCode = texts[0];
            module.lecSlot = texts[1];
            module.tutSlot = texts[2];
            if (!texts[3].equals("null")) {
                module.labSlot = texts[3];
            } else {
                module.labSlot = null;
            }
            module.lecSlot2 = texts[4];
        }
        module.setSlot();
        return module;
    }

    /**
     * Delete the module from data file.
     *
     * @param index the index of module in the list that needs to be deleted
     * @throws IOException if cannot open, read or write the file
     */
    public static void deleteModuleFromFile(int index) throws IOException {
        File newFile = new File(directory + "/data-new.txt");
        File f = new File(filePathOfModule);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        PrintWriter writer = new PrintWriter(newFile);
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            if (lineNum == index) {
                lineNum++;
                continue;
            }
            lineNum++;
            writer.flush();
        }
        reader.close();
        writer.close();
        //replace original data file with new data file
        f.delete();
        newFile.renameTo(f);
    }

    /**
     * Update module to file.
     *
     * @param modules contains the details of modules
     */
    public static void updateModuleToFile(ArrayList<Module> modules) {
        try {
            File f = new File(filePathOfModule);
            FileWriter fw = new FileWriter(filePathOfModule);
            for (Module module : modules) {
                if (module != null) {
                    fw.write(module.toString() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with IO stream.\n");
        }
    }

    /**
     * Add new line to the end of data file.
     *
     * @param textToAppend text needs to be added
     * @throws IOException if cannot open and write the file
     */
    public static void appendToFileModule(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePathOfModule, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void loadJson() throws IOException {

        File f = new File(filePathOfJson);

        if (!f.exists()) {
            System.out.println("Loading information....please wait.");
            createFile(filePathOfJson, directory);
            URL url = new URL("https://api.nusmods.com/v2/2019-2020/moduleInfo.json");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream in = url.openStream();
            Files.copy(in, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Module information has been loaded successfully");
        }

    }


}
