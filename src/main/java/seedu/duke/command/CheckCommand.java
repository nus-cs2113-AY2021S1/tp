package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.MissingSemicolonException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.exception.TryRegularParserException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static seedu.duke.parser.DateTimeParser.timeParser;

/**
 * Command to check availability.
 */
public class CheckCommand extends Command {
    /**
     * Constructor for checking availability.
     *
     * @param command from user input
     */
    public CheckCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * Execute function for the command to state user's availability in a given period.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws MissingSemicolonException if the input does not contain any semicolons to separate input fields.
     * @throws DateErrorException the date is not input in a valid format
     * @throws TimeErrorException the time is not input in a valid format
     * @throws InvalidListException the event list indicated is not valid (should not be thrown in normal operation)
     * @throws WrongNumberOfArgumentsException if insufficient fields are given after the "check" keyword
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws MissingSemicolonException,
            DateErrorException, TimeErrorException, InvalidListException, WrongNumberOfArgumentsException {
        if (!command.contains(";")) {
            throw new MissingSemicolonException("Remember to separate input fields with a ';'.");
        }

        String[] datesAndTime = command.split(";");

        try {
            LocalDate startDate = getDate(datesAndTime[0].trim());
            LocalDate endDate = getDate(datesAndTime[2].trim());
            assert startDate != null : "null date read for startDate";
            assert endDate != null : "null date read for endDate";

            LocalTime startTime = getTime(datesAndTime[1].trim());
            LocalTime endTime = getTime(datesAndTime[3].trim());
            assert startTime != null : "null time read for startTime";
            assert endTime != null : "null time read for endTime";

            ArrayList<Event> eventsInTimeRange = new ArrayList<>();
            String[] eventTypes = new String[]{"Personal", "Timetable", "Zoom"};
            for (String type: eventTypes) {
                ArrayList<Event> events = data.getEventList(type).getEvents();
                eventsInTimeRange.addAll(checkEventsInTimeRange(events, startDate, endDate, startTime, endTime));
            }
            EventList coinciding = new EventList("coinciding", eventsInTimeRange);

            ui.printList(coinciding);
        } catch (ArrayIndexOutOfBoundsException e) { // if datesAndTime[x] is unable to be accessed
            throw new WrongNumberOfArgumentsException("Insufficient fields provided to check events. "
                    + "Remember to put a semicolon even for blank fields.");
        }
    }

    /**
     * Date parser that interprets a date from a given string.
     *
     * @param stringDate the string containing date information
     * @return a LocalDate corresponding to the date in stringDate or the current date if no date is found in the string
     * @throws DateErrorException if stringDate does not correspond to a valid date format
     */
    private LocalDate getDate(String stringDate) throws DateErrorException {
        stringDate = stringDate.replace("-","/");
        String[] dateFields = stringDate.split("/");

        LocalDate date;
        LocalDate currentDate = LocalDate.now();

        if (stringDate.isBlank()) { // if date is blank, defaults to current date
            return currentDate;
        }

        try {
            switch (dateFields.length) {
            case 1: // only year is given
                DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("[yyyy][yy]");
                Year givenYear = Year.parse(stringDate, yearFormat);
                date = currentDate.with(givenYear);
                return date;
            case 2: // month and year is given
                DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("M/[yyyy][yy]");
                YearMonth givenYearMonth = YearMonth.parse(stringDate, yearMonthFormat);
                date = currentDate.with(givenYearMonth);
                return date;
            case 3: // day, month and year given
                DateTimeFormatter dayMonthYearFormat = DateTimeFormatter.ofPattern("d/M/[yyyy][yy]");
                date = LocalDate.parse(stringDate, dayMonthYearFormat);
                return date;
            default:
                throw new DateErrorException("Too many fields given for the date!" + System.lineSeparator()
                        + "D/M/YYYY is the longest date format accepted.");
            }
        } catch (DateTimeParseException e) {
            throw new DateErrorException("Something is wrong with the date!" + System.lineSeparator()
                    + "The accepted formats are: d/m/yyyy, m/yyyy or yyyy. yyyy can be shortened to yy." + System.lineSeparator()
                    + "Dashes may be used in place of slashes.");
        }
    }

