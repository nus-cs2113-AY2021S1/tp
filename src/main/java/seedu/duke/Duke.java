package seedu.duke;

import seedu.duke.timeTable.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        DateList event = new DateList();
        Event lesson = new Lesson("CS2113", "www.zoom.com", true);
        lesson.addPeriod(new Duration(LocalDateTime.of(2020, 10,12,11,00)));
        event.addLesson(lesson);
        System.out.println(event.dateList.get(0).events.get(0).periods.get(0).timeSlot);
        System.out.println(LocalDate.now());
        Table.printTable(event.dateList);
    }
}
