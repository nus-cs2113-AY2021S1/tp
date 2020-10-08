package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


import seedu.duke.data.UserData;

/**
 * Command to check availability.
 */
public class CheckCommand extends Command {
    /**
     * Constructor for checking availability seedu.duke
     *
     * @param command from user input
     */
    public CheckCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        String checkDate = command.replaceFirst("check", "").trim();
        String[] datesAndTime = checkDate.split(";");

        LocalDate startDate = getDate(datesAndTime[0].trim());
        LocalDate endDate = getDate(datesAndTime[2].trim());

        LocalTime startTime = getTime(datesAndTime[1].trim());
        LocalTime endTime = getTime(datesAndTime[3].trim());

        ArrayList<Event> eventsInTimeRange = new ArrayList<>();
        String[] eventTypes = new String[]{"Personal", "Timetable", "Zoom"};
        for (String type: eventTypes) {
            EventList eventsList = data.getEventList(type);
            eventsInTimeRange.addAll(eventsList.checkEventsInTimeRange(startDate, endDate, startTime, endTime));
        }

        //in place of a UI function
        if (eventsInTimeRange.size() > 0) {
            for (Event event: eventsInTimeRange) {
                System.out.println(event);
            }
        } else {
            ui.printNoEventInTimeRangeMessage();
        }

    }

    private LocalDate getDate(String stringDate) {
        String[] dateFormats = new String[]{"dd/MM/yy", "MM/yy", "yy"};
        LocalDate date;

        for (String format : dateFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                date = LocalDate.parse(stringDate.trim(), formatter);
                return date;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }

        System.out.println("Input does not match any date formats.");
        date = LocalDate.now();

        return date;
    }

    private LocalTime getTime(String stringTime) {
        String[] timeFormats = new String[]{"HH:mm", "HH", "hh:mm a", "hh a"};
        LocalTime time;

        for (String format : timeFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            try {
                time = LocalTime.parse(stringTime.trim(), formatter);
                return time;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }

        System.out.println("Input does not match any time formats.");
        time = LocalTime.now();

        return time;
    }
}
