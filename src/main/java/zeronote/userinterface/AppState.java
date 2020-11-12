package zeronote.userinterface;

import zeronote.exceptions.ZeroNoteException;
import zeronote.notebooks.Notebook;
import zeronote.notebooks.NotebookShelf;
import zeronote.notebooks.Page;
import zeronote.notebooks.Section;
import zeronote.tasks.Task;
import zeronote.tasks.TaskList;

//@@author longngng

/**
 * Represents the current mode and state the app is in.
 */
public class AppState {
    private AppMode appMode;
    private TaskList taskList;
    private NotebookShelf currentBookShelf;
    private Notebook currentNotebook;
    private Section currentSection;
    private Page currentPage;
    private int indexOfCurrentPage;
    private String userName;

    public AppState() {
        taskList = new TaskList();
        appMode = AppMode.TIMETABLE;
        currentBookShelf = new NotebookShelf();
        userName = "";
    }

    public AppMode getAppMode() {
        return appMode;
    }

    public void setAppMode(AppMode appMode) {
        this.appMode = appMode;
    }

    public NotebookShelf getCurrentBookShelf() {
        return currentBookShelf;
    }

    //@@author chuckiex3
    public void setCurrentBookShelf(NotebookShelf currentBookShelf) {
        this.currentBookShelf = currentBookShelf;
    }

    public Notebook getCurrentNotebook() {
        return currentNotebook;
    }

    public void setCurrentNotebook(Notebook currentNotebook) {
        this.currentNotebook = currentNotebook;
    }

    public Section getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(Section currentSection) {
        this.currentSection = currentSection;
    }

    //@@author Lusi711
    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int indexOfCurrentPage) {
        setIndexOfCurrentPage(indexOfCurrentPage);
        currentPage = currentSection.getPageAtIndex(indexOfCurrentPage);
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    //@@author yAOwzers
    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public Task markTaskAsDone(int index) throws ZeroNoteException {
        return taskList.markAsDone(index);
    }

    //@@author Lusi711
    public int getIndexOfCurrentPage() {
        return indexOfCurrentPage;
    }

    public void setIndexOfCurrentPage(int indexOfCurrentPage) {
        this.indexOfCurrentPage = indexOfCurrentPage;
    }

    //@@author neilbaner
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
