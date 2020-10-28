package seedu.notus.command;

import seedu.notus.data.notebook.Note;
import seedu.notus.ui.Formatter;

import java.util.ArrayList;
import java.util.Map;

import static seedu.notus.util.parser.Parser.inputContent;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_CONTENT;
import static seedu.notus.util.PrefixSyntax.PREFIX_DELETE_LINE;
import static seedu.notus.util.PrefixSyntax.PREFIX_ADD_LINE;

//@@author Nazryl
public class EditNoteCommand extends Command {

    public static final String COMMAND_WORD = "edit-n";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Edits a note in the notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_LINE + " LINE_INDEX CONTENTS]"
            + "[" + PREFIX_DELIMITER + PREFIX_CONTENT + " CONTENT]"
            + "[" + PREFIX_DELIMITER + PREFIX_DELETE_LINE + " INDEX]"
            + "[" + PREFIX_DELIMITER + PREFIX_ADD_LINE + " INDEX STRING]"
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR"
            + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...]";

    public static final String COMMAND_SUCCESSFUL_MESSAGE = "Edit note successfully: ";
    public static final String COMMAND_UNSUCCESSFUL_NOTE_DOES_NOT_EXIST = "This note does not exist in the notebook! ";
    public static final String COMMAND_UNSUCCESSFUL_SAME_NOTE_NAME = "This note has the same title as the new title! ";
    public static final String COMMAND_UNSUCCESSFUL_INVALID_LINE = "Invalid line! ";

    private int index;
    private boolean isInput;
    private Note newNote;
    private Map<Integer, String> addLists;
    private Map<Integer, String> editLists;
    private Map<Integer, String> deleteLists;

    /**
     * Constructs an EditNoteCommand to edit a Note.
     *
     * @param index of the Note to be edited.
     */
    public EditNoteCommand(int index, Note newNote, Map<Integer, String> addLists,
                           Map<Integer, String> editLists, Map<Integer, String> deleteLists, boolean isInput) {
        this.index = index;
        this.newNote = newNote;
        this.addLists = addLists;
        this.editLists = editLists;
        this.deleteLists = deleteLists;
        this.isInput = isInput;
    }

    @Override
    public String execute() {
        Note oldNote;
        ArrayList<String> content;

        // Retrieve note
        try {
            oldNote = notebook.getNote(index);
            content = oldNote.getContent();
        } catch (IndexOutOfBoundsException exception) {
            return Formatter.formatString(COMMAND_UNSUCCESSFUL_NOTE_DOES_NOT_EXIST);
        }

        // Set title
        if (!newNote.getTitle().isBlank()) {
            if (newNote.getTitle().equals(oldNote.getTitle())) {
                return Formatter.formatString(COMMAND_UNSUCCESSFUL_SAME_NOTE_NAME);
            }
            oldNote.setTitle(newNote.getTitle());
        }

        // Set new lines
        if (!addLists.isEmpty()) {
            for (Map.Entry<Integer, String> entry : addLists.entrySet()) {
                if (entry.getKey() < content.size()) {
                    content.add(entry.getKey(), entry.getValue());
                } else {
                    return Formatter.formatString(COMMAND_UNSUCCESSFUL_INVALID_LINE);
                }
            }
            oldNote.setContent(content);
        }

        // Edit lines
        if (!editLists.isEmpty()) {
            for (Map.Entry<Integer, String> entry : editLists.entrySet()) {
                if (entry.getKey() < content.size()) {
                    content.set(entry.getKey(), entry.getValue());
                } else {
                    return Formatter.formatString(COMMAND_UNSUCCESSFUL_INVALID_LINE);
                }
            }
            oldNote.setContent(content);
        }

        // Delete lines
        if (!deleteLists.isEmpty()) {
            for (int key : deleteLists.keySet()) {
                if (key < content.size() && content.size() != 1) {
                    content.remove(key);
                } else {
                    return Formatter.formatString(COMMAND_UNSUCCESSFUL_INVALID_LINE);
                }
            }
            oldNote.setContent(content);
        }

        // Edit tags
        if (!newNote.getTags().isEmpty()) {
            tagManager.tagAndUntag(oldNote, newNote.getTags(), "", "");
        }

        // Add extra content
        if (isInput) {
            content.addAll(inputContent());
            oldNote.setContent(content);
        }

        notebook.setNotes(index, oldNote);
        return Formatter.formatNote(COMMAND_SUCCESSFUL_MESSAGE, oldNote);
    }
}
