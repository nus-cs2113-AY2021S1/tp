package seedu.duke.Finance;

import seedu.duke.backend.UserInput;

public class CommandFinance {
    public void commandList (FinanceLog fl, UserInput ui) {
        if (ui.getCommand().equals("summary")) {
            fl.summary();
        }
        else if (ui.getCommand().equals("addLog")) {
            String input=ui.getArg("");
            String[] contents=input.trim().split(" ");
            fl.addFin(contents[0],Integer.parseInt(contents[1]));
        }
        else if (ui.getCommand().equals("delLog")) {
            String input=ui.getArg("");
            int index=Integer.parseInt(input.trim());
            fl.delFin(index);
        }
    }
}
