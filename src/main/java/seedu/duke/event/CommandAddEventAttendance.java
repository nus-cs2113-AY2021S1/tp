package seedu.duke.event;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.isNumber;

public class CommandAddEventAttendance extends Command {
    private UserInput savedInput;
    private int index;

    @Override
    public String execute() {
        String output = "";
        index = Integer.parseInt(savedInput.getArg(""));
        String memberName = savedInput.getArg("n");
        //output = EventList.addAttendance(index, memberName);
        return output;
    }

    @Override
    public int validate(UserInput userInput) {
        userInput = savedInput;
        if (userInput.getCategory().equals("event") && (userInput.getCommand().equalsIgnoreCase("addAttendance")
                || userInput.getCommand().equalsIgnoreCase("addAttend")
                || userInput.getCommand().equalsIgnoreCase("aa"))) {
            if (userInput.getNumArgs() >= 2) {
                if (userInput.getArg("") == null || userInput.getArg("n") == null || !isNumber(userInput.getArg(""))) {
                    return ARGUMENT_ERR;
                }
                if (userInput.getArg("").equals("") || userInput.getArg("n").equals("")) {
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
        return "Syntax: event addEvent /n <Name> /d <Date YYYY-MM-DD> /t <Time>";
    }
}
