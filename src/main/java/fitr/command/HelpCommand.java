package fitr.command;

import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;

public class HelpCommand extends Command {
    public HelpCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        Ui.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
