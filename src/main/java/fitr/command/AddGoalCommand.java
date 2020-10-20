package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.user.User;

public class AddGoalCommand extends Command {

    public AddGoalCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage, User user) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
