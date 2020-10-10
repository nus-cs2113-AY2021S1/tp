package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.DateStatusPair;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.event.Repeat;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    private static final String COMMANDTYPE_LIST = "list";
    private static final String COMMANDTYPE_ADD = "add";
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
    public void execute(UserData data, Ui ui, Storage storage) {
        switch (commandType) {
        case COMMANDTYPE_ADD:
            executeAdd(data, ui, storage);
            break;
        case COMMANDTYPE_LIST:
            executeList(data, ui);
            break;
        default:
            //do nothing
        }
    }

    public static Command parse(String input) {
        String[] words = input.split(" ");
        switch (words.length) {
        case 2:
            return new RepeatCommand(input, COMMANDTYPE_LIST);
        case 4:
            return new RepeatCommand(input, COMMANDTYPE_ADD);
        default:
            return null; //to change to throw exception invalid repeat command
        }
    }

    private void executeAdd(UserData data, Ui ui, Storage storage) {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]);
        Event eventToRepeat = eventList.getEventByIndex(index);
        LocalDate startDate = eventToRepeat.getDate();
        LocalTime startTime = eventToRepeat.getTime();
        int count = Integer.parseInt(words[3]);
        Repeat repeat = new Repeat(startDate, startTime, words[2], count);
        eventToRepeat.setRepeat(repeat);
        ui.printRepeatAdd(eventToRepeat);
    }

    private void executeList(UserData data, Ui ui) {
        String[] words = command.split(" ");
        EventList eventList = data.getEventList(words[0]);
        int index = Integer.parseInt(words[1]);
        Event repeatEvent = eventList.getEventByIndex(index);
        ui.printRepeatList(repeatEvent);
    }
}
