package seedu.duke.command;

import seedu.duke.EventLogger;
import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.MissingSemicolonException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import static seedu.duke.parser.DateTimeParser.dateParser;

public class DeleteCommand extends Command {
    private final String listType;

    private static Logger logger = EventLogger.getEventLogger();

    public DeleteCommand(String listType, String command) {
        this.isExit = false;
        this.listType = listType;
        this.command = command;
    }

    /**
     * Parses the user input and returns the corresponding DeleteCommand.
     *
     * @param input the processed user input with the command keyword removed.
     * @return the DeleteCommand corresponding to the list type and event indicated in the input.
     * @throws MissingSemicolonException if the input does not contain any semicolons to separate input fields.
     * @throws WrongNumberOfArgumentsException if the event type or event index is missing.
     * @throws WrongNumberFormatException if the event index is not an integer.
     */
    public static Command parse(String input) throws MissingSemicolonException,
            WrongNumberOfArgumentsException, WrongNumberFormatException {
        logger.fine("Parsing DeleteCommand input: \"" + input + "\"");

        if (!input.contains(";")) {
            logger.warning("MissingSemicolonException: User input fields was not separated with semicolon.");
            throw new MissingSemicolonException("Remember to separate input fields with a ';'.");
        }

        String[] inputParameters = input.trim().split(";", 2);

        if (inputParameters[0].isBlank() || inputParameters[1].isBlank()) {
            logger.warning("WrongNumberOfArgumentsException: User did not provide event type or event index.");
            throw new WrongNumberOfArgumentsException("Event type or index is missing.");
        }

        String listType = capitaliseFirstLetter(inputParameters[0].trim());
        String eventIdentifier = inputParameters[1].trim();

        try {
            String[] eventIdentifierArray = eventIdentifier.split(";",2);
            Integer.parseInt(eventIdentifierArray[0]);
        } catch (NumberFormatException e) {
            logger.warning("WrongNumberFormatException: Event index given is not an integer.");
            throw new WrongNumberFormatException("Event index given is not an integer.");
        }

        logger.fine("Successfully parsed input and created DeleteCommand.");
        return new DeleteCommand(listType, eventIdentifier);
    }

    /**
     * Looks for the event indicated and deletes it.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws DukeException if en error occurs during the execution of a method called in the command.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        logger.info("Start executing delete command.");
        logger.info("listType: \"" + listType + "\", command: \"" + command + "\"");
        EventList eventList = data.getEventList(listType);
        String[] eventIdentifierArray = command.split(";",2);

        int eventIndex = Integer.parseInt(eventIdentifierArray[0]) - 1;
        Event deleteEvent = eventList.getEventByIndex(eventIndex);

        if (eventIdentifierArray.length == 1 || deleteEvent.getRepeatType() == null) {
            eventList.getEvents().remove(deleteEvent);
            ui.printEventDeletedMessage(deleteEvent);
            storage.saveFile(storage.getFileLocation(listType), data, listType);
            logger.fine("Event deleted: \"" + deleteEvent + "\"");
        } else if (eventIdentifierArray.length == 2 && deleteEvent.getRepeatType() != null) { // event is a repeat task
            LocalDate deleteEventDate = dateParser(eventIdentifierArray[1].trim());
            boolean isDateFound;

            if (deleteEventDate.isEqual(deleteEvent.getDate())) {
                isDateFound = true;
                eventList.getEvents().remove(deleteEvent);
                ui.printEventDeletedMessage(deleteEvent);
                logger.fine("Event deleted: \"" + deleteEvent + "\"");
            } else {
                ArrayList<Event> repeatEventList = deleteEvent.getRepeatEventList();
                isDateFound = scanRepeatList(repeatEventList, deleteEventDate, ui, deleteEvent);
            }

            if (!isDateFound) {
                throw new DateErrorException("This event does not occur on this date.");
            }

            storage.saveFile(storage.getFileLocation(listType), data, listType);
            logger.fine("Changes saved to external file.");
        }
    }

    /**
     * Capitalises the first letter of an input string.
     *
     * @param input the string to be capitalised.
     * @return the capitalised string.
     */
    private static String capitaliseFirstLetter(String input) {
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * Scans the repeat event array list of a repeat event for an event matching the given date and deletes it.
     *
     * @param repeatEventList the array list containing all the repeated sub events under the main repeat event.
     * @param deleteEventDate the date of the sub repeat event to be deleted.
     * @param ui containing the responses to print.
     * @param deleteEvent the main repeat event.
     * @return boolean stating if an event matching the date given was found and deleted
     */
    private boolean scanRepeatList(ArrayList<Event> repeatEventList, LocalDate deleteEventDate,
                                   Ui ui, Event deleteEvent) {
        logger.fine("Checking events in repeat event list.");
        boolean isDateFound = false;
        for (Event e: repeatEventList) {
            if (e.getDate().isEqual(deleteEventDate)) {
                isDateFound = true;
                repeatEventList.remove(e);
                ui.printEventDeletedMessage(e);
                logger.info("Repeat list event deleted: \"" + e + "\"");
                break;
            }
        }
        if (repeatEventList.size() == 0) {
            deleteEvent.setRepeatType(null);
            deleteEvent.setRepeatEventList(null);
            logger.info("Last sub repeat event was deleted, repeat status and repeat list set to null.");
        }
        logger.fine("Finished checking repeat event list.");
        return isDateFound;
    }
}
