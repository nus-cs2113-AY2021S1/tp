package seedu.duke;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        super();
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        UI.printInvalidCommandError();
    }
}
