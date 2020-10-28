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
        LocalDateTime dateTime = null;
        // Not set yet. Has no prefix for this.
        LocalDateTime recurringEndTime = null;
        boolean toRemind = false;
        boolean isRecurring = false;
        String recurringType = "";
        HashMap<String, ArrayList<Integer>> reminderSchedule = null;
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
                    exception = ExceptionType.EXCEPTION_MISSING_TIMING;
                    String timingString = checkBlank(infoDetails[1], exception);
                    dateTime = DateTimeManager.dateTimeParser(timingString);
                    break;
                case PREFIX_REMIND:
                    toRemind = true;
                    if (infoDetails.length == 1 || infoDetails[1].isBlank()) {
                        reminderSchedule = new HashMap<>();
                        reminderSchedule.put(Event.REMINDER_DAY, new ArrayList<>(List.of(1)));
                        break;
                    }
                    reminderSchedule = handleReminderParsing(infoDetails[1]);

//                    String[] reminderDetails = infoDetails[1].split(STRING_SPLIT_DELIMITER);
//                    for (String reminderDetail : reminderDetails) {
//                        String[] timeStrings = reminderDetail.split(TIMING_SPLIT_DELIMITER);
//                        if (timeStrings.length != 2) {
//                            throw new SystemException(ExceptionType.EXCEPTION_INVALID_REMINDER_FORMAT);
//                        }
//                        int timePeriod;
//                        String timeUnit;
//                        try {
//                            timePeriod = Integer.parseInt(timeStrings[0]);
//                            timeUnit = timeStrings[1];
//                            if ((!timeUnit.equalsIgnoreCase(Event.REMINDER_DAY)
//                                    && !timeUnit.equalsIgnoreCase(Event.REMINDER_WEEK)) || timePeriod < 1) {
//                                throw new SystemException(ExceptionType.EXCEPTION_INVALID_REMINDER_FORMAT);
//                            }
//
//                            exception = ExceptionType.EXCEPTION_EARLY_REMINDER;
//                            if (timeUnit.equalsIgnoreCase(Event.REMINDER_WEEK) && timePeriod > 1) {
//                                throw new SystemException(exception);
//                            } else if (timeUnit.equalsIgnoreCase(Event.REMINDER_DAY) && timePeriod > 7) {
//                                throw new SystemException(exception);
//                            }
//                        } catch (NumberFormatException exceptionNumFormat) {
//                            throw new SystemException(ExceptionType.EXCEPTION_INVALID_REMINDER_FORMAT);
//                        }
//                        timeUnits.add(timeUnit);
//                        timePeriods.add(timePeriod);
//                    }
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

            if (dateTime == null) {
                throw new SystemException(ExceptionType.EXCEPTION_MISSING_TIMING);
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
        }

        Event event;

        if (isRecurring) {
            LocalDate date = (recurringEndTime != null) ? recurringEndTime.toLocalDate() : null;
            switch (recurringType) {
            case RecurringEvent.DAILY_RECURRENCE_TYPE:
                event = new DailyEvent(title, dateTime, toRemind, date, reminderSchedule);
                break;
            case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
                event = new WeeklyEvent(title, dateTime, toRemind, date, reminderSchedule);
                break;
            case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
                event = new MonthlyEvent(title, dateTime, toRemind, date, reminderSchedule);
                break;
            case RecurringEvent.YEARLY_RECURRENCE_TYPE:
                event = new YearlyEvent(title, dateTime, toRemind, date, reminderSchedule);
                break;
            default:
                throw new SystemException(ExceptionType.EXCEPTION_INVALID_RECURRING_TYPE);
            }
        } else {
            event = new Event(title, dateTime, toRemind, false, reminderSchedule);
        }
        return new AddEventCommand(event);
    }
}
