package fitr.command;

import fitr.list.GoalList;
import fitr.Recommender;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.StorageManager;
import fitr.user.User;

public abstract class Command {
    protected String command;

    public abstract void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                                 User user, GoalList goalList, Recommender recommender);

    public abstract boolean isExit();
}
