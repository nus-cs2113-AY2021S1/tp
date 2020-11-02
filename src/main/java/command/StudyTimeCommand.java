package command;

import event.Event;
import eventlist.EventList;
import exception.NoEventDateException;
import exception.NoEventDoneException;
import exception.NuScheduleException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the Command for finding the study time spent on a certain day.
 */
public class StudyTimeCommand extends Command {
    protected LocalDate date;

    public StudyTimeCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Execute the command based on the specific command type.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws NoEventDoneException the users try to find the study time but he had not done any thing related to
     *                              academics on that day.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws NoEventDoneException {
        ArrayList<Event> filteredEventList = events.filterDateDoneEventWith(date);
        if (filteredEventList.size() == 0) {
            throw new NoEventDoneException();
        }
        ui.printStudyTime(filteredEventList,date);
    }
}
