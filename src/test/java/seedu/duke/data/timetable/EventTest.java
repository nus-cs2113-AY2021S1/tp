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
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1, 3));
    private static final ArrayList<String> TEST_TIME_UNITS
            = new ArrayList<>(List.of(Event.REMINDER_DAY, Event.REMINDER_DAY));
    private static final boolean TEST_RECURRING = false;

    Event event = new Event(TEST_TITLE, TEST_DATE_TIME, TEST_REMINDER, TEST_RECURRING,
            TEST_TIME_PERIODS, TEST_TIME_UNITS);

    @Test
    void getReminderDates() {
        ArrayList<LocalDate> reminderDates = event.getReminderDates();
        int i = 0;
        for (Integer daysBefore : TEST_TIME_PERIODS) {
            LocalDate dateTime = TEST_DATE_TIME.minusDays(daysBefore).toLocalDate();
            assertReminderDate(reminderDates.get(i), dateTime);
            i++;
        }
    }

    @Test
    void assertReminderDate(LocalDate generatedDate, LocalDate correctDate) {
        assertEquals(generatedDate, correctDate);
    }
}
