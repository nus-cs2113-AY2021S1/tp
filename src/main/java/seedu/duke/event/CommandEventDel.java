package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.DukeNoMatchException;
import seedu.duke.backend.UserInput;

/**
 * @@author  Varsha3006
 */
public class CommandEventDel extends Command {
    private UserInput userInput;
    int index;

    @Override
    public String execute() throws DukeNoMatchException {
        boolean isInteger = true;
        String output = "";
        try {
            index = Integer.parseInt(userInput.getArg(""));
        } catch (NumberFormatException e) {
            isInteger = false;
        }

        if (isInteger) {
            output = output.concat(EventList.deleteEvent(index - 1));
        } else {
            if (userInput.getArg("").equals("all")) {
                output = output.concat(EventList.clearEvents());
            } else {
                throw new DukeNoMatchException();
            }
        }
        return output;
    }

    @Override
    public String help() {
        return "Syntax: event delEvent <Index>";
    }

    @Override
    public int validate(UserInput ui) {
        userInput = ui;
        if (ui.getCategory().equals("event") && (ui.getCommand().equalsIgnoreCase("delEvent")
                || ui.getCommand().equalsIgnoreCase("delete")
                || ui.getCommand().equalsIgnoreCase("del")
                || ui.getCommand().equalsIgnoreCase("d"))) {
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
}

