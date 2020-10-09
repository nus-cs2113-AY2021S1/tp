package seedu.duke.Event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandEventAdd extends Command{
    UserInput userInput;
    //EventClass event;
    //public static ArrayList<String> tasks = new ArrayList<>();



    @Override
    public String execute() {
       // event.addEvent();
        return null;
    }


    public int validate(UserInput ui) {
        if (userInput.getCategory().equals("event") && userInput.getCommand().equalsIgnoreCase("addEvent")) {
            userInput = ui;
            return ACCEPT;
        } else return NO_MATCH;
    }

    @Override
    public String help() {
        return null;
    }
}
