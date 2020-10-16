package timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DurationTest {

    private static final  LocalDateTime dateTime1 = LocalDateTime.of(2020, 10,16,16,0);
    private static final  LocalDateTime dateTime2 = LocalDateTime.of(2020,10,16,18,0);
    private static final Duration duration = new Duration(dateTime1, dateTime2);

    @Test
    void getTime() {
        assertEquals(1600, duration.getTime(dateTime1));
    }

    @Test
    void containTimeSlot_testTrue() {
        assertTrue(duration.containTimeSlot(1700));
    }

    @Test
    void containTimeSlot_testFalse() {
        assertFalse(duration.containTimeSlot(1300));
    }
}