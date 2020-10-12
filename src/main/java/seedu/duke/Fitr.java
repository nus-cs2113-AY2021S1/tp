package seedu.duke;

import java.io.IOException;

public class Fitr {
    private Storage storage;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private User user;

    public Fitr(String filePathOfUserConfig, String filePathOfFoodList, String filePathOfExerciseList) {
        try {
            user = new User();
            storage = new Storage(filePathOfUserConfig, filePathOfFoodList, filePathOfExerciseList);
            if (!storage.readUserConfigFile(user)) {
                user.setup();
                storage.writeUserConfigFile(user);
            }
            foodList = new FoodList(storage.loadFoodList());
            exerciseList = new ExerciseList(storage.loadExerciseList());
        } catch (IOException e) {
            System.out.println("Theres no file");
        }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = UI.read();
            Command c = Parser.parse(userInput);
            c.execute(foodList, exerciseList, storage);
            isExit = c.isExit();
        }
        UI.printExitMessage();
    }

    public static void main(String[] args) {
        new Fitr("userConfig.txt", "foodList.txt", "exerciseList.txt").run();
    }
}
