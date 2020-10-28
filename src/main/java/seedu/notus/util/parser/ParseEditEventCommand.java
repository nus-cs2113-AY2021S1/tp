package seedu.notus.util.parser;

//@@author brandonywl

import seedu.notus.command.Command;
import seedu.notus.command.EditEventCommand;
import seedu.notus.data.exception.SystemException;
import seedu.notus.data.exception.SystemException.ExceptionType;
import seedu.notus.data.tag.Tag;
import seedu.notus.util.DateTimeManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static seedu.notus.util.PrefixSyntax.PREFIX_INDEX;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_ADD;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_CLEAR;
import static seedu.notus.util.PrefixSyntax.PREFIX_REMIND_DROP;
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
        String new_title = "";
        LocalDateTime startDateTime = null;
        // Not set yet. Has no prefix for this.
        LocalDateTime endDateTime = null;
        Boolean isRecurring = null;
        String recurringType = "";
        HashMap<String, ArrayList<Integer>> reminderSchedule = null;
        String reminderTodo = "";
        String remindersString;

        try {
            ArrayList<String[]> splitInfo = splitInfoDetails(userMessage);
            for (String[] infoDetails : splitInfo) {
                String prefix = infoDetails[0].toLowerCase();
                ExceptionType exception;
                switch (prefix) {
                case PREFIX_TITLE:
                    exception = ExceptionType.EXCEPTION_MISSING_TITLE;
                    new_title = checkBlank(infoDetails[1], exception);
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

        return new EditEventCommand(index - 1, new_title, startDateTime, reminderTodo, reminderSchedule);
    }
}
