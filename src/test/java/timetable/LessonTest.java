package timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LessonTest {

    @Test
    void getStorageString_test() {
        Lesson lesson = new Lesson("CS2113", "www.zoom.com/abcde", true, 1);
        Duration duration = new Duration(LocalDateTime.of(2020,10,16,16,0),
                LocalDateTime.of(2020,10,16,18,0));
        lesson.addPeriod(duration);
        assertEquals("|1|1|2020-10-16T16:00|2020-10-16T18:00",
                lesson.getStorageString());
    }
}