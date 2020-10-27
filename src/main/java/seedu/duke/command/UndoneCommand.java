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

public class UndoneCommand extends Command {
    private String listType;

    /**
     * Constructor for setting event to undone.
     *
     * @param command from user input
     */
    public UndoneCommand(String listType, String command) {
        this.isExit = false;
        this.listType = listType;
        this.command = command;
    }

    public static Command parse(String input) throws WrongNumberOfArgumentsException, WrongNumberFormatException {
        String[] inputParameters = input.trim().split(" ", 2);

        if (inputParameters.length < 2) {
            throw new WrongNumberOfArgumentsException("Event type or index not provided.");
        }

        String listType = inputParameters[0];
        String eventIndex = inputParameters[1].trim();

        try {
            String[] eventIndexArray = eventIndex.split(" ",2);
            Integer.parseInt(eventIndexArray[0]);
        } catch (NumberFormatException e) {
            throw new WrongNumberFormatException("Event index given is not an integer.");
        }

        return new UndoneCommand(listType, eventIndex);
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        listType = capitaliseFirstLetter(listType);
        EventList eventList = data.getEventList(listType);
        String[] eventIndexArray = command.split(" ",2);
        
        int eventIndex = Integer.parseInt(eventIndexArray[0]) - 1;
        Event undoneEvent = eventList.getEventByIndex(eventIndex);

        if (eventIndexArray.length == 1 || undoneEvent.getRepeatType() == null) {
            undoneEvent.markAsUndone();
            ui.printEventMarkedUndoneMessage(undoneEvent);
        } else if (eventIndexArray.length == 2 && undoneEvent.getRepeatType() != null) { // event is a repeat task
            LocalDate undoneEventDate = dateParser(eventIndexArray[1]);
            ArrayList<Event> repeatEventList = undoneEvent.getRepeatEventList();
            for (Event e: repeatEventList) {
                if (e.getDate().isEqual(undoneEventDate)) {
                    e.markAsUndone();
                    ui.printEventMarkedUndoneMessage(e);
                }
            }
        }
    }

    private String capitaliseFirstLetter(String input) {
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
