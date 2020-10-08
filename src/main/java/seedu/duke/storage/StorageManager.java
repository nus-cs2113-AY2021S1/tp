package seedu.duke.storage;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.timetable.Timetable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    private static final String DELIMINATOR = "|";


    /**
     * Saves all the Notes in the Notebook to the storage file.
     *
     * @param notebook The Notebook containing all the notes to be saved.
     */
    public static void saveNotebook(Notebook notebook) throws SystemException {

        for (int i = 0; i < notebook.getSize(); i++){
            try{
                saveNoteContent(notebook.getNote(i));

            } catch (IOException e) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_CREATION_ERROR);
            }
        }
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
    public void saveAll(Notebook notebook, Timetable timetable)throws SystemException{
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

    /**
     * Saves an individual note to the storage file
     *
     * @param note The note to be saved
     */
    public static void saveNoteContent(Note note) throws IOException {
        String path = FOLDER_DIR + NOTES_DIR + "/"+note.getTitle() + ".txt";
        createFile (path);
        FileWriter fwAppend = new FileWriter(path, true);
        fwAppend.write(note.getContent());
        fwAppend.close();
    }

    /**
     * Checks if a file exists. If it does not, creates file with the input path
     * @param path path of file to be created
     * @throws IOException thrown when directory does not exist. Unable to create file
     */
    private static void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
    }
}
