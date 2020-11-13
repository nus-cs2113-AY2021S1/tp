package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

/**
 * Represents view member command.
 */
public class CommandViewMember extends Command {

    private UserInput savedInput;

    @Override
    public int validate(UserInput input) {
        this.savedInput = input;
        if (input.getCategory().equals("hr") && input.getArg("") == null
                && (input.getCommand().equalsIgnoreCase("listMember")
                || input.getCommand().equalsIgnoreCase("list")
                || input.getCommand().equalsIgnoreCase("l"))) {
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }

    @Override
    public String execute() {
        String output = MemberList.listMembers();
        return output;
    }

    @Override
    public String help() {

        return "Syntax: hr list\n";
    }
}
