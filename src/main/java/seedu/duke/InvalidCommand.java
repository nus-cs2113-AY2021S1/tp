package seedu.duke;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        super();
    }

    @Override
    public boolean exit() {
        return false;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        UI.printInvalidCommandError();
        return false;
    }
}
