package fitr.command;

import fitr.Calorie;
import fitr.Exercise;
import fitr.Recommender;
import fitr.exception.FitrException;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;

import java.io.IOException;

public class AddExerciseCommand extends Command {
    public AddExerciseCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage,
                        User user, GoalList goalList, Recommender recommender) {
        command = command.split(" ", 2)[1];
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
                exerciseList.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt));
                storage.writeExerciseList(exerciseList);
                Ui.printCustomMessage("The following exercise has been added: " + nameOfExercise);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (NumberFormatException | NullPointerException e) {
            Ui.printCustomError("Sorry, invalid calorie amount entered");
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printCustomError("Please key in the correct format");
        } catch (IOException e) {
            Ui.printCustomError("Sorry, there is an error in the file");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
