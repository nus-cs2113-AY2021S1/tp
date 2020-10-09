package seedu.duke.Finance;

import seedu.duke.Command;
import seedu.duke.backend.UserInput;

public class CommandFinanceAdd extends Command {
    FinanceLog finLog;
    UserInput userinput;
    public CommandFinanceAdd(FinanceLog fl) {
        finLog=fl;
    }
    @Override
    public String execute() {
        String input=userinput.getArg("");
        String[] contents=input.trim().split(" ");
        finLog.addFin(contents[0],Double.parseDouble(contents[1]));
        return null;
    }

    @Override
    public String help() {
        return null;
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance")&&ui.getCommand().equalsIgnoreCase("addlog")) {
            userinput=ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