    /**
     * Time parser that interprets a time from a given string.
     *
     * @param stringTime the string containing time information
     * @return a LocalTime corresponding to the time in stringTime or the current time if no time is found in the string
     * @throws TimeErrorException if stringTime does not correspond to a valid time format
     */
    private LocalTime getTime(String stringTime) throws TimeErrorException {
        LocalTime time;
        if (stringTime.isBlank()) { // if blank time is provided, default to current time
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:m a");
            String currentTime = LocalTime.now().format(timeFormatter);
            time = LocalTime.parse(currentTime, timeFormatter);
            return time;
        }

        String[] stringTimeArray = stringTime.split(" ");

        try {
            if (stringTimeArray.length == 2) { // 12 hour format hh a
                int givenTwelveHour = Integer.parseInt(stringTimeArray[0]);
                String amPmIndicator = stringTimeArray[1];
                if (givenTwelveHour >= 0 & givenTwelveHour <= 12) {
                    time = timeParser(givenTwelveHour + ":00 " + amPmIndicator); // default to minute 00
                    return time;
                } else {
                    throw new TryRegularParserException("12 hour format (h) requires hours between 1-12.");
                }
            } else if (stringTimeArray.length == 1) { // 24 hour format HH
                int givenTwentyFourHour = Integer.parseInt(stringTimeArray[0]);
                if (givenTwentyFourHour >= 0 & givenTwentyFourHour <= 24) {
                    time = timeParser(givenTwentyFourHour + ":00"); // default to minute 00
                    return time;
                } else {
                    throw new TryRegularParserException("HH format time requires hours between 0-24.");
                }
            } else {
                throw new TimeErrorException("Something is wrong with the time!" + System.lineSeparator()
                        + "The accepted formats are:" + System.lineSeparator()
                        + "(12 hour) hh:mm am/pm, hhmm am/pm, hh am/pm or " + System.lineSeparator()
                        + "(24 hour) HH:mm, HHmm, HH.");
            }
        } catch (NumberFormatException | TryRegularParserException e) {
            // if hh:mm, HH:mm or other invalid non integers is given
            time = timeParser(stringTime); // exception will be thrown if invalid non-integer is given
            return time;
        }
    }

    /**
     * Checks for events within a given time period.
     *
     * @param events the eventsList containing events to be checked
     * @param startDate the start date of the time period to be checked
     * @param endDate the end date of the time period to be checked
     * @param startTime the start time of the time period to be checked
     * @param endTime the end time of the time period to be checked
     * @return an ArrayList of events found occurring during the time period
     */
    private ArrayList<Event> checkEventsInTimeRange(ArrayList<Event> events, LocalDate startDate, LocalDate endDate,
                                                    LocalTime startTime, LocalTime endTime) {
        ArrayList<Event> eventsInTimeRange = new ArrayList<>();

        for (Event event : events) {
            if (event.getDate() == null) {
                continue;
            }

            boolean eventIsBetweenDate = event.getDate().isAfter(startDate) && event.getDate().isBefore(endDate);
            boolean eventIsWithinTimePeriod;

            if (eventIsBetweenDate) { // if an event is after start date and before end date, it is definitely within the time period
                eventIsWithinTimePeriod = true;
            } else if (event.getDate().isEqual(startDate)) { // if an event is on the start date, check if its after the start time
                if (event.getTime() == null) { // if the event has no time, by default count it as coinciding
                    eventIsWithinTimePeriod = true;
                } else { // if the event is before the start time, it is not within the time period
                    eventIsWithinTimePeriod = !(event.getTime().isBefore(startTime));
                }
            } else if (event.getDate().isEqual(endDate)) { // if an event is on the end date, check if its before the end time
                if (event.getTime() == null) { // if the event has no time, by default count it as coinciding
                    eventIsWithinTimePeriod = true;
                } else { // if the event is after the end time, it is not within the time period
                    eventIsWithinTimePeriod = !(event.getTime().isAfter(endTime));
                }
            } else {
                eventIsWithinTimePeriod = false;
            }

            if (eventIsWithinTimePeriod) {
                eventsInTimeRange.add(event);
            }

            if (event.getRepeatType() != null && event.getRepeatEventList() != null) {
                eventsInTimeRange.addAll(checkEventsInTimeRange(event.getRepeatEventList(),
                        startDate, endDate, startTime, endTime));
            }
        }

        return eventsInTimeRange;
    }
}
