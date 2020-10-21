package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.changeMemberRole;

public class CommandChangeRole extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("changeRole")
                || input.getCommand().equalsIgnoreCase("cr"))) {
            if (input.getNumArgs() == 2) {
                if ((input.getArg("n") == null) || (input.getArg("r") == null)) {
                    return ARGUMENT_ERR;
                }
                if ((input.getArg("n").equals("")) || (input.getArg("r").equals(""))) {
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
        String output;
        Member m = MemberList.findMemberByName(savedInput.getArg("n"));
        if (m == null) {
            return "OOPS!!! The member does not exist. You can add this member into the list instead. \n";
        }
        output = changeMemberRole(m, savedInput.getArg("r"));
        return output;
    }

    @Override
    public String help() {

        return "You can use 'hr changeRole' command this way:\n"
                + "hr changeRole /n NAME /r ROLE\n";
    }
}
