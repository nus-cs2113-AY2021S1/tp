package command;

import event.Event;
import eventlist.EventList;
import exception.NoDateBeforeException;
import exception.EmptyEventListException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDate;
import java.util.ArrayList;

/*
* Represents the command when the user wants to clear all events before a certain date
* */
public class ClearBeforeCommand extends Command {
    protected LocalDate clearDate;

    public ClearBeforeCommand(LocalDate date) {
        clearDate = date;
    }

    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws EmptyEventListException the exceptions when the user try to clear an empty list.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NoDateBeforeException, EmptyEventListException {
        ArrayList<Event> filteredEventList = events.filterDateBefore(clearDate);
        if (filteredEventList.size() == 0) {
            throw new NoDateBeforeException();
        } else {
            events.clearBefore(clearDate);
            ui.printClearBefore(clearDate);
        }

    }
}
