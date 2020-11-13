package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;


public class CommandEventList extends Command {
    private UserInput userInput;

    @Override
    public String execute() {
        String output = EventList.printEventList();
        return output;
    }

    @Override
    public int validate(UserInput input) {
        this.userInput = input;
        if (input.getCategory().equals("event") && input.getArg("") == null
                && (input.getCommand().equalsIgnoreCase("listEvent")
            || input.getCommand().equalsIgnoreCase("list")
            || input.getCommand().equalsIgnoreCase("l"))) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String help() {
        return "Syntax: event listEvent";
    }
}
