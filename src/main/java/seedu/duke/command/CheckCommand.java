package seedu.duke.command;

import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


import seedu.duke.data.UserData;

import static seedu.duke.parser.DateTimeParser.timeParser;

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
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        String[] datesAndTime = command.split(";");


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
        EventList coinciding = new EventList("coinciding", eventsInTimeRange);

        ui.printList(coinciding);
    }

    private LocalDate getDate(String stringDate) throws DateErrorException {

        String[] dateFields = stringDate.replace("-","/").split("/");

        LocalDate date;
        LocalDate currentDate = LocalDate.now();

        try {
            switch (dateFields.length) {
            case 0: // empty date field
                return currentDate;
            case 1: // only year is given
                DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("yy");
                Year givenYear = Year.parse(stringDate, yearFormat);
                date = currentDate.with(givenYear);
                return date;
            case 2: // month and year is given
                DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("M/yy");
                YearMonth givenYearMonth = YearMonth.parse(stringDate, yearMonthFormat);
                date = currentDate.with(givenYearMonth);
                return date;
            case 3: // day, month and year given
                DateTimeFormatter dayMonthYearFormat = DateTimeFormatter.ofPattern("d/M/yy");
                date = LocalDate.parse(stringDate, dayMonthYearFormat);
                return date;
            default:
                throw new DateErrorException("Something is wrong with the date!");
            }
        } catch (DateTimeParseException e) {
            throw new DateErrorException("Something is wrong with the date!");
        }
    }

    private LocalTime getTime(String stringTime) throws TimeErrorException {
        LocalTime time;

        int givenHour;
        String[] stringTimeArray = stringTime.split(" ");
        givenHour = Integer.parseInt(stringTimeArray[0]);

        String upperCaseStringTime = stringTime.toUpperCase();

        if (upperCaseStringTime.contains("AM") || upperCaseStringTime.contains("PM")) { // 12 hour format
            try { // if hh a is given
                String amPmIndicator = stringTimeArray[1];
                if (givenHour >= 0 & givenHour <= 12) {
                    time = timeParser(givenHour + ":00 " + amPmIndicator); // default to minute 00
                    return time;
                } else {
                    throw new TimeErrorException("AM/PM format time requires hours between 1-12.");
                }
            } catch (NumberFormatException e) { // if hh:mm a or invalid hour is given
                time = timeParser(stringTime); // exception will be thrown if invalid hour is given
                return time;
            }
        } else { // 24 hour format
            try { // if HH is given
                if (givenHour >= 0 & givenHour <= 24) {
                    time = timeParser(givenHour + ":00"); // default to minute 00
                    return time;
                } else {
                    throw new TimeErrorException("24 hour format time requires hours between 0-23.");
                }
            } catch (NumberFormatException e) { // if HH:mm or invalid number is given
                time = timeParser(stringTime); // exception will be thrown if invalid hour is given
                return time;
            }
        }
    }
}
