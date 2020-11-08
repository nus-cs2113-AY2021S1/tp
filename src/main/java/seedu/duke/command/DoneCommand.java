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

public class DoneCommand extends Command {
    private final String listType;

    private static Logger logger = EventLogger.getEventLogger();

    /**
     * Constructor for setting event to done.
     *
     * @param command from user input
     */
    public DoneCommand(String listType, String command) {
        this.isExit = false;
        this.listType = listType;
        this.command = command;
    }

    /**
     * Parses the user input and returns the corresponding DoneCommand.
     *
     * @param input the processed user input with the command keyword removed.
     * @return the DoneCommand corresponding to the list type and event indicated in the input.
     * @throws MissingSemicolonException if the input does not contain any semicolons to separate input fields.
     * @throws WrongNumberOfArgumentsException if the event type or event index is missing.
     * @throws WrongNumberFormatException if the event index is not an integer.
     */
    public static Command parse(String input) throws MissingSemicolonException,
            WrongNumberOfArgumentsException, WrongNumberFormatException {
        logger.fine("Parsing DoneCommand input: \"" + input + "\"");

        if (!input.contains(";")) {
            logger.warning("MissingSemicolonException: User input fields was not separated with semicolon.");
            throw new MissingSemicolonException("Remember to separate input fields with a ';'." + System.lineSeparator()
                    + "The format for done is: \"done <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".");
        }

        String[] inputParameters = input.trim().split(";", 2);

        if (inputParameters[0].isBlank() || inputParameters[1].isBlank()) {
            logger.warning("WrongNumberOfArgumentsException: User did not provide event type or event index.");
            throw new WrongNumberOfArgumentsException("Event type or index is missing." + System.lineSeparator()
                    + "The format for done is: \"done <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".");
        }

        String listType = capitaliseFirstLetter(inputParameters[0].trim());
        String eventIdentifier = inputParameters[1].trim();

        try {
            String[] eventIdentifierArray = eventIdentifier.split(";",2);
            Integer.parseInt(eventIdentifierArray[0]);
        } catch (NumberFormatException e) {
            logger.warning("WrongNumberFormatException: Event index given is not an integer.");
            throw new WrongNumberFormatException("Event index given is not an integer." + System.lineSeparator()
                    + "The format for done is: \"done <EVENT_TYPE>; <EVENT_INDEX>; [<REPEAT_EVENT_DATE>]\".");
        }

        logger.fine("Successfully parsed input and created DoneCommand.");
        return new DoneCommand(listType, eventIdentifier);
    }

    /**
     * Looks for the event indicated and marks it as done.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws DukeException if en error occurs during the execution of a method called in the command.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        logger.fine("Start executing done command.");
        logger.info("listType: \"" + listType + "\", command: \"" + command + "\"");
        EventList eventList = data.getEventList(listType);
        String[] eventIdentifierArray = command.split(";",2);

        int eventIndex = Integer.parseInt(eventIdentifierArray[0]) - 1;
        Event doneEvent = eventList.getEventByIndex(eventIndex);

        if (eventIdentifierArray.length == 1 || doneEvent.getRepeatType() == null) {
            doneEvent.markAsDone();
            ui.printEventMarkedDoneMessage(doneEvent);
            storage.saveFile(storage.getFileLocation(listType), data, listType);
            logger.fine("Event marked as done: \"" + doneEvent + "\"");
        } else if (eventIdentifierArray.length == 2 && doneEvent.getRepeatType() != null) { // event is a repeat task
            LocalDate doneEventDate = dateParser(eventIdentifierArray[1].trim());
            boolean isDateFound;

            if (doneEventDate.isEqual(doneEvent.getDate())) {
                isDateFound = true;
                doneEvent.markAsDone();
                ui.printEventMarkedDoneMessage(doneEvent);
                logger.fine("Event marked as done: \"" + doneEvent + "\"");
            } else {
                ArrayList<Event> repeatEventList = doneEvent.getRepeatEventList();
                isDateFound = scanRepeatList(repeatEventList, doneEventDate, ui);
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
     * Scans the repeat event array list of a repeat event for an event matching the given date and marks it done.
     *
     * @param repeatEventList the array list containing all the repeated sub events under the main repeat event.
     * @param doneEventDate the date of the sub repeat event to be marked done.
     * @param ui containing the responses to print.
     * @return boolean stating if an event matching the date given was found and marked done
     */
    private boolean scanRepeatList(ArrayList<Event> repeatEventList, LocalDate doneEventDate, Ui ui) {
        logger.fine("Checking events in repeat event list.");
        boolean isDateFound = false;
        for (Event e: repeatEventList) {
            if (e.getDate().isEqual(doneEventDate)) {
                isDateFound = true;
                e.markAsDone();
                ui.printEventMarkedDoneMessage(e);
                logger.info("Repeat list event marked as done: \"" + e + "\"");
            }
        }
        logger.fine("Finished checking repeat event list.");
        return isDateFound;
    }
}
