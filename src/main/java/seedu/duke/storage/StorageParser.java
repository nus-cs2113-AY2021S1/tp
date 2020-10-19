package seedu.duke.storage;

import seedu.duke.event.Event;
import seedu.duke.event.Personal;
import seedu.duke.event.Repeat;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class StorageParser {

    public static Event stringToEvent(String line, String type) {
        String[] words = line.split("\\|");
        String name;
        String date;
        String time;
        String repeatUnit;
        String repeatNumber;
        String link;
        String location;
        String[] statuses;
        String[] info;

        switch(type) {
        case "Personal":
            info = Arrays.copyOfRange(words, 0, 5);
            statuses = Arrays.copyOfRange(words, 5, words.length);
            return makePersonal(info, statuses);

        }
    }

    private static Personal makePersonal(String[] info, String[] statuses) {
        //0 is name, 1 is date, 2 is time, 3 is repeat unit, 4 is repeat number
        Personal p = new Personal(info[0]);
        if (info[1] == "0") {
            //no date, event can be returned as is
            setDone(p, statuses[0]);
            return p;
        } else if (info[2] == "0") {
            //no time, but got date
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = null;
        } else {
            //has both date and time
            LocalDate date = DateTimeParser.dateParser(info[1]);
            LocalTime time = DateTimeParser.timeParser(info[2]);
        }

        setDone(p, statuses[0]);
        setRepeat(p, statuses, info[3], info[4]);

    }

    private static void setDone(Event e, String doneStatus) {
        boolean isDone = doneStatus.equals("T");
        if (isDone) {
            e.markAsDone();
        }
    }

    private static void setRepeat(Event activity, String[] statuses, String timeUnit, String repeatNumber) {

        LocalDate startDate = activity.getDate();
        LocalTime startTime = activity.getTime();
        try {
            Repeat repeatList = new Repeat(startDate, startTime, timeUnit, Integer.parseInt(repeatNumber));

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
