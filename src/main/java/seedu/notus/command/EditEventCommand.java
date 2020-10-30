package seedu.notus.command;

import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.DailyEvent;
import seedu.notus.data.timetable.Event;
import seedu.notus.data.timetable.MonthlyEvent;
import seedu.notus.data.timetable.RecurringEvent;
import seedu.notus.data.timetable.WeeklyEvent;
import seedu.notus.data.timetable.YearlyEvent;
import seedu.notus.ui.Formatter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.CommandMessage.EDIT_EVENT_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_RECURRENCE_DATE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_RECURRENCE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_REMINDER_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_START_DATE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_TITLE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_WARNING_RECURRENCE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_WARNING_RECURRENCE_ON_NON_RECURRENCE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_WARNING_REMINDER_MESSAGE;
import static seedu.notus.util.CommandMessage.FILE_WRITE_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.PROCESSING_EDIT_MESSAGE;

//@@author brandonywl
/**
 * Edits a Note in the Notebook or an Event from the Timetable.
 */
public class EditEventCommand extends Command {

    public static final String COMMAND_WORD = "edit-e";

    public static final String REMINDER_TYPE_ADD = "add";
    public static final String REMINDER_TYPE_DROP = "drop";
    public static final String REMINDER_TYPE_CLEAR = "clear";

    private final int index;
    private final String newTitle;
    private final LocalDateTime newStartDate;
    private final String reminderTodo;
    private final HashMap<String, ArrayList<Integer>> reminderSchedule;
    private boolean warningSignalReminders = false;
    private boolean warningSignalRecurrence = false;
    private final String recurringType;
    private final LocalDate endRecurrenceDate;

    /**
     * Constructs an EditEventCommand to edit an Event.
     *
     * @param index Index of the event to be edited.
     * @param newTitle New title of the event. Null if it is not edited.
     * @param newStartDate New start date of the event. Null if it is not edited.
     * @param reminderTodo Reminder edit type of the event. "" If it is not edited.
     * @param reminderSchedule Reminder schedule of the things to do to event. Null if it is not edited.
     */
    public EditEventCommand(int index, String newTitle, LocalDateTime newStartDate,
                            String reminderTodo, HashMap<String, ArrayList<Integer>> reminderSchedule,
                            String recurringType, LocalDate endRecurrenceDate) {
        this.index = index;
        this.newTitle = newTitle;
        this.newStartDate = newStartDate;
        this.reminderTodo = reminderTodo;
        this.reminderSchedule = reminderSchedule;
        this.recurringType = recurringType;
        this.endRecurrenceDate = endRecurrenceDate;
    }

