package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//@@author prachi2023
/**
 * Views a specific Note in the Notebook.
 */
public class ViewNoteCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("ViewNoteCommand");

    public static final String COMMAND_WORD = "view-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Views a note. Parameters: "
            + "[" + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX] "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Here's the note you're looking for: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note does not exists in the notebook";

    private int index;
    private String title;
    private boolean isViewByIndex;

    /**
     * Constructs a ViewNoteCommand to view a Note by the index.
     *
     * @param index of the Note.
     */
    public ViewNoteCommand(int index) {
        this.index = index;
        this.title = null;
        this.isViewByIndex = true;

        setupLogger();
        LOGGER.log(Level.INFO, "New ViewNoteCommand object created using index.");
    }

    /**
     * Constructs a ViewNoteCommand to view a Note by the title.
     *
     * @param title of the Note.
     */
    public ViewNoteCommand(String title) {
        this.title = title;
        this.isViewByIndex = false;

        setupLogger();
        LOGGER.log(Level.INFO, "New ViewNoteCommand object created using title.");
    }

    @Override
    public String execute() {
        Note note = new Note("", null, false, false);
        boolean noteExists = false;
        if (isViewByIndex) {
            try {
                note = notebook.getNotes().get(index);
            } catch (IndexOutOfBoundsException exception) {
                LOGGER.log(Level.INFO, "Note does note exist. unable to find note with index" + index);
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
            }
            LOGGER.log(Level.INFO, "Note found using index");
            noteExists = true;
        } else {
            for (Note notes : notebook.getNotes()) {
                if (notes.getTitle().equalsIgnoreCase(title)) {
                    note = notes;
                    noteExists = true;
                    LOGGER.log(Level.INFO, "Note found using title of note");
                }
            }
        }
        if (!noteExists) {
            LOGGER.log(Level.INFO, "Note does not exist.");
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }

        return Formatter.formatNote(COMMAND_SUCCESSFUL_MESSAGE, note);
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        try {
            FileHandler fileHandler = new FileHandler("ViewNoteCommand.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "File logger not working.", exception);
        }

    }
}
