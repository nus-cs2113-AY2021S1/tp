package timetable;

import exceptions.ClashScheduleException;
import exceptions.InvalidDayOfTheWeekException;
import exceptions.InvalidTimeException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeTableParserTest {


    @Test
    void addClassTest() throws InvalidDayOfTheWeekException, InvalidTimeException {
        String input = "CS1234\n" + "yes\n" + "www.zoom.com/asdf\n"
                + "Wednesday 2-4pm\n" + "1\n" + "20/10/2020\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Lesson lesson = TimeTableCommand.addClass();
        Duration duration = new Duration(LocalDateTime.of(2020,10,21,14,0),
                LocalDateTime.of(2020,10,21,16,0));
        List<Duration> expectedPeriod = new ArrayList<>();
        expectedPeriod.add(duration);
        assertEquals("CS1234", lesson.name);
        assertEquals(EventType.L, lesson.eventType);
        assertEquals(expectedPeriod.get(0).timeSlot, lesson.periods.get(0).timeSlot);
    }

    @Test
    void addClassTest_throwInvalidDayOfWeekException() {
        String input = "CS1234\n" + "yes\n" + "www.zoom.com/asdf\n"
                + "Wednfesday 2-4pm\n" + "1\n" + "20/10/2020\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(InvalidDayOfTheWeekException.class, TimeTableCommand::addClass);
    }


    @Test
    void showLinkTest() throws InvalidDayOfTheWeekException, ClashScheduleException, InvalidTimeException {
        DateList dateList = new DateList();
        int currentHour = LocalDateTime.now().getHour();
        String currentDay = LocalDateTime.now().getDayOfWeek().toString();
        Lesson lesson1 = new Lesson("CS1234", "www.zoom.com/asdf", true, 1);
        Lesson lesson2 = new Lesson("CS5678", "www.zoom.com/qwer", true, 1);
        if (currentHour < 22) {
            String periodText1 = currentDay + " " + (currentHour) + "-" + (currentHour + 1);
            String[] period1 = periodText1.split(", ");
            String periodText2 = currentDay + " " + (currentHour + 1) + "-" + (currentHour + 2);
            String[] period2 = periodText2.split(", ");
            TimeTableCommand.addClassPeriods(period1, 1, LocalDateTime.now().toLocalDate().atTime(0, 0), lesson1);
            TimeTableCommand.addClassPeriods(period2, 1, LocalDateTime.now().toLocalDate().atTime(0, 0), lesson2);
            dateList.addEvent(lesson1);
            dateList.addEvent(lesson2);
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            TimeTableCommand.showLink(dateList);
            String expected = "www.zoom.com/asdf | CS1234\nwww.zoom.com/qwer | CS5678\n";
            assertEquals(expected, outContent.toString());
        }
    }
}