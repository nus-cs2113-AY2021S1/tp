package fitr.command;

import fitr.common.Commands;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Clears the food or exercise lists.
 */
public class ClearCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(ClearCommand.class.getName());

    public ClearCommand(String arguments) {
        this.command = arguments;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage, User user) {
        if (command.length() == 0) {
            LOGGER.fine("Clearing food and exercise lists.");
            foodList.clearList();
            exerciseList.clearList();
            Ui.printCustomMessage("Food and exercise lists are both cleared!");
        } else {
            switch (command) {
            case Commands.COMMAND_FOOD:
                LOGGER.fine("Clearing food list.");
                foodList.clearList();
                Ui.printCustomMessage("Food list is cleared!");
                break;
            case Commands.COMMAND_EXERCISE:
                LOGGER.fine("Clearing exercise lists.");
                exerciseList.clearList();
                Ui.printCustomMessage("Exercise list is cleared!");
                break;
            default:
                Ui.printCustomMessage("Invalid clear command entered!");
                break;
            }
        }

        try {
            if (!storage.isTest()) {
                storage.writeExerciseList(exerciseList);
                storage.writeFoodList(foodList);
            }
        } catch (IOException e) {
            Ui.printCustomError("The file cannot be written!");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
