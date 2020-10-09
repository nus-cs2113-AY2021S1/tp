package seedu.duke;

public class AddCommand extends Command {
    @Override
    public void execute(FoodList foodlist,ExerciseList exerciseList, Storage storage) {

        String type = command.split(" ", 2)[0];
        command = command.split(" ", 2)[1];
        if (type.equals("food")) {
            try {
                String nameOfFood = command.split("/", 2)[0];
                command = command.split("/", 2)[1];
                if (command.split(" ").length == 1) {
                    Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                    foodlist.addFood(new Food(nameOfFood, amountOfCalories));
                } else if (command.split(" ").length == 2) {
                    Calorie amountOfCalories = new Calorie(Integer.parseInt(command.split(" ")[0]));
                    int amountOfFood = Integer.parseInt(command.split(" ", 2)[1]);
                    foodlist.addFood(new Food(nameOfFood, amountOfCalories, amountOfFood));
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Sorry calories have to be a number");
            }
        } else if (type.equals("exercise")) {
            try {
                String nameOfExercise = command.split("/", 2)[0];
                command = command.split("/", 2)[1];
                if (command.split(" ").length == 1) {
                    Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                    foodlist.addFood(new Food(nameOfExercise, amountOfCaloriesBurnt));
                } else if (command.split(" ").length == 2) {
                    Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                    int durationOfExercise = Integer.parseInt(command.split(" ", 2)[1]);
                    exerciseList.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt, durationOfExercise));
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Sorry calories have to be a number");
            }
        }
    }
}
