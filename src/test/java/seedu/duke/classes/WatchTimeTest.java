package seedu.duke.classes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WatchTimeTest {
    WatchTime limitation = new WatchTime(LocalDate.of(2020,10,19),60,120);

    @Test
    void userReportString() {
        assertEquals("Date : 2020-10-19" + System.lineSeparator()
                + "Time left today : 1 hour(s) 0 minutes."
                        + " To update the time allocated to watching shows, use the 'updateTimeLimit' command.",
                limitation.userReportString());
    }

}