package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.calendar.CalendarList;
import seedu.duke.calendar.event.Event;

public class DeleteInfoCommand extends Command {

    public DeleteInfoCommand(String userInput) {
        super(userInput);
    }

    /**
     * Deletes an additional information from an event.
     * Saves the updated calendar list in the storage after the additional information is deleted.
     *
     * @param calendarList the calendar list containing the event.
     * @param storage      the storage to be saved to.
     * @throws DukeException if the add command input is invalid.
     */
    @Override
    public void execute(CalendarList calendarList, Storage storage) throws DukeException {
        String[] command;
        int eventNumber = 0;
        int infoNumber = 0;

        try {
            command = userInput.split("a", 2);
            eventNumber = Integer.parseInt(command[0].replace("/-", "").trim());
            infoNumber = Integer.parseInt(command[1].trim());
        } catch (Exception e) {
            throw new DukeException("invalid delete info");
        }

        int calendarNumber = CalendarList.convertEventNumberToCalendarNumber(eventNumber, calendarList);
        assert calendarNumber >= 0;

        Event event = (Event) calendarList.getItem(calendarNumber);
        if (infoNumber > event.getAdditionalInformationCount() || infoNumber <= 0) {
            throw new DukeException("invalid info action");
        }
        int indexInfo = infoNumber - 1; // to account for arraylist index starting from 0.

        Ui.printAdditionalInformation(event, indexInfo);
        event.deleteAdditionalInformation(indexInfo);
        storage.writeToFile(calendarList);

    }
}
