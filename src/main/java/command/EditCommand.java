package command;

import event.Event;
import eventlist.EventList;
import exception.EditIndexException;
import exception.NuScheduleException;
import storage.Storage;
import ui.UI;

/**
 * Represents the command call when the user adds a new event.
 */
public class EditCommand extends Command{
    private int index;
    private Event event;

    public EditCommand(Event event, int index) {
        this.event = event;
        this.index = index;
    }

    /**
     * Edits the corresponding event/task in the list.
     *
     * @param events   the list of events.
     * @param ui      do outputs.
     * @param storage store the data.
     */
    @Override
    public void execute(EventList events, UI ui, Storage storage) throws NuScheduleException {
        events.editEvent(this.event, this.index);
        ui.printEditEventMessage(event);
        ui.printNumEvent(events.getSize());
    }
}
