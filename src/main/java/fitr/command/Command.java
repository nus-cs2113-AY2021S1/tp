package fitr.command;

import fitr.list.GoalList;
import fitr.storage.Storage;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.user.User;

public abstract class Command {
    protected String command;

    public abstract void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                                 User user, GoalList goalList);

    public abstract boolean isExit();
}
