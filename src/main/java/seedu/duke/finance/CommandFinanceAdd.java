package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.backend.UserInput;
import java.util.logging.Logger;

public class CommandFinanceAdd extends Command {
    private UserInput userinput;
    private Logger logger = Logger.getGlobal();

    @Override
    public String execute() throws DukeFinanceAddDescriptionLostException {
        logger.info("Start adding finance log...\n");
        String input = userinput.getArg("");
        if (input == null) {
            logger.warning("The input format is wrong.\n");
            throw new DukeFinanceAddDescriptionLostException();
        }
        String[] contents = input.trim().split(" ");
        int length = contents.length;
        String item = "";
        for (int i = 0;i < length - 1;i++) {
            if (i == length -2) {
                item = item + contents[i];
            } else {
                item = item + contents[i] + " ";
            }
        }
        FinanceLog fl = new FinanceLog(item,Double.parseDouble(contents[length - 1]));
        String output = FinanceList.addLog(fl);
        logger.info("End adding...\n");
        return output;
    }

    @Override
    public String help() {
        return "The format input to add a finance log is: hr addLog title value";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && ui.getCommand().equalsIgnoreCase("addlog")) {
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
