package seedu.duke.command;

import seedu.duke.model.DayMap;

import java.time.LocalDateTime;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.Ui.displayMessage;
import static seedu.duke.ui.Ui.displaySavedMessage;

//@@author 1-Karthigeyan-1
/**
 * Delete by index command.
 */
public class DeleteByIndexCommand extends Command {
    protected LocalDateTime date;
    protected int index;
    protected DayMap dayList;

    /**
     * Initializes delete by index command.
     *
     * @param index index of activity to be removed in list.
     */
    public DeleteByIndexCommand(int index) {
        this.date = LocalDateTime.now();
        this.index = index;
    }

    @Override
    public void execute() {
        logging.writeToLogInfo("Executing delete by index command.");
        if (index >= 0) {
            try {
                dayMap.deleteActivity(index);
                logging.writeToLogInfo("Activity deleted successfully.");
                displaySavedMessage();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                logging.writeToLogInfo("Invalid Index from DeleteByIndexCommand.execute");
                displayMessage("Invalid Index");
            }
        }
    }
}
