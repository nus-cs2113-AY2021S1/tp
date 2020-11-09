package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.exception.MissingDeadlineRepeatException;
import seedu.duke.exception.MissingRepeatListException;
import seedu.duke.exception.NumberOverflowException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;


public class RepeatCommand extends Command {
    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";
    public static final String MONTHLY = "MONTHLY";
    private static final String COMMANDTYPE_LIST = "list";
    private static final String COMMANDTYPE_ADD = "add";
    private static final String COMMANDTYPE_ERROR = "error";
    private String commandType;
    private static Logger logger = EventLogger.getEventLogger();
    private static final int MAXIMUM_SIZE = 1000;


    /**
     * Constructor for the repeat command.
     *
     * @param command String containing the user arguments to repeat an event
     * @param commandType Can be add, list or null. Tells repeat what to execute with the arguments
     */
    public RepeatCommand(String command, String commandType) {
        this.isExit = false;
        this.command = command;
        this.commandType = commandType;
    }

    /**
     * Executes the repeat command according to the user input stored in the command object.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      object of UI class which helps to print out instructions or responses on the terminal
     * @param storage object of Storage class that helps save information to a location on the computer
     * @throws DukeException if any errors occurs with repeating an event occurs
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        switch (commandType) {
        case COMMANDTYPE_ADD:
            executeAdd(data, ui, storage);
            break;
        case COMMANDTYPE_LIST:
            executeList(data, ui);
            break;
        case COMMANDTYPE_ERROR:
            executeNull(data, ui, storage);
            break;
        default:
            break;
        }


    }

    /**
     * Static parser for repeat command creation. Distinguish between adding repeated dates or listing current repeats.
     *
     * @param input String containing user inputs
     * @return RepeatCommand set to either add additional dates or set to list out current dates in event
     */
    public static Command parse(String input) throws DukeException {
        String[] words = input.split(";");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }
        logger.fine("Arguments for Repeat processed");
        switch (words.length) {
        case 2:
            logger.fine("List Repeat command initiated");
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_LIST);
        case 4:
            logger.fine("Add Repeat command initiated");
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            words[2] = words[2].toUpperCase();
            isValidNumber(words[3]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_ADD);
        default:


            String errorMessage = "Wrong number of arguments provided, "
                    + "Format for repeat is: repeat EVENT_TYPE; EVENT_INDEX; [UNIT]; [COUNT]";
            throw new WrongNumberOfArgumentsException(errorMessage);
        }

    }

    /**
     * Change the name of the list to ensure the first character is capitalised.
     *
     * @param name String containing name of list for the first character to be capitalised
     * @return String with first character capitalised
     */
    private static String formatListName(String name) {
        name = name.toLowerCase();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Checks if the string can be converted to an integer.
     *
     * @param number String containing the String form of an integer
     * @throws DukeException if the number given by the users is not written in numeric format
     */
    private static void isValidNumber(String number) throws DukeException {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            logger.warning("Number provided by user was not in numerical format");
            throw new WrongNumberFormatException("Numbers must be in numerical format");
        }
    }

    /**
     * List command. Used to show dates of repeated events.
     *
     * @param data location where all user event information is stored
     * @param ui   User Interface class for printing on screens
     * @throws DukeException if there are no list of repeated event to show or if there are errors in user inputs
     */
    private void executeList(UserData data, Ui ui) throws DukeException {
        logger.fine("Begin executeList in Repeat");
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event repeatEvent = eventList.getEventByIndex(index);
        if (repeatEvent.getRepeatEventList() == null) {
            logger.warning("Event has no repeated stuff to list out");
            throw new MissingRepeatListException();
        }
        ui.printRepeatList(repeatEvent);
    }

    /**
     * Add command. Used to add repeated dates to an event.
     *
     * @param data    location where all user event information is stored
     * @param ui      User Interface class for printing on screens
     * @param storage File storage location on computer
     * @throws DukeException if there is an error processing the user input for adding repeated events
     */
    private void executeAdd(UserData data, Ui ui, Storage storage) throws DukeException {
        logger.fine("Begin executeAdd in Repeat");
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event eventToRepeat = eventList.getEventByIndex(index);
        LocalDate startDate = eventToRepeat.getDate();
        if (startDate == null) {
            logger.warning("User provided event had no deadline");
            throw new MissingDeadlineRepeatException();
        }
        String repeatType = words[2];
        int count = Integer.parseInt(words[3]);
        repeat(eventToRepeat, startDate, repeatType, count);
        ui.printRepeatAdd(eventToRepeat);
        storage.saveFile(storage.getFileLocation(eventList.getName()), data, eventList.getName());
    }

    /**
     * Private function that helps to record repeated events in a list to be stored under the original event.
     *
     * @param eventToRepeat Event object that is to be repeated over a period of time
     * @param startDate Date of the original eventToRepeat object
     * @param repeatType String containing the time unit to advance. Can be daily, weekly or monthly
     * @param count Integer containing how many times to advance by repeatType
     * @throws DukeException if there are errors occur while repeating the event such as incorrect arguments given
     */
    private void repeat(Event eventToRepeat, LocalDate startDate, String repeatType, int count) throws DukeException {
        ArrayList<Event> repeatEventList = new ArrayList<>();
        if (count > MAXIMUM_SIZE) {
            throw new NumberOverflowException("Repeat amount is too large, please limit to only "
                    + Integer.toString(MAXIMUM_SIZE) + " repetitions.");
        }
        for (int i = 1; i <= count; i++) {
            LocalDate repeatDate;
            logger.fine("Repeat repetition number" + Integer.toString(i));
            switch (repeatType) {
            case MONTHLY:
                logger.fine("Event date incremented monthly");
                repeatDate = startDate.plusMonths(i);
                break;
            case WEEKLY:
                logger.fine("Event date incremented weekly");
                repeatDate = startDate.plusWeeks(i);
                break;
            case DAILY:
                logger.fine("Event date incremented daily");
                repeatDate = startDate.plusDays(i);
                break;
            default:
                throw new InvalidTimeUnitException(repeatType);
            }
            eventToRepeat.setRepeatType(repeatType);
            eventToRepeat.setRepeatEventList(null);
            Event repeatEvent;
            try {
                repeatEvent = eventToRepeat.clone();
                logger.fine("Event for " + Integer.toString(i) + " has been cloned");
            } catch (CloneNotSupportedException e) {
                logger.warning("Event cannot be cloned");
                throw new DukeException("Cant clone");
            }
            repeatEvent.setDate(repeatDate);
            logger.fine("Cloned event incremented date set");
            repeatEventList.add(repeatEvent);
            logger.fine("Cloned event added to repeat event list");
        }
        eventToRepeat.setRepeatEventList(repeatEventList);
        logger.fine("All cloned event list stored in original event");
    }

    /**
     * Function does nothing. Usually occurs if there are errors in the user input detected by parser.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      object of UI class which helps to print out instructions or responses on the terminal
     * @param storage object of Storage class that helps save information to a location on the computer
     */
    private void executeNull(UserData data, Ui ui, Storage storage) {
        //do nothing
        logger.warning("There was an error in the user input. Command does nothing");
    }
}
