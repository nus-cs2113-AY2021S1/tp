package seedu.duke.Event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;


public class CommandEventAdd extends Command{
    private UserInput userInput;

    @Override
    public String execute() {
        Event m = new Event(userInput.getArg("n"), userInput.getArg("d"), userInput.getArg("t"));
        String output = EventList.addEvent(m);
        return output;
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (userInput.getCategory().equals("event") && userInput.getCommand().equalsIgnoreCase("addEvent")) {
            if (ui.getNumArgs() == 3) {
                if ((ui.getArg("n") != null) && (ui.getArg("d") != null) && (ui.getArg("t") != null)){
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
        return null;
    }
}
