package seedu.duke;

abstract class Command {
    protected String command;

    public abstract void execute(FoodList foodlist,ExericseList exericseList, Storage storage);

}

