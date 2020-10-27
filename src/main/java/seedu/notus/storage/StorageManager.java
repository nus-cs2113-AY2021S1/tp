package seedu.notus.storage;

import seedu.notus.command.AddNoteCommand;
import seedu.notus.command.Command;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.notebook.Note;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.data.tag.TagManager;
import seedu.notus.util.parser.ParserManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author prachi2023
/**
 * Represents a StorageManager. Manages the saving and loading of task list data.
 */
public class StorageManager {
    /** Default folders directory. */
    public static final String FOLDER_DIR = "data";
    public static final String NOTES_DIR = "/notes";
    private static final String ARCHIVED_NOTES_DIR = "/archived";

    /** Default file path. */
    public static final String NOTEBOOK_FILE_PATH = "/notebook.txt";
    private static final String ARCHIVED_NOTEBOOK_FILE_PATH = "/archived_notebook.txt";
    private static final String TAG_FILE_PATH = "/tags.txt";
    private static final String TIMETABLE_FILE_PATH = "/timetable.txt";

    /* Set up of Storage manager */

    /**
     * Checks if the file directories exist otherwise creates them.
     * It also creates the files for the Notebook and timetable information if it does not already exist
     *
     * @throws SystemException when it is unable to create a file
     */
    public void createFiles() throws SystemException {
        //Create directories
        String dataPath = FOLDER_DIR;
        String notesPath = FOLDER_DIR + NOTES_DIR;
        String archivedNotesPath = FOLDER_DIR + ARCHIVED_NOTES_DIR;

        String notebookFilePath = FOLDER_DIR + NOTEBOOK_FILE_PATH;
        String archivedNotebookFilePath = FOLDER_DIR + ARCHIVED_NOTEBOOK_FILE_PATH;
        String tagsFilePath = FOLDER_DIR + TAG_FILE_PATH;
        String timetableFilePath = FOLDER_DIR + TIMETABLE_FILE_PATH;

        String[] paths = {dataPath, notesPath, archivedNotesPath};
        String[] files = {notebookFilePath, archivedNotebookFilePath, tagsFilePath, timetableFilePath};

        for (String path: paths) {
            createDirectory(path);
        }

        for (String file: files) {
            try {
                createFile(file);
            } catch (IOException exception) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_CREATION_ERROR);
            }
        }
    }

    /**
     * Creates a directory path data/notes. In case both data and /notes do not exist.
     */
    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Checks if a file exists. If it does not, creates file with the input path
     * @param path path of file to be created
     * @throws IOException thrown when directory does not exist. Unable to create file
     */
    public void createFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /* Loading of information from files */

    /**
     * Loads the Notebook details and content for unArchived Notebooks.
     *
     * @param notebook The Notebook to be loaded into.
     * @param timetable The Timetable to be loaded into.
     */
    public void loadAllNotes(Notebook notebook, Timetable timetable, TagManager tagManager,
                             ParserManager parserManager, boolean isArchive) throws SystemException {
        String path;
        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTEBOOK_FILE_PATH;
        } else {
            path = FOLDER_DIR + NOTEBOOK_FILE_PATH;
        }

        File f = new File(path);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException exception) {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_NOT_FOUND_ERROR);
        }
        while (s.hasNext()) {
            String taskDetails = AddNoteCommand.COMMAND_WORD + " " +  s.nextLine();
            Command command = parserManager.parseCommand(taskDetails);
            command.setData(notebook, timetable, tagManager, this);
            command.execute();
        }
        s.close();
    }

    public ArrayList<String> getNoteContent(Note note, boolean isArchive) throws SystemException {
        ArrayList<String> content = new ArrayList<>();
        String path;

        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTES_DIR + "/" + note.getTitle() + ".txt";
        } else {
            path = FOLDER_DIR + NOTES_DIR + "/" + note.getTitle() + ".txt";
        }

        File f = new File(path);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException exception) {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_NOT_FOUND_ERROR);
        }

        while (s.hasNext()) {
            content.add(s.nextLine());
        }
        s.close();
        return content;
    }

    /**
     * Saves all the Notes in the Notebook to the storage file.
     *
     * @param notebook The Notebook containing all the notes to be saved.
     */
    public void saveNotebook(Notebook notebook) throws SystemException {
        for (int i = 0; i < notebook.getSize();i++) {
            try {
                saveNoteContent(notebook.getNote(i), true);
                saveNoteDetails(notebook.getNote(i), true);
            } catch (IOException e) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_CREATION_ERROR);
            }
        }
    }

    /* Saving and deleting notes */

    /**
     * Clears the content in the original file storing all the note details.
     * Replaces it with the new note content details.
     *
     * @param notebook notebook that stores all the notes to be saved
     * @param isArchive determines whether to save archived notes or normal notes
     * @throws IOException thrown when unable to write to the file
     */
    public void saveAllNoteDetails(Notebook notebook, Boolean isArchive) throws IOException {
        String path;

        ArrayList<Note> notes;

        if (isArchive) {
            notes = notebook.getArchivedNotes();
            path = FOLDER_DIR + ARCHIVED_NOTEBOOK_FILE_PATH;
        } else {
            notes = notebook.getNotes();
            path = FOLDER_DIR + NOTEBOOK_FILE_PATH;
        }
        FileWriter fw = new FileWriter(path, false);
        fw.write("");
        fw.close();

        for (Note note: notes) {
            saveNoteDetails(note, isArchive);
        }
    }

    public void saveNote(Note note, boolean isArchive) throws IOException {
        if (!noteExists(note, isArchive)) {
            saveNoteContent(note, isArchive);
            saveNoteDetails(note, isArchive);
        }
    }

    /**
     * Saves an individual note to the storage file.
     *
     * @param note The note to be saved
     */
    public void saveNoteContent(Note note, boolean isArchive) throws IOException {
        String path;

        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTES_DIR + "/" + note.getTitle() + ".txt";
        } else {
            path = FOLDER_DIR + NOTES_DIR + "/" + note.getTitle() + ".txt";
        }

        createFile(path);
        FileWriter fw = new FileWriter(path);
        fw.write(note.getContentString());
        fw.close();
    }

    /**
     * Saves the details of notes such as title, tags and pinned status to the notebook text file.
     * @param note Note of which details are to be saved to the file
     */
    public void saveNoteDetails(Note note, boolean isArchive) throws IOException {
        String path;

        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTEBOOK_FILE_PATH;
        } else {
            path = FOLDER_DIR + NOTEBOOK_FILE_PATH;
        }

        FileWriter fwAppend = new FileWriter(path, true);
        fwAppend.write(note.toSaveString());
        fwAppend.close();
    }

    public void deleteNoteContentFile(String noteTitle, boolean isArchive) throws SystemException {
        String path;

        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTES_DIR + "/" + noteTitle + ".txt";
        } else {
            path = FOLDER_DIR + NOTES_DIR + "/" + noteTitle + ".txt";
        }

        File file = new File(path);

        if (file.exists()) {
            if (!file.delete()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_DELETION_ERROR);
            }
        } else {
            throw new SystemException(SystemException.ExceptionType.EXCEPTION_FILE_NOT_FOUND_ERROR);
        }
    }

    /**
     * Returns a boolean of whether the file storing the content of the note already exists.
     *
     * @param note note whose file status needs to be checked
     * @return boolean
     */
    public boolean noteExists(Note note, boolean isArchive) {
        String path;

        if (isArchive) {
            path = FOLDER_DIR + ARCHIVED_NOTES_DIR + "/" + note.getTitle() + ".txt";
        } else {
            path = FOLDER_DIR + NOTES_DIR + "/" + note.getTitle() + ".txt";
        }

        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return true;
    }
}
