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
    public int validate(UserInput ui) {
        this.userInput = ui;
        if (ui.getCategory().equals("event") && (ui.getCommand().equalsIgnoreCase("countdown")
            || ui.getCommand().equalsIgnoreCase("c"))) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }


    @Override
    public String help() {
        return "Syntax: event countdown";
    }
}
