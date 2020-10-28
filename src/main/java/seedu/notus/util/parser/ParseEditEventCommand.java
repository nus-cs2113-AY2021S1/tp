package seedu.notus.util.parser;

//@@author brandonywl

import seedu.notus.command.Command;
import seedu.notus.command.EditEventCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.timetable.RecurringEvent;
import seedu.notus.util.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_ADD;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_CLEAR;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_DROP;
import static seedu.notus.util.PrefixSyntax.PREFIX_STOP_RECURRING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TIMING;
import static seedu.notus.util.PrefixSyntax.PREFIX_TITLE;

/**
 * Represents a parser object specifically to parse message for EditEventCommand.
 */
public class ParseEditEventCommand extends Parser {

    public ParseEditEventCommand(String userMessage) {
        super(userMessage);
    }

    @Override
    public Command parse() throws SystemException {

        int index = -1;
        String newTitle = "";
        LocalDateTime startDateTime = null;
        // Not set yet. Has no prefix for this.
        LocalDateTime endDateTime = null;
        String recurringType = "";
        HashMap<String, ArrayList<Integer>> reminderSchedule = null;
        String reminderTodo = "";
        String remindersString;
        LocalDate endRecurrenceDate = null;

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);
            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    newTitle = checkBlank(infoDetails[1], exception);
                    break;
                case PREFIX_TIMING:
                    exception = ExceptionType.EXCEPTION_MISSING_TIMING;
                    String timingString = checkBlank(infoDetails[1], exception);
                    startDateTime = DateTimeManager.dateTimeParser(timingString);
                    break;
                case PREFIX_INDEX:
                    exception = ExceptionType.EXCEPTION_MISSING_INDEX;
                    String indexString = checkBlank(infoDetails[1], exception);
                    try {
                        index = Integer.parseInt(indexString);
                    } catch (NumberFormatException exception1) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_FORMAT);
                    }
                    if (index <= NULL_INDEX) {
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_INDEX_VALUE);
                    }
                    break;
                case PREFIX_REMIND_ADD:
                    if (!reminderTodo.isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_EDIT_REMINDER_SET);
                    }
                    reminderTodo = EditEventCommand.REMINDER_TYPE_ADD;
                    exception = ExceptionType.EXCEPTION_MISSING_EDIT_REMINDERS;
                    remindersString = checkBlank(infoDetails[1], exception);
                    reminderSchedule = handleReminderParsing(remindersString);
                    break;
                case PREFIX_REMIND_CLEAR:
                    if (!reminderTodo.isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_EDIT_REMINDER_SET);
                    }
                    reminderTodo = EditEventCommand.REMINDER_TYPE_CLEAR;
                    // Ignore if there are any reminder timings set after /remind-clear
                    break;
                case PREFIX_REMIND_DROP:
                    if (!reminderTodo.isBlank()) {
                        throw new SystemException(ExceptionType.EXCEPTION_EDIT_REMINDER_SET);
                    }
                    reminderTodo = EditEventCommand.REMINDER_TYPE_DROP;
                    exception = ExceptionType.EXCEPTION_MISSING_EDIT_REMINDERS;
                    remindersString = checkBlank(infoDetails[1], exception);
                    reminderSchedule = handleReminderParsing(remindersString);
                    break;
                case PREFIX_RECURRING:
                    exception = ExceptionType.EXCEPTION_MISSING_EDIT_RECURRING_TYPE;
                    String recurringString = checkBlank(infoDetails[1], exception);
                    switch (recurringString) {
                    // Fall through for all cases
                    case RecurringEvent.NO_RECURRENCE_TYPE:
                    case RecurringEvent.DAILY_RECURRENCE_TYPE:
                    case RecurringEvent.WEEKLY_RECURRENCE_TYPE:
                    case RecurringEvent.MONTHLY_RECURRENCE_TYPE:
                    case RecurringEvent.YEARLY_RECURRENCE_TYPE:
                        recurringType = recurringString;
                        break;
                    default:
                        throw new SystemException(ExceptionType.EXCEPTION_INVALID_EDIT_RECURRING_TYPE);
                    }
                    break;
                case PREFIX_STOP_RECURRING:
                    exception = ExceptionType.EXCEPTION_INVALID_END_RECURRENCE_TIMING_FORMAT;
                    String endRecurrenceDateString = checkBlank(infoDetails[1], exception);
                    endRecurrenceDate = DateTimeManager.dateTimeParser(endRecurrenceDateString).toLocalDate();
                    break;
                default:
                    throw new SystemException(ExceptionType.EXCEPTION_INVALID_PREFIX);
                }
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_DESCRIPTION);
        }

        if (index == -1) {
            throw new SystemException(ExceptionType.EXCEPTION_MISSING_INDEX_PREFIX);
        }

        return new EditEventCommand(index - 1, newTitle, startDateTime,
                reminderTodo, reminderSchedule, recurringType, endRecurrenceDate);
    }
}
