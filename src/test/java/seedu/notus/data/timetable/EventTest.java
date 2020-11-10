package seedu.notus.data.timetable;

import org.junit.jupiter.api.Test;
import seedu.notus.data.tag.Tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//@@author brandonywl
/**
 * Test Driver for all functionality of EventClass.
 */
public class EventTest {
    private static final String TEST_TITLE = "CS2113 Tutorial";
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2020, 8, 27, 13,0);
    private static final LocalDateTime TEST_END_DATE_TIME = LocalDateTime.of(2020, 8, 27, 14,0);
    private static final boolean TEST_REMINDER = true;
    private static final ArrayList<Integer> TEST_TIME_PERIODS = new ArrayList<>(List.of(1,3));
    private static HashMap<String, ArrayList<Integer>> reminderSchedule = new HashMap<>();
    private static final boolean TEST_RECURRING = false;
    private static final ArrayList<Tag> tags = new ArrayList<>();

    Event event = new Event(TEST_TITLE, TEST_DATE_TIME, TEST_END_DATE_TIME, TEST_REMINDER, TEST_RECURRING,
            reminderSchedule, tags);

    /**
     * Test if you can get all the reminder dates that's needed from an event.
     */
    @Test
    void getReminderDates() {
        reminderSchedule.put("day", TEST_TIME_PERIODS);
        ArrayList<LocalDate> reminderDates = event.getReminderDates();
        reminderDates.sort(LocalDate::compareTo);
        Collections.reverse(reminderDates);
        ArrayList<Integer> timePeriods = new ArrayList<>(TEST_TIME_PERIODS);
        timePeriods.sort(Integer::compareTo);
        int i = 0;
        for (Integer daysBefore : timePeriods) {
            LocalDate dateTime = TEST_DATE_TIME.minusDays(daysBefore).toLocalDate();
            assertReminderDate(dateTime, reminderDates.get(i));
            i++;
        }
    }

    /**
     * Wrapper around assertEquals for code readbility.
     *
     * @param correctDate Expected date.
     * @param generatedDate Actual date.
     */
    void assertReminderDate(LocalDate correctDate, LocalDate generatedDate) {
        assertEquals(correctDate, generatedDate);
    }
}
