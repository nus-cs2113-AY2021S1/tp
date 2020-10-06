package seedu.duke;

public abstract class Command {
    protected String command;

    public abstract void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage);

}

