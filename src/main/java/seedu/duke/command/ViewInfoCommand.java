package seedu.duke.command;

import seedu.duke.CommandException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;

import java.util.ArrayList;

public class ViewInfoCommand extends Command {

    public ViewInfoCommand(String command) {
        super(command);
    }

    /**
     * Prints the list of additional information of a particular event.
     *
     * @param calendarList the calendar list containing the event.
     * @param storage      not required.
     * @throws CommandException if the view info command is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws CommandException {
        int eventNumber = 0;
        int calendarNumber;
        try {
            eventNumber = Integer.parseInt(userInput.replace("/v", "").trim());
        } catch (Exception e) {
            throw new CommandException("invalid view info");
        }
        calendarNumber = CalendarList.convertEventNumberToCalendarNumber(eventNumber, calendarList);

        assert calendarNumber >= 0;
        Event event = (Event) calendarList.getItem(calendarNumber);
        ArrayList<String> additionalInformation = event.getAdditionalInformation();
        Ui.printAllAdditionalInformation(additionalInformation, event);
    }
}
