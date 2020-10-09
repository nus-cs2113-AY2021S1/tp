package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Fitr {
    private Storage storage;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private User user;

    public Fitr(String filePathOfFoodList, String filePathOfExerciseList){
        try {
            storage = new Storage(filePathOfFoodList, filePathOfExerciseList);
            try {
                foodList = new FoodList(storage.loadFoodList());
            } catch (FileNotFoundException e) {
                UI.printFoodListNotFoundError();
                foodList = new FoodList();
            }
            try {
                exerciseList = new ExerciseList(storage.loadExerciseList());
            } catch (FileNotFoundException e) {
                UI.printExerciseListNotFoundError();
                exerciseList = new ExerciseList();
            }
        }catch (IOException e){
            System.out.println("Theres no file");
        }

    }

    public void run() {
        boolean isExit = false;
        user = new User(foodList, exerciseList, storage);
        while(!isExit) {
            String userInput = UI.read();
            Command c = Parser.parse(userInput);
            c.execute(foodList, exerciseList, storage);
            isExit = c.isExit();
        }
        UI.printExitMessage();
    }

    public static void main(String[] args) {
        new Fitr("data/foodList.txt", "data/exerciseList.txt").run();
    }
}
