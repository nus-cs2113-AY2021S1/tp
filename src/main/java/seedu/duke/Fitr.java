package seedu.duke;

public class Fitr {
    private Storage storage;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private User user;

    public Fitr (String filePathOfFoodList, String filePathOfExerciseList) {
        storage = new Storage(filePathOfFoodList, filePathOfExerciseList);
        user = new User();
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
    }

    public void run() {
        UI.printGreetingMessage();
        while(true) {
            try {
                String userInput = UI.read();
                Command c = Parser.parse(userInput);
                c.execute(foodList, exerciseList, storage);
            } catch (Fitrexception e) {  //Please replace this with any exception when executing the command.
                UI.showError(e.getMessage()); //Please replace this with the corresponding error message in UI.
            }
        }
        UI.printExitMessage();
    }

    public static void main(String[] args) {
        new Fitr("data/foodList.txt", "data/exerciseList.txt").run();
    }
}
