package timetable;

import java.util.Scanner;

public class TimeTableRun {

    public DateList events;
    public TimeTableStorage storage;

    public TimeTableRun(){
        events = new DateList();
        storage = new TimeTableStorage("TimeTable.txt", events);
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        String command;
        do {
            command = in.nextLine();
            TimeTableParser.commandParser(command, events, storage);
        } while (!command.equals("exit"));
    }
}
