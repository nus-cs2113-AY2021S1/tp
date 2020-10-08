package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;

/**
 * Adds a Note into the Notebook or an Event into the Timetable.
 */
public class AddCommand extends Command {

    public static final String  COMMAND_WORD_NOTE = "add-n";

    public static final String COMMAND_USAGE_NOTE = COMMAND_WORD_NOTE + ": Adds a note to notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAGS] "
            + "[" + PREFIX_DELIMITER + PREFIX_PIN + " PIN]";

    private Note note;

    /**
     * Constructs an AddCommand to add a Note into the Notebook.
     *
     * @param note to be added.
     */
    public AddCommand(Note note) {
        this.note = note;
    }

    @Override
    public String execute() {
        try {
            // Search for duplicates
            ArrayList<Note> filteredTaskList = (ArrayList<Note>) notebook.getNotes().stream()
                    .filter((s) -> s.getTitle().equals(note.getTitle()))
                    .collect(toList());

            if (!filteredTaskList.isEmpty()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_DUPLICATE_NOTE);
            }

            // Rebind the tags if there are duplicated tags
            tagManager.rebindTags(note);
            notebook.addNote(note);
            return "New note added: " + note.getTitle();
        } catch (SystemException exception) {
            return exception.getMessage();
        }
    }
}
