package seedu.duke.commands;

import seedu.duke.classes.WatchTime;

import java.util.ArrayList;

import static seedu.duke.utility.Ui.printBadInputException;
import static seedu.duke.utility.Ui.printUpdatedTimeLimit;

public class UpdateTimeLimitCommand extends Command {
    ArrayList<String> inputs;

    public UpdateTimeLimitCommand(String description, ArrayList<String> inputs) throws NullPointerException {
        super(description);
        this.inputs = inputs;
        if (inputs.size() != 2) {
            throw new NullPointerException();
        }
    }

    //INPUT : updatetimelimit <new time>
    public void processCommand() {
        Integer newTimeLimit = Integer.parseInt((inputs.get(1)));
        try {
            WatchTime.watchLimitUpdate(newTimeLimit);
            printUpdatedTimeLimit(newTimeLimit);
        } catch (NullPointerException e) {
            printBadInputException();
        }
    }


}
