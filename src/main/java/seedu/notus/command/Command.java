package seedu.notus.command;

import com.diogonunes.jcolor.Attribute;
import seedu.notus.data.notebook.Notebook;
import seedu.notus.data.tag.TagManager;
import seedu.notus.data.timetable.Timetable;
import seedu.notus.storage.StorageManager;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected Notebook notebook;
    protected Timetable timetable;
    protected TagManager tagManager;
    protected StorageManager storageManager;

    protected Command() {
    }

    /**
     * Executes the command and returns the result. Method to be implemented by child class.
     *
     * @return result of the command execution.
     */
    public abstract String execute();

    /**
     * Sets the data that the command will operate on.
     *
     * @param notebook referenced Notebook data.
     * @param timetable referenced Timetable data.
     * @param tagManager referenced TagManager.
     */
    public void setData(Notebook notebook, Timetable timetable, TagManager tagManager, StorageManager storageManager) {
        this.notebook = notebook;
        this.timetable = timetable;
        this.tagManager = tagManager;
        this.storageManager = storageManager;
    }
}
