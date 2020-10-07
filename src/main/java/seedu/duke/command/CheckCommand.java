package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Command to check availability.
 */
public class CheckCommand extends Command {

    public static final int DATE_TIME_FIELD_COUNT = 4;

    /**
     * Constructor for checking availability seedu.duke
     *
     * @param command from user input
     */
    public CheckCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    //@Override
    public void execute(EventList events, Ui ui, Storage storage) {
        String checkDate = command.replaceFirst("check", "").trim();
        String[] datesAndTime = checkDate.split(";");

        LocalDate startDate = getDate(datesAndTime[0]);
        LocalDate endDate = getDate(datesAndTime[2]);

        LocalTime startTime = getTime(datesAndTime[1]);
        LocalTime endTime = getTime(datesAndTime[3]);

        events.checkEventsInTimeRange(startDate, endDate, startTime, endTime);
    }

    private void getStartAndEndTime(String[] datesAndTime, List<LocalTime> startAndEndTime) {
        LocalTime startTime = getTime(datesAndTime[1]);
        LocalTime endTime = getTime(datesAndTime[3]);
        startAndEndTime.add(startTime);
        startAndEndTime.add(endTime);
    }

    private void getStartAndEndDate(String[] datesAndTime, List<LocalDate> startAndEndDate) {
        LocalDate startDate = getDate(datesAndTime[0]);
        LocalDate endDate = getDate(datesAndTime[2]);
        startAndEndDate.add(startDate);
        startAndEndDate.add(endDate);
    }

    private LocalDate getDate(String stringDate) {
        String[] dateFormats = new String[]{"dd/MM/yy", "MM/yy", "yy"};
        LocalDate date = null;

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
        LocalTime time = null;

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
