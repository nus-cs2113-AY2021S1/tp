package timetable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeTableParserTest {

    @Test
    void commandParser() {
    }

    @Test
    void fileParser() {
    }

    @Test
    void getDateTimeTest() {

    }

    @Test
    void addClassTest() throws InvalidDayOfTheWeekException {
        String input = "CS1234\n" + "yes\n" + "www.zoom.com/asdf\n"
                + "Wednesday 2-4pm\n" + "1\n" + "20/10/2020\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Lesson lesson = TimeTableParser.addClass();
        Duration duration = new Duration(LocalDateTime.of(2020,10,21,14,0),
                LocalDateTime.of(2020,10,21,16,0));
        List<Duration> expectedPeriod = new ArrayList<>();
        expectedPeriod.add(duration);
        assertEquals("CS1234", lesson.name);
        assertEquals(EventType.L, lesson.eventType);
        assertEquals(expectedPeriod.get(0).timeSlot, lesson.periods.get(0).timeSlot);
    }

    @Test
    void addClassTest_throwInvalidDayOfWeekException() throws InvalidDayOfTheWeekException {
        String input = "CS1234\n" + "yes\n" + "www.zoom.com/asdf\n"
                + "Wednfesday 2-4pm\n" + "1\n" + "20/10/2020\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(InvalidDayOfTheWeekException.class, TimeTableParser::addClass);
    }
}