package fitr.command;

import fitr.Calorie;
import fitr.Recommender;
import fitr.StandardExercise;
import fitr.StandardExerciseList;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.storage.StorageManager;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.Exercise;

import java.io.IOException;

import static fitr.common.Messages.BURNT_CAL_HEADER;
import static fitr.common.Messages.CLOSE_SQUARE_BRACKET;
import static fitr.common.Messages.EXERCISE_HEADER;
import static fitr.common.Messages.INTENSITY_CAL_HEADER;
import static fitr.common.Messages.OPEN_SQUARE_BRACKET;
import static fitr.common.Messages.SPACE_FORMATTING;

public class RecommendCommand extends Command {
    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, StorageManager storageManager,
                        User user, GoalList goalList, Recommender recommender) {
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
                + "type y for yes and n for no");
        String checker = Ui.read();
        if (checker.equals("y")) {
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
                exerciseList.addExercise(new Exercise(standardExercise.getName(), caloriesBurnt));
            }
            try {
                storageManager.writeExerciseList(exerciseList);
            } catch (IOException e) {
                Ui.printCustomError("Sorry, something is wrong with the storage file");
            }
        } else if (checker.equals("n")) {
            Ui.printCustomMessage("Next time then!");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
