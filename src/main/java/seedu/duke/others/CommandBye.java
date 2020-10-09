package seedu.duke.others;

import seedu.duke.Command;
import seedu.duke.backend.Ui;
import seedu.duke.backend.UserInput;

public class CommandBye extends Command {
    @Override
    public String execute() {
        Ui.setShutdown(true);
        return "See you next time!";
    }

    @Override
    public int validate(UserInput userInput) {
        if (userInput.getCategory().equals("") && userInput.getCommand().equalsIgnoreCase("bye")) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String help() {
        return "";
    }
}
