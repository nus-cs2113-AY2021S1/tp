package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidTimeUnitException;
import seedu.duke.exception.MissingDeadlineRepeatException;
import seedu.duke.exception.MissingRepeatListException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    public static final String DAILY = "DAILY";
    public static final String WEEKLY = "WEEKLY";
    public static final String MONTHLY = "MONTHLY";
    private static final String COMMANDTYPE_LIST = "list";
    private static final String COMMANDTYPE_ADD = "add";
    private static final String COMMANDTYPE_ERROR = "error";
    private String commandType;

    /**
     * Constructor for the repeat command.
     *
     * @param command user input with the format eventIndex; eventType; timeInterval; NumberofIterations
     */
    public RepeatCommand(String command, String commandType) {
        this.isExit = false;
        this.command = command;
        this.commandType = commandType;
    }

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
        String[] words = input.split(" ");
        switch (words.length) {
        case 2:
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_LIST);
        case 4:
            words[0] = formatListName(words[0]);
            isValidNumber(words[1]);
            words[2] = words[2].toUpperCase();
            isValidNumber(words[3]);
            input = String.join(" ", words);
            return new RepeatCommand(input, COMMANDTYPE_ADD);
        default:
            String errorMessage = "Wrong number of arguments provided";
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
     */
    private static void isValidNumber(String number) throws DukeException {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new WrongNumberFormatException("Numbers must be in numerical format");
        }
    }

    /**
     * List command. Used to show dates of repeated events.
     *
     * @param data location where all user event information is stored
     * @param ui   User Interface class for printing on screens
     */
    private void executeList(UserData data, Ui ui) throws DukeException {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event repeatEvent = eventList.getEventByIndex(index);
        if (repeatEvent.getRepeatEventList() == null) {
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
     */

    private void executeAdd(UserData data, Ui ui, Storage storage) throws DukeException {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]) - 1;
        Event eventToRepeat = eventList.getEventByIndex(index);
        LocalDate startDate = eventToRepeat.getDate();
        if (startDate == null) {
            throw new MissingDeadlineRepeatException();
        }
        String repeatType = words[2];
        int count = Integer.parseInt(words[3]);
        repeat(eventToRepeat, startDate, repeatType, count);
        ui.printRepeatAdd(eventToRepeat);
        storage.saveFile(storage.getFileLocation(eventList.getName()), data, eventList.getName());
    }

    private void repeat(Event eventToRepeat, LocalDate startDate, String repeatType, int count) throws DukeException {
        ArrayList<Event> repeatEventList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            LocalDate repeatDate;
            switch (repeatType) {
            case MONTHLY:
                repeatDate = startDate.plusMonths(i);
                break;
            case WEEKLY:
                repeatDate = startDate.plusWeeks(i);
                break;
            case DAILY:
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
            } catch (CloneNotSupportedException e) {
                throw new DukeException("Cant clone");
            }
            repeatEvent.setDate(repeatDate);
            repeatEventList.add(repeatEvent);
        }
        eventToRepeat.setRepeatEventList(repeatEventList);
    }

    private void executeNull(UserData data, Ui ui, Storage storage) {
        //do nothing
    }
}
