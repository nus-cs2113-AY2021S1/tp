package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.ui.Formatter;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.duke.util.Parser.inputContent;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;

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
        String content = note.getContent();
        boolean isInputSuccess = false;

        // Search for duplicates
        ArrayList<Note> filteredTaskList = (ArrayList<Note>) notebook.getNotes().stream()
                .filter((s) -> s.getTitle().equalsIgnoreCase(note.getTitle()))
                .collect(toList());

        if (!filteredTaskList.isEmpty()) {
            return Formatter.formatNote(COMMAND_UNSUCCESSFUL_MESSAGE);
        }

        // Get Content
        if (content.isBlank()) {
            do {
                System.out.println("Enter Note:");
                try {
                    content = inputContent();
                    isInputSuccess = true;
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(SystemException.ExceptionType.EXCEPTION_INVALID_END_INPUT);
                }
            } while (!isInputSuccess);
        }

        // Edit the note
        note.setContent(content);

        // Rebind the tags if there are duplicated tags
        tagManager.rebindTags(note);
        notebook.addNote(note);

        if (note.getTagsName().isBlank()) {
            return Formatter.formatNote(COMMAND_SUCCESSFUL_MESSAGE + note.getTitle());
        } else {
            return Formatter.formatNote(COMMAND_SUCCESSFUL_MESSAGE + note.getTitle() + " "
                    + note.getTagsName());
        }
    }
}
