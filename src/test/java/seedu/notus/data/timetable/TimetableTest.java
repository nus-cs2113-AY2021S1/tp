package seedu.notus.data.timetable;

import org.junit.jupiter.api.Test;
import seedu.notus.data.tag.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@@author brandonywl
class TimetableTest {
    private static final String TEST_TITLE_1 = "CS2113 Tutorial";
    private static final String TEST_TITLE_2 = "CS2113 Lecture";
    private static final String TEST_TITLE_3 = "CS2113 Meeting";
    private static final String TEST_TITLE_4 = "CS2113 Coding";
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2020, 8, 27, 13,0);
    private static final boolean TEST_REMINDER = true;
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1,3));
    private static HashMap<String, ArrayList<Integer>> reminderSchedule = new HashMap<>();
    private static final ArrayList<Tag> tags = new ArrayList<>();
    private final DailyEvent dailyEvent = new DailyEvent(TEST_TITLE_4, TEST_DATE_TIME,
            TEST_REMINDER, reminderSchedule, tags);
    private final WeeklyEvent weeklyEvent = new WeeklyEvent(TEST_TITLE_3, TEST_DATE_TIME,
            TEST_REMINDER, reminderSchedule, tags);
    private final MonthlyEvent monthlyEvent = new MonthlyEvent(TEST_TITLE_2, TEST_DATE_TIME,
            TEST_REMINDER, reminderSchedule, tags);
    private final YearlyEvent yearlyEvent = new YearlyEvent(TEST_TITLE_1, TEST_DATE_TIME,
            TEST_REMINDER, reminderSchedule, tags);


    /**
     * Asserts storing of events in timetable in respective recurrence length is correct.
     */
    @Test
    void addEvent_oneDailyThreeOthers_success() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = initializeTimetable();
        assertEquals(4, timetable.getEvents().size());
        assertEquals(1, timetable.getDailyEvents().size());
    }

    /**
     * Assert that deleting an event would result in deleting the event from the total pool
     * as well as the sub-arraylist it should be in.
     */
    @Test
    void deleteEvent_oneDailyThreeOthers_success() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = initializeTimetable();
        timetable.deleteEvent(0);
        assertEquals(3, timetable.getEvents().size());
        assertEquals(0, timetable.getDailyEvents().size());
    }

    /**
     * Asserts that recurring events are displayed properly across the specified year.
     */
    @Test
    void getAllEvents_dailyEvent_success() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = initializeTimetable(dailyEvent);
        LocalDate startDate = TEST_DATE_TIME.withDayOfYear(1).toLocalDate();
        LocalDate endDate = startDate.withDayOfYear(startDate.lengthOfYear());
        assertEquals(startDate.lengthOfYear() - TEST_DATE_TIME.getDayOfYear() + 1,
                timetable.getAllEvents(startDate, endDate).size());
    }

    /**
     * Assert that getReminders should work correctly.
     * Correct result should be 2, tomorrow's daily event and three day's from now daily event.
     */
    @Test
    void getReminders_dailyEvent_success() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = initializeTimetable(dailyEvent);
        assertEquals(2, timetable.getReminders().size());
    }

    /**
     * Method to initialize a timetable for testing purposes with 4 default events.
     *
     * @return Instantiated Timetable with 4 default events.
     */
    private Timetable initializeTimetable() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = new Timetable();
        timetable.addEvent(dailyEvent);
        timetable.addEvent(weeklyEvent);
        timetable.addEvent(monthlyEvent);
        timetable.addEvent(yearlyEvent);
        return timetable;
    }

    /**
     * Method to initialize a timetable for testing purposes with 1 event.
     *
     * @return Instantiated Timetable with 1 event.
     */
    private Timetable initializeTimetable(Event event) {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        Timetable timetable = new Timetable();
        timetable.addEvent(event);
        return timetable;
    }
}
