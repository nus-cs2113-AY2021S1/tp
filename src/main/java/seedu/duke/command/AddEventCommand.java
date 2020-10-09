package seedu.duke.command;

import seedu.duke.data.timetable.Event;

public class AddEventCommand extends Command{

    public static final String COMMAND_WORD_EVENT = "add-e";
    private Event event;

    public AddEventCommand(Event e) {
        event = e;
    }


    @Override
    public String execute() {
        timetable.addEvent(event);
        return "Added the following!\n\n" + event.toString();
    }
}
