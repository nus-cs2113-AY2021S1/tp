//@@author scjx123

package data;

import constants.Constants;
import data.jobs.Task;
import org.junit.jupiter.api.Test;
import seedu.duke.Domsun;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckRecurrenceDateTest {
    Period recurrence = Period.ofDays(7);
    private boolean isWeekly = false;
    private static String str = "2016-03-04 11:30";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static LocalDateTime testDate = LocalDateTime.parse(str, formatter);

    @Test
    public void weekly_GeneralInputWithWeekly_UpdatedDate() {
        Domsun d = new Domsun(false, System.out, System.in, Constants.PATH,
                Constants.TEST_TASK_FILENAME, Constants.TEST_COURSE_FILENAME);
        isWeekly = true;
        assertTrue(d.testSut("deadline eat /by 12-12-2019 11:30 Weekly", false, true)
                .contains("19"));
    }

    @Test
    public void weekly_NormalInputWithWeekly_UpdatedDate() {
        isWeekly = true;
        LocalDateTime returnDate = checkRecurrenceDate(testDate);
        assertTrue(returnDate.isAfter(testDate));
    }

    @Test
    public void weekly_NormalInputWithouteekly_UpdatedDate() {
        isWeekly = false;
        LocalDateTime returnDate = checkRecurrenceDate(testDate);
        assertNull(returnDate);
    }

    public LocalDateTime checkRecurrenceDate(LocalDateTime testDate) {
        LocalDateTime newDate = null;
        if (isWeekly) {
            //assuming 7 days recurrence
            LocalDateTime todayDate = LocalDateTime.now();
            LocalDateTime endDate = testDate;
            if (todayDate.isAfter(endDate)) {
                newDate = endDate.plus(recurrence);
            }
        }
        return newDate;
    }
}


