package timetable;

import java.util.Scanner;

public class TimeTableRun {

    public DateList events;
    public TimeTableStorage storage;

    public TimeTableRun() {
        events = new DateList();
        storage = new TimeTableStorage("data/timetable.txt", events);
    }

    public void run(String command) {
        TimeTableParser.commandParser(command, events, storage);
    }
}
