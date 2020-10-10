package seedu.duke;

public class ViewCommand extends Command {
    private User user;

    public ViewCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodList, ExerciseList exerciseList, Storage storage) {
        user = new User();
        if (command.equalsIgnoreCase("view food")) {
            viewFood(foodList);
        } else if (command.equalsIgnoreCase("view exercise")) {
            viewExercise(exerciseList);
        } else if (command.equalsIgnoreCase("view summary")) {
            viewSummary(foodList, exerciseList, storage);
        } else {
            System.out.println("Invalid view command!");
        }
    }

    //View food
    public void viewFood(FoodList foodList) {
        int index = 0;
        int printIndex = index + 1;
        System.out.println("Here is the list of your food:");
        while (index < foodList.getSize()) {
            System.out.println("[" + printIndex + "] " + "Food: " + foodList.getFood(index).getFoodName()
                    + "\n    Cal: " + foodList.getFood(index).getCalories());
            index++;
            printIndex++;
        }
    }

    //View exercise
    public void viewExercise(ExerciseList exerciseList) {
        int index = 0;
        int printIndex = index + 1;
        System.out.println("Here is the list of your exercises:");
        while (index < exerciseList.getSize()) {
            System.out.println("[" + printIndex + "] " + "Exercise: "
                    + exerciseList.getExercise(index).getNameOfExercise()
                    + "\n    Burnt Cal: " + exerciseList.getExercise(index).getCalories());
            index++;
            printIndex++;
        }
    }

    //View summary of total amount of calories consumed and burnt.
    public void viewSummary(FoodList foodList, ExerciseList exerciseList, Storage storage) {
        System.out.println("Total calorie consumed:");
        System.out.println(user.calculateCalorieConsumed(foodList).get());
        System.out.println("Total calorie burnt:");
        System.out.println(user.calculateCalorieBurnt(exerciseList).get());
        System.out.println("Net calorie:");
        System.out.println(user.calculateCalorie(foodList, exerciseList).get());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
