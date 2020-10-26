package fitr.command;

import fitr.Calorie;
import fitr.Exercise;
import fitr.Recommender;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Commands.COMMAND_EXERCISE;

public class AddExerciseCommand extends Command {
    public AddExerciseCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        try {
            String nameOfExercise = command.split("/", 2)[0];
            if (nameOfExercise.isEmpty()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            nameOfExercise = nameOfExercise.trim();
            command = command.split("/", 2)[1];
            if (command.split(" ").length == 1) {
                Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                if (amountOfCaloriesBurnt.get() < -1) {
                    throw new NumberFormatException();
                }
                listManager.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt, getCurrentDate()));
                storageManager.writeExerciseList(listManager.getExerciseList());
                Ui.printCustomMessage("The following exercise has been added:\n"
                        + "Name of Exercise: " + nameOfExercise + "\n"
                        + "Burnt Cal: " + amountOfCaloriesBurnt.get());
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry, invalid calorie amount entered");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFormatError(COMMAND_EXERCISE);
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
