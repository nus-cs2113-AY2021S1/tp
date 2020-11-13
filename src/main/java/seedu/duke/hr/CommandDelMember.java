package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

import static seedu.duke.hr.MemberList.isNumber;

/**
 * Represents delete member command.
 */
public class CommandDelMember extends Command {

    private UserInput savedInput;
    private int index;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("delMember")
                || input.getCommand().equalsIgnoreCase("delete")
                || input.getCommand().equalsIgnoreCase("d"))) {
            if (input.getNumArgs() == 1) {
                if ((input.getArg("") != null) && isNumber(input.getArg(""))) {

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
        String output = MemberList.deleteFromList(index - 1);
        return output;
    }

    @Override
    public String help() {
        return "Syntax: hr delete MEMBER_INDEX (INTEGER)\n";
    }

}