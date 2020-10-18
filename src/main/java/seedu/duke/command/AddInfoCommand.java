package seedu.duke.command;

import seedu.duke.DukeException;
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
     *
     * @param calendarList the calendar list containing the event.
     * @param storage      not required.
     * @throws DukeException if the additional info command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        String[] command;
        int eventNumber = 0;
        int calendarNumber;
        String additionalInformation = null;

        try {
            command = userInput.split("-", 2);
            additionalInformation = command[1];
            eventNumber = Integer.parseInt(command[0].replace("/a", "").trim());

        } catch (Exception e) {
            throw new DukeException("invalid add info");
        }
        calendarNumber = CalendarList.convertEventNumberToCalendarNumber(eventNumber, calendarList);
        Event event = (Event) calendarList.getItem(calendarNumber);
        event.setAdditionalInformation(additionalInformation);
        Ui.printLastAdditionalInformation(event);
    }
}
