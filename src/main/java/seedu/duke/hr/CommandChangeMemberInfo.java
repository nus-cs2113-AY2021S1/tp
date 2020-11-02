package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.changeMemberInfo;

/**
 * Represents change member information command.
 */

public class CommandChangeMemberInfo extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("changeInfo")
                || input.getCommand().equalsIgnoreCase("c"))) {
            if (input.getNumArgs() >= 2) {
                if ((input.getArg("n") == null) || (input.getArg("n").equals(""))
                        || ((input.getArg("p") == null) && (input.getArg("e") == null)
                        && (input.getArg("r") == null))) {
                    return ARGUMENT_ERR;
                }
                if ((input.getArg("p") != null) && (input.getArg("p").equals(""))) {
                    return ARGUMENT_ERR;
                }
                if ((input.getArg("e") != null) && (input.getArg("e").equals(""))) {
                    return ARGUMENT_ERR;
                }
                if ((input.getArg("r") != null) && (input.getArg("r").equals(""))) {
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
        output = changeMemberInfo(m, savedInput.getArg("p"), savedInput.getArg("e"), savedInput.getArg("r"));

        return output;
    }

    @Override
    public String help() {

        return "You can use 'hr changeInfo' command this way:\n"
                + "hr changeInfo /n NAME (/p ITEM) (/e ITEM) (/r ITEM)\n"
                + "One or more information in the brackets need to be present.\n";
    }
}
