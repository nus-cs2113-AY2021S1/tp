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
        if (input==null) {
            logger.warning("The input format is wrong.\n");
            throw new DukeFinanceAddDescriptionLostException();
        }
        String[] contents = input.trim().split(" ");
        FinanceLog fl = new FinanceLog(contents[0],Double.parseDouble(contents[1]));
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
