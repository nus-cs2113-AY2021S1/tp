package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceDel extends Command {
    FinanceLog finLog;
    UserInput userinput;
    public CommandFinanceDel(FinanceLog fl) {
        finLog=fl;
    }
    @Override
    public String execute() {
        String input=userinput.getArg("");
        int index=Integer.parseInt(input.trim());
        finLog.delFin(index);
        return null;
    }

    @Override
    public String help() {
        return null;
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("dellog")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
