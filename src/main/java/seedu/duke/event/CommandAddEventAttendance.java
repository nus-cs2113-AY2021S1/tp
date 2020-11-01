package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

/**
 * Represents add attendance command.
 */
public class CommandAddEventAttendance extends Command {
    private UserInput savedInput;

    @Override
    public String execute() {
        String output;
        output = EventList.addAttendance(savedInput.getArg("n"), savedInput.getArg("m"));
        return output;
    }

    @Override
    public int validate(UserInput input) {
        savedInput = input;
        if (input.getCategory().equals("event") && (input.getCommand().equalsIgnoreCase("addAttendance")
                || input.getCommand().equalsIgnoreCase("addAttend")
                || input.getCommand().equalsIgnoreCase("aa"))) {
            if (input.getNumArgs() >= 2) {
                if (input.getArg("n") == null || input.getArg("m") == null
                        || input.getArg("n").equals("") || input.getArg("m").equals("")) {
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
    public String help() {
        return "Syntax: event addAttendance /n <Event Name> /m <Participant Name>";
    }
}
