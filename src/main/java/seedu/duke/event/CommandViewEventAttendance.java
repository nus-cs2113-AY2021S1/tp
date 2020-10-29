package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

/**
 * Represents view attendance command.
 */
public class CommandViewEventAttendance extends Command {
    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("event") && input.getArg("") == null
                && (input.getCommand().equalsIgnoreCase("listAttendance")
                || input.getCommand().equalsIgnoreCase("listAttend")
                || input.getCommand().equalsIgnoreCase("la"))) {
            if (input.getNumArgs() >= 1) {
                if (input.getArg("n") == null || input.getArg("n").equals("")) {
                    return ARGUMENT_ERR;
                }
                return ACCEPT;
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String execute() {
        String output = EventList.listAttendance(savedInput.getArg("n"));
        return output;
    }

    @Override
    public String help() {
        return "Syntax: event listAttendance /n <Event Name>";
    }
}
