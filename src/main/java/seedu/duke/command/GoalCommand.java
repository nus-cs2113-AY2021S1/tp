package seedu.duke.command;

import seedu.duke.data.UserData;
import seedu.duke.event.Goal;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Command to set goals.
 */
public class GoalCommand extends Command {
    /**
     * Constructor for setting goals seedu.duke
     *
     * @param command from user input
     */
    public GoalCommand(String command) {
        this.isExit = false;
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        Goal goal = data.getGoal();
        if (command == null) {
            ui.printGoalMessage(goal);
        } else {
            goal.changeGoal(command);
            ui.printChangeGoalMessage(goal);
            //update storage
        }
    }
}
