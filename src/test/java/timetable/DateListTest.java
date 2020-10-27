package timetable;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateListTest {

    @Test
    void addLesson_checkListValue() throws ClashScheduleException {
        DateList dateList = new DateList();
        Lesson lesson = new Lesson("CS2113", "www.zoom.com/abcde", true, 1);
        Duration duration1 = new Duration(LocalDateTime.of(2020,10,16,16,0),
                LocalDateTime.of(2020,10,16,18,0));
        lesson.addPeriod(duration1);
        Duration duration2 = new Duration(LocalDateTime.of(2020,10,19,16,0),
                LocalDateTime.of(2020,10,19,18,0));
        lesson.addPeriod(duration2);
        LocalDate result1 = LocalDate.of(2020,10,16);
        LocalDate result2 = LocalDate.of(2020,10,19);
        dateList.addEvent(lesson);
        assertEquals(result1, dateList.dateList.get(0).dateTag);
        assertEquals(result2, dateList.dateList.get(1).dateTag);
    }

    @Test
    void addLesson_addTwoLesson() throws ClashScheduleException {
        Lesson lesson1 = new Lesson("CS2113", "www.zoom.com/abcde", true, 1);
        Lesson lesson2 = new Lesson("CS2101", "www.zoom.com/cdefg", true, 1);
        Duration duration1 = new Duration(LocalDateTime.of(2020,10,16,16,0),
                LocalDateTime.of(2020,10,16,18,0));
        lesson1.addPeriod(duration1);
        Duration duration2 = new Duration(LocalDateTime.of(2020,10,19,16,0),
                LocalDateTime.of(2020,10,19,18,0));
        lesson2.addPeriod(duration2);
        DateList dateList = new DateList();
        dateList.addEvent(lesson1);
        dateList.addEvent(lesson2);
        assertEquals("CS2101", dateList.dateList.get(1).events.get(0).name);
    }
}