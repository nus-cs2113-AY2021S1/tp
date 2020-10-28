package seedu.duke.command;

import seedu.duke.CommandException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;


public class AddInfoCommand extends Command {

    public AddInfoCommand(String command) {
        super(command);
    }

    /**
     * Adds additional information to an event. Multiple additional information can be added to an event.
     * Saves the updated calendar list in the storage after the additional information is added.
     *
     * @param calendarList the calendar list containing the event.
     * @param storage      the storage to be saved to.
     * @throws CommandException if the additional info command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws CommandException {
        String[] command;
        int eventNumber = 0;
        int calendarNumber;
        String additionalInformation = null;

        try {
            command = userInput.split("-", 2);
            additionalInformation = command[1].trim();
            eventNumber = Integer.parseInt(command[0].replace("/a", "").trim());

        } catch (Exception e) {
            throw new CommandException("invalid add info");
        }
        calendarNumber = CalendarList.convertEventNumberToCalendarNumber(eventNumber, calendarList);

        assert calendarNumber >= 0;

        Event event = (Event) calendarList.getItem(calendarNumber);

        assert event != null;

        event.setAdditionalInformation(additionalInformation);
        Ui.printLastAdditionalInformation(event);
        storage.writeToFile(calendarList);
    }
}
