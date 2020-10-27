package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Personal;
import seedu.duke.exception.DateErrorException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.exception.TimeErrorException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.parser.DateTimeParser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Command to set deadline for personal events.
 */
public class DeadlineCommand extends Command {
    private int index;
    private LocalDate date;
    private LocalTime time;

    /**
     * Constructor for setting deadline seedu.duke.
     *
     * @param command from user input
     */
    public DeadlineCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    /**
     * To change to deadline of personal event.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {

        try {
            parseUserCommand(command);
            EventList personalList = data.getEventList("Personal");
            Event updatedEvent = personalList.getEventByIndex(index - 1);
            if (updatedEvent != null) {
                //Personal event = (Personal) updatedEvent;
                if (time == null) {
                    updatedEvent.setDate(date);
                } else {
                    updatedEvent.setDate(date);
                    updatedEvent.setTime(time);
                }
                ui.printDeadlineChangedMessage(updatedEvent);
            }
            storage.saveFile(storage.getFileLocation("Personal"), data, "Personal");

        } catch (InvalidIndexException e) {
            throw new InvalidIndexException("Error, no such index is available!");
        }


    }

    /**
     * Parsing user command to put in the correct format for arguments and checking if its date or date and time.
     *
     * @param command user input arguments
     */
    private void parseUserCommand(String command) throws DukeException {
        command = command.trim();
        String[] commandSplit = command.split(";");
        if (commandSplit.length == 2) {
            try {
                index = parsingNumber(commandSplit[0].trim());
                date = DateTimeParser.dateParser(commandSplit[1].trim());
                assert date != null : "date is not detected after parsing";
            } catch (DateErrorException e) {
                throw new DateErrorException("Something is wrong with the date!");
            } catch (NumberFormatException e) {
                throw new WrongNumberFormatException("Index must be numerical format!");
            }

        } else if (commandSplit.length == 3) {

            try {
                index = parsingNumber(commandSplit[0].trim());
                date = DateTimeParser.dateParser(commandSplit[1].trim());
                assert date != null : "date is not detected after parsing";
                String timeString = commandSplit[2].trim();
                timeString = timeString.replace(":", "");
                time = DateTimeParser.timeParser(timeString);
                assert time != null : "time is not detected after parsing";
            } catch (DateErrorException e) {
                throw new DateErrorException("Something is wrong with the date!");
            } catch (TimeErrorException e) {
                throw new TimeErrorException("Something is wrong with the time!");
            } catch (NumberFormatException e) {
                throw new WrongNumberFormatException("Index must be numerical format!");
            }
        } else {
            throw new WrongNumberOfArgumentsException("Incorrect number of parameters for Deadline!");

        }

    }

    /**
     * Check if index is numerical format.
     *
     * @param number index in string format
     * @return index of event
     */
    private int parsingNumber(String number) {
        try {
            int index = Integer.parseInt(number);
            return index;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Index must be numerical format!");
        }

    }
}
