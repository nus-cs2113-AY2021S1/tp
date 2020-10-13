package fitr.command;

import fitr.storage.Storage;
import fitr.list.ExerciseList;
import fitr.list.FoodList;

public abstract class Command {
    protected String command;

    public abstract void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage);

    public abstract boolean isExit();
}
