package seedu.notus.command;

import seedu.notus.data.timetable.Event;
import seedu.notus.ui.Formatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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

    private static final String COMMAND_SUCCESSFUL_MESSAGE = "Event successfully edited!";
    private static final String COMMAND_SUCCESSFUL_WARNING_MESSAGE = "Event successfully edited! "
            + "However there was no changes made to the stored reminders.";


    public static final String REMINDER_TYPE_ADD = "add";
    public static final String REMINDER_TYPE_DROP = "drop";
    public static final String REMINDER_TYPE_CLEAR = "clear";

    private final int index;
    private final String new_title;
    private final LocalDateTime newStartDate;
    private final String reminderTodo;
    private final HashMap<String, ArrayList<Integer>> reminderSchedule;
    private boolean warningSignal = false;

    /**
     * Constructs an EditEventCommand to edit an Event.
     *
     * @param index Index of the event to be edited.
     * @param new_title New title of the event. Null if it is not edited.
     * @param newStartDate New start date of the event. Null if it is not edited.
     * @param reminderTodo Reminder edit type of the event. "" If it is not edited.
     * @param reminderSchedule Reminder schedule of the things to do to event. Null if it is not edited.
     */
    public EditEventCommand(int index, String new_title, LocalDateTime newStartDate, String reminderTodo,
                            HashMap<String, ArrayList<Integer>> reminderSchedule) {
        this.index = index;
        this.new_title = new_title;
        this.newStartDate = newStartDate;
        this.reminderTodo = reminderTodo;
        this.reminderSchedule = reminderSchedule;
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
            HashMap<String, ArrayList<Integer>> originalReminderSchedule = event.getReminderPeriods();
            switch (reminderTodo) {
            case REMINDER_TYPE_ADD:
                if (!event.getIsToRemind()) {
                    event.setIsToRemind(true);
                    event.setReminderPeriods(reminderSchedule);
                    break;
                }
                warningSignal = true;
                for (String unit : reminderSchedule.keySet()) {
                    ArrayList<Integer> remindersToAdd = reminderSchedule.get(unit);
                    ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
                    if (remindersPresent == null) {
                        originalReminderSchedule.put(unit, remindersToAdd);
                        warningSignal = false;
                        continue;
                    }
                    for (Integer reminder : remindersToAdd) {
                        if (!remindersPresent.contains(reminder)) {
                            remindersPresent.add(reminder);
                            warningSignal = false;
                        }
                    }
                }
                break;
            case REMINDER_TYPE_DROP:
                if (!event.getIsToRemind()) {
                    warningSignal = true;
                    break;
                }
                warningSignal = true;
                for (String unit : reminderSchedule.keySet()) {
                    ArrayList<Integer> remindersToDrop = reminderSchedule.get(unit);
                    ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
                    if (remindersPresent == null) {
                        continue;
                    }
                    for (Integer reminder : remindersToDrop) {
                        if (remindersPresent.contains(reminder)) {
                            remindersPresent.remove(reminder);
                            warningSignal = false;
                        }
                    }
                    if (remindersPresent.size() == 0) {
                        originalReminderSchedule.remove(unit);
                    }
                }
                if (originalReminderSchedule.size() == 0) {
                    event.setIsToRemind(false);
                }
                break;
            case REMINDER_TYPE_CLEAR:
                if (!event.getIsToRemind()) {
                    warningSignal = true;
                    break;
                }
                event.setIsToRemind(false);
                event.setReminderPeriods(new HashMap<>());
                break;
            default:
                // Should not hit here at all.
                break;
            }
        }

        // If change recurring, edit all the other data first, instantiate a new object with the relevant type of
        // recurring event and replace the original event.

        return Formatter.formatString((!warningSignal)
                ? COMMAND_SUCCESSFUL_MESSAGE : COMMAND_SUCCESSFUL_WARNING_MESSAGE);
    }
}
