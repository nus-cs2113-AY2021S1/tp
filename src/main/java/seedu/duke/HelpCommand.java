package seedu.duke;

public class HelpCommand extends Command {
    public HelpCommand(String userInput) {
        this.command = userInput;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        UI.printHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
