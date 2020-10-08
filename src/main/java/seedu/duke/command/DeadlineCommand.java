package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Personal;
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
    public void execute(UserData data, Ui ui, Storage storage) {
        parseUserCommand(command, ui, data);
        EventList personalList = data.getEventList("Personal");
        Event updatedEvent = personalList.getEventByIndex(index - 1);
        if (updatedEvent != null) {
            Personal event = (Personal) updatedEvent;
            if (time == null) {
                event.setHasDate(true);
                event.setHasTime(false);
                updatedEvent.setDate(date);
            } else {
                event.setHasDate(true);
                event.setHasTime(true);
                updatedEvent.setDate(date);
                updatedEvent.setTime(time);
            }
            ui.printDeadlineChangedMessage(updatedEvent);
        }

    }

    /**
     * Parsing user command to put in the correct format for arguments and checking if its date or date and time.
     *
     * @param command user input arguments
     */
    private void parseUserCommand(String command, Ui ui, UserData data) {
        command = command.trim();
        String[] commandSplit = command.split(";");
        if (commandSplit.length == 2) {
            index = Integer.parseInt(commandSplit[0].trim());
            try {
                date = DateTimeParser.dateParser(commandSplit[1].trim());
            } catch (Exception e) {
                ui.printExceptionMessage(e.toString());
            }

        } else if (commandSplit.length == 3) {
            index = Integer.parseInt(commandSplit[0].trim());
            try {
                date = DateTimeParser.dateParser(commandSplit[1].trim());
                String timeString = commandSplit[2].trim();
                timeString = timeString.replace(":", "");
                time = DateTimeParser.timeParser(timeString);
            } catch (Exception e) {
                ui.printExceptionMessage(e.toString());
            }
        }

    }
}
