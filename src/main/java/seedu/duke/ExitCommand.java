package seedu.duke;

public class ExitCommand extends Command {
    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
