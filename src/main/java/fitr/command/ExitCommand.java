package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
