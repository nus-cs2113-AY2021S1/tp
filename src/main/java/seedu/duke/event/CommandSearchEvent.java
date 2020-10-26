package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandSearchEvent extends Command {
    private UserInput userInput;
    
    @Override
    public String execute()  {
        String input = userInput.getArg("s");
        String output = EventList.searchEvents(input);
        return output;
    }

    @Override
    public String help() {
        return "Syntax: event search /s <KEYWORD>";
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (ui.getCategory().equals("event") && (ui.getCommand().equalsIgnoreCase("search"))) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
