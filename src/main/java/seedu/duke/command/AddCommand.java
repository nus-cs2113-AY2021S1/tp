package seedu.duke.command;

import seedu.duke.data.exception.SystemException;
import seedu.duke.data.notebook.Note;
import seedu.duke.data.timetable.Event;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;
import static seedu.duke.util.PrefixSyntax.PREFIX_DATETIME;
import static seedu.duke.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.duke.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.duke.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.duke.util.PrefixSyntax.PREFIX_TITLE;
import static seedu.duke.util.PrefixSyntax.PREFIX_TAG;
import static seedu.duke.util.PrefixSyntax.PREFIX_PIN;

/**
 * Adds a Note into the Notebook or an Event into the Timetable.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD_NOTE = "add-n";
    public static final String COMMAND_WORD_EVENT = "add-e";

    private static final String COMMAND_USAGE_NOTE = COMMAND_WORD_NOTE + ": Adds a note to notebook. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + "[" + PREFIX_DELIMITER + PREFIX_TAG + " TAG TAG_COLOR " + PREFIX_DELIMITER + PREFIX_TAG + " TAG1 TAG_COLOR...] "
            + "[" + PREFIX_DELIMITER + PREFIX_PIN + " PIN]";
    private static final String COMMAND_USAGE_EVENT = COMMAND_WORD_EVENT + ": Adds an event to timetable. Parameters: "
            + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE "
            + PREFIX_DATETIME + " DATE_TIME "
            + "[" + PREFIX_RECURRING + " RECURRING] "
            + "[" + PREFIX_REMIND + " REMIND]";

    private Note note;
    private Event event;
    private boolean isAddNote;

    public static String getCommandUsageNote() {
        return COMMAND_USAGE_NOTE;
    }

    public static String getCommandUsageEvent() {
        return COMMAND_USAGE_EVENT;
    }

    /**
     * Constructs an AddCommand to add a Note into the Notebook.
     *
     * @param note to be added.
     */
    public AddCommand(Note note) {
        this.note = note;
        this.event = null;
        this.isAddNote = true;
    }

    /**
     * Constructs an AddCommand to add an Event into the Timetable.
     *
     * @param event to be added.
     */
    public AddCommand(Event event) {
        this.note = null;
        this.event = event;
        this.isAddNote = false;
    }

    @Override
    public String execute() {
        try {
            ArrayList<Note> filteredTaskList = (ArrayList<Note>) notebook.getNotes().stream()
                    .filter((s) -> s.getTitle().equals(note.getTitle()))
                    .collect(toList());

            if (!filteredTaskList.isEmpty()) {
                throw new SystemException(SystemException.ExceptionType.EXCEPTION_DUPLICATE_NOTE);
            }

            // rebind the tags if there are duplicated tags
            tagManager.rebindTags(note);
            notebook.addNote(note);
            return "New note added: " + note.getTitle();
        } catch (SystemException exception) {
            return exception.getMessage();
        }
    }


}
