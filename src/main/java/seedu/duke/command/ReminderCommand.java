package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.InvalidIndexException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Command to print reminder for user.
 */
public class ReminderCommand extends Command {
    /**
     * Constructor for reminder seedu.duke.
     */
    public ReminderCommand() {
        this.isExit = false;
    }

    /**
     * To find the events for today.
     *
     * @param data    object of UserData class containing user's data.
     * @param ui      containing the responses to print.
     * @param storage with the save file path to write to.
     * @throws DukeException error caught by duke
     */
    @Override
    public void execute(UserData data, Ui ui, Storage storage) throws DukeException {
        ArrayList<EventList> allEventList = data.getAllEventLists();
        ArrayList<Event> reminderEvents = new ArrayList<>();
        for (int i = 0; i < allEventList.size(); i++) {
            ArrayList<Event> eventList = filterTodayEvents(allEventList.get(i));
            reminderEvents.addAll(eventList);
        }
        ArrayList<Event> reminderEventsWithoutTime = new ArrayList<>();
        ArrayList<Event> reminderEventsWithTime = new ArrayList<>();
        for (int i = 0; i < reminderEvents.size(); i++) {
            if (reminderEvents.get(i).getTime() == null) {
                reminderEventsWithoutTime.add(reminderEvents.get(i));
            } else {
                reminderEventsWithTime.add(reminderEvents.get(i));
            }
        }
        ui.printReminder(reminderEventsWithTime, reminderEventsWithoutTime);

    }

    /**
     * Filter the events.
     *
     * @param eventList list of events in the category
     * @return todayEvents events happening today
     * @throws InvalidIndexException index is invalid
     */
    private ArrayList<Event> filterTodayEvents(EventList eventList) throws InvalidIndexException {

        ArrayList<Event> todayEvents = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();
        for (int i = 0; i < eventList.getSize(); i++) {

            LocalDate eventDate = eventList.getEventByIndex(i).getDate();
            ArrayList<Event> repeatedList = eventList.getEventByIndex(i).getRepeatEventList();
            if (repeatedList != null) {
                if (checkingRepeatedEvent(repeatedList) != null) {
                    todayEvents.addAll(checkingRepeatedEvent(repeatedList));
                }
            }
            if (eventDate != null) {
                if (dateNow.compareTo(eventDate) == 0) {
                    todayEvents.add(eventList.getEventByIndex(i));
                }
            }

        }
        return todayEvents;
    }

    /**
     * Filter through repeated events.
     *
     * @param event list of repeated eventss
     * @return todayRepeatedEvent repeated events that happen today
     */
    private ArrayList<Event> checkingRepeatedEvent(ArrayList<Event> event) {
        LocalDate dateNow = LocalDate.now();
        ArrayList<Event> todayRepeatedEvent = new ArrayList<>();
        for (int i = 0; i < event.size(); i++) {
            LocalDate eventDate = event.get(i).getDate();
            if (eventDate != null) {
                if (dateNow.compareTo(eventDate) == 0) {
                    todayRepeatedEvent.add(event.get(i));
                }
            }
        }
        return todayRepeatedEvent;
    }

}
