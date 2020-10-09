package seedu.duke;

public class InvalidCommand extends Command {

    public InvalidCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        UI.printInvalidCommandError();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
