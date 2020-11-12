// @@author neilbaner

package zeronote.storage;

import zeronote.exceptions.FileSavingException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.tasks.Task;
import zeronote.tasks.TaskList;
import zeronote.userinterface.AppState;
import zeronote.userinterface.CliMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the storage of where ZeroNote is loading from and saving information to.
 */
public class Storage {

    private final String tasksFilePath = "data/tasks.txt";
    private final String notebooksFilePath = "data/notebooks.txt";
    private final String nameFilepath = "data/nameOfUser.txt";

    /**
     * Saves the tasks to the specified path.
     *
     * @param currentAppState the AppState of the running instance.
     * @throws FileSavingException of there is an error saving the file.
     */
    public void saveTasks(AppState currentAppState) throws FileSavingException {
        File tasksFile = new File(tasksFilePath);
        TaskList currentTaskList = currentAppState.getTaskList();
        String tasksToSave = currentTaskList.serialize();
        try {
            FileWriter tasksFileWriter = new FileWriter(tasksFile);
            tasksFileWriter.write(tasksToSave);
            tasksFileWriter.close();
        } catch (IOException e) {
            throw new FileSavingException(e.getMessage());
        }
    }

    /**
     * Saves the notebooks to the specified path.
     *
     * @param currentAppState the AppState of the running instance.
     * @throws FileSavingException if there is an error saving the notebooks.
     */
    public void saveNotebooks(AppState currentAppState) throws FileSavingException {
        File notebooksFile = new File(notebooksFilePath);
        NotebookShelf currentNotebookShelf = currentAppState.getCurrentBookShelf();
        String notebooksToSave = currentNotebookShelf.serialize();
        try {
            FileWriter notebooksFileWriter = new FileWriter(notebooksFile);
            notebooksFileWriter.write(notebooksToSave);
            notebooksFileWriter.close();
        } catch (IOException e) {
            throw new FileSavingException(e.getMessage());
        }
    }

    /**
     * Saves the username to the specified path.
     *
     * @param currentAppState the AppState of the running instance.
     * @throws FileSavingException if there is an error saving the username.
     */
    public void saveUserName(AppState currentAppState) throws FileSavingException {
        File userNameFile = new File(nameFilepath);
        try {
            FileWriter userNameFileWriter = new FileWriter(userNameFile);
            String userName = currentAppState.getUserName();
            userNameFileWriter.write(userName);
            userNameFileWriter.close();
        } catch (IOException e) {
            throw new FileSavingException(e.getMessage());
        }
    }

    /**
     * Save the current application state to 3 text files.
     *
     * @param currentAppState the AppState of the running instance.
     * @throws FileSavingException if there is an error saving the instance.
     */
    public void saveToFile(AppState currentAppState) throws FileSavingException {
        saveTasks(currentAppState);
        saveNotebooks(currentAppState);
        saveUserName(currentAppState);
    }

    /**
     * Read the tasks previously saved to a text file.
     *
     * @param loadedAppState the AppState of the running instance.
     */
    public void readTasks(AppState loadedAppState) {
        TaskList loadedTaskList = new TaskList(); // temporary TaskList
        File tasksFile = new File(tasksFilePath);
        try {
            Scanner tasksFileScanner = new Scanner(tasksFile);
            int numTasks = Integer.parseInt(tasksFileScanner.nextLine()); // Attempt to extract the number of tasks
            for (int i = 0; i < numTasks; i++) { // loop numTasks times to read numTasks Task objects
                // Read one task
                String taskTitle = tasksFileScanner.nextLine();
                String taskDate = tasksFileScanner.nextLine();
                String done = tasksFileScanner.nextLine();
                Task currentTask = new Task(taskTitle, taskDate);
                if (done.equals("true")) {
                    currentTask.markAsDone();
                }
                // Add task to temporary list
                loadedTaskList.addTask(currentTask);
            }
            // move temporary list into current AppState
            // Note: only done if the whole file was read correctly
            loadedAppState.setTaskList(loadedTaskList);
        } catch (FileNotFoundException e) {
            CliMessages.printNoTaskFile();
        } catch (Exception e) {
            CliMessages.printCorruptTaskFile();
            PrintStream syserr = System.err;
            System.setErr(System.out);
            e.printStackTrace();
            System.setErr(syserr);
        }
    }

