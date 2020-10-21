package fitr.command;


import fitr.Calorie;
import fitr.Recommender;
import fitr.StandardExercise;
import fitr.StandardExerciseList;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.Exercise;

public class RecommendCommand extends Command {
    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage, User user,
                        Recommender recommender) {
        StandardExerciseList recommendList = recommender.recommend();
        int fitnessLevel = user.getFitnessLevel();
        for (int i = 0;i < 4; i++) {
            Ui.printCustomMessage(recommendList.getExercise(i).getName());
        }
        Ui.printCustomMessage("Will you be doing this workout?/n type y for yes and n for no");
        String checker = Ui.read();
        if (checker.equals("y")) {
            for (int i = 0; i < 4; i++) {
                StandardExercise standardExercise = recommendList.getExercise(i);
                Calorie caloriesBurnt = new Calorie((int) (standardExercise.getDuration().get(fitnessLevel)
                        * standardExercise.getCaloricBurnRate()
                        * standardExercise.getSets().get(fitnessLevel)));
                exerciseList.addExercise(new Exercise(standardExercise.getName(),caloriesBurnt));
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
