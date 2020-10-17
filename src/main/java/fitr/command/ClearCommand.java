package fitr.command;

import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;

/**
 * Clears the food or exercise lists.
 */
public class ClearCommand extends Command {
    private final String[] arguments;

    public ClearCommand(String userInput) {
        arguments = userInput.split(" ");
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        if (arguments.length == 1) {
            foodlist.clearList();
            exerciseList.clearList();
            System.out.println("Food and exercise lists are both cleared!");
        } else if (arguments.length == 2) {
            switch (arguments[1]) {
            case "food":
                foodlist.clearList();
                System.out.println("Food list is cleared!");
                break;
            case "exercise":
                exerciseList.clearList();
                System.out.println("Exercise list is cleared!");
                break;
            default:
                System.out.println("Invalid clear command entered!");
                break;
            }
        } else {
            System.out.println("Invalid clear command entered!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
