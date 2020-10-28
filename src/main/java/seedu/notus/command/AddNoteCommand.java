package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.data.exception.SystemException;
import seedu.notus.ui.Formatter;

import static seedu.notus.util.parser.Parser.inputContent;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_PIN;

import java.io.IOException;
import java.util.ArrayList;

//@@author Nazryl
/**
 * Adds a Note into the Notebook.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "add-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Adds a note to notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR "
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...] "
            + "[" + PREFIX_DELIMITER + PREFIX_PIN + " PIN]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "New note added: ";
    public static final String COMMAND_UNSUCCESSFUL_MESSAGE = "This note already exists in the notebook! ";
    public static final String COMMAND_UNSUCCESSFUL_FILE_CREATION = "Unable to create and save details in a file";

    private Note note;

    /**
     * Constructs an AddNoteCommand to add a Note into the Notebook.
     *
     * @param note refers to the note to be added.
     */
    public AddNoteCommand(Note note) {
        this.note = note;
    }

    @Override
    public String execute() {
        ArrayList<String> content = note.getContent();

        // Search for duplicates
        if (notebook.getNote(note.getTitle())) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_MESSAGE);
        }

        // Get Content
        try {
            if (storageManager.noteExists(note, note.getIsArchived())) {
                content = storageManager.getNoteContent(note, note.getIsArchived());
            }
        } catch (SystemException exception) {
            return Formatter.formatString(exception.getMessage());
        }

        if (content.isEmpty()) {
            content = inputContent();
        }
        // Edit the note
        note.setContent(content);

        // Rebind the tags if there are duplicated tags
        tagManager.rebindTags(note);
        notebook.addNote(note);

        //Save the notes in storage
        try {
            storageManager.saveNote(note, note.getIsArchived());
        } catch (IOException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_FILE_CREATION);
        }

        return Formatter.formatNote(COMMAND_SUCCESSFUL_MESSAGE, note);
    }
}
