package seedu.duke.command;

import seedu.duke.data.notebook.Notebook;
import seedu.duke.data.notebook.TagManager;
import seedu.duke.data.timetable.Timetable;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected Notebook notebook;
    protected Timetable timetable;
    protected TagManager tagManager;

    protected static final int NULL_INT = -1;

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
    public void setData(Notebook notebook, Timetable timetable, TagManager tagManager) {
        this.notebook = notebook;
        this.timetable = timetable;
        this.tagManager = tagManager;
    }
}
