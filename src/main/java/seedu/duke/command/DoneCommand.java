package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.WrongNumberFormatException;
import seedu.duke.exception.WrongNumberOfArgumentsException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

import static seedu.duke.parser.DateTimeParser.dateParser;

public class DoneCommand extends Command {
    private String listType;

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

    public static Command parse(String input) throws WrongNumberOfArgumentsException {
        String[] inputParameters = input.trim().split(" ", 3);
        String listType = inputParameters[0];
        String eventIndex = inputParameters[1].trim();

        if (inputParameters.length < 2) {
            throw new WrongNumberOfArgumentsException("Event index not provided.");
        }

        return new DoneCommand(listType, eventIndex);
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        listType = capitaliseFirstLetter(listType);
        EventList eventList = data.getEventList(listType);
        String[] eventIndexArray = command.split(" ",2);

        try {
            int eventIndex = Integer.parseInt(eventIndexArray[0]) - 1;
            Event doneEvent = eventList.getEventByIndex(eventIndex);

            if (eventIndexArray.length == 1 || doneEvent.getRepeatType() == null) {
                doneEvent.markAsDone();
                ui.printEventMarkedDoneMessage(doneEvent);
            } else if (eventIndexArray.length == 2 && doneEvent.getRepeatType() != null) { // event is a repeat task
                LocalDate doneEventDate = dateParser(eventIndexArray[1]);
                ArrayList<Event> repeatEventList = doneEvent.getRepeatEventList();
                for (Event e: repeatEventList) {
                    if (e.getDate().isEqual(doneEventDate)) {
                        e.markAsDone();
                        ui.printEventMarkedDoneMessage(e);
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new WrongNumberFormatException("Event index given is not an integer.");
        }
    }

    private String capitaliseFirstLetter(String input) {
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
