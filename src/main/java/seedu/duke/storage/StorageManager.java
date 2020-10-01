package seedu.duke.storage;

import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.timetable.Timetable;

/**
 * Represents a StorageManager. Manages the saving and loading of task list data.
 */
public class StorageManager {
    /** Default folders directory. */
    private static final String FOLDER_DIR = "data";
    private static final String NOTES_DIR = "/notes";
    /** Default file path. */
    private static final String NOTEBOOK_FILE_PATH = "notebook.txt";
    private static final String TIMETABLE_FILE_PATH = "timetable.txt";

    /**
     * Saves all the Notes in the Notebook to the storage file.
     *
     * @param notebook The Notebook containing all the notes to be saved.
     */
    private void saveNotebook(Notebook notebook) {

    }

    /**
     * Saves all the Events in the Timetable to the storage file.
     *
     * @param timetable The Timetable containing all the events to be saved.
     */
    private void saveTimetable(Timetable timetable) {

    }

    /**
     * Saves all the Notes in the Notebook and the Events in the Timetable to the storage file.
     *
     * @param notebook The Notebook containing all the notes to be saved.
     * @param timetable The Timetable containing all the events to be saved.
     */
    public void saveAll(Notebook notebook, Timetable timetable) {
        saveNotebook(notebook);
        saveTimetable(timetable);
    }

    /**
     * Loads the Notebook and Timetable from the storage file.
     *
     * @param notebook The Notebook to be loaded into.
     * @param timeTable The Timetable to be loaded into.
     */
    public void loadAll(Notebook notebook, Timetable timeTable) {

    }
}
