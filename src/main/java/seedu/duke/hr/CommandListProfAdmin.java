package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandListProfAdmin extends Command {
    private UserInput userInput;

    @Override
    public String execute() {
        String output = MemberList.searchProfAdmin();
        return output;
    }

    @Override
    public String help() {
        return "Syntax: hr list prof&admin\n";
    }

    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && (input.getCommand().equalsIgnoreCase("list")
            || input.getCommand().equalsIgnoreCase("l"))
            && (input.getArg("").equals("prof&admin") || input.getArg("").equalsIgnoreCase("pa"))) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
