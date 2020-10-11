package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.isInteger;

/**
 * Represents delete member command.
 */
public class CommandDelMember extends Command {

    private UserInput savedInput;
    private int index;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("delete")) {
            if (input.getNumArgs() == 1) {
                if ((input.getArg("")!= null) && isInteger(input.getArg(""))) {

                    return ACCEPT;
                }
            }
            return ARGUMENT_ERR;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String execute() {
        index = Integer.parseInt(savedInput.getArg(""));
        String output = MemberList.deleteFromList(index-1);
        return output;
    }

    @Override
    public String help() {
        return "You can use 'hr delete' command this way:\n" + "hr delete MEMBER_INDEX (INTEGER)\n";
    }

}