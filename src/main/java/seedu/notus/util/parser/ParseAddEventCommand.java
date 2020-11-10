package seedu.notus.util.parser;

//@@author brandonywl
import seedu.notus.command.AddEventCommand;
import seedu.notus.command.Command;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.tag.Tag;
import seedu.notus.data.timetable.DailyEvent;
import seedu.notus.data.timetable.Event;
import seedu.notus.data.timetable.MonthlyEvent;
import seedu.notus.data.timetable.RecurringEvent;
import seedu.notus.data.timetable.WeeklyEvent;
import seedu.notus.data.timetable.YearlyEvent;
import seedu.notus.util.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static seedu.notus.util.PrefixSyntax.PREFIX_END_TIMING;
import static seedu.notus.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND;
import static seedu.notus.util.PrefixSyntax.PREFIX_STOP_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TAG;
import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for AddEventCommand.
 */
public class ParseAddEventCommand extends Parser {

    public ParseAddEventCommand(String userMessage) {
        super(userMessage);
    }

    /**
     * Takes a user string designated to add an event and prepares it by extracting relevant information from the
     * provided required and optional tags. It requires a title tag and a timing tag (/t and /timing). Other tags allow
     * for it to be recurring and to set reminders of the event.
     * Takes the format "add-e /t {Title} /timing {YYYY-MM-DD HH:MM} [/rem [How much earlier to remind]]
     * [/rec {How often to re-occur}] [/stop {YYYY-MM-DD HH:MM}]
     *
     * @return Returns an AddEventCommand to be executed by NotUS.
     * @throws SystemException Information provided by the tags are blank, wrong or do not have a default value.
     */
    @Override
    public Command parse() throws SystemException {
        // add-e eventTitle /t timing /rec occurrence /rem time before (default same day)
        String title = "";
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        // Not set yet. Has no prefix for this.
        LocalDateTime recurringEndTime = null;
        boolean toRemind = false;
        boolean isRecurring = false;
        String recurringType = "";
        HashMap<String, ArrayList<Integer>> reminderSchedule = new HashMap<>();
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);
            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    title = checkBlank(infoDetails[1], exception);
                    break;
                case PREFIX_TAG:
                    Tag tag = handleTagPrefix(infoDetails);
                    tags.add(tag);
                    break;
                case PREFIX_TIMING:
                    exception = ExceptionType.EXCEPTION_MISSING_START_TIMING;
                    String timingString = checkBlank(infoDetails[1], exception);
                    startDateTime = DateTimeManager.dateTimeParser(timingString);
                    break;
                case PREFIX_END_TIMING:
                    exception = ExceptionType.EXCEPTION_MISSING_END_TIMING;
                    timingString = checkBlank(infoDetails[1], exception);
                    endDateTime = DateTimeManager.dateTimeParser(timingString);
                    break;
                case PREFIX_REMIND:
                    toRemind = true;
                    if (infoDetails.length == 1 || infoDetails[1].isBlank()) {
                        reminderSchedule = new HashMap<>();
                        reminderSchedule.put(Event.REMINDER_DAY, new ArrayList<>(List.of(1)));
                        break;
                    }
                    reminderSchedule = handleReminderParsing(infoDetails[1]);
                    break;
                case PREFIX_RECURRING:
                    isRecurring = true;
                    exception = ExceptionType.EXCEPTION_MISSING_RECURRING_TYPE;
                    try {
                        recurringType = checkBlank(infoDetails[1], exception).toLowerCase();
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        recurringType = RecurringEvent.DAILY_RECURRENCE_TYPE;
                    }
                    break;
                case PREFIX_STOP_RECURRING:
                    exception = ExceptionType.EXCEPTION_MISSING_RECURRING_END_TIME;
                    String endTimingString = checkBlank(infoDetails[1], exception);
                    recurringEndTime = DateTimeManager.dateTimeParser(endTimingString);
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
            title = checkBlank(title, ExceptionType.EXCEPTION_MISSING_TITLE);

            if (startDateTime == null) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_START_TIMING);
            }

            if (endDateTime == null) {
                // Handle warning if endDateTime extends into the next day in AddEventCommand
                // Handle warning if endDateTime < startDateTime in the AddEventCommand
                endDateTime = startDateTime.plusHours(AddEventCommand.DEFAULT_EVENT_LENGTH);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
        }

        Event event;

        if (isRecurring) {
            LocalDate date = (recurringEndTime != null) ? recurringEndTime.toLocalDate() : null;
            switch (recurringType) {
            case RecurringEvent.DAILY_RECURRENCE_TYPE:
                event = new DailyEvent(title, startDateTime, endDateTime, toRemind, date, reminderSchedule, tags);
                break;
            case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
                event = new WeeklyEvent(title, startDateTime, endDateTime, toRemind, date, reminderSchedule, tags);
                break;
            case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
                event = new MonthlyEvent(title, startDateTime, endDateTime, toRemind, date, reminderSchedule, tags);
                break;
            case RecurringEvent.YEARLY_RECURRENCE_TYPE:
                event = new YearlyEvent(title, startDateTime, endDateTime, toRemind, date, reminderSchedule, tags);
                break;
            default:
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_RECURRING_TYPE);
            }
        } else {
            event = new Event(title, startDateTime, endDateTime, toRemind, false, reminderSchedule, tags);
        }
        return new AddEventCommand(event);
    }
}
