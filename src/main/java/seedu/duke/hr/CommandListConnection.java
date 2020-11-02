package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandListConnection extends Command {
    private UserInput userInput;

    @Override
    public String execute() {
        String output = MemberList.searchConnection();
        return output;
    }

    @Override
    public String help() {
        return "hr list connections\n";
    }

    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("list")
            || input.getCommand().equalsIgnoreCase("l"))
                && (input.getArg("").equalsIgnoreCase("connections")
                || input.getArg("").equalsIgnoreCase("c"))) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
