package seedu.duke.hr;

import seedu.duke.Command;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.DukeNotNumberException;
import seedu.duke.backend.UserInput;

public class CommandListConnection extends Command {
    private UserInput userInput;

    @Override
    public String execute() throws DukeFinanceAddDescriptionLostException, DukeNotNumberException {
        String output = MemberList.searchConnection();
        return output;
    }

    @Override
    public String help() {
        return null;
    }

    public int validate(UserInput input) {
        if (input.getCategory().equals("hr") && input.getCommand().equalsIgnoreCase("list")
                && input.getArg("").equals("connections")) {
            userInput = input;
            return ACCEPT;
        } else {
            return NO_MATCH;
        }
    }
}
