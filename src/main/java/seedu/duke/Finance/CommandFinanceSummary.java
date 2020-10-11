package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceSummary extends Command {
    UserInput userinput;

    @Override
    public String execute() {
        String output = FinanceList.summary();
        return output;
    }

    @Override
    public String help() {
        return "The format of input to show finance list is: finance summary";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("summary")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
