package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.notus.util.CommandMessage.NOTE_DOES_NOT_EXIST_MESSAGE;

//@@author prachi2023
/**
 * Pins or unpins a Note in the Notebook.
 */
public class PinCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger("PinCommand");

    public static final String COMMAND_WORD = "pin-n";

    private int index;
    private String title;
    private boolean isPinByIndex;

    /**
     * Constructs a PinCommand to pin or unpin a Note in the Notebook by the index.
     *
     * @param index of the Note.
     */
    public PinCommand(int index) {
        this.index = index;
        this.title = null;
        this.isPinByIndex = true;
        setupLogger();

        LOGGER.log(Level.INFO, "New pinCommand object created.");
    }

    /**
     * Constructs a PinCommand to pin or unpin a Note in the Notebook by the title.
     *
     * @param title of the Note.
     */
    public PinCommand(String title) {
        this.title = title;
        this.isPinByIndex = false;
        setupLogger();

        LOGGER.log(Level.INFO, "New pinCommand object created.");
    }

    @Override
    public String execute() {
        Note note = null;
        if (isPinByIndex) {
            try {
                note = notebook.getNotes().get(index);
            } catch (IndexOutOfBoundsException exception) {
                LOGGER.log(Level.INFO, "Note does note exist. unable to find note with index" + index);
                return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
            }
            LOGGER.log(Level.INFO, "Note found using index");
        } else {
            for (Note notes : notebook.getNotes()) {
                if (notes.getTitle().equalsIgnoreCase(title)) {
                    note = notes;
                    LOGGER.log(Level.INFO, "Note found using title of note");
                }
            }
        }

        if (note == null) {
            LOGGER.log(Level.INFO, "Note does not exist.");
            return Formatter.formatString(NOTE_DOES_NOT_EXIST_MESSAGE);
        }

        note.togglePinned();
        LOGGER.log(Level.INFO, "Pin status of note toggled");

        try {
            storageManager.saveAllNoteDetails(false);
        } catch (IOException e) {
            return Formatter.formatString(e.getMessage());
        }

        return Formatter.formatString(note.getTitle() + " pinned: " + note.getPinnedString());
    }

    private void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.INFO);

        try {
            FileHandler fileHandler = new FileHandler("PinCommand.log");
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            LOGGER.addHandler(fileHandler);
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "File logger not working.", exception);
        }

    }
}