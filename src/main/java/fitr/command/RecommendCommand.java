package fitr.command;

import fitr.Calorie;
import fitr.Recommender;
import fitr.StandardExercise;
import fitr.list.StandardExerciseList;
import fitr.exception.FitrException;
import fitr.list.ListManager;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.Exercise;

import java.io.IOException;

import static fitr.common.DateManager.getCurrentDate;
import static fitr.common.Messages.BURNT_CAL_HEADER;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.EXERCISE_HEADER;
import static fitr.common.Messages.INTENSITY_CAL_HEADER;
import static fitr.common.Messages.OPEN_SQUARE_BRACKET;
import static fitr.common.Messages.SPACE_FORMATTING;

public class RecommendCommand extends Command {
    @Override
    public void execute(ListManager listManager, StorageManager storageManager, User user, Recommender recommender) {
        StandardExerciseList recommendList = recommender.recommend();
        int fitnessLevel = user.getFitnessLevel();
        for (int i = 0; i < 4; i++) {
            StandardExercise standardExercise = recommendList.getExercise(i);
            int calorieBurnt = (int) (standardExercise.getDuration().get(fitnessLevel)
                    * standardExercise.getMet()
                    * standardExercise.getSets().get(fitnessLevel)
                    * user.getWeight())
                    / 60;
            Ui.printCustomMessage(OPEN_SQUARE_BRACKET + (i + 1) + CLOSE_SQUARE_BRACKET
                    + EXERCISE_HEADER + recommendList.getExercise(i).getName()
                    + SPACE_FORMATTING + INTENSITY_CAL_HEADER
                    + recommendList.getExercise(i).getSets().get(fitnessLevel) + " sets of "
                    + recommendList.getExercise(i).getDuration().get(fitnessLevel) + " minutes"
                    + SPACE_FORMATTING + BURNT_CAL_HEADER + calorieBurnt);
        }
      
        Ui.printCustomMessage("Will you be doing this workout?\n"
                + "type y for yes or n for no!");

        String checker = Ui.read();
        try {
            if (checker.toLowerCase().equals("y")) {
                Ui.printCustomMessage("The following Exercises has been added:");
                for (int i = 0; i < 4; i++) {
                    StandardExercise standardExercise = recommendList.getExercise(i);
                    Calorie caloriesBurnt = new Calorie((int) (standardExercise.getDuration().get(fitnessLevel)
                            * standardExercise.getMet()
                            * standardExercise.getSets().get(fitnessLevel)
                            * user.getWeight())
                            / 60);
                    Ui.printCustomMessage(OPEN_SQUARE_BRACKET + (i + 1) + CLOSE_SQUARE_BRACKET
                            + EXERCISE_HEADER + standardExercise.getName()
                            + SPACE_FORMATTING + BURNT_CAL_HEADER
                            + caloriesBurnt.get());
                    listManager.addExercise(new Exercise(standardExercise.getName(), caloriesBurnt, getCurrentDate()));
                    storageManager.writeExerciseList(listManager.getExerciseList());
                }
            } else if (checker.toLowerCase().equals("n")) {
                Ui.printCustomError("Okay! Next time then.");
            } else {
                Ui.printCustomMessage("Next time then!");
            }
        } catch (IOException e) {
            Ui.printCustomError("Sorry there is an error with the file");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
