package fitr.command;

import fitr.calorie.Calorie;
import fitr.exercise.Exercise;
import fitr.exercise.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Commands.COMMAND_EXERCISE;
import static fitr.common.Messages.ECHO_ADDED_EXERCISE;
import static fitr.common.Messages.ERROR_INVALID_CALORIE;
import static fitr.common.Messages.ERROR_IN_FILE;
import static fitr.common.Messages.EXERCISE_NAME_HEADER;
import static fitr.common.Messages.LINE_BREAK;

public class AddExerciseCommand extends Command {
    public AddExerciseCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String nameOfExercise = command.split("/", 2)[0].trim();
            if (nameOfExercise.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            command = command.split("/", 2)[1].trim();
            if (command.split(" ").length == 1) {
                Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                if (amountOfCaloriesBurnt.get() < 1) {
                    throw new NumberFormatException();
                }
                listManager.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt, getCurrentDate()));
                storageManager.writeExerciseList(listManager.getExerciseList());
                Ui.printCustomMessage(ECHO_ADDED_EXERCISE + LINE_BREAK
                        + EXERCISE_NAME_HEADER + nameOfExercise + LINE_BREAK
                        + "Burnt Cal: " + amountOfCaloriesBurnt.get());
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError(ERROR_INVALID_CALORIE);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_EXERCISE);
        } catch (IOException e) {
            Ui.printCustomError(ERROR_IN_FILE);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
