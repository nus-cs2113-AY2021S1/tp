package fitr.command;

import fitr.list.GoalList;
import fitr.Recommender;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

public class HelpCommand extends Command {
    public HelpCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
        Ui.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
