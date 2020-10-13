package seedu.duke;


import timetable.DateList;
import timetable.TimeTableParser;
import timetable.TimeTableStorage;

import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        DateList events = new DateList();
        TimeTableStorage storage = new TimeTableStorage("storage.txt", events);
        Scanner in = new Scanner(System.in);
        String command;
        do{
            command = in.nextLine();
            TimeTableParser.commandParser(command, events,storage);
        }while (!command.equals("exit"));
    }
}
