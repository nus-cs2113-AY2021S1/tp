package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.Storage;
import fitr.user.User;

import java.util.logging.Logger;

public class ExitCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class.getName());

    public ExitCommand(String command) {
        assert command != null;
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList) {
        LOGGER.fine("Exiting the application.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
