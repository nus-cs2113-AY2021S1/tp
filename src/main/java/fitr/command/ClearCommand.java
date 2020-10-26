package fitr.command;

import fitr.Recommender;
import fitr.common.Commands;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
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
    public void execute(ListManager listManager, StorageManager storage, User user, Recommender recommender) {
        if (command.length() == 0) {
            LOGGER.fine("Clearing food, exercise and goal lists.");
            listManager.clearFoodList();
            listManager.clearExerciseList();
            listManager.clearGoalList();
            Ui.printCustomMessage("Food, exercise and goal lists are both cleared!");
        } else {
            switch (command) {
            case Commands.COMMAND_FOOD:
                LOGGER.fine("Clearing food list.");
                listManager.clearFoodList();
                Ui.printCustomMessage("Food list is cleared!");
                break;
            case Commands.COMMAND_EXERCISE:
                LOGGER.fine("Clearing exercise list.");
                listManager.clearExerciseList();
                Ui.printCustomMessage("Exercise list is cleared!");
                break;
            case Commands.COMMAND_GOAL:
                LOGGER.fine("Clearing goal list.");
                listManager.clearGoalList();
                Ui.printCustomMessage("Goal list is cleared!");
                break;
            default:
                Ui.printCustomMessage("Invalid clear command entered!");
                break;
            }
        }

        try {
            storage.writeExerciseList(listManager.getExerciseList());
            storage.writeFoodList(listManager.getFoodList());
        } catch (IOException e) {
            Ui.printCustomError("The file cannot be written!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
