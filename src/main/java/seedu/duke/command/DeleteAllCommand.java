package seedu.duke.command;

import java.time.LocalDateTime;

import static seedu.duke.Trakcal.logging;
import static seedu.duke.ui.Ui.displayMessage;

//@@author 1-Karthigeyan-1
/**
 * Delete All Command.
 */
public class DeleteAllCommand extends Command {
    protected LocalDateTime date;

    /**
     * Initializes delete all command.
     */
    public DeleteAllCommand() {
        this.date = LocalDateTime.now();
    }

    @Override
    public void execute() {
        logging.writeToLogInfo("Executing Delete All command.");
        dayMap.getActivityList(this.date).clearList();
        dayMap.getHashMap().remove(this.date.toLocalDate());
        displayMessage("All activities have been deleted");
    }
}