    public void readNotebooks(AppState loadedAppState) {
        NotebookShelf loadedNotebookShelf = new NotebookShelf();
        File notebooksFile = new File(notebooksFilePath);
        try {
            Scanner notebooksFileScanner = new Scanner(notebooksFile);
            int numNotebooks = Integer.parseInt(notebooksFileScanner.nextLine());
            for (int i = 0; i < numNotebooks; i++) {
                String notebookTitle = notebooksFileScanner.nextLine();
                int numSections = Integer.parseInt(notebooksFileScanner.nextLine());
                Notebook currentNotebook = new Notebook(notebookTitle);
                readSections(numSections, currentNotebook, notebooksFileScanner);
                loadedNotebookShelf.addNotebook(currentNotebook);
            }
            loadedAppState.setCurrentBookShelf(loadedNotebookShelf);
        } catch (FileNotFoundException e) {
            CliMessages.printNoNotebookFile();
        } catch (Exception e) {
            CliMessages.printCorruptNotebookFile();
            PrintStream syserr = System.err;
            System.setErr(System.out);
            e.printStackTrace();
            System.setErr(syserr);
        }
    }

    public void readSections(int numSections, Notebook currentNotebook, Scanner notebooksFileScanner) {
        for (int j = 0; j < numSections; j++) {
            String sectionTitle = notebooksFileScanner.nextLine();
            int numPages = Integer.parseInt(notebooksFileScanner.nextLine());
            Section currentSection = new Section(sectionTitle);
            readPages(numPages, currentSection, notebooksFileScanner);
            currentNotebook.addSection(currentSection);
        }
    }

    public void readPages(int numPages, Section currentSection, Scanner notebooksFileScanner) {
        for (int k = 0; k < numPages; k++) {
            String pageTitle = notebooksFileScanner.nextLine();
            String pageContent = notebooksFileScanner.nextLine();
            pageContent = pageContent.replaceAll("~~~", System.lineSeparator());
            Page currentPage = new Page(pageTitle, pageContent);
            currentSection.addPage(currentPage);
        }
    }

    public void readUserName(AppState loadedAppState) {
        File userNameFile = new File(nameFilepath);
        try {
            Scanner userNameFileScanner = new Scanner(userNameFile);
            String userName = userNameFileScanner.nextLine();
            loadedAppState.setUserName(userName);
        } catch (Exception e) {
            loadedAppState.setUserName("");
        }
    }

    public AppState readFromFile() {
        AppState loadedAppState = new AppState();
        readTasks(loadedAppState);
        readNotebooks(loadedAppState);
        readUserName(loadedAppState);
        return loadedAppState;
    }

    // @@author yAOwzers
    @Deprecated
    public boolean isNameOfUserFilled() {
        File nameOfUserFile = new File(this.nameFilepath);
        return nameOfUserFile.length() != 0 && nameOfUserFile.exists();
    }

    //@@author yAOwzers
    @Deprecated
    public void saveNameOfUser() {
        File file = new File(this.nameFilepath);
        try {
            file.getParentFile().mkdir(); // create a directory
            file.createNewFile(); // create .txt file
            Scanner keyboard = new Scanner(System.in);
            String userInput = keyboard.nextLine();
            FileWriter nameOfUserFileToSave = new FileWriter(this.nameFilepath);
            nameOfUserFileToSave.write(userInput);
            nameOfUserFileToSave.close();
        } catch (IOException e) {
            System.out.println("Error in FileSaving");
        }

    }
}
