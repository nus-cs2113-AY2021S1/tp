package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.DukeNotNumberException;
import seedu.duke.backend.UserInput;

public class CommandListProfSpeaker extends Command {
    private UserInput userInput;

    @Override
    public String execute() throws DukeFinanceAddDescriptionLostException, DukeNotNumberException {
        String output = MemberList.searchProfSpeaker();
        return output;
    }

    @Override
    public String help() {
        return "Syntax: hr list prof&admin\n";
    }

    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("list")
            && input.getArg("").equals("prof&admin")) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
