package fitr.command;

import fitr.list.GoalList;
import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;

public class HelpCommand extends Command {
    public HelpCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList) {
        Ui.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
