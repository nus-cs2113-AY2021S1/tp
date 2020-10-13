package fitr.command;

import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        Ui.printInvalidCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
