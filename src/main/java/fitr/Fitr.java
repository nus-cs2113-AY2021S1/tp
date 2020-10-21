package fitr;

import fitr.command.Command;
import fitr.tip.TipManager;
import fitr.list.ExerciseList;
import fitr.list.FoodList;
import fitr.list.GoalList;
import fitr.list.TipList;
import fitr.storage.Storage;
import fitr.ui.Ui;
import fitr.user.User;
import fitr.parser.Parser;

import java.io.IOException;

public class Fitr {
    private Storage storage;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private User user;
    private GoalList goalList;
    private Recommender recommender;

    public Fitr(String filePathOfUserConfig, String filePathOfFoodList,
                String filePathOfExerciseList, String filePathOfGoalList) {
        try {
            Ui.printGreetingMessage();
            storage = new Storage(filePathOfUserConfig, filePathOfFoodList,
                    filePathOfExerciseList, filePathOfGoalList);

            user = storage.loadUserProfile();
            storage.writeUserProfile(user);
            foodList = new FoodList(storage.loadFoodList());
            exerciseList = new ExerciseList(storage.loadExerciseList());
            goalList = new GoalList(storage.loadGoalList());

            TipList tipList = new TipList(storage.loadTipList());
            TipManager tipOfTheDay = new TipManager(tipList);
            recommender = new Recommender();
            tipOfTheDay.execute();

            Ui.printSuggestQuestion();
        } catch (IOException e) {
            System.out.println("An error has occurred - the file cannot be opened!");
        }
    }

    public void run() {
        /*Recommender recommender = new Recommender();
        recommender.printExercise();*/
        boolean isExit = false;
        while (!isExit) {
            String userInput = Ui.read();
            Command c = Parser.parse(userInput);
            c.execute(foodList, exerciseList, storage, user,goalList, recommender);
            isExit = c.isExit();
        }
        Ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Fitr("userConfig.txt", "foodList.txt", "exerciseList.txt", "goalList.txt").run();
    }
}
