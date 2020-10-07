package seedu.duke;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        super();
    }

    @Override
    public boolean execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        UI.printInvalidCommandError();
        return false;
    }
}
