package seedu.duke.storage;

import seedu.duke.event.*;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class StorageParser {

    public static Event stringToEvent(String line, String type) {
        String[] words = line.split("\\|");
        String[] statuses;
        String[] info;

        for(int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        switch(type) {
        case "Personal":
            info = Arrays.copyOfRange(words, 0, 5);
            statuses = Arrays.copyOfRange(words, 5, words.length);
            return makePersonal(info, statuses);
        case "Zoom":
            info = Arrays.copyOfRange(words, 0, 6);
            statuses = Arrays.copyOfRange(words, 6, words.length);
            return makeZoom(info, statuses);
        case "Timetable":
            info = Arrays.copyOfRange(words, 0, 6);
            statuses = Arrays.copyOfRange(words, 6, words.length);
            return makeTimetable(info, statuses);
        default:
            return null;
        }
    }

    private static Personal makePersonal(String[] info, String[] statuses) {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number
        try {
            Personal p = new Personal(info[0]);
            if (info[1].equals("0")) {
                //no date, event can be returned as is
                setDone(p, statuses[0]);
                return p;
            } else if (info[2].equals("0")) {
                //no time, but got date
                LocalDate date = DateTimeParser.dateParser(info[1]);
                LocalTime time = null;
                p.setDate(date);
                p.setHasDate(true);
            } else {
                //has both date and time
                LocalDate date = DateTimeParser.dateParser(info[1]);
                LocalTime time = DateTimeParser.timeParser(info[2]);
                p.setDate(date);
                p.setHasDate(true);
                p.setTime(time);
                p.setHasTime(true);
            }
            setDone(p, statuses[0]);
            repeatSetter(p, statuses, info[3], info[4]);
            return p;
        } catch (Exception e) {
            System.out.println("file corruption detected");
        }
        return null;
    }

    private static Zoom makeZoom(String[] info, String[] statuses) {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number, 5 is zoom link
        try {
            Zoom z = new Zoom(info[0], info[5]);
            if (info[1].equals("0")) {
                //no date, event can be returned as is
                setDone(z, statuses[0]);
                return z;
            } else if (info[2].equals("0")) {
                //no time, but got date
                LocalDate date = DateTimeParser.dateParser(info[1]);
                LocalTime time = null;
                z.setDate(date);
            } else {
                //has both date and time
                LocalDate date = DateTimeParser.dateParser(info[1]);
                LocalTime time = DateTimeParser.timeParser(info[2]);
                z.setDate(date);
                z.setTime(time);
            }
            setDone(z, statuses[0]);
            repeatSetter(z, statuses, info[3], info[4]);
            return z;
        } catch (Exception e) {
            System.out.println("file corruption detected");
        }
        return null;
    }

    private static Timetable makeTimetable(String[] info, String[] statuses) {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number, 5 is location
        try {
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = DateTimeParser.timeParser(info[2]);
            Timetable t = new Timetable(info[0], date, time);
            if (!info[5].equals("0")) { //location provided
                t.setLocation(info[5]);
            }
            setDone(t, statuses[0]);
            repeatSetter(t, statuses, info[3], info[4]);
            return t;
        } catch (Exception e) {
            System.out.println("file corruption detected");
        }
        return null;
    }

    private static void setDone(Event e, String doneStatus) {
        boolean isDone = doneStatus.equals("T");
        if (isDone) {
            e.markAsDone();
        }
    }

    private static void repeatSetter(Event activity, String[] statuses, String timeUnit, String repeatNumber) {

        LocalDate startDate = activity.getDate();
        LocalTime startTime = activity.getTime();
        if (Integer.parseInt(repeatNumber) == 0) {
            return;
        }
        try {
            Repeat repeatList = new Repeat(startDate, startTime, timeUnit, Integer.parseInt(repeatNumber));
            activity.setRepeat(repeatList);

            for (int i = 0; i < statuses.length; i++) {

                String status = statuses[i];
                if (status.equals("T")) {
                    activity.markAsDone(i);
                }
            }
        } catch (InvalidTimeUnitException e) {
            System.out.println("Error, wrong date should not happen, file corrupted");
        }

    }
}
