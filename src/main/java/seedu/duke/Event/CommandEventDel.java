package seedu.duke.Event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandEventDel extends Command {
    private UserInput userInput;
    int index;

    @Override
    public String execute() {
        index = Integer.parseInt(userInput.getArg(""));
        String output = EventList.deleteEvent(index-1);
        return output;
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (ui.getCategory().equals("event") && ui.getCommand().equalsIgnoreCase("delEvent")) {
            if (ui.getNumArgs() == 1) {
                if ((ui.getArg("") != null)){
                    return ACCEPT;
                }
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }
}

