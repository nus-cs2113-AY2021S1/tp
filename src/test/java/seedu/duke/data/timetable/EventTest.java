package seedu.duke.data.timetable;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventTest {
    private static final String TEST_TITLE = "CS2113 Tutorial";
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2020, 8, 27, 13,0);
    private static final boolean TEST_REMINDER = true;
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1));
    private static final ArrayList<String> TEST_TIME_UNITS
            = new ArrayList<>(List.of(Event.REMINDER_DAY));
    private static final boolean TEST_RECURRING = false;

    Event event = new Event(TEST_TITLE, TEST_DATE_TIME, TEST_REMINDER, TEST_RECURRING,
            TEST_TIME_PERIODS, TEST_TIME_UNITS);

    @Test
    void getReminderDates() {
        ArrayList<LocalDate> reminderDates = event.getReminderDates();
        reminderDates.sort(LocalDate::compareTo);
        ArrayList<Integer> timePeriods = new ArrayList<>(TEST_TIME_PERIODS);
        timePeriods.sort(Integer::compareTo);
        int i = 0;
        for (Integer daysBefore : timePeriods) {
            LocalDate dateTime = TEST_DATE_TIME.minusDays(daysBefore).toLocalDate();
            assertReminderDate(reminderDates.get(i), dateTime);
            i++;
        }
    }

    void assertReminderDate(LocalDate correctDate, LocalDate generatedDate) {
        assertEquals(correctDate, generatedDate);
    }
}