    @Override
    public String execute() {
        ArrayList<String> results = new ArrayList<>();
        results.add(PROCESSING_EDIT_MESSAGE);
        Event event = timetable.getEvent(index);

        // Edit title is indicated
        if (!newTitle.isBlank()) {
            event.setTitle(newTitle);
            results.add(EDIT_TITLE_MESSAGE);
        }

        // Edit startDate is indicated
        if (newStartDate != null) {
            event.setStartDateTime(newStartDate);
            results.add(EDIT_START_DATE_MESSAGE);
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
                warningSignalReminders = true;
                for (String unit : reminderSchedule.keySet()) {
                    ArrayList<Integer> remindersToAdd = reminderSchedule.get(unit);
                    ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
                    if (remindersPresent == null) {
                        originalReminderSchedule.put(unit, remindersToAdd);
                        warningSignalReminders = false;
                        continue;
                    }
                    for (Integer reminder : remindersToAdd) {
                        if (!remindersPresent.contains(reminder)) {
                            remindersPresent.add(reminder);
                            warningSignalReminders = false;
                        }
                    }
                }
                break;
            case REMINDER_TYPE_DROP:
                if (!event.getIsToRemind()) {
                    warningSignalReminders = true;
                    break;
                }
                warningSignalReminders = true;
                for (String unit : reminderSchedule.keySet()) {
                    ArrayList<Integer> remindersToDrop = reminderSchedule.get(unit);
                    ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
                    if (remindersPresent == null) {
                        continue;
                    }
                    for (Integer reminder : remindersToDrop) {
                        if (remindersPresent.contains(reminder)) {
                            remindersPresent.remove(reminder);
                            warningSignalReminders = false;
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
                    warningSignalReminders = true;
                    break;
                }
                event.setIsToRemind(false);
                event.setReminderPeriods(new HashMap<>());
                break;
            default:
                // Should not hit here at all.
                break;
            }
            if (warningSignalReminders) {
                results.add(EDIT_WARNING_REMINDER_MESSAGE);
            } else {
                results.add(EDIT_REMINDER_MESSAGE);
            }
        }

        // If change recurring, edit all the other data first, instantiate a new object with the relevant type of
        // recurring event and replace the original event.
        // Set endRecurrenceDate to the original or DEFAULT whichever is set originally first.
        if (!recurringType.isBlank()) {
            Event newEvent = event;
            String eventTitle = event.getTitle();
            LocalDateTime eventStartDateTime = event.getStartDateTime();
            LocalDate endRecurrenceDate = null;
            ArrayList<Tag> tags = event.getTags();
            if (!recurringType.equals(RecurringEvent.NO_RECURRENCE_TYPE)) {
                if (event instanceof RecurringEvent) {
                    endRecurrenceDate = ((RecurringEvent) event).getEndRecurrenceDate();
                } else {
                    endRecurrenceDate = RecurringEvent.DEFAULT_END_RECURRENCE;
                }
            }
            boolean eventIsToRemind = event.getIsToRemind();
            switch (recurringType) {
            case RecurringEvent.NO_RECURRENCE_TYPE:
                if (!(event instanceof RecurringEvent)) {
                    warningSignalRecurrence = true;
                    break;
                }
                newEvent = new Event(eventTitle, eventStartDateTime, eventIsToRemind,
                        false, event.getReminderPeriods(), tags);
                break;
            case RecurringEvent.DAILY_RECURRENCE_TYPE:
                if (event instanceof DailyEvent) {
                    warningSignalRecurrence = true;
                    break;
                }
                assert (endRecurrenceDate != null);
                newEvent = new DailyEvent(eventTitle, eventStartDateTime, eventIsToRemind, endRecurrenceDate,
                        event.getReminderPeriods(), tags);
                break;
            case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
                if (event instanceof WeeklyEvent) {
                    warningSignalRecurrence = true;
                    break;
                }
                assert (endRecurrenceDate != null);
                newEvent = new WeeklyEvent(eventTitle, eventStartDateTime, eventIsToRemind, endRecurrenceDate,
                        event.getReminderPeriods(), tags);
                break;
            case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
                if (event instanceof MonthlyEvent) {
                    warningSignalRecurrence = true;
                    break;
                }
                assert (endRecurrenceDate != null);
                newEvent = new MonthlyEvent(eventTitle, eventStartDateTime, eventIsToRemind, endRecurrenceDate,
                        event.getReminderPeriods(), tags);
                break;
            case RecurringEvent.YEARLY_RECURRENCE_TYPE:
                if (event instanceof YearlyEvent) {
                    warningSignalRecurrence = true;
                    break;
                }
                assert (endRecurrenceDate != null);
                newEvent = new YearlyEvent(eventTitle, eventStartDateTime, eventIsToRemind, endRecurrenceDate,
                        event.getReminderPeriods(), tags);
                break;
            default:
                // Should not hit here.
                warningSignalRecurrence = true;
                break;
            }
            if (warningSignalRecurrence) {
                results.add(EDIT_WARNING_RECURRENCE_MESSAGE);
            } else {
                results.add(EDIT_RECURRENCE_MESSAGE);
                timetable.setEvent(index, newEvent);
            }

        }

        // Edit endRecurrenceDate here.
        if (endRecurrenceDate != null) {
            Event currEvent = timetable.getEvent(index);
            if (currEvent instanceof RecurringEvent) {
                results.add(EDIT_RECURRENCE_DATE_MESSAGE);
                ((RecurringEvent) currEvent).setEndRecurrenceDate(endRecurrenceDate);
            } else {
                // Throw warnings
                results.add(EDIT_WARNING_RECURRENCE_ON_NON_RECURRENCE_MESSAGE);
            }
        }


        if (results.size() == 1) {
            results.add(EDIT_EVENT_UNSUCCESSFUL_MESSAGE);
        } else {
            try {
                storageManager.saveTimetable(timetable);
            } catch (IOException exception) {
                results.add(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
                return Formatter.formatString(FILE_WRITE_UNSUCCESSFUL_MESSAGE);
            }
        }

        return Formatter.formatString(results, true);
    }
}
