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
        assert (command != null);
        this.isExit = false;
        this.command = command;
    }

    @Override
    public void execute(UserData data, Ui ui, Storage storage) {
        if (command.isBlank()) {
            Goal goal = data.getGoal();
            ui.printGoalMessage(goal);
        } else {
            Goal goal = null;
            switch (command.toLowerCase()) {
            case "na": case "nil": case "delete":
                data.setGoal(null);
                break;
            default:
                goal = new Goal(command);
                data.setGoal(goal);
            }
            ui.printChangeGoalMessage(goal);
            //update storage
        }
    }
}
