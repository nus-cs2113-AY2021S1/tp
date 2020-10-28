package seedu.notus.command;

import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.notus.util.PrefixSyntax.PREFIX_DELIMITER;
import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_ADD;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_CLEAR;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_DROP;
import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

//@@author brandonywl
/**
 * Edits a Note in the Notebook or an Event from the Timetable.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "edit-e";

    public static final String COMMAND_USAGE = COMMAND_WORD + ": Edits an event in the timetable. "
            + "Parameters: " + PREFIX_DELIMITER + PREFIX_INDEX + " INDEX "
            + "[" + PREFIX_DELIMITER + PREFIX_TITLE + " TITLE] "
            + "[" + PREFIX_DELIMITER + PREFIX_TIMING + " DATE_TIME] "
            + "[" + PREFIX_DELIMITER + PREFIX_RECURRING + " RECURRING] "
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_ADD + " REMIND]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_DROP + " REMIND]"
            + "[" + PREFIX_DELIMITER + PREFIX_REMIND_CLEAR + "]";

    private static final String COMMAND_SUCCESS_MESSAGE = "Event successfully edited!";

    public static final String REMINDER_TYPE_ADD = "add";
    public static final String REMINDER_TYPE_DROP = "drop";
    public static final String REMINDER_TYPE_CLEAR = "clear";

    private final int index;
    private final String new_title;
    private final LocalDateTime newStartDate;
    private final String reminderTodo;
    private final ArrayList<Integer> timePeriods;
    private final ArrayList<String> timeUnits;

    /**
     * Constructs an EditEventCommand to edit an Event.
     *
     * @param index of the Event to be edited.
     */
    public EditEventCommand(int index, String new_title, LocalDateTime newStartDate, String reminderTodo,
                            ArrayList<Integer> timePeriods, ArrayList<String> timeUnits) {
        this.index = index;
        this.new_title = new_title;
        this.newStartDate = newStartDate;
        this.reminderTodo = reminderTodo;
        this.timePeriods = timePeriods;
        this.timeUnits = timeUnits;
    }

    @Override
    public String execute() {
        Event event = timetable.getEvent(index);

        // Edit title is indicated
        if (!new_title.isBlank()) {
            event.setTitle(new_title);
        }

        // Edit startDate is indicated
        if (newStartDate != null) {
            event.setStartDateTime(newStartDate);
        }

        // Edit a reminder is indicated
        if (!reminderTodo.isBlank()) {
            switch (reminderTodo) {
            case REMINDER_TYPE_ADD:
                break;
            case REMINDER_TYPE_DROP:
                break;
            case REMINDER_TYPE_CLEAR:
                break;
            default:
                // Should not hit here at all.
                break;
            }
        }

        // If change recurring, edit all the other data first, instantiate a new object with the relevant type of
        // recurring event and replace the original event.

        return null;
    }
}
