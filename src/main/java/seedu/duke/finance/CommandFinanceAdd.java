package seedu.duke.finance;

import seedu.duke.Command;
import seedu.duke.DukeFinanceAddDescriptionLostException;
import seedu.duke.DukeNotNumberException;
import seedu.duke.backend.UserInput;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents add finance log entry command.
 */
public class CommandFinanceAdd extends Command {
    private UserInput userinput;
    private Logger logger = Logger.getGlobal();

    @Override
    public String execute() throws DukeFinanceAddDescriptionLostException, DukeNotNumberException {
        logger.setLevel(Level.OFF);
        logger.info("Start adding finance log...\n");
        String input = userinput.getArg("");
        if (input == null) {
            logger.warning("The input format is wrong.\n");
            throw new DukeFinanceAddDescriptionLostException();
        }
        String[] contents = input.trim().split(" ");
        int length = contents.length;
        if (length <= 1) {
            return "Syntax: finance addLog ITEM ITEM_VALUE";
        }
        String item = "";
        for (int i = 0; i < length - 1; i++) {
            if (i == length - 2) {
                item = item + contents[i];
            } else {
                item = item + contents[i] + " ";
            }
        }
        try {
            for (int i = 0; i < contents[length - 1].length(); i++) {
                if (!Character.isDigit(contents[length - 1].charAt(i)) && contents[length - 1].charAt(i) != '.'
                    && contents[length - 1].charAt(i) != '-') {
                    logger.warning("The number in the end is missing...\n");
                    throw new DukeNotNumberException();
                }
            }
            double budget = Double.parseDouble(contents[length - 1]);
            if (budget <= 0) {
                return "Please input a valid number for budget amount.\n";
            }
            FinanceLog fl = new FinanceLog(item,budget);
            String output = FinanceList.addLog(fl);
            logger.info("End adding...\n");
            return output;
        } catch (NumberFormatException nfe) {
            logger.warning("The input format is wrong.\n");
            throw new DukeFinanceAddDescriptionLostException();
        }
    }

    @Override
    public String help() {
        return "Syntax: finance addLog ITEM_NAME ITEM_VALUE";
    }

    public int validate(UserInput ui) {
        if (ui.getCategory().equals("finance") && (ui.getCommand().equalsIgnoreCase("addlog")
                || ui.getCommand().equalsIgnoreCase("add")
                || ui.getCommand().equalsIgnoreCase("a"))) {
            userinput = ui;
            return ACCEPT;
        }
        return NO_MATCH;
    }
}
