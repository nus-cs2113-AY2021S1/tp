package seedu.duke.commands;

import seedu.duke.classes.WatchTime;
import seedu.duke.utility.TimeParser;

import java.util.ArrayList;

import static seedu.duke.utility.Ui.printBadInputException;

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
        try {
            int newTimeLimit = TimeParser.parseTime(inputs.get(1));
            if (newTimeLimit == -1) {
                throw new IllegalArgumentException();
            }
            WatchTime.watchLimitUpdate(newTimeLimit);
        } catch (NullPointerException e) {
            printBadInputException();
        } catch (IllegalArgumentException e) {
            return;
        }
    }


}
