package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;


public class CommandEventCountdown extends Command {
    private UserInput userInput;

    @Override
    public String execute()  {
       String userOutput = EventList.countdownView();
        return userOutput;
    }

    @Override
    public int validate(UserInput input) {
        this.userInput = input;
        if (input.getCategory().equals("event") && input.getCommand().equalsIgnoreCase("countdown")) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }


    @Override
    public String help() {
        return null;
    }
}
