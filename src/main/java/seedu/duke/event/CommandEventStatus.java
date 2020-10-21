package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;


public class CommandEventStatus extends Command {
    private UserInput userInput;
    int index;

    @Override
    public String execute() {
        index = Integer.parseInt(userInput.getArg(""));
        String output = EventList.isCompleted(index - 1);
        return output;
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (ui.getCategory().equals("event") && ui.getCommand().equalsIgnoreCase("done")) {
            if (ui.getNumArgs() == 1) {
                if ((ui.getArg("") != null)) {
                    return ACCEPT;
                }
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String help() {
        return "Syntax: event done <EVENT_INDEX>";
    }


}
