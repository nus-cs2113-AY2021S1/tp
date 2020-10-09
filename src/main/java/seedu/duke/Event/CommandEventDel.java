package seedu.duke.Event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandEventDel extends Command {
    UserInput userInput;
    //EventClass event;

   // public CommandEventDel(EventClass ev) {
    //    event = ev;
   // }
    @Override
    public String execute() {
       // String input = userInput.getArg("");
        //int index = Integer.parseInt(input.trim());
       // event.delEvent(index);
        return null;
    }

    @Override
    public String help() {
        return null;
    }

    public int validate(UserInput ui) {
        if (userInput.getCategory().equals("event") && userInput.getCommand().equalsIgnoreCase("delEvent")) {
            userInput = ui;
            return ACCEPT;
        } else return NO_MATCH;
    }
}

