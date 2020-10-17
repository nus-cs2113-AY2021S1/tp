package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;

import java.util.logging.Logger;

public class ExitCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ExitCommand.class.getName());

    public ExitCommand(String command) {
        assert command != null;
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        LOGGER.fine("Exiting the application.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
