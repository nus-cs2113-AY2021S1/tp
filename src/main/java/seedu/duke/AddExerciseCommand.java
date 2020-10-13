package seedu.duke;

public class AddExerciseCommand extends Command {
    public AddExerciseCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(FoodList foodlist, ExerciseList exerciseList, Storage storage) {
        command = command.split(" ", 2)[1];
        try {
            String nameOfExercise = command.split("/", 2)[0];
            nameOfExercise = nameOfExercise.trim();
            command = command.split("/", 2)[1];
            if (command.split(" ").length == 1) {
                Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                exerciseList.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt));
                UI.showAdd("The following execise has been added: " + nameOfExercise);
            } else if (command.split(" ").length == 2) {
                Calorie amountOfCaloriesBurnt = new Calorie(Integer.parseInt(command.split(" ")[0]));
                int durationOfExercise = Integer.parseInt(command.split(" ", 2)[1]);
                exerciseList.addExercise(new Exercise(nameOfExercise, amountOfCaloriesBurnt, durationOfExercise));
                UI.showAdd("The following execise has been added: " + nameOfExercise);
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Sorry calories have to be a number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Please key in the correct format");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
