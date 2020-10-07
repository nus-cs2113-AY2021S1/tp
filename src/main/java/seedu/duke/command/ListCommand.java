package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.data.UserData;

/**
 * Command to list events.
 */
public class ListCommand extends Command {
    private String listEventType;
    private Boolean isInvalidEventType = false;
    /**
     * Constructor for listing events seedu.duke
     *
     * @param command from user input
     */
    public ListCommand(String command) {
        String tempEventType = command.toLowerCase();
        this.isExit = false;
        switch (tempEventType) {
        case "zoom":
            listEventType = "Zoom";
            break;
        case "personal":
            listEventType = "Personal";
            System.out.println("Set list event type as personal");
            break;
        case "timetable":
            listEventType = "Timetable";
            break;
        case "":
            listEventType = "ListAll";
            break;
        default:
            isInvalidEventType = true;
            break;
        }
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        if(!isInvalidEventType) {
            switch (listEventType) {
            case "Zoom":
            case "Personal":
            case "Timetable":
                ui.printSpecificList(listEventType, data.getEventList(listEventType));
                break;
            case "ListAll":
                ui.printList(data.getAllEventLists());
                break;
            default:
                break;
            }
        }
    }
}
