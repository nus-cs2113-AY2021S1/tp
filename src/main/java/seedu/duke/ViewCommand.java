package seedu.duke;

public class ViewCommand extends Command {
    private User user;
    private FoodList foodList;
    private ExerciseList exerciseList;
    private Storage storage;
    public ViewCommand(String command){
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage) {
        user = new User(foodList, exerciseList, storage);
        this.foodList = foodList;
        this.exerciseList = exerciseList;
        this.storage = storage;
        if (command.equalsIgnoreCase("view food")) {
            viewFood();
        } else if (command.equalsIgnoreCase("view exercise")) {
            viewExercise();
        } else if (command.equalsIgnoreCase("view summary")) {
            viewSummary();
        }
    }

    //View food
    public void viewFood() {
        int index = 0;
        int printIndex = index + 1;
        //can remove
        System.out.println("Here is the list of food:");
        while (foodList.getFood(index) != null) {
            System.out.println("[" + printIndex + "] " + foodList.getFood(index));
            index++;
        }
    }

    //View exercise
    public void viewExercise() {
        int index = 0;
        int printIndex = index + 1;
        //can remove
        System.out.println("Here is the list of your exercises:");
        while (exerciseList.getExercise(index) != null) {
            System.out.println("[" + printIndex + "] " + exerciseList.getExercise(index));
            index++;
        }
    }

    //View summary of total amount of calories consumed and burnt.
    public void viewSummary() {
        System.out.println("Total calorie consumed:");
        System.out.println(user.calculateCalorieConsumed());
        System.out.println("Total calorie burnt:");
        System.out.println(user.calculateCalorieBurnt());
        System.out.println("Net calorie:");
        System.out.println(user.calculateCalorie());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
