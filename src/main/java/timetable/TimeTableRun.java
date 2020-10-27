package timetable;

import studyit.StudyItLog;

import java.util.Scanner;

public class TimeTableRun {

    public DateList events;
    public TimeTableStorage storage;

    public TimeTableRun() {
        events = new DateList();
        storage = new TimeTableStorage("data/timetable.txt", events);
        StudyItLog.logger.info("Academic mode initialized");
    }

    public void run(String command) {
        TimeTableParser.commandParser(command, events, storage);
    }
}
