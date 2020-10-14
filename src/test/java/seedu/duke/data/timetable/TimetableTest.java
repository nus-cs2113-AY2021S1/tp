package seedu.duke.data.timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TimetableTest {
    private static final String TEST_TITLE_1 = "CS2113 Tutorial";
    private static final String TEST_TITLE_2 = "CS2113 Lecture";
    private static final String TEST_TITLE_3 = "CS2113 Meeting";
    private static final String TEST_TITLE_4 = "CS2113 Coding";
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2020, 8, 27, 13,0);
    private static final boolean TEST_REMINDER = true;
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1,3));
    private static final ArrayList<String> TEST_TIME_UNITS
            = new ArrayList<>(List.of(Event.REMINDER_DAY, Event.REMINDER_DAY));
    private final DailyEvent dailyEvent = new DailyEvent(TEST_TITLE_4, TEST_DATE_TIME,
            TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);
    private final WeeklyEvent weeklyEvent = new WeeklyEvent(TEST_TITLE_3, TEST_DATE_TIME,
            TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);
    private final MonthlyEvent monthlyEvent = new MonthlyEvent(TEST_TITLE_2, TEST_DATE_TIME,
            TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);
    private final YearlyEvent yearlyEvent = new YearlyEvent(TEST_TITLE_1, TEST_DATE_TIME,
            TEST_REMINDER, TEST_TIME_PERIODS, TEST_TIME_UNITS);

    /**
     * Asserts storing of events in timetable in respective recurrence length is correct.
     */
    @Test
    void assertAddEvent() {
        Timetable timetable = initializeTimetable();
        assertEquals(4, timetable.getEvents().size());
        assertEquals(1, timetable.getDailyEvents().size());
    }

    /**
     * Assert that deleting an event would result in deleting the event from the total pool
     * as well as the sub-arraylist it should be in.
     */
    @Test
    void assertDeleteEvent() {
        Timetable timetable = initializeTimetable();
        timetable.deleteEvent(0);
        assertEquals(3, timetable.getEvents().size());
        assertEquals(0, timetable.getDailyEvents().size());
    }

    /**
     * Asserts that recurring events are displayed properly across the specified year.
     */
    @Test
    void assertYearlyTimetable() {
        Timetable timetable = initializeTimetable();
        assertEquals(5, timetable.getYearTimetable(2020).size());
        ArrayList<String> keys = new ArrayList<>(timetable.getYearTimetable(2020).keySet());
        for (String key : keys) {
            HashMap<Integer, ArrayList<Event>> monthSchedule = timetable.getYearTimetable(2020).get(key);
            int numDays = 0;
            if (key.equals(Month.AUGUST.toString())) {
                numDays = TEST_DATE_TIME.toLocalDate().lengthOfMonth() - TEST_DATE_TIME.getDayOfMonth() + 1;
            } else if (key.equals(Month.SEPTEMBER.toString()) || key.equals(Month.NOVEMBER.toString())) {
                numDays = 30;
            } else if (key.equals(Month.OCTOBER.toString()) || key.equals(Month.DECEMBER.toString())) {
                numDays = 31;
            }
            assertEquals(numDays, monthSchedule.size());
        }
    }

    /**
     * Assert that getAllEvents, getEventSetReminder and getReminders should be working correclty.
     * Correct result should be 2, tomorrow's daily event and three day's from now daily event.
     */
    @Test
    void assertTodayReminders() {
        Timetable timetable = initializeTimetable();
        LocalDate startDate = TEST_DATE_TIME.toLocalDate();
        LocalDate endDate = startDate.plusMonths(1);
        ArrayList<Event> eventSet = timetable.getAllEvents(startDate, endDate);
        assertEquals(40, eventSet.size());
        PriorityQueue<Reminder> reminders = timetable.getEventSetReminder(eventSet);
        assertEquals(80, reminders.size());
        ArrayList<Reminder> todayReminders = new ArrayList<>();
        while (reminders.size() > 0 && reminders.peek().reminderDue(startDate)) {
            Reminder reminder = reminders.poll();
            assertNotNull(reminder);
            if (reminder.toRemind(startDate)) {
                todayReminders.add(reminder);
            }
        }
        System.out.println(todayReminders);
        assertEquals(2, todayReminders.size());
    }

    /**
     * Method to initialize a timetable for testing purposes with 4 default events.
     * @return Instantiated Timetable with 4 default events.
     */
    private Timetable initializeTimetable() {
        Timetable timetable = new Timetable();
        timetable.addEvent(dailyEvent);
        timetable.addEvent(weeklyEvent);
        timetable.addEvent(monthlyEvent);
        timetable.addEvent(yearlyEvent);
        return timetable;
    }
}