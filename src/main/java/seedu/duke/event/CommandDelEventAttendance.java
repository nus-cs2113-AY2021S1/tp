package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandDelEventAttendance extends Command {
    private UserInput savedInput;

    @Override
    public String execute() {
        String output;
        output = EventList.deleteAttendance(savedInput.getArg("n"), savedInput.getArg("m"));
        return output;
    }

    @Override
    public int validate(UserInput input) {
        savedInput = input;
        if (input.getCategory().equals("event") && (input.getCommand().equalsIgnoreCase("delAttendance")
                || input.getCommand().equalsIgnoreCase("delAttend")
                || input.getCommand().equalsIgnoreCase("da"))) {
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
        return "Syntax: event delAttendance /n <Event Name> /m <Participant Name>";
    }
}
