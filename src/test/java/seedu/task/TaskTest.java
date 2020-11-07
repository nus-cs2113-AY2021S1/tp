package seedu.task;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidDatetimeException;
import seedu.exceptions.InvalidPriorityException;
import seedu.exceptions.InvalidReminderException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.messages.Messages.INVALID_DATETIME;
import static seedu.messages.Messages.INVALID_PRIORITY;
import static seedu.messages.Messages.INVALID_REMINDER;

class TaskTest {

    @Test
    void initialiseTask_differentPriority_success() throws InvalidDatetimeException, InvalidReminderException {

        try {
            new Task("p1", null,
                    null, null, "1", null, null);
            new Task("p2", null,
                null, null, "2", null, null);
            new Task("p3", null,
                null, null, "3", null, null);
        } catch (InvalidPriorityException e) {
            fail();
        }
    }

    @Test
    void initialiseTask_invalidPriorityExceptionThrown() throws InvalidDatetimeException, InvalidReminderException {

        try {
            new Task("p0",
                null, null, null, "0", null, null);
        } catch (InvalidPriorityException e) {
            assertEquals(INVALID_PRIORITY, e.toString());
        }

        try {
            new Task("p4",
                null, null, null, "4", null, null);
        } catch (InvalidPriorityException e) {
            assertEquals(INVALID_PRIORITY, e.toString());
        }
    }

    @Test
    void initialiseTask__datetime_success() throws InvalidPriorityException, InvalidReminderException {

        try {
            new Task("dt1", "20-10-2020",
                null, null, null, null, null);
            new Task("dt2", null,
                "0000", null, null, null, null);
            new Task("dt3", null,
                null, "2359", null, null, null);
        } catch (InvalidDatetimeException e) {
            fail();
        }
    }

    @Test
    void initialise_task_invalidDatetimeExceptionThrown() throws InvalidPriorityException, InvalidReminderException {

        try {
            new Task("date_fail",
                "20-20-2020", null, null, null, null, null);
        } catch (InvalidDatetimeException e) {
            assertEquals(INVALID_DATETIME, e.toString());
        }

        try {
            new Task("time_fail",
                null, "3000", null, null, null, null);
        } catch (InvalidDatetimeException e) {
            assertEquals(INVALID_DATETIME, e.toString());
        }
    }

    @Test
    void initialiseTask_differentReminder_success() throws InvalidDatetimeException, InvalidPriorityException {

        try {
            new Task("t1", null,
                    "1800", null, null, "on", null);
            new Task("t2", null,
                    null, null, null, "off", null);
            new Task("t3", null,
                    null, null, null, "on", "1800");
        } catch (InvalidReminderException e) {
            fail();
        }
    }

    @Test
    void initialise_task_invalidReminderExceptionThrown() throws InvalidPriorityException, InvalidDatetimeException {

        try {
            new Task("reminder_fail",
                    null, null, null, null, "random", null);
        } catch (InvalidReminderException e) {
            assertEquals(INVALID_REMINDER, e.toString());
        }

        try {
            new Task("reminder_time_fail",
                    null, null, null, null, "on", null);
        } catch (InvalidReminderException e) {
            assertEquals(INVALID_REMINDER, e.toString());
        }
    }
}