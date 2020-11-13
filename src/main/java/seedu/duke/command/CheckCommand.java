package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.InvalidListException;
import seedu.duke.exception.InvalidTimePeriodException;
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
import java.util.logging.Logger;

import static seedu.duke.parser.DateTimeParser.timeParser;

/**
 * Command to check availability.
 */
public class CheckCommand extends Command {
    private static Logger logger = EventLogger.getEventLogger();

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
     * @throws MissingSemicolonException if the input does not contain any semicolons to separate input fields
     * @throws DateErrorException the date is not input in a valid format
     * @throws TimeErrorException the time is not input in a valid format
     * @throws InvalidTimePeriodException the start of the time period is after the end
     * @throws InvalidListException the event list indicated is not valid (should not be thrown in normal operation)
     * @throws WrongNumberOfArgumentsException if insufficient fields are given after the "check" keyword
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws MissingSemicolonException, DateErrorException,
            TimeErrorException, InvalidTimePeriodException, InvalidListException, WrongNumberOfArgumentsException  {
        logger.fine("Start executing check command: \"" + command + "\"");
        if (!command.contains(";")) {
            logger.warning("MissingSemicolonException: User input fields was not separated with semicolon.");
            throw new MissingSemicolonException("Remember to separate input fields with a ';'." + System.lineSeparator()
                    + "The format for check is: \"check [<START_DATE>]; [<START_TIME>]; [<END_DATE>]; [<END_TIME>]\".");
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

            boolean isTimePeriodValid = verifyValidTimePeriod(startDate, endDate, startTime, endTime);

            if (!isTimePeriodValid) {
                logger.warning("InvalidTimePeriodException: Start of time period given was not before end of period.");
                throw new InvalidTimePeriodException("The start of the time period should be earlier than the end.");
            }

            ArrayList<Event> eventsInTimeRange = new ArrayList<>();
            String[] eventTypes = new String[]{"Personal", "Timetable", "Zoom"};
            for (String type: eventTypes) {
                ArrayList<Event> events = data.getEventList(type).getEvents();
                eventsInTimeRange.addAll(checkEventsInTimeRange(events, startDate, endDate, startTime, endTime));
            }
            EventList coinciding = new EventList("coinciding", eventsInTimeRange);

            ui.printList(coinciding);
            logger.fine("Check command successfully executed.");
        } catch (ArrayIndexOutOfBoundsException e) { // if datesAndTime[x] is unable to be accessed
            logger.warning("WrongNumberOfArgumentsException: Not enough date/time fields were given to be processed.");
            throw new WrongNumberOfArgumentsException("Insufficient fields provided to check events. "
                    + "Remember to put a semicolon even for blank fields." + System.lineSeparator()
                    + "The format for check is: \"check [<START_DATE>]; [<START_TIME>]; [<END_DATE>]; [<END_TIME>]\".");
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
        logger.fine("Begin parsing date: \"" + stringDate + "\"");
        stringDate = stringDate.replace("-","/");
        String[] dateFields = stringDate.split("/");

        LocalDate date;
        LocalDate currentDate = LocalDate.now();

        if (stringDate.isBlank()) { // if date is blank, defaults to current date
            logger.fine("Date field left blank, current date returned.");
            return currentDate;
        }

        try {
            switch (dateFields.length) {
            case 1: // only year is given
                DateTimeFormatter yearFormat = DateTimeFormatter.ofPattern("[yyyy][yy]");
                Year givenYear = Year.parse(stringDate, yearFormat);
                date = currentDate.with(givenYear);
                logger.fine("Year provided, current d/m at provided year returned.");
                return date;
            case 2: // month and year is given
                DateTimeFormatter yearMonthFormat = DateTimeFormatter.ofPattern("M/[yyyy][yy]");
                YearMonth givenYearMonth = YearMonth.parse(stringDate, yearMonthFormat);
                date = currentDate.with(givenYearMonth);
                logger.fine("Month and year provided, current d at provided m/yyyy returned.");
                return date;
            case 3: // day, month and year given
                DateTimeFormatter dayMonthYearFormat = DateTimeFormatter.ofPattern("d/M/[yyyy][yy]");
                date = LocalDate.parse(stringDate, dayMonthYearFormat);
                logger.fine("Full date provided, provided date returned.");
                return date;
            default:
                logger.warning("DateErrorException: More fields than d/m/yyyy were given.");
                throw new DateErrorException();
            }
        } catch (DateTimeParseException e) {
            logger.warning("DateErrorException: Invalid date was given.");
            throw new DateErrorException();
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
        logger.fine("Begin parsing time: \"" + stringTime + "\"");
        LocalTime time;
        if (stringTime.isBlank()) { // if blank time is provided, default to current time
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:m a");
            String currentTime = LocalTime.now().format(timeFormatter);
            time = LocalTime.parse(currentTime, timeFormatter);
            logger.fine("Time field left blank, current time returned.");
            return time;
        }

        stringTime = stringTime.toUpperCase();

        String[] stringTimeArray = stringTime.split(" ");

        try {
            if (stringTimeArray.length == 2) { // 12 hour format hh a
                int givenTwelveHour = Integer.parseInt(stringTimeArray[0]);
                String amPmIndicator = stringTimeArray[1];
                if (givenTwelveHour >= 0 & givenTwelveHour <= 12) {
                    time = timeParser(givenTwelveHour + ":00 " + amPmIndicator); // default to minute 00
                    logger.fine("Hour given in 12 hour format, start of hour time returned.");
                    return time;
                } else {
                    logger.warning("Integer >12 provided for hour in 12 hour format.");
                    logger.warning("TryRegularParserException: Try to parse in hhmm am/pm format.");
                    throw new TryRegularParserException("12 hour format (h) requires hours between 1-12.");
                }
            } else if (stringTimeArray.length == 1) { // 24 hour format HH
                int givenTwentyFourHour = Integer.parseInt(stringTimeArray[0]);
                if (givenTwentyFourHour >= 0 & givenTwentyFourHour <= 24) {
                    time = timeParser(givenTwentyFourHour + ":00"); // default to minute 00
                    logger.fine("Hour given in 24 hour format, start of hour time returned.");
                    return time;
                } else {
                    logger.warning("Integer >24 provided for hour in 24 hour format.");
                    logger.warning("TryRegularParserException: Try to parse in HHmm format.");
                    throw new TryRegularParserException("HH format time requires hours between 0-24.");
                }
            } else {
                logger.warning("TimeErrorException: Time has more than hh:mm am/pm fields.");
                throw new TimeErrorException();
            }
        } catch (NumberFormatException | TryRegularParserException e) {
            // if hh:mm, HH:mm or other invalid non integers is given
            time = timeParser(stringTime); // exception will be thrown if invalid non-integer is given
            logger.fine("Time successfully returned.");
            return time;
        }
    }

    /**
     * Checks to make sure the start of the time period is not after the end.
     *
     * @param startDate the start date of the time period
     * @param endDate the end date of the time period
     * @param startTime the start time of the time period
     * @param endTime the end time of the time period
     * @return boolean showing if the time period is valid i.e. the start is before the end
     */
    private boolean verifyValidTimePeriod(LocalDate startDate, LocalDate endDate,
                                          LocalTime startTime, LocalTime endTime) {
        logger.fine("Start verifying time period validity.");
        logger.info("Start date: \"" + startDate + "\", Start time: \"" + startTime + "\"");
        logger.info("End date: \"" + endDate + "\", End time: \"" + endTime + "\"");
        boolean isStartAndEndValid;
        boolean isStartBeforeEnd;
        boolean isStartDateBeforeEndDate = startDate.isBefore(endDate);

        if (isStartDateBeforeEndDate) { // if start date is before end date start is before end
            isStartBeforeEnd = true;
        } else if (startDate.isEqual(endDate)) { // if start time before end time when start date equal end date
            isStartBeforeEnd = startTime.isBefore(endTime);
        } else {
            isStartBeforeEnd = false;
        }

        isStartAndEndValid = isStartBeforeEnd;
        logger.fine("Successfully checked time period validity.");
        return isStartAndEndValid;
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
        logger.fine("Start checking events in time period.");

        ArrayList<Event> eventsInTimeRange = new ArrayList<>();

        for (Event event : events) {
            if (event.getDate() == null) {
                continue;
            }

            boolean eventIsBetweenDate = event.getDate().isAfter(startDate) && event.getDate().isBefore(endDate);
            boolean eventIsWithinTimePeriod;

            if (eventIsBetweenDate) { // if an event is after start date and before end date, it is in the time period
                eventIsWithinTimePeriod = true;
            } else if (event.getDate().isEqual(startDate)) { // if an event is on the start date check event time
                if (event.getTime() == null) { // if the event has no time, by default count it as coinciding
                    eventIsWithinTimePeriod = true;
                } else { // if the event is before the start time, it is not within the time period
                    eventIsWithinTimePeriod = !(event.getTime().isBefore(startTime));
                }
            } else if (event.getDate().isEqual(endDate)) { // if an event is on the end date check event time
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
                logger.info("Coinciding event added: \"" + event + "\"");
            }

            if (event.getRepeatType() != null && event.getRepeatEventList() != null) {
                eventsInTimeRange.addAll(checkEventsInTimeRange(event.getRepeatEventList(),
                        startDate, endDate, startTime, endTime));
            }
        }

        logger.fine("Successfully checked events in time period.");
        return eventsInTimeRange;
    }
}
