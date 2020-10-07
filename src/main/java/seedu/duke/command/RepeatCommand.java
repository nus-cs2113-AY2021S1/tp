package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Event;
import seedu.duke.event.EventList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Command to repeat task.
 */
public class RepeatCommand extends Command {
    /**
     * Constructor for repeating events seedu.duke
     *
     * @param command from user input
     */

    private int eventIndex;
    private String eventType;
    private int timeInterval;
    private int repeatAmount;
    private boolean isValid;

    public RepeatCommand(String command) {
        this.isExit = false;
        this.command = command;
        parseUserCommand(command);
    }

    private void parseUserCommand(String command) {

        int argumentNumber = 4;
        //Split up into various components and remove trailing spaces
        String[] words = command.split(";");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        if (words.length != argumentNumber) {
            this.isValid = false;
            return;
        }

        eventIndex = Integer.parseInt(words[0]) - 1;
        eventType = words[1];
        repeatAmount = Integer.parseInt(words[3]);

        String intervalWord = words[2];

        switch (intervalWord) {
        case "weekly":
            timeInterval = 1;
        case "monthly":
            timeInterval = 2;
        default:
            timeInterval = 0;
        }

    }
    @Override
    public void execute(UserData data, Ui ui, Storage storage) {


        EventList information = data.getEventList(this.eventType);
        Event toChange = information.getEventByIndex(this.eventIndex);
        toChange.setRepeatUnit(this.timeInterval);
        toChange.setRepeatCount(this.repeatAmount);

        if (!isValid) {
            System.out.println("Error! invalid input given to repeat.");
            return;
        }
        System.out.println("All done, the program is set to repeat.");
    }
}
