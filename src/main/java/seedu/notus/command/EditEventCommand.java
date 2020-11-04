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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.CommandMessage.EDIT_EVENT_END_DATE_AFTER_START_DATE_WARNING;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_END_TIME_AFTER_START_WARNING;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_END_TIME_SHIFT_SUCCESS_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_END_TIME_SUCCESS_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_START_TIME_SUCCESS_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_START_TIME_SUCCESS_WARNING;
import static seedu.notus.util.CommandMessage.EDIT_EVENT_UNSUCCESSFUL_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_END_RECURRENCE_DATE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_RECURRENCE_TYPE_MESSAGE;
import static seedu.notus.util.CommandMessage.EDIT_REMINDER_MESSAGE;
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
    private final LocalDateTime newStartDateTime;
    private final LocalDateTime newEndDateTime;
    private final String reminderTodo;
    private final HashMap<String, ArrayList<Integer>> reminderSchedule;
    private final String recurringType;
    private final LocalDate endRecurrenceDate;

    public static final LocalTime DEFAULT_EVENT_END_TIMING = LocalTime.of(23,59);

    /**
     * Constructs an EditEventCommand to edit an Event.
     *
     * @param index Index of the event to be edited.
     * @param newTitle New title of the event. Null if it is not edited.
     * @param newStartDateTime New start date of the event. Null if it is not edited.
     * @param reminderTodo Reminder edit type of the event. "" If it is not edited.
     * @param reminderSchedule Reminder schedule of the things to do to event. Null if it is not edited.
     */
    public EditEventCommand(int index, String newTitle, LocalDateTime newStartDateTime, LocalDateTime newEndDateTime,
                            String reminderTodo, HashMap<String, ArrayList<Integer>> reminderSchedule,
                            String recurringType, LocalDate endRecurrenceDate) {
        this.index = index;
        this.newTitle = newTitle;
        this.newStartDateTime = newStartDateTime;
        this.newEndDateTime = newEndDateTime;
        this.reminderTodo = reminderTodo;
        this.reminderSchedule = reminderSchedule;
        this.recurringType = recurringType;
        this.endRecurrenceDate = endRecurrenceDate;
    }

    /**
     * Attributes to the object are first edited. Next if there is indication to change the class of the object,
     * we would take all attributes of the event and instantiate a new class based on it. The new object then replaces
     * the old object in the timetable object.
     *
     * @return Warnings and success messages.
     */
    @Override
    public String execute() {
        ArrayList<String> results = new ArrayList<>();
        results.add(PROCESSING_EDIT_MESSAGE);
        Event event = timetable.getEvent(index);

        editTitle(event, results);
        editTimings(event, results);
        editReminder(event, results);

        // If change recurring, edit all the other data first, instantiate a new object with the relevant type of
        // recurring event and replace the original event.
        // Set endRecurrenceDate to the original or DEFAULT whichever is set originally first.
        editRecurrenceType(event, results);
        editRecurrenceDate(timetable.getEvent(index), results);

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

    /**
     * Private method to edit the title. Method call will exit if there is no newTitle.
     *
     * @param event Event to set on.
     * @param results Results List to add on the result of this operation.
     */
    private void editTitle(Event event, ArrayList<String> results) {
        if (newTitle.isBlank()) {
            return;
        }
        event.setTitle(newTitle);
        results.add(EDIT_TITLE_MESSAGE);
    }

    /**
     * Private method to check if two datetime are on the same day.
     *
     * @param startDateTime Start Date to check
     * @param endDateTime End Date to check
     * @return Whether they are the same day
     */
    private boolean isSameDay(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return (endDateTime.getDayOfYear() == startDateTime.getDayOfYear());
    }

    private String getStartEndTimeErrors(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (endDateTime.compareTo(startDateTime) < 0) {
            return EDIT_EVENT_END_TIME_AFTER_START_WARNING;
        }
        if (isSameDay(startDateTime, endDateTime)) {
            return EDIT_EVENT_END_DATE_AFTER_START_DATE_WARNING;
        }
        return "";
    }

    /**
     * Private method to edit both startDateTime and endDateTime of events. Will exit if none is edited. Will print
     * errors if endDate is set to earlier than startDate, or endDate is on a different date from startDate.
     *
     * @param event Event to set on.
     * @param results Results List to add on the result of this operation.
     */
    private void editTimings(Event event, ArrayList<String> results) {
        // Edit startDate is indicated
        if (newStartDateTime != null) {
            if (newEndDateTime == null) {
                LocalDateTime endDateTime = newStartDateTime.plusMinutes(event.getEventLengthInMinutes());
                event.setStartDateTime(newStartDateTime);
                if (isSameDay(newStartDateTime, endDateTime)) {
                    event.setEndDateTime(newStartDateTime.with(DEFAULT_EVENT_END_TIMING));
                    results.add(EDIT_EVENT_START_TIME_SUCCESS_WARNING);
                    return;
                }
                event.setEndDateTime(endDateTime);
                results.add(EDIT_EVENT_START_TIME_SUCCESS_MESSAGE);
                results.add(EDIT_EVENT_END_TIME_SHIFT_SUCCESS_MESSAGE);
                return;
            }

            String errorMessages = getStartEndTimeErrors(newStartDateTime, newEndDateTime);
            if (errorMessages.isBlank()) {
                event.setStartDateTime(newStartDateTime);
                event.setEndDateTime(newEndDateTime);
                results.add(EDIT_EVENT_START_TIME_SUCCESS_MESSAGE);
                results.add(EDIT_EVENT_END_TIME_SUCCESS_MESSAGE);
                return;
            }
            results.add(errorMessages);
        } else if (newEndDateTime != null) {
            // No start date time but have end date time changes.
            String errorMessages = getStartEndTimeErrors(event.getStartDateTime(), newEndDateTime);
            if (errorMessages.isBlank()) {
                event.setEndDateTime(newEndDateTime);
                results.add(EDIT_EVENT_END_TIME_SUCCESS_MESSAGE);
                return;
            }
            results.add(errorMessages);
        }
    }

    private boolean addReminders(ArrayList<Integer> remindersToAdd, ArrayList<Integer> remindersPresent) {
        boolean warningSignal = true;
        for (Integer reminder : remindersToAdd) {
            if (!remindersPresent.contains(reminder)) {
                remindersPresent.add(reminder);
                warningSignal = false;
            }
        }
        return warningSignal;
    }

    private boolean addAllReminders(HashMap<String, ArrayList<Integer>> originalReminderSchedule) {
        boolean warningSignal = true;
        for (String unit : reminderSchedule.keySet()) {
            ArrayList<Integer> remindersToAdd = reminderSchedule.get(unit);
            ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
            if (remindersPresent == null) {
                originalReminderSchedule.put(unit, remindersToAdd);
                warningSignal = false;
                continue;
            }
            // As long as addReminder returns false or warningSignal was previously false, all future will be false
            // && statement is set behind to prevent short-circuiting
            warningSignal = addReminders(remindersToAdd, remindersPresent) && warningSignal;
        }
        return warningSignal;
    }

    private boolean dropReminders(ArrayList<Integer> remindersToDrop, ArrayList<Integer> remindersPresent) {
        boolean warningSignal = true;
        for (Integer reminder : remindersToDrop) {
            if (remindersPresent.contains(reminder)) {
                remindersPresent.remove(reminder);
                warningSignal = false;
            }
        }
        return warningSignal;
    }

    private boolean dropAllReminders(HashMap<String, ArrayList<Integer>> originalReminderSchedule) {
        boolean warningSignal = true;
        for (String unit : reminderSchedule.keySet()) {
            ArrayList<Integer> remindersToDrop = reminderSchedule.get(unit);
            ArrayList<Integer> remindersPresent = originalReminderSchedule.get(unit);
            if (remindersPresent == null) {
                continue;
            }
            // As long warningSignal is once set to false, this will equate to false
            // && statement is set behind to prevent short-circuiting
            warningSignal = dropReminders(remindersToDrop, remindersPresent) && warningSignal;
            if (remindersPresent.size() == 0) {
                originalReminderSchedule.remove(unit);
            }
        }
        return warningSignal;
    }

    /**
     * Private method to edit the reminders of the event based on the reminderTodo content. If it is empty, the function
     * will exit. Will print warnings if remind-drop/remind-clear does not end up dropping any reminders and if
     * remind-add does not end up adding any new reminders
     *
     * @param event Event to set on.
     * @param results Results List to add on the result of this operation.
     */
    private void editReminder(Event event, ArrayList<String> results) {
        if (reminderTodo.isBlank()) {
            return;
        }
        HashMap<String, ArrayList<Integer>> originalReminderSchedule = event.getReminderPeriods();
        boolean warningSignal = false;
        switch (reminderTodo) {
        case REMINDER_TYPE_ADD:
            if (!event.getIsToRemind()) {
                event.setIsToRemind(true);
                event.setReminderPeriods(reminderSchedule);
                break;
            }
            warningSignal = addAllReminders(originalReminderSchedule);
            break;
        case REMINDER_TYPE_DROP:
            if (!event.getIsToRemind()) {
                warningSignal = true;
                break;
            }
            warningSignal = dropAllReminders(originalReminderSchedule);
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
            assert false;
            break;
        }
        if (warningSignal) {
            results.add(EDIT_WARNING_REMINDER_MESSAGE);
        } else {
            results.add(EDIT_REMINDER_MESSAGE);
        }
    }

    /**
     * Private method for the command class to edit recurrence type of the event. Replaces the Event object in the
     * timetable cached in this object.
     * Will exit if no new recurrence type is set.
     *
     * @param event Event to set on.
     * @param results Results List to add on the result of this operation.
     */
    private void editRecurrenceType(Event event, ArrayList<String> results) {
        if (recurringType.isBlank()) {
            return;
        }
        String eventTitle = event.getTitle();
        LocalDateTime eventStartDateTime = event.getStartDateTime();
        LocalDateTime eventEndDateTime = event.getEndDateTime();
        LocalDate endRecurrenceDate = null;
        ArrayList<Tag> tags = event.getTags();
        boolean eventIsToRemind = event.getIsToRemind();
        boolean warningSignal = false;
        // Get end recurrence date if there is one inside. If not take the default.
        // If one is set in the same command, it will be set later.
        if (!recurringType.equals(RecurringEvent.NO_RECURRENCE_TYPE)) {
            if (event instanceof RecurringEvent) {
                endRecurrenceDate = ((RecurringEvent) event).getEndRecurrenceDate();
            } else {
                endRecurrenceDate = RecurringEvent.DEFAULT_END_RECURRENCE;
            }
        }

        switch (recurringType) {
        case RecurringEvent.NO_RECURRENCE_TYPE:
            if (!(event instanceof RecurringEvent)) {
                warningSignal = true;
                break;
            }
            event = new Event(eventTitle, eventStartDateTime, eventEndDateTime, eventIsToRemind,
                    false, event.getReminderPeriods(), tags);
            break;
        case RecurringEvent.DAILY_RECURRENCE_TYPE:
            if (event instanceof DailyEvent) {
                warningSignal = true;
                break;
            }
            assert (endRecurrenceDate != null);
            event = new DailyEvent(eventTitle, eventStartDateTime, eventEndDateTime, eventIsToRemind,
                    endRecurrenceDate, event.getReminderPeriods(), tags);
            break;
        case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
            if (event instanceof WeeklyEvent) {
                warningSignal = true;
                break;
            }
            assert (endRecurrenceDate != null);
            event = new WeeklyEvent(eventTitle, eventStartDateTime, eventEndDateTime, eventIsToRemind,
                    endRecurrenceDate, event.getReminderPeriods(), tags);
            break;
        case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
            if (event instanceof MonthlyEvent) {
                warningSignal = true;
                break;
            }
            assert (endRecurrenceDate != null);
            event = new MonthlyEvent(eventTitle, eventStartDateTime, eventEndDateTime, eventIsToRemind,
                    endRecurrenceDate, event.getReminderPeriods(), tags);
            break;
        case RecurringEvent.YEARLY_RECURRENCE_TYPE:
            if (event instanceof YearlyEvent) {
                warningSignal = true;
                break;
            }
            assert (endRecurrenceDate != null);
            event = new YearlyEvent(eventTitle, eventStartDateTime, eventEndDateTime, eventIsToRemind,
                    endRecurrenceDate, event.getReminderPeriods(), tags);
            break;
        default:
            // Should not hit here.
            warningSignal = true;
            break;
        }
        if (warningSignal) {
            results.add(EDIT_WARNING_RECURRENCE_MESSAGE);
        } else {
            results.add(EDIT_RECURRENCE_TYPE_MESSAGE);
            timetable.setEvent(index, event);
        }
    }

    /**
     * Private method for the command class to edit recurrence date of the event.
     * Will exit if no new recurrence date is set.
     *
     * @param event Event to set on.
     * @param results Results List to add on the result of this operation.
     */
    private void editRecurrenceDate(Event event, ArrayList<String> results) {
        if (endRecurrenceDate == null) {
            return;
        }
        if (event instanceof RecurringEvent) {
            results.add(EDIT_END_RECURRENCE_DATE_MESSAGE);
            ((RecurringEvent) event).setEndRecurrenceDate(endRecurrenceDate);
        } else {
            results.add(EDIT_WARNING_RECURRENCE_ON_NON_RECURRENCE_MESSAGE);
        }
    }
}
