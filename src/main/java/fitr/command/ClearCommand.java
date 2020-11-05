package fitr.command;

import fitr.exercise.Recommender;
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
            Ui.printCustomMessage("Food, exercise and goal lists are all cleared!");
        } else {
            switch (command.toLowerCase()) {
            case Commands.COMMAND_FOOD:
                LOGGER.fine("Clearing food list.");
                if (listManager.getFoodList().getSize() == 0) {
                    Ui.printCustomMessage("Food list is empty!");
                    return;
                }
                listManager.clearFoodList();
                Ui.printCustomMessage("Food list is cleared!");
                break;
            case Commands.COMMAND_EXERCISE:
                LOGGER.fine("Clearing exercise list.");
                if (listManager.getExerciseList().getSize() == 0) {
                    Ui.printCustomMessage("Exercise list is empty!");
                    return;
                }
                listManager.clearExerciseList();
                Ui.printCustomMessage("Exercise list is cleared!");
                break;
            case Commands.COMMAND_GOAL:
                LOGGER.fine("Clearing goal list.");
                if (listManager.getGoalList().getSize() == 0) {
                    Ui.printCustomMessage("Goal list is empty!");
                    return;
                }
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
            storage.writeGoalList(listManager.getGoalList(), listManager.getFoodList(),
                    listManager.getExerciseList(), user);
        } catch (IOException e) {
            Ui.printCustomError("The file cannot be written!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
