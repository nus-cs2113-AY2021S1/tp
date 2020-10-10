package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;
public class CommandDelMember extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("delete")) {
            if (input.getNumArgs() == 1) {
                if ((input.getArg("")!= null)) {
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
        int index = Integer.parseInt(savedInput.getArg(""));
        String output = MemberList.deleteFromList(index-1);
        return output;
    }

    @Override
    public String help() {
        return "";
    }
}