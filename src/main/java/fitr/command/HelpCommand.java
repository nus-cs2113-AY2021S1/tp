package fitr.command;

import fitr.Recommender;
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
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user,
                        Recommender recommender) {
        Ui.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
