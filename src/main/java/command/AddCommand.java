package command;

import event.Event;
import eventlist.EventList;
import exception.NuScheduleException;
import storage.Storage;
import ui.UI;


/**
 * Represents the command call when the user adds a new event.
 */
public class AddCommand extends Command {
    private Event event;

    /**
     * Sets the task to be added to the list
     *
     * @param event The event to be added
     */
    public AddCommand(Event event){
        this.event = event;
    }

    /**
     * Adds the appropriate type of event/task to the list.
     *
     * @param events   the list of events.
     * @param ui      do outputs.
     * @param storage store the data.
     */
    @Override
    public void execute(EventList events, UI ui, Storage storage) throws NuScheduleException{
        events.addEvent(event);
        ui.printAddEventMessage(event);
        ui.printNumEvent(events.getSize());
        //storage.writeFile(tasks.getEventList());
    }
}
