package seedu.duke;

public abstract class Command {
    protected String command;

    public abstract boolean execute(FoodList foodlist, ExerciseList exerciseList, Storage storage);

}

