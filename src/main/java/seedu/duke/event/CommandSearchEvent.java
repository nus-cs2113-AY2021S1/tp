package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.backend.UserInput;

public class CommandSearchEvent extends Command {
    private UserInput userInput;
    @Override
    public String execute() {
        String input = userInput.getArg("f");
        String output = EventList.searchEvents(input);
        return output;
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public int validate(UserInput input) {
        this.userInput = input;
        if (input.getCategory().equals("event") && input.getCommand().equalsIgnoreCase("search")) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
