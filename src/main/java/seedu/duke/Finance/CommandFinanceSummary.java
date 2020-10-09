package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceSummary extends Command {
    FinanceLog finlog;
    UserInput userinput;
    public CommandFinanceSummary(FinanceLog fl) {
        finlog=fl;
    }
    @Override
    public String execute() {
        finlog.summary();
        return null;
    }

    @Override
    public String help() {
        return null;
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("summary")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
