package seedu.duke;


import seedu.duke.timetable.DateList;
import seedu.duke.timetable.Duration;
import seedu.duke.timetable.Event;
import seedu.duke.timetable.Lesson;
import seedu.duke.timetable.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        DateList event = new DateList();
        Event lesson = new Lesson("CS2113", "www.zoom.com", true);
        lesson.addPeriod(new Duration(LocalDateTime.of(2020, 10,12,11, 0)));
        event.addLesson(lesson);
        System.out.println(event.dateList.get(0).events.get(0).periods.get(0).timeSlot);
        System.out.println(LocalDate.now());
        Table.printTable(event.dateList);
    }
}
